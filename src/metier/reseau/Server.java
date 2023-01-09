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
        this.clients = new ArrayList<ServerClientHandler>();
        this.metier = metier;
    }

    public void writeonce(String cmd)
    {
        for (ServerClientHandler sch : clients)
        {
            sch.writeonce(cmd);
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
                if (socket == null)
                {
                    System.out.println("Socket null");
                    return;
                }else{
                    System.out.println("Socket not null");
                                    
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
        }).start();
    }


}
