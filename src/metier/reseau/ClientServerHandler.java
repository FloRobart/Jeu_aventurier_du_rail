package metier.reseau;

/*
 * Fichier qui gère la communication du client vers le serveur
 * Voire ServerClientHandler pour la liste des commands
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.net.Socket;


import metier.Metier;


public class ClientServerHandler implements Runnable
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

    public ClientServerHandler(Metier metier, Socket socket)
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
                    ObjectInputStream ois = new ObjectInputStream(this.in);
                    try {

                        Metier nouveau_metier = (Metier) ois.readObject();
                        //TODO: Charger le nouveau metier

                        System.out.println("Class metier charger");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            

                    
            
        }
    }
}
