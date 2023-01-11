package ihm.accueil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;
import ihm.customComponent.TextFieldAdresseIP;
import ihm.customComponent.TextFieldMdp;
import ihm.customComponent.TextFieldPseudo;
import ihm.customComponent.TextFieldWithHint;
import metier.Joueur;
import metier.reseau.Client;


/**
 * C'est le premier panel qui s'affiche quand on lance le jeu.
 * Ce panel permet de créer ou de rejoindre une partie.
 */
public class PanelAccueil extends JPanel implements ActionListener
{
    private static final String FONT = "Liberation Sans";

    private Controleur        ctrl;
	private boolean           mappeImportee;
    private HashMap<String, List<Color>> theme;

    /* Panels */
    private JPanel            panelCreerPartie    ;
    private JPanel            panelRejoindrePartie;

    /* Boutons */
    private JButton           btnCreerMulti    ;
    private JButton           btnCreerSolo     ;
    private JButton           btnImportMappe   ;
    private JButton           btnRejoindre     ;
    private JButton           btnTestSolo      ;
    private JButton           btnTestMulti     ;
    private JButton           btnTestRejoindre ;

    /* Labels */
    private JLabel            lblImportMappe   ;
    private JLabel            lblIpRejoindre   ;
    private JLabel            lblMdpCreer      ;
    private JLabel            lblMdpRejoindre  ;
    private JLabel            lblPseudo        ;
    private JLabel            lblTitre         ;
    private JLabel            lblTitreCreer    ;
    private JLabel            lblTitreRejoindre;

    /* TextFields */
    private TextFieldAdresseIP txtIpRejoindre   ;
    private TextFieldMdp       txtMdpCreer      ;
    private TextFieldMdp       txtMdpRejoindre  ;
    private TextFieldPseudo    txtPseudo        ;


    public PanelAccueil(Controleur ctrl)
    {
        /*=========================*/
        /* Création des composants */
        /*=========================*/
        /*----------*/
        /* Création */
        /*----------*/
        this.ctrl                 = ctrl;
        this.mappeImportee        = false;
        this.theme                = this.ctrl.getTheme();

        /* Panels */
        this.panelCreerPartie     = new JPanel ();
        this.panelRejoindrePartie = new JPanel ();

        /* Boutons */
        this.btnCreerMulti        = new JButton();
        this.btnCreerSolo         = new JButton();
        this.btnImportMappe       = new JButton();
        this.btnRejoindre         = new JButton();
        this.btnTestSolo          = new JButton();
        this.btnTestMulti         = new JButton();
        this.btnTestRejoindre     = new JButton();

        /* Labels */
        this.lblImportMappe       = new JLabel ();
        this.lblIpRejoindre       = new JLabel ();
        this.lblMdpCreer          = new JLabel ();
        this.lblMdpRejoindre      = new JLabel ();
        this.lblPseudo            = new JLabel ();
        this.lblTitre             = new JLabel ();
        this.lblTitreCreer        = new JLabel ();
        this.lblTitreRejoindre    = new JLabel ();
        
        /* Textfields */
        this.txtIpRejoindre       = new TextFieldAdresseIP("127.0.0.1"       , this.ctrl);
        this.txtMdpCreer          = new TextFieldMdp      ("0000"            , this.ctrl);
        this.txtMdpRejoindre      = new TextFieldMdp      ("0000"            , this.ctrl);
        this.txtPseudo            = new TextFieldPseudo   ("Entrez un pseudo", this.ctrl);


        /*--------------*/
        /* Modification */
        /*--------------*/
        /* Panels */
        this.panelCreerPartie.setMaximumSize  (new Dimension(400, 400));
        this.panelCreerPartie.setMinimumSize  (new Dimension(400, 400));
        this.panelCreerPartie.setPreferredSize(new Dimension(400, 400));

        this.panelRejoindrePartie.setMaximumSize  (new Dimension(400, 400));
        this.panelRejoindrePartie.setMinimumSize  (new Dimension(400, 400));
        this.panelRejoindrePartie.setPreferredSize(new Dimension(400, 400));


        /* Labels */
        this.lblTitre.setVerticalAlignment  (JLabel.CENTER);
        this.lblTitre.setHorizontalAlignment(JLabel.CENTER);
        this.lblTitre.setSize         (new Dimension(1000, 100));
        this.lblTitre.setMaximumSize  (new Dimension(1000, 100));
        this.lblTitre.setMinimumSize  (new Dimension(1000, 100));
        this.lblTitre.setPreferredSize(new Dimension(1000, 100));

        ImageIcon imgIconTitre = null;
        try { imgIconTitre = new ImageIcon(ImageIO.read(new File("./bin/donnees/images/Titre_ADR.png")).getScaledInstance((this.lblTitre.getWidth()), this.lblTitre.getHeight(), Image.SCALE_SMOOTH)); } catch (IOException e) {}
        this.lblTitre.setIcon(imgIconTitre);


        this.lblPseudo.setText("Comment vous appelez-vous ?");
        this.lblPseudo.setFont(new Font(PanelAccueil.FONT, 0, 24));
        this.lblPseudo.setHorizontalAlignment(JLabel.CENTER);
        this.lblPseudo.setMaximumSize  (new Dimension(350, 40));
        this.lblPseudo.setMinimumSize  (new Dimension(350, 40));
        this.lblPseudo.setPreferredSize(new Dimension(350, 40));


        /* Labels Creer */
        this.lblTitreCreer.setText("Créer une partie");
        this.lblTitreCreer.setFont(new Font(PanelAccueil.FONT, 0, 36));
        this.lblTitreCreer.setHorizontalAlignment   (JLabel.CENTER);
        this.lblTitreCreer.setHorizontalTextPosition(JLabel.CENTER);
        this.lblTitreCreer.setMaximumSize  (new Dimension(400, 40));
        this.lblTitreCreer.setMinimumSize  (new Dimension(400, 40));
        this.lblTitreCreer.setPreferredSize(new Dimension(400, 40));

        this.lblImportMappe.setText("");
        this.lblImportMappe.setHorizontalAlignment(JLabel.CENTER);
        this.lblImportMappe.setMaximumSize  (new Dimension(200, 30));
        this.lblImportMappe.setMinimumSize  (new Dimension(200, 30));
        this.lblImportMappe.setPreferredSize(new Dimension(200, 30));

        this.lblMdpCreer.setText("Mot de passe");
        this.lblMdpCreer.setHorizontalAlignment(JLabel.CENTER);
        this.lblMdpCreer.setMaximumSize  (new Dimension(200, 30));
        this.lblMdpCreer.setMinimumSize  (new Dimension(200, 30));
        this.lblMdpCreer.setPreferredSize(new Dimension(200, 30));


        /* Labels Rejoindre */
        this.lblTitreRejoindre.setText("Rejoindre une partie");
        this.lblTitreRejoindre.setFont(new Font(PanelAccueil.FONT, 0, 36));
        this.lblTitreRejoindre.setHorizontalAlignment(JLabel.CENTER);
        this.lblTitreRejoindre.setMaximumSize  (new Dimension(400, 40));
        this.lblTitreRejoindre.setMinimumSize  (new Dimension(400, 40));
        this.lblTitreRejoindre.setPreferredSize(new Dimension(400, 40));

        this.lblIpRejoindre.setText("Adresse IP");
        this.lblIpRejoindre.setHorizontalAlignment(JLabel.CENTER);
        this.lblIpRejoindre.setMaximumSize  (new Dimension(200, 30));
        this.lblIpRejoindre.setMinimumSize  (new Dimension(200, 30));
        this.lblIpRejoindre.setPreferredSize(new Dimension(200, 30));

        this.lblMdpRejoindre.setText("Mot de passe");
        this.lblMdpRejoindre.setHorizontalAlignment(JLabel.CENTER);
        this.lblMdpRejoindre.setMaximumSize  (new Dimension(200, 30));
        this.lblMdpRejoindre.setMinimumSize  (new Dimension(200, 30));
        this.lblMdpRejoindre.setPreferredSize(new Dimension(200, 30));


        /* Boutons */
        this.btnTestSolo.setText("Solo");
        this.btnTestSolo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnTestSolo.setMaximumSize  (new Dimension(100, 30));
        this.btnTestSolo.setMinimumSize  (new Dimension(100, 30));
        this.btnTestSolo.setPreferredSize(new Dimension(100, 30));
        this.btnTestSolo.addActionListener(this);

        this.btnTestMulti.setText("Multi");
        this.btnTestMulti.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnTestMulti.setMaximumSize  (new Dimension(100, 30));
        this.btnTestMulti.setMinimumSize  (new Dimension(100, 30));
        this.btnTestMulti.setPreferredSize(new Dimension(100, 30));
        this.btnTestMulti.addActionListener(this);

        this.btnTestRejoindre.setText("Rejoindre");
        this.btnTestRejoindre.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnTestRejoindre.setMaximumSize  (new Dimension(100, 30));
        this.btnTestRejoindre.setMinimumSize  (new Dimension(100, 30));
        this.btnTestRejoindre.setPreferredSize(new Dimension(100, 30));
        this.btnTestRejoindre.addActionListener(this);


        /* Boutons Creer */
        this.btnImportMappe.setText("Importer une mappe");
        this.btnImportMappe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnImportMappe.setMaximumSize  (new Dimension(200, 30));
        this.btnImportMappe.setMinimumSize  (new Dimension(200, 30));
        this.btnImportMappe.setPreferredSize(new Dimension(200, 30));

        this.btnCreerSolo.setText("Lancer en Local");
        this.btnCreerSolo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCreerSolo.setMaximumSize  (new Dimension(150, 30));
        this.btnCreerSolo.setMinimumSize  (new Dimension(150, 30));
        this.btnCreerSolo.setPreferredSize(new Dimension(150, 30));

        this.btnCreerMulti.setText("Lancer en multi");
        this.btnCreerMulti.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCreerMulti.setMaximumSize  (new Dimension(150, 30));
        this.btnCreerMulti.setMinimumSize  (new Dimension(150, 30));
        this.btnCreerMulti.setPreferredSize(new Dimension(150, 30));


        /* Boutons Rejoindre */
        this.btnRejoindre.setText("Rejoindre");
        this.btnRejoindre.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnRejoindre.setMaximumSize  (new Dimension(200, 30));
        this.btnRejoindre.setMinimumSize  (new Dimension(200, 30));
        this.btnRejoindre.setPreferredSize(new Dimension(200, 30));



        /* TextFields */
        this.txtPseudo.setFont(new Font(PanelAccueil.FONT, 0, 24));
        this.txtPseudo.setHorizontalAlignment(JTextField.CENTER);
        this.txtPseudo.setMaximumSize  (new Dimension(240, 40));
        this.txtPseudo.setMinimumSize  (new Dimension(240, 40));
        this.txtPseudo.setPreferredSize(new Dimension(240, 40));


        /* TextFields Creer */
        this.txtMdpCreer.setHorizontalAlignment(JTextField.CENTER);
        this.txtMdpCreer.setMaximumSize  (new Dimension(200, 30));
        this.txtMdpCreer.setMinimumSize  (new Dimension(200, 30));
        this.txtMdpCreer.setPreferredSize(new Dimension(200, 30));


        /* TextFields Rejoindre */
        this.txtIpRejoindre.setHorizontalAlignment(JTextField.CENTER);
        this.txtIpRejoindre.setMaximumSize  (new Dimension(200, 30));
        this.txtIpRejoindre.setMinimumSize  (new Dimension(200, 30));
        this.txtIpRejoindre.setPreferredSize(new Dimension(200, 30));

        this.txtMdpRejoindre.setHorizontalAlignment(JTextField.CENTER);
        this.txtMdpRejoindre.setMaximumSize  (new Dimension(200, 30));
        this.txtMdpRejoindre.setMinimumSize  (new Dimension(200, 30));
        this.txtMdpRejoindre.setPreferredSize(new Dimension(200, 30));



        /*======================*/
        /* Ajout des composants */
        /*======================*/
        /*--------------------------------------------------------------*/
        /* Titre et pseudo ET Ajout des deux panels au panel principale */
        /*--------------------------------------------------------------*/
        /* Horizontale */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblTitre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.lblPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(this.txtPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(this.btnTestSolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(this.btnTestMulti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(this.btnTestRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.panelCreerPartie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(this.panelRejoindrePartie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))))
        );

        /* Vertical */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.lblTitre, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.txtPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnTestSolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnTestMulti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnTestRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.panelCreerPartie, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.panelRejoindrePartie, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        

        /*-----------------------------*/
        /* panel pour creer une partie */
        /*-----------------------------*/
        /* Horizontale */
        GroupLayout panelCreerPartieLayout = new GroupLayout(this.panelCreerPartie);
        this.panelCreerPartie.setLayout(panelCreerPartieLayout);
        panelCreerPartieLayout.setHorizontalGroup(
            panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblTitreCreer, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCreerPartieLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.lblImportMappe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.btnImportMappe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.txtMdpCreer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.lblMdpCreer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
            .addGroup(GroupLayout.Alignment.TRAILING, panelCreerPartieLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(this.btnCreerSolo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(this.btnCreerMulti, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        /* Verticale */
        panelCreerPartieLayout.setVerticalGroup(
            panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelCreerPartieLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(this.lblTitreCreer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(this.lblImportMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.btnImportMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(this.lblMdpCreer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.txtMdpCreer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.btnCreerMulti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnCreerSolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        

        /*---------------------------------*/
        /* panel pour rejoindre une partie */
        /*---------------------------------*/
        /* Horizontale */
        GroupLayout panelRejoindrePartieLayout = new GroupLayout(this.panelRejoindrePartie);
        this.panelRejoindrePartie.setLayout(panelRejoindrePartieLayout);
        panelRejoindrePartieLayout.setHorizontalGroup(
            panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblTitreRejoindre, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRejoindrePartieLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.txtMdpRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.txtIpRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.btnRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.lblIpRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.lblMdpRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );

        /* Verticale */
        panelRejoindrePartieLayout.setVerticalGroup(
            panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelRejoindrePartieLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(this.lblTitreRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(this.lblIpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.txtIpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(this.lblMdpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.txtMdpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(this.btnRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );



        /*===========================*/
        /* Activation des composants */
        /*===========================*/
        /* Titre et pseudo */
        this.txtPseudo      .addActionListener(this);

        /* panel pour creer une partie */
        this.btnImportMappe.addActionListener(this);
        this.btnCreerMulti .addActionListener(this);
        this.btnCreerSolo  .addActionListener(this);

        this.txtMdpCreer   .addActionListener(this);


        /* panel pour rejoindre une partie */
        this.btnRejoindre   .addActionListener(this);

        this.txtIpRejoindre .addActionListener(this);
        this.txtMdpRejoindre.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        Color disableColor = this.theme.get("disableColor").get(0);
        Color enableColor  = this.theme.get("enableColor" ).get(0);

        /*------------*/
        /* Textfields */
        /*------------*/
        if (e.getSource() instanceof JTextField)
        {
            /* Textfield Pseudo */
            if (e.getSource() == this.txtPseudo)
            {
                if (this.txtPseudo.getText().length() > TextFieldPseudo.getMaxSizePseudo())
                    this.txtPseudo.setText(this.txtPseudo.getText().substring(0, TextFieldPseudo.getMaxSizePseudo()));

                if (!this.txtPseudo.getText().isEmpty())
                {
                    if (this.txtPseudo.getBorder() != null)
                        this.txtPseudo.setBorder(null);

                    if (this.txtPseudo.getPlaceholderColor() == disableColor)
                        this.txtPseudo.setPlaceholderColor(this.theme.get("saisies").get(2));
                }
                else
                {
                    this.txtPseudo.setText("");
                    this.txtPseudo.setPlaceholderColorOnly(disableColor);
                    this.txtPseudo.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                }
            }

            /* Textfield Mot de passe Creer */
            if (e.getSource() == this.txtMdpCreer)
            {
                /* Empêche d'avoir plus de 4 caractères même en copier/coller */
                if (this.txtMdpCreer.getText().length() > TextFieldMdp.getMaxSizeMdp())
                    this.txtMdpCreer.setText(this.txtMdpCreer.getText().substring(0, TextFieldMdp.getMaxSizeMdp()));

                /* Complète avec des 0 s'il y a moins de 4 caractères */
                if (this.txtMdpCreer.getText().length() < TextFieldMdp.getMaxSizeMdp())
                {
                    String complement = "";
                    for (int i = this.txtMdpCreer.getText().length(); i < TextFieldMdp.getMaxSizeMdp(); i++)
                        complement += "0";

                    this.txtMdpCreer.setText(complement + this.txtMdpCreer.getText());
                }

                if (this.txtMdpCreer.getBorder() != null)
                        this.txtMdpCreer.setBorder(null);

                if (this.txtMdpCreer.getPlaceholderColor() == disableColor)
                    this.txtMdpCreer.setPlaceholderColor(this.theme.get("saisies").get(2));
            }

            /* Textfield Adresse IP */
            if (e.getSource() == this.txtIpRejoindre)
            {
                if (this.txtIpRejoindre.getBorder() != null)
                    this.txtIpRejoindre.setBorder(null);

                if (this.txtIpRejoindre.getPlaceholderColor() == disableColor)
                    this.txtIpRejoindre.setPlaceholderColor(this.theme.get("saisies").get(2));
            }

            /* Textfield Mot de passe Rejoindre */
            if (e.getSource() == this.txtMdpRejoindre)
            {
                /* Empêche d'avoir plus de 4 caractères */
                if (this.txtMdpRejoindre.getText().length() > TextFieldMdp.getMaxSizeMdp())
                    this.txtMdpRejoindre.setText(this.txtMdpRejoindre.getText().substring(0, TextFieldMdp.getMaxSizeMdp()));

                /* Complète avec des 0 s'il y a moins de 4 caractères */
                if (this.txtMdpRejoindre.getText().length() < TextFieldMdp.getMaxSizeMdp())
                {
                    String complement = "";
                    for (int i = this.txtMdpRejoindre.getText().length(); i < TextFieldMdp.getMaxSizeMdp(); i++)
                        complement += "0";

                    this.txtMdpRejoindre.setText(complement + this.txtMdpRejoindre.getText());
                }

                if (this.txtMdpRejoindre.getBorder() != null)
                        this.txtMdpRejoindre.setBorder(null);
                    
                if (this.txtMdpRejoindre.getPlaceholderColor() == disableColor)
                    this.txtMdpRejoindre.setPlaceholderColor(this.theme.get("saisies").get(2));
            }
        }


        /*---------------------*/
        /* Bouton import mappe */
        /*---------------------*/
        if (e.getSource() instanceof JButton)
        {
            if (e.getSource() == this.btnImportMappe)
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
						this.mappeImportee = this.ctrl.ouvrir(fichier);

						if (this.mappeImportee)
						{
							if (this.btnImportMappe.getBorder() == BorderFactory.createLineBorder(disableColor, 3))
                            {
                                this.lblImportMappe.setText("");
								this.btnImportMappe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                            }

                            this.btnImportMappe.setText(fichier.getName());

                            this.lblImportMappe.setForeground(enableColor);
                            this.lblImportMappe.setText("Importation réussie");
                            this.btnImportMappe.setBorder(BorderFactory.createLineBorder(enableColor, 3));
						}
                        else
                        {
                            this.lblImportMappe.setForeground(disableColor);
                            this.lblImportMappe.setText("Le fichier est incorrect");
                            this.btnImportMappe.setText("Importer une mappe");
                            this.btnImportMappe.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                        }
                    }
					else
						JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format XML", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            }
            

            /*------------------*/
            /* Creer une partie */
            /*------------------*/
            if (e.getSource() == this.btnCreerSolo || e.getSource() == this.btnCreerMulti)
            {
                /* Vérification du pseudo */
                boolean pseudoCorrect = this.verifPseudo();
                boolean mdpCorrect = false;

                if (e.getSource() == this.btnCreerMulti) { mdpCorrect = this.verifMdp("creer", this.txtMdpCreer); }


                /* Vérification de la mappe */
                if (!this.mappeImportee)
                {
                    this.lblImportMappe.setForeground(disableColor);
                    this.lblImportMappe.setText("Veuillez importer un fichier");
                    this.btnImportMappe.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                }
                else
                {
                    /* Vérification du pseudo */
                    if (pseudoCorrect)
                    {
                        if (e.getSource() == this.btnCreerSolo)
                        {
                            this.ctrl.ajouterJoueur(new Joueur(this.ctrl, this.txtPseudo.getText()));
                            this.ctrl.creerPartieLocal();
                        }
                        else
                        {
                            /* Vérification du mot de passe */
                            if (mdpCorrect)
                            {
                                /* Emmène le joueur qui créée la partie dans la salle d'attente */
                                this.ctrl.ajouterJoueur(new Joueur(this.ctrl, this.txtPseudo.getText()));
                                this.ctrl.creerPartieMulti(this.txtMdpCreer.getText());
                            }
                        }
                    }
                }
            }


            /* Test */
            if (e.getSource() == this.btnTestSolo     ) { this.mappeImportee = this.ctrl.ouvrir(new File("./France.xml")); this.ctrl.ajouterJoueur(new Joueur(this.ctrl, this.txtPseudo.getText())); this.ctrl.creerPartieLocal(); }
            if (e.getSource() == this.btnTestMulti    ) { this.mappeImportee = this.ctrl.ouvrir(new File("./France.xml")); this.ctrl.ajouterJoueur(new Joueur(this.ctrl, "Test rapide")); this.ctrl.creerPartieMulti("0000"); }
            if (e.getSource() == this.btnTestRejoindre) { if(this.txtPseudo.getText().isBlank()) this.txtPseudo.setText("Joe"); this.ctrl.joinGame("127.0.0.1", this.txtPseudo.getText(), "0000");}

			          

            /*---------------------*/
            /* Rejoidre une partie */
            /*---------------------*/
            if (e.getSource() == this.btnRejoindre)
            {
                boolean pseudoCorrect = this.verifPseudo();
                boolean mdpCorrect    = this.verifMdp("rejoindre", this.txtMdpRejoindre);

                /* Vérification de l'adresse IP */
                if (this.txtIpRejoindre.getText().isEmpty() /*|| !this.ctrl.verifAdresseIP()*/)
                {
                    this.txtIpRejoindre.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                    this.txtIpRejoindre.setHint("Entrez une adresse IP");
                    this.txtIpRejoindre.setPlaceholderColor(disableColor);
                }
                else
                {
                    /* Vérification du pseudo */
                    if (pseudoCorrect)
                    {
                        if (mdpCorrect)
                        {
                            // Vérifier que le mot de passe colle avec l'adresse IP

                            this.ctrl.joinGame(this.txtIpRejoindre.getText(), this.txtPseudo.getText(), this.txtMdpRejoindre.getText());
                        }
                    }
                }
            }
        }
    }


    /**
     * Permet de vérifier qu'il y a bien un pseudo et de changer les couleurs en fonction
     * @return boolean, true si le pseudo est correct, sinon false
     */
    private boolean verifPseudo()
    {
        Color disableColor = this.theme.get("disableColor").get(0);
        boolean pseudoCorrect = false;

        if (this.txtPseudo.getText().isEmpty())
        {
            this.txtPseudo.setHint("Entrez un pseudo");
            this.txtPseudo.setPlaceholderColor(disableColor);
            this.txtPseudo.setBorder(BorderFactory.createLineBorder(disableColor, 3));
        }
        else
        {
            boolean onlySpace = true;
            for (int i = 0; i < this.txtPseudo.getText().length(); i++)
                if (this.txtPseudo.getText().charAt(i) != ' ')
                    onlySpace = false;

            if (!onlySpace)
            {
                pseudoCorrect = true;
                this.txtPseudo.setBorder(null);

                if (this.txtPseudo.getPlaceholderColor() == disableColor)
                    this.txtPseudo.setPlaceholderColor(this.theme.get("saisies").get(2));
            }
            else
            {
                this.txtPseudo.setText("");
                this.txtPseudo.setPlaceholderColor(disableColor);
                this.txtPseudo.setBorder(BorderFactory.createLineBorder(disableColor, 3));
            }
        }

        return pseudoCorrect;
    }


    /**
     * Permet de vérifier qu'il y a bien un mot de passe et de changer les couleurs en fonction.
     * Cette vérification s'effectue uniquement sur le texte brut du TextFieldMdp et non sur la partie réseau
     * @param txt : TextFieldMdp --> le TextField à vérifier
     * @return bollean, true si le mot de passe est correct, sinon false
     */
    private boolean verifMdp(String type, TextFieldWithHint txt)
    {
        Color disableColor = this.theme.get("disableColor").get(0);
        boolean mdpCorrect = false;

        if (type.toLowerCase().equals("rejoindre") || type.toLowerCase().equals("creer"))
        {
            if (txt.getText().isEmpty())
            {
                txt.setHint("Entrez un mot de passe");
                txt.setPlaceholderColor(disableColor);
                txt.setBorder(BorderFactory.createLineBorder(disableColor, 3));
            }
            else
            {
                if (type.toLowerCase().equals("rejoindre"))
                {
                    try
                    {
                        Client c = new Client(this.txtIpRejoindre.getText(), this.ctrl);

                        Boolean ok = c.passTest(txt.getText());

                        if (ok)
                        {
                            mdpCorrect = true;
                            txt.setBorder(null);

                            if (txt.getPlaceholderColor() == disableColor)
                                txt.setPlaceholderColor(this.theme.get("saisies").get(2));
                        }
                        else
                        {
                            txt.setText("");
                            txt.setForeground(disableColor);
                            txt.setBorder(BorderFactory.createLineBorder(disableColor, 3));
                        }

                    } catch (IOException e) { e.printStackTrace(); }

                }
                else
                {
                    mdpCorrect = true;
                    txt.setBorder(null);
    
                    if (txt.getPlaceholderColor() == disableColor)
                        txt.setPlaceholderColor(this.theme.get("saisies").get(2));
                }
            }
        }
        else { System.out.println("Erreur : le bouton n'est pas reconnu."); }


        return mdpCorrect;
    }


    /**
     * Permet d'appliquer le thème au panel et à tous ses composants.
     */
    public void appliquerTheme()
    {
        Color background       = this.theme.get("background"  ).get(0);
        Color titleForeColor   = this.theme.get("titles"      ).get(0);
        Color titleBackColor   = this.theme.get("titles"      ).get(1);
        Color labelForeColor   = this.theme.get("labels"      ).get(0);
        Color saisiForeColor   = this.theme.get("saisies"     ).get(0);
		Color saisiBackColor   = this.theme.get("saisies"     ).get(1);
        Color placeholderColor = this.theme.get("saisies"     ).get(2);
        Color btnForeColor     = this.theme.get("buttons"     ).get(0);
		Color btnBackColor     = this.theme.get("buttons"     ).get(1);


        this.setForeground(saisiForeColor);
        this.setBackground(background    );


        /*--------------*/
        /* Modification */
        /*--------------*/
        /* Panels */
        this.panelCreerPartie.setForeground(saisiForeColor);
        this.panelCreerPartie.setBackground(titleBackColor);

        this.panelRejoindrePartie.setForeground(saisiForeColor);
        this.panelRejoindrePartie.setBackground(titleBackColor);


        /* Labels */
        this.lblTitre.setForeground(titleForeColor);
        this.lblTitre.setOpaque(false);

        this.lblPseudo.setForeground(labelForeColor);
        this.lblPseudo.setOpaque(false);


        /* Labels Creer */
        this.lblTitreCreer.setForeground(titleForeColor);
        this.lblTitreCreer.setOpaque(false);

        this.lblMdpCreer.setForeground(labelForeColor);
        this.lblMdpCreer.setOpaque(false);


        /* Labels Rejoindre */
        this.lblTitreRejoindre.setForeground(titleForeColor);
        this.lblTitreRejoindre.setOpaque(false);

        this.lblIpRejoindre.setForeground(labelForeColor);
        this.lblIpRejoindre.setOpaque(false);

        this.lblMdpRejoindre.setForeground(labelForeColor);
        this.lblMdpRejoindre.setOpaque(false);


        /* Boutons */
        /* Boutons Creer */
        this.btnImportMappe.setForeground(btnForeColor);
        this.btnImportMappe.setBackground(btnBackColor);

        this.btnCreerSolo.setForeground(btnForeColor);
        this.btnCreerSolo.setBackground(btnBackColor);

        this.btnCreerMulti.setForeground(btnForeColor);
        this.btnCreerMulti.setBackground(btnBackColor);


        /* Boutons Rejoindre */
        this.btnRejoindre.setForeground(btnForeColor);
        this.btnRejoindre.setBackground(btnBackColor);



        /* TextFields */
        this.txtPseudo.setBorder(null);
        this.txtPseudo.setForeground(saisiForeColor); //placeholderColor;
        this.txtPseudo.setBackground(saisiBackColor);
        this.txtPseudo.setForegroundColor (saisiForeColor);
        this.txtPseudo.setPlaceholderColor(placeholderColor);


        /* TextFields Creer */
        this.txtMdpCreer.setBorder(null);
        this.txtMdpCreer.setForeground(placeholderColor);
        this.txtMdpCreer.setBackground(saisiBackColor);
        this.txtMdpCreer.setForegroundColor (saisiForeColor);
        this.txtMdpCreer.setPlaceholderColor(placeholderColor);


        /* TextFields Rejoindre */
        this.txtIpRejoindre.setBorder(null);
        this.txtIpRejoindre.setForeground(placeholderColor);
        this.txtIpRejoindre.setBackground(saisiBackColor);
        this.txtIpRejoindre.setForegroundColor (saisiForeColor);
        this.txtIpRejoindre.setPlaceholderColor(placeholderColor);

        this.txtMdpRejoindre.setBorder(null);
        this.txtMdpRejoindre.setForeground(placeholderColor);
        this.txtMdpRejoindre.setBackground(saisiBackColor);
        this.txtMdpRejoindre.setForegroundColor (saisiForeColor);
        this.txtMdpRejoindre.setPlaceholderColor(placeholderColor);
    }
}