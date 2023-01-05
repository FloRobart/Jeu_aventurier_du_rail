package metier.reseau;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import metier.Metier;


public class Server 
{
    private Metier metier;
    private ServerSocket socket;
    private ArrayList<ServerClientHandler> clients;
    public Server(Metier metier)
    {
        this.metier = metier;
    }

    public void sendCommand(String cmd)
    {
        for (ServerClientHandler sch : clients)
        {
            sch.sendCommand(cmd);
        }
    }

    public int getNbJoeurs()
    {
        return clients.size();
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
        
        new Thread(new Runnable() {
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
                        ServerClientHandler sch = new ServerClientHandler(metier, client);
                        clients.add(sch);
                        new Thread(sch).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
