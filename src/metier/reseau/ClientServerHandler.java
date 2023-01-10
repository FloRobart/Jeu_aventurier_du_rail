package metier.reseau;

/*
 * Fichier qui gère la communication du client vers le serveur
 * Voire ServerClientHandler pour la liste des commands
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.Socket;

import controleur.Controleur;
import metier.Metier;
import metier.partie.Partie;


public class ClientServerHandler implements Runnable
{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Controleur ctrl;
    private Metier metier;
    private String password;

    public void writeonce(String cmd)
    {
        try
        {
            this.out.writeUTF(cmd);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la commande réseau");
        }
    }

    /*
     * Lit le flux réseau jusqu'à ce que la chaîne until soit lue
     * @param until Chaîne à lire
     * @return Chaîne lue
     */
    private String readonce()
    {
        // read until string "until" is read, blocking
        String ret = "";
        try
        {
            ret = this.in.readUTF();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
        }
        return ret;
    }

    public void majPartie()
    {
        try {
            this.out.writeUTF("MISE_A_JOUR_PARTIE");
            this.out.flush();
            this.out.writeObject(this.ctrl.getPartie());
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientServerHandler(Controleur ctrl, Socket socket, String password)
    {
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
        this.password = password;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            writeonce("BONJOUR");
            writeonce(this.metier.getNomClient());
            writeonce("MOT_DE_PASSE");
            writeonce(this.password);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        while (true)
        {
            String command = readonce();
            if (command == null)
                break;
            
            System.out.println("[Client] " + command.substring(0, Math.min(command.length(), 10)));

            if (command.equals("ERREUR"))
            {
                String message = readonce();
                System.out.println("ERREUR SERVER : " + message);
                javax.swing.JOptionPane.showMessageDialog(null, message, "Message du serveur", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

            if (command.equals("MESSAGE"))
            {
                String message = readonce();
                System.out.println("MESSAGE SERVER : " + message);
                javax.swing.JOptionPane.showMessageDialog(null, message, "Message du serveur", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }

            if (command.equals("DEMARRER_PARTIE"))
            {
                
            }

            if (command.equals("MISE_A_JOUR_PARTIE"))
            {
                try {
                    Partie nouvelle_partie = (Partie) this.in.readObject();

                    this.ctrl.setPartie(nouvelle_partie);
                    this.ctrl.majIHM();

                    System.out.println("Nouvelle partie");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            if (command.equals("METIER"))
            {
                try {
                    Metier nouveau_metier = (Metier) this.in.readObject();
                    for (java.lang.reflect.Field f : this.metier.getClass().getDeclaredFields()) {
                        if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) continue;
                        f.setAccessible(true);
                        f.set(this.metier, f.get(nouveau_metier));
                    }
                    System.out.println("Class metier charger");
                } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            

                    
            
        }
    }
}
