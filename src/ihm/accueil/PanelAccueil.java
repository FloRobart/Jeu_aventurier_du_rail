package ihm.accueil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.customComponent.TextFieldWithHint;


/**
 * C'est le premier panel qui s'affiche quand on lance le jeu.
 * Ce panel permet de créer ou de rejoindre une partie.
 */
public class PanelAccueil extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private Dimension  dimFrame;

    /* Titre et pseudo */
    private JLabel            lblImgTitre;
    private BufferedImage     bfImgTitre;
    private JLabel            lblPseudo;
    private TextFieldWithHint txtPseudo;

    /* Panel qui permet de créer une partie avec tout les élements qu'il contient */
    private JPanel               panelCreerPartie;
    private JLabel               lblCreerPartie;
    private JButton              btnImportMappe;
    private TextFieldOnlyInteger txtMdpCreer;
    private JButton              btnCreerMulti;
    private JButton              btnCreerSolo;

    /* Panel qui permet de rejoindre une partie avec tout les élements qu'il contient */
    private JPanel               panelRejoindrePartie;
    private JLabel               lblRejoindrePartie;
    private TextFieldWithHint    txtIpRejoindre;
    private TextFieldOnlyInteger txtMdpRejoindre;
    private JButton              btnRejoindre;


    public PanelAccueil(Controleur ctrl, Dimension dimFrame)
    {
        this.ctrl = ctrl;
        this.dimFrame = dimFrame;

  

        /*=========================*/
        /* Création des composants */
        /*=========================*/
        /* Image du titre */
        try { this.bfImgTitre = ImageIO.read(new File("./bin/donnees/images/Titre_ADR.png")); } catch (IOException e) { e.printStackTrace(); System.out.println("Erreur lors du chargement de l'image TITRE"); }
        this.lblImgTitre = new JLabel();
        this.lblImgTitre.setSize(new Dimension((int) this.dimFrame.getWidth()-10, this.bfImgTitre.getHeight()));
        this.lblImgTitre = new JLabel(new ImageIcon(this.bfImgTitre.getScaledInstance(this.lblImgTitre.getWidth(), this.bfImgTitre.getHeight(), Image.SCALE_SMOOTH)));

        /* Titre et pseudo */
        this.lblPseudo = new JLabel("Comment vous appelez-vous ? ");
        this.txtPseudo = new TextFieldWithHint("sans nom", this.ctrl);


        /*-----------------------------*/
        /* panel pour creer une partie */
        /*-----------------------------*/
        /* Panel Création partie */
        this.panelCreerPartie  = new JPanel              ();
        this.panelCreerPartie.setPreferredSize(new Dimension(400, 400));

        /* Label Creer partie */
        this.lblCreerPartie    = new JLabel              ("Créer une partie"          );

        this.btnImportMappe    = new JButton             ("Importer une mappe"        );
        this.btnImportMappe.setSize(200, 24);

        this.txtMdpCreer       = new TextFieldOnlyInteger("Mot de passe de la partie", this.ctrl);
 
        this.btnCreerMulti    = new JButton             ("Créer la partie en Multi"  );
        this.btnCreerMulti.setSize(new Dimension(150, 24));

        this.btnCreerSolo     = new JButton             ("Créer la partie en solo"   );
        this.btnCreerSolo.setSize(new Dimension(150, 24));

        /* panel pour rejoindre une partie */
        this.panelRejoindrePartie  = new JPanel              ();
        this.lblRejoindrePartie    = new JLabel              ("Rejoindre une partie"      );
        this.txtIpRejoindre  = new TextFieldWithHint   ("IP"  , this.ctrl           );
        this.txtMdpRejoindre = new TextFieldOnlyInteger("0000", this.ctrl           );
        this.btnRejoindre    = new JButton             ("Rejoindre la partie"       );



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
            .addComponent(this.lblImgTitre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.lblPseudo)
                        .addGap(30, 30, 30)
                        .addComponent(this.txtPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.panelCreerPartie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(this.panelRejoindrePartie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))))
        );

        /* Verticale */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.lblImgTitre, GroupLayout.PREFERRED_SIZE, this.bfImgTitre.getHeight(), GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblPseudo)
                    .addComponent(this.txtPseudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.panelCreerPartie, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.panelRejoindrePartie, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        

        /*-----------------------------*/
        /* panel pour creer une partie */
        /*-----------------------------*/
        /* Horizontale */
        GroupLayout panelCreerPartieLayout = new GroupLayout(this.panelCreerPartie);
        this.panelCreerPartie.setLayout(panelCreerPartieLayout);
        panelCreerPartieLayout.setHorizontalGroup(
            panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblCreerPartie, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCreerPartieLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.btnImportMappe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.txtMdpCreer))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, panelCreerPartieLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(this.btnCreerSolo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.btnCreerMulti, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        /* Verticale */
        panelCreerPartieLayout.setVerticalGroup(
            panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelCreerPartieLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(this.lblCreerPartie, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(this.btnImportMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(this.txtMdpCreer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(panelCreerPartieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.btnCreerMulti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnCreerSolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        

        /*---------------------------------*/
        /* panel pour rejoindre une partie */
        /*---------------------------------*/
        /* Horizontale */
        GroupLayout panelRejoindrePartieLayout = new GroupLayout(this.panelRejoindrePartie);
        this.panelRejoindrePartie.setLayout(panelRejoindrePartieLayout);
        panelRejoindrePartieLayout.setHorizontalGroup(
            panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblRejoindrePartie, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRejoindrePartieLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.txtMdpRejoindre, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(this.txtIpRejoindre)
                    .addComponent(this.btnRejoindre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        /* Verticale */
        panelRejoindrePartieLayout.setVerticalGroup(
            panelRejoindrePartieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelRejoindrePartieLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(this.lblRejoindrePartie, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(this.txtIpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(this.txtMdpRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(this.btnRejoindre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        // test
        this.lblImgTitre.getAccessibleContext().setAccessibleName("lblImgTitre");

        /*===========================*/
        /* Activation des composants */
        /*===========================*/
        /* panel pour creer une partie */
        this.btnImportMappe.addActionListener(this);
        this.btnCreerMulti .addActionListener(this);

        /* panel pour rejoindre une partie */
        this.btnRejoindre.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton btn = (JButton) e.getSource();

            if (btn == this.btnImportMappe)
            {
                JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier XML", "xml"));

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
				{
					File fichier = chooser.getSelectedFile();
					String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

					if (extention.equals("xml"))
						this.ctrl.ouvrir(fichier);
					else
						JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format XML", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            }
            else if (btn == this.btnCreerMulti)
            {
                //this.ctrl.creerPartie(this.txtMdpCreer.getText());
            }
            else if (btn == this.btnRejoindre)
            {
                //this.ctrl.rejoindrePartie(this.txtIpRejoindre.getText(), this.txtMdpRejoindre.getText());
            }
        }
    }


    /**
     * Permet d'appliquer le thème au panel et à tous ses composants.
     */
    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background       = theme.get("background").get(0);
        Color titleForeColor   = theme.get("titles"    ).get(0);
        Color titleBackColor   = theme.get("titles"    ).get(1);
        Color saisiForeColor   = theme.get("saisies"   ).get(0);
		Color saisiBackColor   = theme.get("saisies"   ).get(1);
        Color placeholderColor = theme.get("saisies"   ).get(2);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


        this.setForeground(saisiForeColor);
        this.setBackground(background    );


        /*-----------------*/
        /* Titre et pseudo */
        /*-----------------*/
        /* Titre */
        this.lblImgTitre.setHorizontalAlignment(JLabel.CENTER);
        this.lblImgTitre.setSize(new Dimension((int) this.dimFrame.getWidth()-10, this.bfImgTitre.getHeight()));
        this.lblImgTitre.setRequestFocusEnabled(false);

        /* Foreground */
        this.lblPseudo.setForeground(saisiForeColor);
        this.txtPseudo.setForeground(placeholderColor);

        /* Placeholder */
        this.txtPseudo.setForegroundColor (saisiForeColor  );
        this.txtPseudo.setPlaceholderColor(placeholderColor);

        /* Background */
        this.lblPseudo.setOpaque(false);
        this.txtPseudo.setBackground(saisiBackColor);

        /* Border */
        this.lblPseudo.setBorder(null);
        this.txtPseudo.setBorder(null);

        /* Alignement */
        this.lblImgTitre .setHorizontalAlignment(JLabel    .CENTER);
        this.lblPseudo.setHorizontalAlignment(JLabel    .CENTER);
        this.txtPseudo.setHorizontalAlignment(JTextField.CENTER);



        /*-----------------------*/
        /* Création d'une partie */
        /*-----------------------*/
        /* Foreground */
        this.panelCreerPartie.setForeground(saisiForeColor  );

        this.lblCreerPartie  .setForeground(titleForeColor  );
        this.lblCreerPartie  .setFont(new Font("Liberation Sans", 0, 36));

        this.btnImportMappe  .setForeground(btnForeColor    );
        this.txtMdpCreer     .setForeground(placeholderColor);
        this.btnCreerMulti   .setForeground(btnForeColor    );
        this.btnCreerSolo    .setForeground(btnForeColor    );

        /* Placeholder */
        this.txtMdpCreer.setForegroundColor (saisiForeColor  );
        this.txtMdpCreer.setPlaceholderColor(placeholderColor);

        /* Background */
        this.panelCreerPartie.setBackground(titleBackColor);
        this.lblCreerPartie  .setOpaque(false);
        this.btnImportMappe  .setBackground(btnBackColor         );
        this.txtMdpCreer     .setBackground(saisiBackColor       );
        this.btnCreerMulti   .setBackground(btnBackColor         );
        this.btnCreerSolo    .setBackground(btnBackColor         );

        /* Border */
        this.lblCreerPartie.setBorder(null);
        this.btnImportMappe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.txtMdpCreer   .setBorder(null);
        this.btnCreerMulti .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCreerSolo  .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        /* Alignement */
        this.lblCreerPartie.setHorizontalAlignment(JLabel    .CENTER);
        this.btnImportMappe.setHorizontalAlignment(JButton   .CENTER);
        this.txtMdpCreer   .setHorizontalAlignment(JTextField.CENTER);
        this.btnCreerMulti .setHorizontalAlignment(JButton   .CENTER);
        this.btnCreerSolo  .setHorizontalAlignment(JButton   .CENTER);



        /*----------------------*/
        /* Rejoindre une partie */
        /*----------------------*/
        /* Foreground */
        this.panelRejoindrePartie.setForeground(saisiForeColor  );
        lblRejoindrePartie.setFont(new Font("Liberation Sans", 0, 36));
        this.lblRejoindrePartie  .setForeground(titleForeColor  );
        this.txtIpRejoindre      .setForeground(placeholderColor);
        this.txtMdpRejoindre     .setForeground(placeholderColor);
        this.btnRejoindre        .setForeground(btnForeColor    );

        /* Placeholder */
        this.txtIpRejoindre .setForegroundColor(saisiForeColor);
        this.txtMdpRejoindre.setForegroundColor(saisiForeColor);

        this.txtIpRejoindre .setPlaceholderColor(placeholderColor);
        this.txtMdpRejoindre.setPlaceholderColor(placeholderColor);

        /* Background */
        this.panelRejoindrePartie.setBackground(titleBackColor);
        this.lblRejoindrePartie  .setOpaque(false);
        this.txtIpRejoindre      .setBackground(saisiBackColor);
        this.txtMdpRejoindre     .setBackground(saisiBackColor);
        this.btnRejoindre        .setBackground(btnBackColor  );

        /* Border */
        this.lblRejoindrePartie.setBorder(null);
        this.txtIpRejoindre    .setBorder(null);
        this.txtMdpRejoindre   .setBorder(null);
        this.btnRejoindre      .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        /* Alignement */
        this.lblRejoindrePartie.setHorizontalAlignment(JLabel    .CENTER);
        this.txtIpRejoindre    .setHorizontalAlignment(JTextField.CENTER);
        this.txtMdpRejoindre   .setHorizontalAlignment(JTextField.CENTER);
        this.btnRejoindre      .setHorizontalAlignment(JButton   .CENTER);
    }
}




/*
public class PanelPartie extends JPanel {

    public PanelPartie()
    {



        txtIpRejoindre.setHorizontalAlignment(JTextField.CENTER);
        txtIpRejoindre.setText("IP de la partie");

        txtMdpRejoindre.setHorizontalAlignment(JTextField.CENTER);
        txtMdpRejoindre.setText("Mot de passe de la partie");
        txtMdpRejoindre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMdpRejoindreActionPerformed(evt);
            }
        });

        btnRejoindre.setText("Rejoindre");
        btnRejoindre.setAlignmentY(0.0F);
        btnRejoindre.setMaximumSize(new java.awt.Dimension(150, 24));
        btnRejoindre.setMinimumSize(new java.awt.Dimension(150, 24));
        btnRejoindre.setPreferredSize(new java.awt.Dimension(150, 24));
        btnRejoindre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejoindreActionPerformed(evt);
            }
        });

        // layout rejoindre une partie

        txtIpRejoindre.getAccessibleContext().setAccessibleName("");

        lblPseudo.setText("Comment vous appelez-vous ?");

        txtPseudo.setText("sans nom");
        txtPseudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPseudoActionPerformed(evt);
            }
        });

        // layout titre et pseudo

        
    }// </editor-fold>
}
*/