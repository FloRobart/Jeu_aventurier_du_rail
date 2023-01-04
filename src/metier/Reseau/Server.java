package metier.Reseau;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controleur.Controleur;

public class Server 
{
    private Controleur ctrl;
    private ServerSocket socket;
    public Server(Controleur ctrl)
    {
        this.ctrl = ctrl;
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
                // TODO Auto-generated method stub
                try {
                    socket = new ServerSocket(5000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true)
                {
                    try {
                        Socket client = socket.accept();
                        new Thread(new ServerClientHandler(ctrl, client)).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
