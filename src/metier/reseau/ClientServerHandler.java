package metier.reseau;

/*
 * Fichier qui gère la communication du client vers le serveur
 * Voire ServerClientHandler pour la liste des commands
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.Socket;

import controleur.Controleur;
import metier.Joueur;
import metier.Metier;
import metier.partie.Partie;


public class ClientServerHandler implements Runnable
{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Controleur ctrl;
    private Boolean shouldStop;
    private Metier metier;
    private String password;
    private Socket socket;
    public void writeonce(String cmd)
    {
        try
        {
            this.out.writeObject(cmd);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la commande réseau");
            this.metier.getClient().Disconnect();
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
            ret =(String) this.in.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
            this.metier.getClient().Disconnect();
        }
        return ret;
    }

    public void Disconnect()
    {
        this.shouldStop = true;
    }

    public void changerCouleur(java.awt.Color c)
    {
        writeonce("COULEUR_JOUEUR");
        try {
            this.out.flush();
            this.out.writeObject(c);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void majPartie()
    {
        try {
            this.out.writeObject("MISE_A_JOUR_PARTIE");
            this.out.flush();
            this.out.reset();
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
        this.socket = socket;
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
        this.shouldStop = false;
        while (!this.shouldStop)
        {
            String command = readonce();
            if (command == null)
                break;
            
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

            if (command.equals("MISE_A_JOUR_PARTIE"))
            {
                try {
                    Partie nouvelle_partie = (Partie) this.in.readObject();

                    this.ctrl.setPartie(nouvelle_partie);
                
                    this.ctrl.majIHM();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            if (command.equals("METIER"))
            {
                    try {

                        Metier nouveau_metier = (Metier) in.readObject();
                        this.ctrl.setMetier(nouveau_metier);
        
                        this.ctrl.majIHM();
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                    
            }

            if (command.equals("LANCER_PARTIE"))
            {
                this.ctrl.setPartieLancer(true);
            }

            if (command.equals("CONNEXION_ACCEPTER"))
            {
                this.ctrl.connexionAccepter();
            }

            if (command.equals("NOUVEAU_JOUEUR"))
            {
                String nom = readonce();
                System.out.println("Nouveau joueur : " + nom);

                Boolean aAjotuer = true;
                for (Joueur j : this.metier.getJoueurs())
                    if (j.getNom().equals(nom))
                        aAjotuer = false;

                if (aAjotuer)
                    this.metier.ajouterJoueur(new Joueur(this.ctrl, nom));
            }
            
            if (command.equals("FINIR_TOUR"))
            {
                this.ctrl.majIHM();
            }

            if (command.equals("FIN_JEU"))
            {
                this.ctrl.ouvrirFinPartie(false);
            }
                    
            
        }
    }
}
