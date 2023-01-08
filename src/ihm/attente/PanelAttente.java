package ihm.attente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import controleur.Controleur;


public class PanelAttente extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private HashMap<String, List<Color>> theme;

    /* Panel */
    private JPanel panelInfo;
    private JPanel panelBtn;

    /* Boutons */
    private JButton btnChangeMappe;
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
    private JLabel lblPreviewMappe;
    private JLabel lblStatut;
    private JLabel lblTitre;

    /* Listes */
    private JScrollPane  jspLstParticipants;
    private List<JLabel> lstParticipants;
    

    public PanelAttente(Controleur ctrl)
    {
        this.ctrl  = ctrl;
        this.theme = this.ctrl.getTheme();

        /* Panel */
        this.panelInfo          = new JPanel();
        this.panelBtn           = new JPanel();

        /* Boutons */
        this.btnChangeMappe     = new JButton();
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
        this.lblPreviewMappe    = new JLabel();
        this.lblStatut          = new JLabel();
        this.lblTitre           = new JLabel();

        /* Listes */
        this.jspLstParticipants = new JScrollPane      ();
        this.lstParticipants    = new ArrayList<JLabel>();
        


        this.lblTitre.setText("this.lblTitre");
        this.lblTitre.setHorizontalAlignment(JLabel.CENTER);
        this.lblTitre.setMaximumSize  (new Dimension(43, 80));
        this.lblTitre.setMinimumSize  (new Dimension(43, 80));
        this.lblTitre.setPreferredSize(new Dimension(43, 80));


        this.btnChangeMappe.setText("btnChangerMappe");
        this.btnChangeMappe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnChangeMappe.setMaximumSize  (new Dimension(150, 30));
        this.btnChangeMappe.setMinimumSize  (new Dimension(150, 30));
        this.btnChangeMappe.setPreferredSize(new Dimension(150, 30));


        this.lblPreviewMappe.setOpaque(true);
        this.lblPreviewMappe.setIcon(new ImageIcon("./bin/donnees/images/Titre_ADR.png")); // il faut adapter la taille de l'image
        this.lblPreviewMappe.setHorizontalAlignment(JLabel.CENTER);
        this.lblPreviewMappe.setMaximumSize  (new Dimension(400, 250));
        this.lblPreviewMappe.setMinimumSize  (new Dimension(400, 250));
        this.lblPreviewMappe.setPreferredSize(new Dimension(400, 250));


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

        this.lblJoueurMinRes.setText(": 2");
        this.lblJoueurMinRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJoueurMinRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJoueurMinRes.setPreferredSize(new Dimension(40, 18));

        this.lblJoueurMaxRes.setText(": 4");
        this.lblJoueurMaxRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJoueurMaxRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJoueurMaxRes.setPreferredSize(new Dimension(40, 18));

        this.lblCarteCoulRes.setText(": 30");
        this.lblCarteCoulRes.setMaximumSize  (new Dimension(40, 18));
        this.lblCarteCoulRes.setMinimumSize  (new Dimension(40, 18));
        this.lblCarteCoulRes.setPreferredSize(new Dimension(40, 18));

        this.lblCarteLocoRes.setText(": 35");
        this.lblCarteLocoRes.setMaximumSize  (new Dimension(40, 18));
        this.lblCarteLocoRes.setMinimumSize  (new Dimension(40, 18));
        this.lblCarteLocoRes.setPreferredSize(new Dimension(40, 18));

        this.lblJetonJoueurRes.setText(": 50");
        this.lblJetonJoueurRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJetonJoueurRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJetonJoueurRes.setPreferredSize(new Dimension(40, 18));

        this.lblJetonFinRes.setText(": 2");
        this.lblJetonFinRes.setMaximumSize  (new Dimension(40, 18));
        this.lblJetonFinRes.setMinimumSize  (new Dimension(40, 18));
        this.lblJetonFinRes.setPreferredSize(new Dimension(40, 18));

        this.lblCoulDispoRes.setText(": 4");
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



        this.lblLstParticipants.setText("Liste des joueurs");
        this.lblLstParticipants.setHorizontalAlignment(JLabel.CENTER);
        this.lblLstParticipants.setFont(new Font("Liberation Sans", 0, 24));


        /* Boutons des joueurs */
        for (int i = 0; i < this.ctrl.getNbJoueursMax(); i++)
        {
            this.lstParticipants.add(new JLabel("Joueur " + (i+1)));
            this.lstParticipants.get(i).setFont(new Font("Liberation Sans", 0, 24));
            this.lstParticipants.get(i).setPreferredSize(new Dimension(200, 40));
            this.lstParticipants.get(i).setOpaque(true);
            this.lstParticipants.get(i).setHorizontalAlignment(JLabel.CENTER);
            this.lstParticipants.get(i).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }

        this.panelBtn.setLayout(new GridLayout(this.lstParticipants.size(), 1));
        for (int i = 0; i < this.lstParticipants.size(); i++)
            this.panelBtn.add(this.lstParticipants.get(i));

        this.jspLstParticipants = new JScrollPane(this.panelBtn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jspLstParticipants.setMaximumSize  (new Dimension(200, 250));
        this.jspLstParticipants.setMinimumSize  (new Dimension(200, 250));
        this.jspLstParticipants.setPreferredSize(new Dimension(200, 250));


        this.lblStatut.setText("Statut : en attente de joueur");
        this.lblStatut.setFont(new Font("Liberation Sans", 0, 36));


        this.btnLancer.setText("this.btnLancer");
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
                            .addComponent(this.btnChangeMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(this.lblPreviewMappe, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(this.panelInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(this.lblLstParticipants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(this.jspLstParticipants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(this.btnLancer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(this.lblStatut)))
                .addGap(40, 40, 40))
        );

        /* Verticale */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.lblTitre, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.btnChangeMappe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblLstParticipants))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.panelInfo, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(this.lblPreviewMappe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.jspLstParticipants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(this.lblStatut)
                .addGap(18, 18, 18)
                .addComponent(this.btnLancer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }


    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.theme.get("background"  ).get(0);
        Color disableColor     = this.theme.get("disableColor").get(0);
        Color titleForeColor   = this.theme.get("titles"      ).get(0);
        Color titleBackColor   = this.theme.get("titles"      ).get(1);
        Color labelForeColor   = this.theme.get("labels"      ).get(0);
        Color saisiForeColor   = this.theme.get("saisies"     ).get(0);
		Color saisiBackColor   = this.theme.get("saisies"     ).get(1);
        Color placeholderColor = this.theme.get("saisies"     ).get(2);
        Color btnForeColor     = this.theme.get("buttons"     ).get(0);
		Color btnBackColor     = this.theme.get("buttons"     ).get(1);


        
        
        /*-------*/
        /* Panel */
        /*-------*/
        /* Ce panel */
        this.setBackground(background);
        this.setForeground(labelForeColor);

        /* Panel d'information */
        this.panelInfo         .setBackground(background);


        /*---------*/
        /* Boutons */
        /*---------*/
        /* Liste de boutons pour chaque joueur */
        for (JLabel btn : this.lstParticipants)
        {
            btn.setForeground(btnForeColor);
            btn.setBackground(background);
        }

        /* Bouton changer mappe */
        this.btnChangeMappe    .setForeground(btnForeColor);
        this.btnChangeMappe    .setBackground(btnBackColor);
        
        /* bouton lancer la partie */
        this.btnLancer         .setForeground(btnForeColor);
        this.btnLancer         .setBackground(btnBackColor);


        /*------------*/
        /* ScrollPane */
        /*------------*/
        /* ScrollPane de la liste de participants */
        this.jspLstParticipants.getVerticalScrollBar().setBackground(background);


        /*--------*/
        /* Labels */
        /*--------*/
        /* Background */
        this.lblPreviewMappe   .setBackground(titleBackColor);

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
        this.lblPreviewMappe   .setForeground(labelForeColor);
        this.lblStatut         .setForeground(labelForeColor);
        this.lblTitre          .setForeground(labelForeColor);
    }
}