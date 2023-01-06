package controleur;

import metier.Metier;
import java.net.*;
import java.io.*;
public class ServerControleur 
{
    private ServerSocket ss;
    private boolean      isWating;
    private Metier       metier;
    public ServerControleur(Metier metier) 
    {
        this.isWating = true;
        this.metier = metier;
		try
		{
			ss	= new ServerSocket(6000);
            Thread threadWaitClient = new Thread(new WaitClient());
            threadWaitClient.start();
		} 
		catch (Exception e){e.printStackTrace();}        
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
                    Thread thread = new Thread (new ClientHandler(s));
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
            // Send the metier
            try 
            {
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(metier);
                objectOutputStream.flush();

            }
            catch (IOException e){e.printStackTrace();}
        }
        
    }
}
