package controleur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import metier.Metier;
import metier.partie.Partie;

public class ClientControleur {
    private String ip;
    private int port=9999;
    private Socket socket;
    private Metier metier;
    private Partie partie;
    public ClientControleur(String ip) throws ConnectException,UnknownHostException, IOException 
    {
        this.socket = new Socket(ip,port);
        Thread thread = new Thread(new ServerHandler());
        thread.start();
        System.out.println("Connected");
    }
    public void updateMap()
    {
        this.sendMetierAndPartie();
    }
    private void sendMetierAndPartie() 
    {
        try 
        {
            OutputStream outputStream = socket.getOutputStream();
            InputStream  inputStream  = socket.getInputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(metier);
            objectOutputStream.writeObject(partie);
            objectOutputStream.flush();

        }
        catch (IOException e){e.printStackTrace();}
    }
    public Metier getMetier (){return this.metier;}

    public Partie getPartie() {return this.partie;}

    /**
     * Class which will wait indefintely for the update map
     */
    class ServerHandler implements Runnable
    {
        public void run()
        {
            while (true)
            {
                receiveMetierAndPartie();
            }
        }

        private void receiveMetierAndPartie()
        {
            try
            {

                InputStream inputStream = ClientControleur.this.socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                if (objectInputStream.available() > 0)
                {
                    ClientControleur.this.metier = (Metier) objectInputStream.readObject();
                    ClientControleur.this.partie = (Partie) objectInputStream.readObject();
                }

            }
            catch (ClassNotFoundException e) {e.printStackTrace();}
            catch (IOException e){e.printStackTrace();}
        }
    }
}
