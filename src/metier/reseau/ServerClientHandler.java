package metier.reseau;

import java.awt.Color;

/*
 * Fichier qui gère la communication de serveur vers le client
 * Commands réseau
 * Format :
 * La command est suivie d'un espace, puis des arguments séparés par des espaces, puis d'un espace suivit d'un retour à la ligne
 * [COMMAND] {[ARGS...] }\n
 * 
 * Liste des commands :
 * BONJOUR : Envoi un message de bienvenue au serveur, le server répond avec des packets OPTION
 * PARTIE [nom] [valeur] : Envoi d'un paramètre de la partie au client
 * ERREUR [message] : Envoi d'un message d'erreur au client
 * NOUVEAU_JOUEUR : Un joueur a rejoint la partie
 * MOT_DE_PASSE [mot_de_passe] : Envoi du mot de passe au serveur
 * 
 * CHARGER_XML [taille] [xml] : Envoi d'un fichier xml au client
 * METIER [class_metier] : Envoi d'un fichier class au client (serealisation de la classe Metier)
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controleur.Controleur;
import metier.Joueur;
import metier.Metier;
import metier.partie.Partie;


public class ServerClientHandler implements Runnable
{

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private Boolean shouldStop;
    private Controleur ctrl;
    private Metier metier;
    private String nomJoueur;
    private Boolean authentifie;
    private Joueur joueur;

    public void majMetier(Metier m)
    {
        try {
            this.out.writeObject("METIER");
            this.out.flush();
            this.out.reset();
            this.out.writeObject(m);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Joueur getJoueur()
    {
        return this.joueur;
    }

    public void majPartie(Partie partie)
    {
        try
        {
            this.out.writeObject("MISE_A_JOUR_PARTIE");
            this.out.flush();
            this.out.reset();
            this.out.writeObject(partie);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la partie");
            this.metier.getServer().RemoveClient(this);
        }
    }

    public void writeonce(String cmd)
    {
        try
        {
            this.out.writeObject(cmd);
            this.out.flush();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de l'envoi de la commande réseau");
            this.metier.getServer().RemoveClient(this);
        }
    }

    /*
     * Lit le flux réseau jusqu'à ce que la chaîne until soit lue
     * @param until Chaîne à lire
     * @return Chaîne lue
     */
    private String readonce()
    {
        // read until string "until" is read, blocking
        String ret = "";
        try
        {
            ret = (String)this.in.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du flux réseau");
            this.metier.getServer().RemoveClient(this);
        }
        return ret;
    }

    public ServerClientHandler(Controleur ctrl, Socket socket)
    {
        this.socket = socket;
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
        this.authentifie = false;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialLoading()
    {
        joueur = new Joueur(this.ctrl, this.nomJoueur);
        joueur.setCouleur(Color.getHSBColor(this.ctrl.getJoueurs().size() * 20, 80, 50));
        this.metier.ajouterJoueur(joueur);     

        this.authentifie = true;
        this.metier.getServer().majMetier();
        this.metier.getServer().writeonce("NOUVEAU_JOUEUR");
        this.metier.getServer().writeonce(this.nomJoueur);

        writeonce("CONNEXION_ACCEPTER");

    }

    public void Disconnect()
    {
        try {
            this.socket.close();
            this.shouldStop = true;
            this.metier.getServer().majMetier();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        this.shouldStop = false;
        while (!this.shouldStop)
        {
            String command = readonce();      
            if (command == null)
                break;
            
            if (command.equals("PASS_TEST"))
            {
                String motDePasse = readonce();
                
                if (motDePasse.equals(this.metier.getMotDePasse()))
                {
                    writeonce("OK");
                }
                else
                {
                    writeonce("WRONG");
                }
                this.metier.getServer().RemoveClient(this);
                return;
            }

            if (!this.authentifie)
            {
                if (command.equals("BONJOUR"))
                {
                    this.nomJoueur = readonce();

                    for (Joueur j : this.ctrl.getJoueurs())
                    {
                        if (j.getNom().equals(this.nomJoueur))
                        {
                            writeonce("ERREUR");
                            writeonce("Ce nom est déjà utilisé");
                            this.metier.getServer().RemoveClient(this);
                            return;
                        }
                    }

                    if (this.nomJoueur.length() > 20)
                    {
                        writeonce("ERREUR");
                        writeonce("Ce nom est trop long");
                        this.metier.getServer().RemoveClient(this);
                        return;
                    }

                    if (this.nomJoueur.isBlank())
                    {
                        writeonce("ERREUR");
                        writeonce("Ce nom est vide");
                        this.metier.getServer().RemoveClient(this);
                        return;
                    }

                }

                if (command.equals("MOT_DE_PASSE"))
                {
                    String motDePasse = readonce();
                    
                    if (motDePasse.equals(this.metier.getMotDePasse()))
                    {
                        initialLoading();
                        writeonce("MESSAGE");
                        writeonce("Bienvenue " + this.nomJoueur + " !");
                    }
                    else
                    {
                        writeonce("ERREUR");
                        writeonce("Mot de passe incorrect");
                    }
                }
                continue;
            }

            // Joueur authentifié

            if (command.equals("MISE_A_JOUR_PARTIE"))
            {
                try {
                    Partie partie = (Partie) in.readObject();
                    this.ctrl.setPartie(partie);

                    this.ctrl.getMetier().getServer().majPartie(partie);

                    this.ctrl.majIHM();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("FINIR_TOUR"))
            {
                this.ctrl.getMetier().getServer().writeonce("FINIR_TOUR");
                this.ctrl.majIHM();
            }

            if (command.equals("COULEUR_JOUEUR"))
            {
                try {
                    Color couleur = (Color) in.readObject();
                    this.joueur.setCouleur(couleur);
                    this.ctrl.majIHM();
                    this.ctrl.getMetier().getServer().majMetier();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("FIN_JEU"))
            {
                this.ctrl.getMetier().getServer().writeonce("FIN_JEU");
                this.ctrl.ouvrirFinPartie(false);
            }

            
                    
            
        }
    }
}
