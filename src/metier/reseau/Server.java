package metier.reseau;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import controleur.Controleur;
import metier.Metier;
import metier.partie.Partie;


public class Server 
{
    private Controleur ctrl;
    private ServerSocket socket;
    private ArrayList<ServerClientHandler> clients;
    public Server(Controleur ctrl)
    {
        this.clients = new ArrayList<ServerClientHandler>();
        this.ctrl = ctrl;
    }

    public void writeonce(String cmd)
    {
        for (ServerClientHandler sch : clients)
        {
            sch.writeonce(cmd);
        }
    }

    public void demarerPartie()
    {
        writeonce("DEMARRER_PARTIE");
    }

    public void majPartie(Partie partie)
    {
        for (ServerClientHandler sch : clients)
        {
            sch.majPartie(partie);
        }
    }

    public void majMetier()
    {
        for (ServerClientHandler sch : clients)
        {
            sch.majMetier(this.ctrl.getMetier());
        }
    }

    public void lancerPartie()
    {
        this.majMetier();
        majPartie(this.ctrl.getPartie());
        writeonce("LANCER_PARTIE");
    }

    public int getNbJoeurs()
    {
        return clients.size();
    }

    public void finirTour()
    {
        majPartie(this.ctrl.getPartie());
        writeonce("FINIR_TOUR");
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

    public void RemoveClient(ServerClientHandler sch)
    {
        sch.Disconnect();
        this.clients.remove(sch);
        this.ctrl.getMetier().getJoueurs().remove(sch.getJoueur());
        this.majMetier();
        this.ctrl.majIHM();
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
                        ServerClientHandler sch = new ServerClientHandler(ctrl, client);
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
