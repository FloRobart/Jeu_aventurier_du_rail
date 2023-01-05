package metier.reseau;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


import metier.Metier;


public class ClientServerHandler implements Runnable
{

    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Metier metier;

    private void sendCommand(String cmd)
    {
        try
        {
            this.out.write(cmd.getBytes());
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la commande réseau");
        }
    }

    /*
     * Lit le flux réseau jusqu'à ce que la chaîne until soit lue
     * @param until Chaîne à lire
     * @return Chaîne lue
     */
    private String readUntil(String until)
    {
        try
        {
            String str = "";
            while (!str.endsWith(until))
            {
                str += (char) this.in.read();
            }
            return str;
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
        }

        return null;
    }

    public ClientServerHandler(Metier metier, Socket socket)
    {
        this.metier = metier;
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
            String command = this.readUntil(" ");          
            if (command == null)
                break;

            if (command.equals("ERREUR "))
            {
                String message = this.readUntil("\n");
                System.out.println("ERREUR SERVER : " + message);
            }

            

                    
            
        }
    }
}
