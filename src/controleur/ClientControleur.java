package controleur;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import metier.Metier;

public class ClientControleur {
    private String ip;
    private int port=9999;
    private Socket socket;
    private Metier metier;
    public ClientControleur(String ip) 
    {
        try
        {
            Socket socket = new Socket(ip,port);
            System.out.println("Connected");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            metier = (Metier) objectInputStream.readObject();
        }
        catch (Exception e) {e.printStackTrace();}
    }
    
    public Metier getMetier (){return this.metier;}
    
}
