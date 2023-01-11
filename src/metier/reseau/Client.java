package metier.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private ClientServerHandler csh;
    private Controleur ctrl;
    private Thread t;
    
    public Client(String ip, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.ip = ip;
        this.port = 5000;
        this.connecte = false;
    }

    public void writeonce(String cmd)
    {
        this.csh.writeonce(cmd);
    }

    public void majPartie()
    {
        this.csh.majPartie();
    }

    public void changerCouleur(java.awt.Color c)
    {
        this.csh.changerCouleur(c);
    }

    public boolean passTest(String password) throws IOException
    {
        this.socket = new Socket(this.ip, this.port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject("PASS_TEST");
        out.writeObject(password);
        out.flush();
        String receive;
        try {
            receive = (String)in.readObject();
            Boolean b = receive.equals("OK");
            return b;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        socket.close();

        return false;
    }

    public void Disconnect()
    {
        this.csh.Disconnect();
        // stop thread
        t.interrupt();
        // close socket
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ctrl.Deconnecter();
    }

    public void finirTour()
    {
        majPartie();
        writeonce("FINIR_TOUR");
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
            this.csh = new ClientServerHandler(this.ctrl, this.socket, password);
            this.t = new Thread(csh);
            t.start();
            
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la connexion au serveur");
        }
        return this.connecte;
    }

}
