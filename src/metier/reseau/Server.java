package metier.reseau;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controleur.Controleur;
import metier.Metier;


public class Server 
{
    private Metier metier;
    private ServerSocket socket;
    public Server(Metier metier)
    {
        this.metier = metier;
    }

    public void Stop()
    {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Start()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new ServerSocket(5000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true)
                {
                    try {
                        Socket client = socket.accept();
                        new Thread(new ServerClientHandler(metier, client)).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
