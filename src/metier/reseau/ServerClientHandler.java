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
 * COMMENCER_PARTIE : La partie commence
 * 
 * Possibilité :
 * CHARGER_XML [taille] [xml] : Envoi d'un fichier xml au client
 */



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


import controleur.Controleur;
import metier.Metier;


public class ServerClientHandler implements Runnable
{

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Metier metier;

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
        try {
            this.in = new BufferedInputStream(socket.getInputStream());
            this.out = new BufferedOutputStream(socket.getOutputStream());
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
            
            if (command.equals("BONJOUR"))
            {
                String joueur_nom = this.readUntil("\n");

                this.metier.getServer().sendCommand("NOUVEAU_JOUEUR " + joueur_nom + "\n");
                this.metier.getServer().sendCommand("PARTIE nombre_joueurs " + this.metier.getServer().getNbJoeurs() + "\n");
                
                sendCommand("PARTIE nom " + this.metier.getNomPartie() + "\n");
                String xml = this.metier.getXml();
                sendCommand("CHARGER_XML " + xml.length() + " " + xml + "\n");
                
            }
                    
            
        }
    }
}
