package metier.reseau;

import java.awt.Color;

/*
 * Commands réseau
 * Format :
 * La command est suivie d'un espace, puis des arguments séparés par des espaces, puis d'un espace suivit d'un retour à la ligne
 * [COMMAND] {[ARGS...] }\n
 * 
 * Liste des commands :
 * BONJOUR : Envoi un message de bienvenue au serveur, le server répond avec des packets OPTION
 * OPTION [nom] [valeur] : Envoi d'un paramètre au client
 * ERREUR [message] : Envoi d'un message d'erreur au client
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

    private void sendCommand(String cmd)
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
                sendCommand("OPTION nom " + this.metier.getNomPartie() + "\n");
                sendCommand("OPTION couleur_plateau" + this.metier.getCouleurPlateau().getRGB() + "\n");

                String couleurs = "";
                for (Color c : this.metier.getCouleurs())
                {
                    couleurs += c.getRGB() + ",";
                }
                sendCommand("OPTION couleurs " + couleurs + "\n");

                try {
                    sendCommand("OPTION image_plateau " + this.metier.imageToBase64(this.metier.getImagePlateau()) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    sendCommand("ERREUR Impossible d'envoyer l'image du plateau");
                }

                try {
                    sendCommand("OPTION image_recto_locomotive " + this.metier.imageToBase64(this.metier.getImageRectoLocomotive()) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    sendCommand("ERREUR Impossible d'envoyer l'image du recto de la locomotive");
                }

                try {
                    sendCommand("OPTION image_verso_locomotive " + this.metier.imageToBase64(this.metier.getImageVersoCouleur()) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    sendCommand("ERREUR Impossible d'envoyer l'image du verso de la locomotive");
                }

                try {
                    sendCommand("OPTION image_verso_objectif " + this.metier.imageToBase64(this.metier.getImageVersoObjectif()) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    sendCommand("ERREUR Impossible d'envoyer l'image du verso de l'objectif");
                }
            }
                    
            
        }
    }
}
