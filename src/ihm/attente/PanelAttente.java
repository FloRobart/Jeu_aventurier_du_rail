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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class PanelAttente extends JPanel implements ActionListener
{
    private Controleur ctrl;


    public PanelAttente(Controleur ctrl)
    {
        lblTitre = new javax.swing.JLabel();
        btnChangeMappe = new javax.swing.JButton();
        lblPreviewMappe = new javax.swing.JLabel();
        panelInfo = new javax.swing.JPanel();
        lblNombreDe = new javax.swing.JLabel();
        lblJoueurMin = new javax.swing.JLabel();
        lblJoueurMax = new javax.swing.JLabel();
        lblCarteCoul = new javax.swing.JLabel();
        lblCarteLoco = new javax.swing.JLabel();
        lblJetonJoueur = new javax.swing.JLabel();
        lblJetonFin = new javax.swing.JLabel();
        lblCoulDispo = new javax.swing.JLabel();
        lblJoueurMinRes = new javax.swing.JLabel();
        lblJoueurMaxRes = new javax.swing.JLabel();
        lblCarteCoulRes = new javax.swing.JLabel();
        lblCarteLocoRes = new javax.swing.JLabel();
        lblJetonJoueurRes = new javax.swing.JLabel();
        lblJetonFinRes = new javax.swing.JLabel();
        lblCoulDispoRes = new javax.swing.JLabel();
        lblLstParticipants = new javax.swing.JLabel();
        jspLstParticipants = new javax.swing.JScrollPane();
        lstParticipants = new javax.swing.JList<>();
        lblStatut = new javax.swing.JLabel();
        btnLancer = new javax.swing.JButton();

        lblTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitre.setText("lblTitre");
        lblTitre.setMaximumSize(new java.awt.Dimension(43, 80));
        lblTitre.setMinimumSize(new java.awt.Dimension(43, 80));
        lblTitre.setPreferredSize(new java.awt.Dimension(43, 80));

        btnChangeMappe.setText("btnChangerMappe");
        btnChangeMappe.setMaximumSize(new java.awt.Dimension(150, 30));
        btnChangeMappe.setMinimumSize(new java.awt.Dimension(150, 30));
        btnChangeMappe.setPreferredSize(new java.awt.Dimension(150, 30));

        lblPreviewMappe.setBackground(new java.awt.Color(0, 0, 0));
        lblPreviewMappe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreviewMappe.setText("lblPreviewMappe");
        lblPreviewMappe.setMaximumSize(new java.awt.Dimension(400, 250));
        lblPreviewMappe.setMinimumSize(new java.awt.Dimension(400, 250));
        lblPreviewMappe.setOpaque(true);
        lblPreviewMappe.setPreferredSize(new java.awt.Dimension(400, 250));

        panelInfo.setBackground(new java.awt.Color(0, 0, 0));
        panelInfo.setMaximumSize(new java.awt.Dimension(250, 200));
        panelInfo.setMinimumSize(new java.awt.Dimension(250, 200));
        panelInfo.setPreferredSize(new java.awt.Dimension(250, 200));

        lblNombreDe.setText("Nombre de :");

        lblJoueurMin.setText("● Joueurs minimum");
        lblJoueurMin.setMaximumSize(new java.awt.Dimension(150, 18));
        lblJoueurMin.setMinimumSize(new java.awt.Dimension(150, 18));
        lblJoueurMin.setPreferredSize(new java.awt.Dimension(150, 18));

        lblJoueurMax.setText("● Joueurs Maximum");
        lblJoueurMax.setMaximumSize(new java.awt.Dimension(150, 18));
        lblJoueurMax.setMinimumSize(new java.awt.Dimension(150, 18));
        lblJoueurMax.setPreferredSize(new java.awt.Dimension(150, 18));

        lblCarteCoul.setText("● Cartes couleur");
        lblCarteCoul.setMaximumSize(new java.awt.Dimension(150, 18));
        lblCarteCoul.setMinimumSize(new java.awt.Dimension(150, 18));
        lblCarteCoul.setPreferredSize(new java.awt.Dimension(150, 18));

        lblCarteLoco.setText("● Cartes locomotives");
        lblCarteLoco.setMaximumSize(new java.awt.Dimension(150, 18));
        lblCarteLoco.setMinimumSize(new java.awt.Dimension(150, 18));
        lblCarteLoco.setPreferredSize(new java.awt.Dimension(150, 18));

        lblJetonJoueur.setText("● Jetons par joueur");
        lblJetonJoueur.setMaximumSize(new java.awt.Dimension(150, 18));
        lblJetonJoueur.setMinimumSize(new java.awt.Dimension(150, 18));
        lblJetonJoueur.setPreferredSize(new java.awt.Dimension(150, 18));

        lblJetonFin.setText("● Jetons pour finir");
        lblJetonFin.setMaximumSize(new java.awt.Dimension(150, 18));
        lblJetonFin.setMinimumSize(new java.awt.Dimension(150, 18));
        lblJetonFin.setPreferredSize(new java.awt.Dimension(150, 18));

        lblCoulDispo.setText("● Couleurs disponibles");
        lblCoulDispo.setMaximumSize(new java.awt.Dimension(150, 18));
        lblCoulDispo.setMinimumSize(new java.awt.Dimension(150, 18));
        lblCoulDispo.setPreferredSize(new java.awt.Dimension(150, 18));

        lblJoueurMinRes.setText(": 2");
        lblJoueurMinRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblJoueurMinRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblJoueurMinRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblJoueurMaxRes.setText(": 4");
        lblJoueurMaxRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblJoueurMaxRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblJoueurMaxRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblCarteCoulRes.setText(": 30");
        lblCarteCoulRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblCarteCoulRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblCarteCoulRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblCarteLocoRes.setText(": 35");
        lblCarteLocoRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblCarteLocoRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblCarteLocoRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblJetonJoueurRes.setText(": 50");
        lblJetonJoueurRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblJetonJoueurRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblJetonJoueurRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblJetonFinRes.setText(": 2");
        lblJetonFinRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblJetonFinRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblJetonFinRes.setPreferredSize(new java.awt.Dimension(40, 18));

        lblCoulDispoRes.setText(": 4");
        lblCoulDispoRes.setMaximumSize(new java.awt.Dimension(40, 18));
        lblCoulDispoRes.setMinimumSize(new java.awt.Dimension(40, 18));
        lblCoulDispoRes.setPreferredSize(new java.awt.Dimension(40, 18));

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNombreDe))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblJoueurMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJoueurMaxRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblCarteLoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCarteLocoRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblJetonJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJetonJoueurRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblJetonFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJetonFinRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblCoulDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCoulDispoRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblJoueurMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblJoueurMinRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblCarteCoul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCarteCoulRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, 0))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJoueurMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJoueurMinRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJoueurMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJoueurMaxRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarteCoul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarteCoulRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarteLoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarteLocoRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJetonJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJetonJoueurRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJetonFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJetonFinRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCoulDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCoulDispoRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblLstParticipants.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        lblLstParticipants.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLstParticipants.setText("lblLstParticipants");

        jspLstParticipants.setMaximumSize(new java.awt.Dimension(200, 250));
        jspLstParticipants.setMinimumSize(new java.awt.Dimension(200, 250));
        jspLstParticipants.setPreferredSize(new java.awt.Dimension(200, 250));

        lstParticipants.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstParticipants.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstParticipants.setMaximumSize(new java.awt.Dimension(200, 250));
        lstParticipants.setMinimumSize(new java.awt.Dimension(200, 250));
        lstParticipants.setPreferredSize(new java.awt.Dimension(200, 250));
        lstParticipants.setVisibleRowCount(6);
        jspLstParticipants.setViewportView(lstParticipants);

        lblStatut.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        lblStatut.setText("Statut : en attente de joueur");

        btnLancer.setText("btnLancer");
        btnLancer.setMaximumSize(new java.awt.Dimension(150, 40));
        btnLancer.setMinimumSize(new java.awt.Dimension(150, 40));
        btnLancer.setPreferredSize(new java.awt.Dimension(150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChangeMappe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPreviewMappe, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLstParticipants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jspLstParticipants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnLancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblStatut)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChangeMappe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLstParticipants))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(lblPreviewMappe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspLstParticipants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(lblStatut)
                .addGap(18, 18, 18)
                .addComponent(btnLancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnChangeMappe;
    private javax.swing.JButton btnLancer;
    private javax.swing.JScrollPane jspLstParticipants;
    private javax.swing.JLabel lblCarteCoul;
    private javax.swing.JLabel lblCarteCoulRes;
    private javax.swing.JLabel lblCarteLoco;
    private javax.swing.JLabel lblCarteLocoRes;
    private javax.swing.JLabel lblCoulDispo;
    private javax.swing.JLabel lblCoulDispoRes;
    private javax.swing.JLabel lblJetonFin;
    private javax.swing.JLabel lblJetonFinRes;
    private javax.swing.JLabel lblJetonJoueur;
    private javax.swing.JLabel lblJetonJoueurRes;
    private javax.swing.JLabel lblJoueurMax;
    private javax.swing.JLabel lblJoueurMaxRes;
    private javax.swing.JLabel lblJoueurMin;
    private javax.swing.JLabel lblJoueurMinRes;
    private javax.swing.JLabel lblLstParticipants;
    private javax.swing.JLabel lblNombreDe;
    private javax.swing.JLabel lblPreviewMappe;
    private javax.swing.JLabel lblStatut;
    private javax.swing.JLabel lblTitre;
    private javax.swing.JList<String> lstParticipants;
    private javax.swing.JPanel panelInfo;
    // End of variables declaration


    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }

    public void appliquerTheme()
    {
        
    }
}
