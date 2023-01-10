package metier.reseau;

import java.awt.Color;

/*
 * Fichier qui gère la communication de serveur vers le client
 * Commands réseau
 * Format :
 * La command est suivie d'un espace, puis des arguments séparés par des espaces, puis d'un espace suivit d'un retour à la ligne
 * [COMMAND] {[ARGS...] }\n
 * 
 * Liste des commands :
 * BONJOUR : Envoi un message de bienvenue au serveur, le server répond avec des packets OPTION
 * PARTIE [nom] [valeur] : Envoi d'un paramètre de la partie au client
 * ERREUR [message] : Envoi d'un message d'erreur au client
 * NOUVEAU_JOUEUR : Un joueur a rejoint la partie
 * MOT_DE_PASSE [mot_de_passe] : Envoi du mot de passe au serveur
 * 
 * CHARGER_XML [taille] [xml] : Envoi d'un fichier xml au client
 * METIER [class_metier] : Envoi d'un fichier class au client (serealisation de la classe Metier)
 */



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controleur.Controleur;
import metier.Metier;
import metier.partie.Partie;


public class ServerClientHandler implements Runnable
{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Controleur ctrl;
    private Metier metier;
    private String nomJoueur;
    private Boolean authentifie;

    public void majPartie(Partie partie)
    {
        try
        {
            this.out.writeUTF("MISE_A_JOUR_PARTIE");
            this.out.flush();
            this.out.writeObject(partie);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la partie");
        }
    }

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

    public ServerClientHandler(Controleur ctrl, Socket socket)
    {
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
        this.authentifie = false;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialLoading()
    {
        Server s = this.metier.getServer();
        s.writeonce("NOUVEAU_JOUEUR");
        s.writeonce(this.nomJoueur);

        s.writeonce("PARTIE");
        s.writeonce("nb_joueurs");
        s.writeonce("" + s.getNbJoeurs());
        

        this.authentifie = true;
        writeonce("METIER");
        try {
            out.writeObject(this.metier);
            out.flush();
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

            System.out.println("[Server] : " + command.substring(0, Math.min(command.length(), 10)));
            
            if (command.equals("PASS_TEST"))
            {
                String motDePasse = readonce();
                System.out.println("mot de passe : " + motDePasse + "");
                if (motDePasse.equals(this.metier.getMotDePasse()))
                {
                    writeonce("OK");
                }
                else
                {
                    writeonce("WRONG");
                }
                return;
            }

            if (!this.authentifie)
            {
                if (command.equals("BONJOUR"))
                {
                    this.nomJoueur = readonce();
                    System.out.println("nom joueur : " + this.nomJoueur + "");

                    writeonce("PARTIE");
                    writeonce("nom");
                    writeonce(this.metier.getNomPartie());

                }

                if (command.equals("MOT_DE_PASSE"))
                {
                    String motDePasse = readonce();
                    System.out.println("mot de passe : " + motDePasse + "");
                    if (motDePasse.equals(this.metier.getMotDePasse()))
                    {
                        initialLoading();
                        writeonce("MESSAGE");
                        writeonce("Bienvenue " + this.nomJoueur + " !");
                    }
                    else
                    {
                        writeonce("ERREUR");
                        writeonce("Mot de passe incorrect");
                    }
                }
                continue;
            }

            // Joueur authentifié

            if (command.equals("MISE_A_JOUR_PARTIE"))
            {
                try {
                    Partie partie = (Partie) in.readObject();
                    this.ctrl.setPartie(partie);
                    this.ctrl.majIHM();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

            
                    
            
        }
    }
}
