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
import java.io.ObjectOutputStream;
import java.net.Socket;

import metier.Metier;


public class ServerClientHandler implements Runnable
{

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Metier metier;
    private String nomJoueur;
    private Boolean authentifie;

    public void sendCommand(String cmd)
    {
        try
        {
            this.out.write(cmd.getBytes());
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
        try
        {
            String str = "";
            while (!str.endsWith(until))
            {
                str += (char) this.in.read();
            }
            return str;
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
        }

        return null;
    }

    public ServerClientHandler(Metier metier, Socket socket)
    {
        this.metier = metier;
        this.authentifie = false;
        try {
            this.in = new BufferedInputStream(socket.getInputStream());
            this.out = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialLoading()
    {
        this.metier.getServer().sendCommand("NOUVEAU_JOUEUR " + this.nomJoueur + "\n");
        this.metier.getServer().sendCommand("PARTIE nombre_joueurs " + this.metier.getServer().getNbJoeurs() + "\n");
        this.authentifie = true;
        //String xml = this.metier.getXml();
        //sendCommand("CHARGER_XML " + xml.length() + " " + xml + "\n");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(this.out);
            oos.writeObject(this.metier);
            oos.flush();
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
            
            if (!this.authentifie)
            {
                if (command.equals("BONJOUR "))
                {
                    this.nomJoueur = this.readUntil("\n");

                    sendCommand("PARTIE nom " + this.metier.getNomPartie() + "\n");


                    if (this.metier.getMotDePasse().isBlank())
                    {
                        initialLoading();
                    }
                }

                if (command.equals("MOT_DE_PASSE "))
                {
                    String motDePasse = this.readUntil("\n");
                    if (motDePasse.equals(this.metier.getMotDePasse()))
                    {
                        initialLoading();
                    }
                    else
                    {
                        sendCommand("ERREUR Mot de passe incorrect\n");
                    }
                }
                continue;
            }

            // Joueur authentifié

            
                    
            
        }
    }
}
