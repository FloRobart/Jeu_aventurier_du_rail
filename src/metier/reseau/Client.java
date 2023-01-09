package metier.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

import controleur.Controleur;
import metier.Metier;


public class Client 
{
    // Socket 
    private String ip;
    private int port;
    private Socket socket;
    private boolean connecte;

    private Controleur ctrl;
    
    public Client(String ip, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.ip = ip;
        this.port = 5000;
        this.connecte = false;
    }


    /*
     * Connexion au serveur
     * @return true si la connexion a réussi, false sinon
     */
    public boolean Connect(String password)
    {
        try
        {
            this.socket = new Socket(this.ip, this.port);
            this.connecte = true;

            new Thread(new ClientServerHandler(this.ctrl, this.socket, password)).start();
            
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la connexion au serveur");
        }
        return this.connecte;
    }

}
