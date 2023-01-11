package ihm.attente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;
import metier.Joueur;


public class PanelAttenteLocal extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private HashMap<String, List<Color>> theme;
    private boolean mappeImportee;

    /* Panel */
    private JPanel panelInfo;
    private JPanel panelJoueurs;
    private PanelPreviewMappe panelPreviewMappe;

    /* Boutons */
    private JButton btnChangeMappe;
    private JButton btnNewJoueur;
    private JButton btnLancer;
    
    /* Labels */
    private JLabel lblCarteCoul;
    private JLabel lblCarteCoulRes;
    private JLabel lblCarteLoco;
    private JLabel lblCarteLocoRes;
    private JLabel lblCoulDispo;
    private JLabel lblCoulDispoRes;
    private JLabel lblJetonFin;
    private JLabel lblJetonFinRes;
    private JLabel lblJetonJoueur;
    private JLabel lblJetonJoueurRes;
    private JLabel lblJoueurMax;
    private JLabel lblJoueurMaxRes;
    private JLabel lblJoueurMin;
    private JLabel lblJoueurMinRes;
    private JLabel lblLstParticipants;
    private JLabel lblNombreDe;
    private JLabel lblStatut;
    private JLabel lblTitre;

    /* Listes */
    private JScrollPane  jspLstParticipants;
    private List<JButton> lstParticipants;

    /* Default Color */
    Color[] defaultColor = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.WHITE};


    public PanelAttenteLocal(Controleur ctrl)
    {
        this.ctrl  = ctrl;
        this.theme = this.ctrl.getTheme();

        /* Panel */
        this.panelInfo          = new JPanel();
        this.panelJoueurs       = new JPanel();
        this.panelPreviewMappe  = new PanelPreviewMappe(this.ctrl);

        /* Boutons */
        this.btnChangeMappe     = new JButton();
        this.btnNewJoueur       = new JButton();
        this.btnLancer          = new JButton();
        
        /* Labels */
        this.lblCarteCoul       = new JLabel();
        this.lblCarteCoulRes    = new JLabel();
        this.lblCarteLoco       = new JLabel();
        this.lblCarteLocoRes    = new JLabel();
        this.lblCoulDispo       = new JLabel();
        this.lblCoulDispoRes    = new JLabel();
        this.lblJetonFin        = new JLabel();
        this.lblJetonFinRes     = new JLabel();
        this.lblJetonJoueur     = new JLabel();
        this.lblJetonJoueurRes  = new JLabel();
        this.lblJoueurMax       = new JLabel();
        this.lblJoueurMaxRes    = new JLabel();
        this.lblJoueurMin       = new JLabel();
        this.lblJoueurMinRes    = new JLabel();
        this.lblLstParticipants = new JLabel();
        this.lblNombreDe        = new JLabel();
        this.lblStatut          = new JLabel();
        this.lblTitre           = new JLabel();

        /* Listes */
        this.jspLstParticipants = new JScrollPane      ();
        this.lstParticipants    = new ArrayList<JButton>();


        this.lblTitre.setHorizontalAlignment(JLabel.CENTER);
        this.lblTitre.setSize         (new Dimension(1000, 100));
        this.lblTitre.setMaximumSize  (new Dimension(1000, 100));
        this.lblTitre.setMinimumSize  (new Dimension(1000, 100));
        this.lblTitre.setPreferredSize(new Dimension(1000, 100));

        ImageIcon imgIconTitre = null;
        try { imgIconTitre = new ImageIcon(ImageIO.read(new File("./bin/donnees/images/Titre_ADR.png")).getScaledInstance((this.lblTitre.getWidth()-100), this.lblTitre.getHeight(), Image.SCALE_SMOOTH)); } catch (IOException e) {}
        this.lblTitre.setIcon(imgIconTitre);


        this.btnChangeMappe.setText("Changer de mappe");
        this.btnChangeMappe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnChangeMappe.setMaximumSize  (new Dimension(150, 30));
        this.btnChangeMappe.setMinimumSize  (new Dimension(150, 30));
        this.btnChangeMappe.setPreferredSize(new Dimension(150, 30));


        this.btnNewJoueur.setText("Ajouter un joueur");
        this.btnNewJoueur.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnNewJoueur.setMaximumSize  (new Dimension(200, 30));
        this.btnNewJoueur.setMinimumSize  (new Dimension(200, 30));
        this.btnNewJoueur.setPreferredSize(new Dimension(200, 30));


        this.panelPreviewMappe.setOpaque(true);
        this.panelPreviewMappe.setSize         (new Dimension(400, 250));
        this.panelPreviewMappe.setMaximumSize  (new Dimension(400, 250));
        this.panelPreviewMappe.setMinimumSize  (new Dimension(400, 250));
        this.panelPreviewMappe.setPreferredSize(new Dimension(400, 250));
        this.panelPreviewMappe.centrer(this.getWidth(), this.getHeight());
        

        this.panelInfo.setMaximumSize  (new Dimension(250, 200));
        this.panelInfo.setMinimumSize  (new Dimension(250, 200));
        this.panelInfo.setPreferredSize(new Dimension(250, 200));

        this.lblNombreDe.setText("Nombre de :");

        this.lblJoueurMin.setText("● Joueurs minimum");
        this.lblJoueurMin.setMaximumSize  (new Dimension(150, 18));
        this.lblJoueurMin.setMinimumSize  (new Dimension(150, 18));
        this.lblJoueurMin.setPreferredSize(new Dimension(150, 18));

        this.lblJoueurMax.setText("● Joueurs Maximum");
        this.lblJoueurMax.setMaximumSize  (new Dimension(150, 18));
        this.lblJoueurMax.setMinimumSize  (new Dimension(150, 18));
        this.lblJoueurMax.setPreferredSize(new Dimension(150, 18));

        this.lblCarteCoul.setText("● Cartes couleur");
        this.lblCarteCoul.setMaximumSize  (new Dimension(150, 18));
        this.lblCarteCoul.setMinimumSize  (new Dimension(150, 18));
        this.lblCarteCoul.setPreferredSize(new Dimension(150, 18));

        this.lblCarteLoco.setText("● Cartes locomotives");
        this.lblCarteLoco.setMaximumSize  (new Dimension(150, 18));
        this.lblCarteLoco.setMinimumSize  (new Dimension(150, 18));
        this.lblCarteLoco.setPreferredSize(new Dimension(150, 18));

        this.lblJetonJoueur.setText("● Jetons par joueur");
        this.lblJetonJoueur.setMaximumSize  (new Dimension(150, 18));
        this.lblJetonJoueur.setMinimumSize  (new Dimension(150, 18));
        this.lblJetonJoueur.setPreferredSize(new Dimension(150, 18));

        this.lblJetonFin.setText("● Jetons pour finir");
        this.lblJetonFin.setMaximumSize  (new Dimension(150, 18));
        this.lblJetonFin.setMinimumSize  (new Dimension(150, 18));
        this.lblJetonFin.setPreferredSize(new Dimension(150, 18));

        this.lblCoulDispo.setText("● Couleurs disponibles");
        this.lblCoulDispo.setMaximumSize  (new Dimension(150, 18));
        this.lblCoulDispo.setMinimumSize  (new Dimension(150, 18));
        this.lblCoulDispo.setPreferredSize(new Dimension(150, 18));

        this.lblJoueurMinRes.setText(": " + this.ctrl.getNbJoueursMin());
        this.lblJoueurMinRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJoueurMinRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJoueurMinRes.setPreferredSize(new Dimension(40, 18));

        this.lblJoueurMaxRes.setText(": " + this.ctrl.getNbJoueursMax());
        this.lblJoueurMaxRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJoueurMaxRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJoueurMaxRes.setPreferredSize(new Dimension(40, 18));

        this.lblCarteCoulRes.setText(": " + this.ctrl.getNbCarteCoul());
        this.lblCarteCoulRes.setMaximumSize  (new Dimension(40, 18));
        this.lblCarteCoulRes.setMinimumSize  (new Dimension(40, 18));
        this.lblCarteCoulRes.setPreferredSize(new Dimension(40, 18));

        this.lblCarteLocoRes.setText(": " + this.ctrl.getNbCarteLocomotive());
        this.lblCarteLocoRes.setMaximumSize  (new Dimension(40, 18));
        this.lblCarteLocoRes.setMinimumSize  (new Dimension(40, 18));
        this.lblCarteLocoRes.setPreferredSize(new Dimension(40, 18));

        this.lblJetonJoueurRes.setText(": " + this.ctrl.getNbJetonJoueur());
        this.lblJetonJoueurRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJetonJoueurRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJetonJoueurRes.setPreferredSize(new Dimension(40, 18));

        this.lblJetonFinRes.setText(": " + this.ctrl.getNbJetonFin());
        this.lblJetonFinRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJetonFinRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJetonFinRes.setPreferredSize(new Dimension(40, 18));

        this.lblCoulDispoRes.setText(": " + this.ctrl.getCouleurs().size());
        this.lblCoulDispoRes.setMaximumSize  (new Dimension(40, 18));
        this.lblCoulDispoRes.setMinimumSize  (new Dimension(40, 18));
        this.lblCoulDispoRes.setPreferredSize(new Dimension(40, 18));


        /* Horizontale */
        GroupLayout panelInfoLayout = new GroupLayout(this.panelInfo);
        this.panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.lblNombreDe))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblJoueurMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblJoueurMaxRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblCarteLoco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblCarteLocoRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblJetonJoueur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblJetonJoueurRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblJetonFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblJetonFinRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblCoulDispo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblCoulDispoRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblJoueurMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblJoueurMinRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(this.lblCarteCoul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.lblCarteCoulRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, 0))
        );

        /* Verticale */
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.lblNombreDe)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblJoueurMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblJoueurMinRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblJoueurMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblJoueurMaxRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblCarteCoul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblCarteCoulRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblCarteLoco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblCarteLocoRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblJetonJoueur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblJetonJoueurRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblJetonFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblJetonFinRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblCoulDispo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblCoulDispoRes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );



        /* Boutons des joueurs */
        Color titleBackColor = this.theme.get("titles").get(1);
        List<Joueur> lstJoueurs = this.ctrl.getJoueurs();
        for (int i = 0; i < this.ctrl.getNbJoueursMax(); i++)
        {
            String txt=" ";
            if (i < lstJoueurs.size()) { txt = lstJoueurs.get(i).getNom(); }
            this.lstParticipants.add(new JButton(txt));
            this.lstParticipants.get(i).setFont(new Font("Liberation Sans", 0, 24));
            this.lstParticipants.get(i).setPreferredSize(new Dimension(200, 40));
            this.lstParticipants.get(i).setOpaque(true);
            this.lstParticipants.get(i).setForeground(defaultColor[i]);
            this.lstParticipants.get(i).setHorizontalAlignment(JButton.CENTER);
            this.lstParticipants.get(i).setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
        }

        this.panelJoueurs.setLayout(new GridLayout(this.lstParticipants.size(), 1));
        for (int i = 0; i < this.lstParticipants.size(); i++)
            this.panelJoueurs.add(this.lstParticipants.get(i));

        Timer timer = new Timer(1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                List<Joueur> lstJoueurs    = ctrl.getJoueurs();
                for (int i = 0; i < ctrl.getNbJoueursMax(); i++)
                {
                    String txt=" ";
                    if (i < lstJoueurs.size())
                    {
                        txt = lstJoueurs.get(i).getNom();
                        lstParticipants.get(i).setText(txt);
                    }
                }  
                panelJoueurs.repaint();
            }
        });
        timer.start();

        this.jspLstParticipants = new JScrollPane(this.panelJoueurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jspLstParticipants.setMaximumSize  (new Dimension(200, 250));
        this.jspLstParticipants.setMinimumSize  (new Dimension(200, 250));
        this.jspLstParticipants.setPreferredSize(new Dimension(200, 250));
        this.jspLstParticipants.setBorder(null);


        this.lblStatut.setText("Statut : en attente de joueur");
        this.lblStatut.setFont(new Font("Liberation Sans", 0, 36));


        this.btnLancer.setText("Lancer la partie");
        this.btnLancer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnLancer.setMaximumSize  (new Dimension(150, 40));
        this.btnLancer.setMinimumSize  (new Dimension(150, 40));
        this.btnLancer.setPreferredSize(new Dimension(150, 40));


        /* Horizontale */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblTitre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(this.btnChangeMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(this.panelPreviewMappe, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.panelInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(this.btnNewJoueur, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.jspLstParticipants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(this.btnLancer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );


        /* Verticale */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.lblTitre, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(this.btnChangeMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(this.btnNewJoueur))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.panelInfo, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(this.panelPreviewMappe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.jspLstParticipants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(this.btnLancer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );



        /*------------------------*/
        /* Activation des boutons */
        /*------------------------*/
        /* Bouton changer mappe */
        this.btnChangeMappe.addActionListener(this);

        /* Bouton ajouter joueur */
        this.btnNewJoueur.addActionListener(this);

        /* Bouton lancer */
        this.btnLancer.addActionListener(this);

        /* Bouton de chaque joueur */
        for (int i = 0; i < this.lstParticipants.size(); i++)
            this.lstParticipants.get(i).addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            Color disableColor = this.theme.get("disableColor").get(0);
            Color enableColor  = this.theme.get("enableColor" ).get(0);

            /* Boutons de changement de la mappe */
            if (e.getSource() == this.btnChangeMappe)
            {
                JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier XML", "xml"));

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
				{
					File fichier = chooser.getSelectedFile();
					String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

					if (extention.equals("xml"))
                    {
                        /* AM = Ancienne Mappe */
                        String pathAM = this.ctrl.getPathMappe(); // permet de recherger la mappe si le fichier si une autre map est charger mais que le nombre de joueur ne vas pas


                        /* Toutes les méthode executer après ça seront éxecuter sur la nouvelle mappe */
                        this.mappeImportee = this.ctrl.ouvrir(fichier);

                        /* action à faire si la mappe est import correctement */
						if (this.mappeImportee)
						{
                            if (this.ctrl.getNbJoueursMax() >= this.ctrl.getJoueurs().size())
                            {
                                this.btnChangeMappe.setText(fichier.getName());
                                this.btnChangeMappe.setForeground(enableColor);
                                this.btnChangeMappe.setBorder(BorderFactory.createLineBorder(enableColor, 3));

                                this.setEnabled(true);

                                this.majIHM();
                            }
                            else
                            {
                                this.btnChangeMappe.setText("Changer de mappe");
                                this.btnChangeMappe.setForeground(enableColor);
                                this.btnChangeMappe.setBorder(BorderFactory.createLineBorder(enableColor, 3));

                                JOptionPane.showMessageDialog(this, "Vous êtes " + this.ctrl.getJoueurs().size() + " Joueurs dans la partie mais cette mappe peux contenir seulement " + this.ctrl.getNbJoueursMax() + " Joueurs maximum", "Erreur", JOptionPane.ERROR_MESSAGE);

                                this.mappeImportee = this.ctrl.ouvrir(new File (pathAM));
                            }
						}
                        else
                        {
                            this.btnChangeMappe.setText("Changer de mappe");
                            this.btnChangeMappe.setForeground(disableColor);
                            this.btnChangeMappe.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                        }
                    }
					else
						JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format XML", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            }


            /* Choix de la couleur (Bouton Joueur) */
            for (int i = 0; i < this.lstParticipants.size(); i++)
            {
                if (e.getSource() == this.lstParticipants.get(i) && this.lstParticipants.get(i).getText() != " ")
                {
                    Color background = this.theme.get("background").get(0);
                    Color c = JColorChooser.showDialog(this, "choix de la couleur", enableColor);

                    if (c != null)
                    {
                        if (!c.equals(background))
                        {
                            for (int j = i; j < this.ctrl.getJoueurs().size(); j++)
                            {
                                if (!c.equals(this.ctrl.getJoueurs().get(j).getCouleur()))
                                {
                                    this.ctrl.getJoueurs().get(i).setCouleur(c);
                                    this.lstParticipants.get(i).setForeground(c);
                                }
                                else
                                    JOptionPane.showMessageDialog(this, "Un joueur possède déjà cette couleur", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Veuillez choisir une couleur visible", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }



            /* Bouton ajouter joueur */
            if (e.getSource() == this.btnNewJoueur)
            {
                if (this.ctrl.getNbJoueursMax() > this.ctrl.getJoueurs().size())
                {
                    String nomJoueur = JOptionPane.showInputDialog(this, "Entrez votre pseudo", "Ajouter un joueur", JOptionPane.QUESTION_MESSAGE);


                    if (nomJoueur != null)
                    {
                        if (nomJoueur.length() > 16)
                            nomJoueur = nomJoueur.substring(0, 16);

                        if (this.verifPseudo(nomJoueur))
                        {
                            Color c = this.aleatoireColor();
                            Joueur j = new Joueur(this.ctrl, nomJoueur);
                            j.setCouleur(c);

                            this.ctrl.ajouterJoueur(j);
                            this.lstParticipants.get(this.ctrl.getJoueurs().size()-1).setForeground(c);
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Le pseudo est incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                    JOptionPane.showMessageDialog(this, "Le nombre de joueur maximum à été ateint", "Erreur", JOptionPane.ERROR_MESSAGE);
            }


            /* Boutons de lancement de la partie */
            if (e.getSource() == this.btnLancer)
            {
                if (this.ctrl.getJoueurs().size() == 1 || this.ctrl.getJoueurs().size() >= this.ctrl.getNbJoueursMin())
                {
                    this.ctrl.lancerPartieLocal();
                }
                else
                    JOptionPane.showMessageDialog(this, "Il manque " + (this.ctrl.getNbJoueursMin() - this.ctrl.getJoueurs().size()) + " joueurs pour lancer la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Permet de vérifier qu'il y a bien un pseudo et de changer les couleurs en fonction
     * @param txt, le pseudo à vérifier
     * @return boolean, true si le pseudo est correct, sinon false
     */
    private boolean verifPseudo(String txt)
    {
        boolean pseudoValide = false;
        if (!txt.isEmpty())
        {
            boolean onlySpace = true;
            for (int i = 0; i < txt.length(); i++)
                if (txt.charAt(i) != ' ')
                    onlySpace = false;

            if (!onlySpace)
            {
                // vérifier qu'il n'y a pas de joueur avec le même pseudo
                for (int i = 0; i < this.lstParticipants.size(); i++)
                {
                    if (this.lstParticipants.get(i).getText().toLowerCase().equals(txt))
                    {
                        return false;
                    }
                    else
                    {
                        pseudoValide = true;
                    }
                }
            }
        }

        return pseudoValide;
    }



    /**
     * Génère une couleur aléatoire parmie le tableau de couleur par défaut
     * @return Color : couleur aléatoire
     */
    private Color aleatoireColor()
    {
        Color c = this. defaultColor[(int) (Math.random() * this.defaultColor.length)];

        if (!c.equals(this.theme.get("background").get(0)))
            for (int i = 0; i < this.ctrl.getJoueurs().size(); i++)
                if (!c.equals(this.ctrl.getJoueurs().get(i).getCouleur()) || i >= this.defaultColor.length)
                    return c;
        
        return this.aleatoireColor();
    }



    /**
     * Met à jour les informations du panel attente
     */
    public void majIHM()
    {
        /* Labels d'information */
        this.lblJoueurMinRes.setText  (": " + this.ctrl.getNbJoueursMin     ());
        this.lblJoueurMaxRes.setText  (": " + this.ctrl.getNbJoueursMax     ());
        this.lblCarteCoulRes.setText  (": " + this.ctrl.getNbCarteCoul      ());
        this.lblCarteLocoRes.setText  (": " + this.ctrl.getNbCarteLocomotive());
        this.lblJetonJoueurRes.setText(": " + this.ctrl.getNbJetonJoueur    ());
        this.lblJetonFinRes.setText   (": " + this.ctrl.getNbJetonFin       ());
        this.lblCoulDispoRes.setText  (": " + this.ctrl.getCouleurs         ().size());


        /* Liste des joueurs */
        Color titleBackColor = this.theme.get("titles"    ).get(1);
        Color background     = this.theme.get("background").get(0);

        this.lstParticipants = new ArrayList<JButton>();
        for (int i = 0; i < this.ctrl.getNbJoueursMax(); i++)
        {
            this.lstParticipants.add(new JButton(" "));
            this.lstParticipants.get(i).setFont(new Font("Liberation Sans", 0, 24));
            this.lstParticipants.get(i).setPreferredSize(new Dimension(200, 40));
            this.lstParticipants.get(i).setOpaque(true);
            this.lstParticipants.get(i).setBackground(background);
            this.lstParticipants.get(i).setHorizontalAlignment(JLabel.CENTER);
            this.lstParticipants.get(i).setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
        }
        
        for (int i = 0; i < this.ctrl.getJoueurs().size(); i++)
            this.lstParticipants.get(i).setForeground(this.ctrl.getJoueurs().get(i).getCouleur());

        this.panelJoueurs.removeAll();
        this.panelJoueurs.setLayout(null);
        this.panelJoueurs.setLayout(new GridLayout(this.lstParticipants.size(), 1));

        for (int i = 0; i < this.lstParticipants.size(); i++)
            this.panelJoueurs.add(this.lstParticipants.get(i));

            for (int i = 0; i < this.lstParticipants.size(); i++)
                this.lstParticipants.get(i).addActionListener(this);

        // Mise à jour
        this.panelPreviewMappe.setMappe();
        this.panelPreviewMappe.repaint();
        this.panelPreviewMappe.centrer(this.getWidth(), this.getHeight());
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.theme.get("background"  ).get(0);
        Color titleBackColor   = this.theme.get("titles"      ).get(1);
        Color labelForeColor   = this.theme.get("labels"      ).get(0);
        Color btnForeColor     = this.theme.get("buttons"     ).get(0);
		Color btnBackColor     = this.theme.get("buttons"     ).get(1);


        /*-------*/
        /* Panel */
        /*-------*/
        /* Ce panel */
        this.setBackground(background);
        this.setForeground(labelForeColor);

        /* Panel de preview de la mappe */
        this.panelPreviewMappe.setForeground(labelForeColor);
        this.panelPreviewMappe.setBackground(titleBackColor);

        /* Panel d'information */
        this.panelInfo         .setBackground(background);


        /*---------*/
        /* Boutons */
        /*---------*/
        /* Bouton changer mappe */
        this.btnChangeMappe    .setForeground(btnForeColor);
        this.btnChangeMappe    .setBackground(btnBackColor);

        /* Bouton nouveau joueur */
        this.btnNewJoueur  .setForeground(btnForeColor);
        this.btnNewJoueur  .setBackground(btnBackColor);
        
        /* bouton lancer la partie */
        this.btnLancer         .setForeground(btnForeColor);
        this.btnLancer         .setBackground(btnBackColor);

        /* Liste des Boutons pour chaque joueur */
        for (JButton btn : this.lstParticipants)
        {
            btn.setBackground(background);
            btn.setBorder(BorderFactory.createBevelBorder(1, titleBackColor, titleBackColor));
        }


        /*------------*/
        /* ScrollPane */
        /*------------*/
        /* ScrollPane de la liste de participants */
        this.jspLstParticipants.getVerticalScrollBar().setBackground(background);


        /*--------*/
        /* Labels */
        /*--------*/
        /* Foreground */
        this.lblCarteCoul      .setForeground(labelForeColor);
        this.lblCarteCoulRes   .setForeground(labelForeColor);
        this.lblCarteLoco      .setForeground(labelForeColor);
        this.lblCarteLocoRes   .setForeground(labelForeColor);
        this.lblCoulDispo      .setForeground(labelForeColor);
        this.lblCoulDispoRes   .setForeground(labelForeColor);
        this.lblJetonFin       .setForeground(labelForeColor);
        this.lblJetonFinRes    .setForeground(labelForeColor);
        this.lblJetonJoueur    .setForeground(labelForeColor);
        this.lblJetonJoueurRes .setForeground(labelForeColor);
        this.lblJoueurMax      .setForeground(labelForeColor);
        this.lblJoueurMaxRes   .setForeground(labelForeColor);
        this.lblJoueurMin      .setForeground(labelForeColor);
        this.lblJoueurMinRes   .setForeground(labelForeColor);
        this.lblLstParticipants.setForeground(labelForeColor);
        this.lblNombreDe       .setForeground(labelForeColor);
        this.lblStatut         .setForeground(labelForeColor);
        this.lblTitre          .setForeground(labelForeColor);
    }
}
