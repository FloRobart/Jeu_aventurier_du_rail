package controleur;

import metier.Metier;
import metier.partie.Partie;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
public class ServerControleur 
{
    private ServerSocket ss;
    private boolean      isWating;
    private Metier       metier;
    private Partie       partie;
    private List<ClientHandler> lstClientHandler;

    public ServerControleur(Metier metier,Partie partie) 
    {
        this.isWating = true;
        this.metier = metier;
        this.partie = partie;
        this.lstClientHandler = new ArrayList<ClientHandler>();
		try
		{
			ss	= new ServerSocket(9999);
            Thread threadWaitClient = new Thread(new WaitClient());
            threadWaitClient.start();
		} 
		catch (Exception e){e.printStackTrace();}        
    }
    /**
     * Method updateMap() notify (to all clients) that the map has been changed
     */
    public void updateMap()
    {
        for (ClientHandler clientHandler : this.lstClientHandler)
            clientHandler.updateMap();
    }


    class WaitClient implements Runnable
    {
        public void run ()
        {
            try
            {
                while (isWating)
                {
                    Socket s =  ss.accept();
                    ClientHandler clientHandler = new ClientHandler(s);
                    lstClientHandler.add(clientHandler);
                    Thread thread = new Thread (clientHandler);
                    thread.start();
                }    
            }
            catch (IOException e){e.printStackTrace();}
        }
    }
    class ClientHandler implements Runnable
    {
        Socket socket;
        public ClientHandler(Socket s)
        {
            this.socket = s;
        }
        @Override
        public void run() 
        {
            sendMetierAndPartie();
            Thread waitUpdateClient = new Thread (new WaitUpdateClient());
            waitUpdateClient.start();
        }
        public void updateMap()
        {
            sendMetierAndPartie();
        }
        public void sendMetierAndPartie()
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
        /**
         * Thread that wait unlimitedly for the response of Client
         */
        class WaitUpdateClient implements Runnable
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
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                        metier = (Metier) objectInputStream.readObject();
                        partie = (Partie) objectInputStream.readObject();
                    System.out.println("Updated");
                    System.out.println(metier.getNoeuds());
                }
                catch (ClassNotFoundException e) {e.printStackTrace();}
                catch (IOException e){e.printStackTrace();}
            }
        }
   }
   private void setMetier(Metier metier) {this.metier = metier;}
   public static void main(String[] args) {
    

    // Metier metierE = new Metier(null);
    // Metier metierF = new Metier(null);
    // metierF.lireFichier(new File ("./France.xml"));
    // metierF.lireFichier(new File ("./exemple.xml"));

    // System.out.println(metierF.getNoeuds());
    // System.out.println(metierE.getNoeuds());

    // ServerControleur serverCtrl = new ServerControleur(metierE, null);
    // int cpt =1;
    // while (true)
    // {
    //     if (cpt ==1) serverCtrl.setMetier(metierF);
    //     else serverCtrl.setMetier(metierE);
    //     serverCtrl.updateMap();
    //     Scanner scaner = new Scanner(System.in);
    //     scaner.next();
    //     cpt = 3-cpt;
    // }

   }
}
