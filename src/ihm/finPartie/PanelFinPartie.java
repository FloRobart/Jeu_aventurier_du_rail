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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;


public class PanelFinPartie extends JPanel implements ActionListener
{
    Controleur ctrl;

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
        scrollPaneRes = new JScrollPane();

        /* Bouton */
        btnQuitter = new JButton();

        /* Labels */
        lblTitre = new JLabel();
        lblRoute = new JLabel();



        /*------------*/
        /* ScrollPane */
        /*------------*/
        scrollPaneRes.setMaximumSize  (new Dimension(500, 200));
        scrollPaneRes.setMinimumSize  (new Dimension(500, 200));
        scrollPaneRes.setPreferredSize(new Dimension(500, 200));

        scrollPaneRes.add(this.PanelResultat);
/*
        GroupLayout PanelResultatLayout = new GroupLayout(PanelResultat);
        PanelResultat.setLayout(PanelResultatLayout);
        PanelResultatLayout.setHorizontalGroup(
            PanelResultatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        PanelResultatLayout.setVerticalGroup(
            PanelResultatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scrollPaneRes.setViewportView(PanelResultat);
*/

        /*--------*/
        /* Bouton */
        /*--------*/
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(this);
        btnQuitter.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


        /*--------*/
        /* Labels */
        /*--------*/
        /* Label Titre */
        lblTitre.setFont(new Font("Liberation Sans", 0, 36));
        lblTitre.setHorizontalAlignment(JLabel.CENTER);
        lblTitre.setText("VICTOIRE DE ...");
        lblTitre.setMaximumSize  (new Dimension(400, 50));
        lblTitre.setMinimumSize  (new Dimension(400, 50));
        lblTitre.setPreferredSize(new Dimension(400, 50));

        /* Label Route */
        lblRoute.setHorizontalAlignment(JLabel.CENTER);
        lblRoute.setText("Route la plus longue : " + "" + " (+10 points)");
        lblRoute.setFocusable(false);
        lblRoute.setMaximumSize  (new Dimension(400, 18));
        lblRoute.setMinimumSize  (new Dimension(400, 18));
        lblRoute.setPreferredSize(new Dimension(400, 18));



        /*--------*/
        /* Layout */
        /*--------*/

        /* Horizontale */
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblTitre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRoute, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(scrollPaneRes, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == this.btnQuitter )
        {
            this.ctrl.disposeFrameFinPartie();
            this.ctrl.disposeFrameJeu();
        }
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



        /* Panel */
        this.PanelResultat.appliquerTheme();

        /* ScrollPane */
        this.scrollPaneRes.getHorizontalScrollBar().setBackground(background);
        this.scrollPaneRes.getVerticalScrollBar  ().setBackground(background);

        /* Bouton */
        this.btnQuitter.setForeground(btnForeColor);
        this.btnQuitter.setBackground(btnBackColor);

        /* Labels */
        lblTitre.setForeground(labelForeColor);
        lblTitre.setOpaque(false);

        lblRoute.setForeground(labelForeColor);
        lblRoute.setOpaque(false);
    }
}

