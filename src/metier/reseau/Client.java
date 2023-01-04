package metier.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;


public class Client 
{
    // Socket 
    private String ip;
    private int port;
    private String nom;
    private Socket socket;
    private boolean connecte;
    
    private BufferedInputStream in;
    private BufferedOutputStream out;
    
    public Client(String ip)
    {
        this.ip = ip;
        this.port = 5000;
        this.nom = "nom_de_la_partie";
        this.connecte = false;
    }


    /*
     * Connexion au serveur
     * @return true si la connexion a réussi, false sinon
     */
    public boolean Connect()
    {
        try
        {
            this.socket = new Socket(this.ip, this.port);
            this.in = new BufferedInputStream(this.socket.getInputStream());
            this.out = new BufferedOutputStream(this.socket.getOutputStream());
            this.connecte = true;

            this.out.write(Packet.BONJOUR.ordinal());
            this.out.flush();

            if (this.in.read() != Packet.BONJOUR.ordinal())
            {
                System.out.println("Erreur lors de la connexion au serveur");
                this.connecte = false;
                return false;
            }

            int nom_taille = this.in.read();
            byte[] nom = new byte[nom_taille];
            this.in.read(nom, 0, nom_taille);
            this.nom = new String(nom);
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la connexion au serveur");
        }
        return this.connecte;
    }

}
