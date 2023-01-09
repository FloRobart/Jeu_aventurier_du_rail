package controleur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

import metier.Metier;
import metier.partie.Partie;

public class ClientControleur {
    private String ip;
    private int port=9999;
    private Socket socket;
    private Metier metier;
    private Partie partie;
    public ClientControleur(String ip) throws ConnectException 
    {
        try
        {
            Socket socket = new Socket(ip,port);
            System.out.println("Connected");
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            metier = (Metier) objectInputStream.readObject();
            partie = (Partie) objectInputStream.readObject();
        }
        catch (ClassNotFoundException e) {e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
    }
    
    public Metier getMetier (){return this.metier;}

    public Partie getPartie() {
        return this.partie;
    }
    
}
