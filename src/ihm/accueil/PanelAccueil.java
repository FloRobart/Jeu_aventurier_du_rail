package ihm.accueil;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

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

    /* Titre et pseudo */
    private JLabel            lblTitre;
    private JLabel            lblPseudo;
    private TextFieldWithHint txtPseudo;

    /* Panel qui permet de créer une partie avec tout les élements qu'il contient */
    private JPanel               panelCreerPartie;
    private JLabel               lblCreerPartie;
    private JButton              btnImporterMappe;
    private TextFieldOnlyInteger txtMdpCreerPartie;
    private JButton              btnCreerPartie;

    /* Panel qui permet de rejoindre une partie avec tout les élements qu'il contient */
    private JPanel               panelRejoindrePartie;
    private JLabel               lblRejoindrePartie;
    private TextFieldWithHint    txtIpRejoindrePartie;
    private TextFieldOnlyInteger txtMdpRejoindrePartie;
    private JButton              btnRejoindrePartie;



    public PanelAccueil(Controleur ctrl)
    {
        this.ctrl = ctrl;


        /*=========================*/
        /* Création des composants */
        /*=========================*/

        /* Titre et pseudo */
        this.lblTitre  = new JLabel(new ImageIcon("Raptor.PNG"));
        this.lblPseudo = new JLabel("Comment vous appelez-vous ? ");
        this.txtPseudo = new TextFieldWithHint("sans nom", this.ctrl);


        /* panel pour creer une partie */
        this.panelCreerPartie  = new JPanel              (new GridLayout(4, 1, 30, 50));
        this.lblCreerPartie    = new JLabel              ("Créer une partie"          );
        this.btnImporterMappe  = new JButton             ("Importer une mappe"        );
        this.txtMdpCreerPartie = new TextFieldOnlyInteger("0000", this.ctrl           );
        this.btnCreerPartie    = new JButton             ("Créer la partie"           );

        /* panel pour rejoindre une partie */
        this.panelRejoindrePartie  = new JPanel              (new GridLayout(4, 1, 30, 50));
        this.lblRejoindrePartie    = new JLabel              ("Rejoindre une partie"      );
        this.txtIpRejoindrePartie  = new TextFieldWithHint   ("IP"  , this.ctrl           );
        this.txtMdpRejoindrePartie = new TextFieldOnlyInteger("0000", this.ctrl           );
        this.btnRejoindrePartie    = new JButton             ("Rejoindre la partie"       );



        /*======================*/
        /* Ajout des composants */
        /*======================*/
        /* Titre et pseudo */
        this.add(this.lblTitre );
        this.add(this.lblPseudo);
        this.add(this.txtPseudo);

        /* panel pour creer une partie */
        this.panelCreerPartie.add(this.lblCreerPartie   );
        this.panelCreerPartie.add(this.btnImporterMappe );
        this.panelCreerPartie.add(this.txtMdpCreerPartie);
        this.panelCreerPartie.add(this.btnCreerPartie   );

        /* panel pour rejoindre une partie */
        this.panelRejoindrePartie.add(this.lblRejoindrePartie   );
        this.panelRejoindrePartie.add(this.txtIpRejoindrePartie );
        this.panelRejoindrePartie.add(this.txtMdpRejoindrePartie);
        this.panelRejoindrePartie.add(this.btnRejoindrePartie   );

        /* Ajout des deux panels au panel principale */
        this.panelCreerPartie    .setBounds(this.getWidth()/8                                                 , 0, this.panelCreerPartie    .getPreferredSize().width, this.panelCreerPartie    .getPreferredSize().height);
        this.panelRejoindrePartie.setBounds((this.getWidth()/8)+this.panelCreerPartie.getPreferredSize().width, 0, this.panelRejoindrePartie.getPreferredSize().width, this.panelRejoindrePartie.getPreferredSize().height);

        this.add(this.panelCreerPartie   );
        this.add(this.panelRejoindrePartie);



        /*===========================*/
        /* Activation des composants */
        /*===========================*/
        /* panel pour creer une partie */
        this.btnImporterMappe.addActionListener(this);
        this.btnCreerPartie   .addActionListener(this);

        /* panel pour rejoindre une partie */
        this.btnRejoindrePartie.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton btn = (JButton) e.getSource();

            if (btn == this.btnImporterMappe)
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
            else if (btn == this.btnCreerPartie)
            {
                //this.ctrl.creerPartie(this.txtMdpCreerPartie.getText());
            }
            else if (btn == this.btnRejoindrePartie)
            {
                //this.ctrl.rejoindrePartie(this.txtIpRejoindrePartie.getText(), this.txtMdpRejoindrePartie.getText());
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
        this.lblTitre .setHorizontalAlignment(JLabel    .CENTER);
        this.lblPseudo.setHorizontalAlignment(JLabel    .CENTER);
        this.txtPseudo.setHorizontalAlignment(JTextField.CENTER);



        /*-----------------------*/
        /* Création d'une partie */
        /*-----------------------*/
        /* Foreground */
        this.panelCreerPartie .setForeground(saisiForeColor  );
        this.lblCreerPartie   .setForeground(titleForeColor  );
        this.btnImporterMappe .setForeground(btnForeColor    );
        this.txtMdpCreerPartie.setForeground(placeholderColor);
        this.btnCreerPartie   .setForeground(btnForeColor    );

        /* Placeholder */
        this.txtMdpCreerPartie.setForegroundColor (saisiForeColor  );
        this.txtMdpCreerPartie.setPlaceholderColor(placeholderColor);

        /* Background */
        this.panelCreerPartie .setBackground(background.brighter());
        this.lblCreerPartie   .setOpaque(false);
        this.btnImporterMappe .setBackground(btnBackColor         );
        this.txtMdpCreerPartie.setBackground(saisiBackColor       );
        this.btnCreerPartie   .setBackground(btnBackColor         );

        /* Border */
        this.lblCreerPartie   .setBorder(null);
        this.btnImporterMappe .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.txtMdpCreerPartie.setBorder(null);
        this.btnCreerPartie   .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        /* Alignement */
        this.lblCreerPartie   .setHorizontalAlignment(JLabel    .CENTER);
        this.btnImporterMappe .setHorizontalAlignment(JButton   .CENTER);
        this.txtMdpCreerPartie.setHorizontalAlignment(JTextField.CENTER);
        this.btnCreerPartie   .setHorizontalAlignment(JButton   .CENTER);



        /*----------------------*/
        /* Rejoindre une partie */
        /*----------------------*/
        /* Foreground */
        this.panelRejoindrePartie .setForeground(saisiForeColor  );
        this.lblRejoindrePartie   .setForeground(titleForeColor  );
        this.txtIpRejoindrePartie .setForeground(placeholderColor);
        this.txtMdpRejoindrePartie.setForeground(placeholderColor);
        this.btnRejoindrePartie   .setForeground(btnForeColor    );

        /* Placeholder */
        this.txtIpRejoindrePartie .setForegroundColor(saisiForeColor);
        this.txtMdpRejoindrePartie.setForegroundColor(saisiForeColor);

        this.txtIpRejoindrePartie .setPlaceholderColor(placeholderColor);
        this.txtMdpRejoindrePartie.setPlaceholderColor(placeholderColor);

        /* Background */
        this.panelRejoindrePartie .setBackground(background.brighter());
        this.lblRejoindrePartie   .setOpaque(false);
        this.txtIpRejoindrePartie .setBackground(saisiBackColor       );
        this.txtMdpRejoindrePartie.setBackground(saisiBackColor       );
        this.btnRejoindrePartie   .setBackground(btnBackColor         );

        /* Border */
        this.lblRejoindrePartie   .setBorder(null);
        this.txtIpRejoindrePartie .setBorder(null);
        this.txtMdpRejoindrePartie.setBorder(null);
        this.btnRejoindrePartie   .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        /* Alignement */
        this.lblRejoindrePartie   .setHorizontalAlignment(JLabel    .CENTER);
        this.txtIpRejoindrePartie .setHorizontalAlignment(JTextField.CENTER);
        this.txtMdpRejoindrePartie.setHorizontalAlignment(JTextField.CENTER);
        this.btnRejoindrePartie   .setHorizontalAlignment(JButton   .CENTER);
    }
}
