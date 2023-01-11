package ihm.finPartie;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;

import controleur.Controleur;
import metier.Joueur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;


public class PanelFinPartie extends JPanel implements ActionListener
{
    private static final int WIDTH = 1200;


    private Controleur ctrl;

    private boolean fermerFrame;

    /* Panel */
    private PanelResultat PanelResultat;

    /* ScrollPane */
    private JScrollPane scrollPaneRes;

    /* Bouton */
    private JButton btnQuitter;

    /* Labels */
    private JLabel lblRoute;
    private JLabel lblTitre;


    public PanelFinPartie(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Panel */
        PanelResultat = new PanelResultat(ctrl);

        /* ScrollPane */
        scrollPaneRes = new JScrollPane(this.PanelResultat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        /* Bouton */
        btnQuitter = new JButton();

        /* Labels */
        lblTitre = new JLabel();
        lblRoute = new JLabel();



        /*------------*/
        /* ScrollPane */
        /*------------*/
        scrollPaneRes.setMaximumSize  (new Dimension(PanelFinPartie.WIDTH, 200));
        scrollPaneRes.setMinimumSize  (new Dimension(PanelFinPartie.WIDTH, 200));
        scrollPaneRes.setPreferredSize(new Dimension(PanelFinPartie.WIDTH, 200));


        /*--------*/
        /* Bouton */
        /*--------*/
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(this);
        btnQuitter.setSize(200, 50);
        btnQuitter.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        /*--------*/
        /* Labels */
        /*--------*/
        /* Label Titre */
        lblTitre.setFont(new Font("Liberation Sans", 0, 36));
        lblTitre.setHorizontalAlignment(JLabel.CENTER);
        lblTitre.setText("VICTOIRE DE " + this.getVainqueur().getNom() + " !");
        lblTitre.setMaximumSize  (new Dimension(PanelFinPartie.WIDTH, 50));
        lblTitre.setMinimumSize  (new Dimension(PanelFinPartie.WIDTH, 50));
        lblTitre.setPreferredSize(new Dimension(PanelFinPartie.WIDTH, 50));

        /* Label Route */
        lblRoute.setHorizontalAlignment(JLabel.CENTER);
        lblRoute.setText("Route la plus longue : " + "" + " (+10 points)");
        lblRoute.setFocusable(false);
        lblRoute.setMaximumSize  (new Dimension(PanelFinPartie.WIDTH, 18));
        lblRoute.setMinimumSize  (new Dimension(PanelFinPartie.WIDTH, 18));
        lblRoute.setPreferredSize(new Dimension(PanelFinPartie.WIDTH, 18));



        /*--------*/
        /* Layout */
        /*--------*/
        int gapButton = ((PanelFinPartie.WIDTH+100) /2) - (this.btnQuitter.getWidth() / 2);
        /* Horizontale */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblTitre, GroupLayout.DEFAULT_SIZE, PanelFinPartie.WIDTH, Short.MAX_VALUE)
            .addComponent(lblRoute, GroupLayout.DEFAULT_SIZE, PanelFinPartie.WIDTH, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(gapButton, gapButton, gapButton)
                .addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, btnQuitter.getWidth(), GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(scrollPaneRes, GroupLayout.PREFERRED_SIZE, PanelFinPartie.WIDTH, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        /* Verticale */
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitre, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRoute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneRes, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, btnQuitter.getHeight(), GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnQuitter )
        {
            this.fermerFrame = true;
            this.ctrl.disposeFrameFinPartie();
            this.ctrl.disposeFrameJeu();
        }
    }


    public boolean getFermerFrame()
    {
        return this.fermerFrame;
    }


    public Joueur getVainqueur()
    {
        return this.PanelResultat.getVainqueur();
    }

    /**
     * Applique les couleurs du thème sélectionné à tout les éléments du panel et au panel lui même
     */
    public void appliquerTheme()
    {
        Color background       = this.ctrl.getTheme().get("background"  ).get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"      ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("buttons"     ).get(0);
        Color btnBackColor     = this.ctrl.getTheme().get("buttons"     ).get(1);


        this.setForeground(labelForeColor);
        this.setBackground(background);

        /* Panel */
        this.PanelResultat.appliquerTheme();

        /* ScrollPane */
        this.scrollPaneRes.getVerticalScrollBar  ().setBackground(background);

        /* Bouton */
        this.btnQuitter.setForeground(btnForeColor);
        this.btnQuitter.setBackground(btnBackColor);

        /* Labels */
        lblTitre.setOpaque(false);
        lblTitre.setForeground(labelForeColor);

        lblRoute.setOpaque(false);
        lblRoute.setForeground(labelForeColor);
    }
}

