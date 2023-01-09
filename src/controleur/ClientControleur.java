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
        try
        {

            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            metier = (Metier) objectInputStream.readObject();
            partie = (Partie) objectInputStream.readObject();

        }
        catch (ClassNotFoundException e) {e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        
        Thread thread = new Thread(new ServerHandler(socket));
        thread.start();
        
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
     * Thread which will wait indefintely for the update of the map
     */
    class ServerHandler implements Runnable
    {
        private Socket socket;
        public ServerHandler(Socket socket)
        {
            this.socket = socket;
        }
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
                InputStream inputStream = this.socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
 
                    metier = (Metier) objectInputStream.readObject();
                    partie = (Partie) objectInputStream.readObject();
                
                System.out.println(metier);
            }
            catch (ClassNotFoundException e) {e.printStackTrace();}
            catch (IOException e){e.printStackTrace();}
        }
    }
    public static void main(String[] args) throws ConnectException, UnknownHostException, IOException {
        ClientControleur clientCtrl = new ClientControleur("127.0.0.1");
        System.out.println(clientCtrl.metier);

    }
}
