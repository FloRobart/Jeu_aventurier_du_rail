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


import metier.Metier;


public class ClientServerHandler implements Runnable
{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Metier metier;
    private String password;

    public void sendCommand(String cmd)
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
    private String readUntil(String until)
    {
        String ret = "";
        try
        {
            while (!ret.endsWith(until))
            {
                ret += this.in.readUTF();
                System.out.println("recu " + ret);

            }
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
        }
        return ret;
    }

    public ClientServerHandler(Metier metier, Socket socket, String password)
    {
        this.metier = metier;
        this.password = password;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.sendCommand("BONJOUR joe\n");
            this.sendCommand("MOT_DE_PASSE " + this.password + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        while (true)
        {
            String command = this.readUntil(" ");          
            if (command == null)
                break;
            
            System.out.println("[Client] " + command.substring(0, Math.min(command.length(), 10)));

            if (command.equals("ERREUR "))
            {
                String message = this.readUntil("\n");
                System.out.println("ERREUR SERVER : " + message);
            }

            if (command.equals("MESSAGE "))
            {
                String message = this.readUntil("\n");
                System.out.println("MESSAGE DU SERVER : " + message);
            }
            /*
            if (command.equals("CHARGER_XML "))
            {
                String tailleString = this.readUntil(" ");
                int taille = Integer.parseInt(tailleString);
                System.out.println("Taille du XML : " + taille + " octets");
                byte[] xml = new byte[taille];
                try {
                    this.in.read(xml, 0, taille);
                    this.metier.chargerXML(new StringReader(new String(xml)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            */

            if (command.equals("METIER "))
            {
                try {
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
                    }
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            

                    
            
        }
    }
}
