package metier.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import controleur.Controleur;


public class ServerClientHandler implements Runnable
{

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Controleur ctrl;
    public ServerClientHandler(Controleur ctrl, Socket socket)
    {
        this.ctrl = ctrl;
        try {
            this.in = new BufferedInputStream(socket.getInputStream());
            this.out = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        while (true)
        {
            try {
                int packet = this.in.read();
                if (packet == -1)
                    break; // Stream déconnecter
                switch (packet)
                {
                    case 0: // Bonjour
                        this.out.write(Packet.BONJOUR.ordinal());
                        String nom = "ma_partie"; // TODO: Mettre dans controleur
                        this.out.write(nom.length());
                        this.out.write(nom.getBytes());
                        this.out.flush();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
