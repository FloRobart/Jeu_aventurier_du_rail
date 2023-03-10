package ihm.accueil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDialog;

import controleur.Controleur;

import ihm.aide.PanelAidePreJeu;
import ihm.aide.PanelAideJeu;

public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	private PanelAidePreJeu panelAideMenu;
	private PanelAideJeu panelAideJeu;

	private JMenu menuPreferences;
	private JMenu menuAide;

	private JMenuItem menuiPreferencesThemes;
	private JMenuItem menuiPreferencesThemesClair;
	private JMenuItem menuiPreferencesThemesSombre;
	private JMenuItem menuiPreferencesThemesDark;

	private JMenuItem menuiAideMenu;
	private JMenuItem menuiAideJeu;

	private JDialog dialogAideMenu;
	private JDialog dialogAideJeu;

	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		this.dialogAideMenu = null;
		this.dialogAideJeu  = null;

        this.panelAideMenu  = null;
		this.panelAideJeu   = null;

		/*=========================*/
		/* Création des composants */
		/*=========================*/
		/*------------*/
		/* Préférence */
		/*------------*/
		this.menuPreferences = new JMenu("Préférences");
		this.menuPreferences.setMnemonic('P');

		/* Thèmes */
		this.menuiPreferencesThemes       = new JMenu    ("Thèmes ");

		/* Clair, Sombre, Dark */
		this.menuiPreferencesThemesClair  = new JMenuItem("Clair" );
		this.menuiPreferencesThemesSombre = new JMenuItem("Sombre");
		this.menuiPreferencesThemesDark   = new JMenuItem("Dark"  );


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide = new JMenu("Aide");
		this.menuAide.setMnemonic('A');

		this.menuiAideMenu = new JMenuItem("Tutoriel Menu"  );
		this.menuiAideJeu  = new JMenuItem("Tutoriel Jeu"  );

		/*=======================*/
		/* Ajouts des composants */
		/*=======================*/
		/*------------*/
		/* Préférence */
		/*------------*/
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesClair);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesSombre);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesDark);
		this.menuPreferences       .add(this.menuiPreferencesThemes);
		this.add(menuPreferences);

		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.add(this.menuiAideMenu);
		this.menuAide.add(this.menuiAideJeu);
		this.add(menuAide);

		/*============================*/
		/* Activations des composants */
		/*============================*/
		this.menuiPreferencesThemesClair .addActionListener(this);
		this.menuiPreferencesThemesSombre.addActionListener(this);
		this.menuiPreferencesThemesDark  .addActionListener(this);
		this.menuiAideMenu               .addActionListener(this);
		this.menuiAideJeu                .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			if (e.getSource() == this.menuiPreferencesThemesClair)
				this.ctrl.changerTheme("clair");
			
			if (e.getSource() == this.menuiPreferencesThemesSombre)
				this.ctrl.changerTheme("sombre");

			if (e.getSource() == this.menuiPreferencesThemesDark)
				this.ctrl.changerTheme("dark");


			if (e.getSource() == this.menuiAideMenu)
			{
				/* Création du panel */
				if (this.panelAideMenu == null) { this.panelAideMenu = new PanelAidePreJeu(this.ctrl); }

				/* Création du JDialog */
				if (this.dialogAideMenu == null || this.dialogAideMenu.getTitle().toLowerCase().contains("jeu"))
				{
					this.dialogAideMenu = new JDialog();

					this.dialogAideMenu.setSize(1500,800);
					this.dialogAideMenu.setTitle("Aide Accueil");
					this.dialogAideMenu.setLocation(10, 10);
					this.dialogAideMenu.setResizable(true);
					this.dialogAideMenu.add(this.panelAideMenu);
					this.dialogAideMenu.pack();
					this.dialogAideMenu.setVisible(true);
				}
				else
				{
					this.dialogAideMenu.setVisible(true);
				}
			}

			if (e.getSource() == this.menuiAideJeu)
			{
				/* Création du panel */
				if (this.panelAideJeu == null) { this.panelAideJeu = new PanelAideJeu(this.ctrl); }

				/* Création du JDialog */
				if (this.dialogAideJeu == null || this.dialogAideJeu.getTitle().toLowerCase().contains("menu"))
				{
					this.dialogAideJeu = new JDialog();

					
					this.dialogAideJeu.setTitle("Aide jeu");
					this.dialogAideJeu.setLocation(10, 10);
					this.dialogAideJeu.add(this.panelAideJeu);
					this.dialogAideJeu.pack();
					this.dialogAideJeu.setVisible(true);
				}
				else
				{
					this.dialogAideJeu.setVisible(true);
				}
			}
		}
	}

	
	/**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
		Color foregroundColor = this.ctrl.getTheme().get("menuBar").get(0);
		Color backgroundColor = this.ctrl.getTheme().get("menuBar").get(1);

		/*-------------------------*/
		/* La Menu Barre elle même */
		/*-------------------------*/
		this.setForeground(foregroundColor);
		this.setBackground(backgroundColor);

		/*------------*/
		/* Préférence */
		/*------------*/
		/* Préférence */
		this.menuPreferences.setForeground(foregroundColor);
		this.menuPreferences.setBackground(backgroundColor);

		/* Thèmes */
		this.menuiPreferencesThemes      .setOpaque(true);
		this.menuiPreferencesThemes      .setForeground(foregroundColor);
		this.menuiPreferencesThemes      .setBackground(backgroundColor);

		/* Clair */
		this.menuiPreferencesThemesClair .setForeground(foregroundColor);
		this.menuiPreferencesThemesClair .setBackground(backgroundColor);

		/* Sombre */
		this.menuiPreferencesThemesSombre.setForeground(foregroundColor);
		this.menuiPreferencesThemesSombre.setBackground(backgroundColor);

		/* Dark */
		this.menuiPreferencesThemesDark.setForeground(foregroundColor);
		this.menuiPreferencesThemesDark.setBackground(backgroundColor);


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.setForeground(foregroundColor);
		this.menuAide.setBackground(backgroundColor);

		this.menuiAideMenu.setForeground(foregroundColor);
		this.menuiAideMenu.setBackground(backgroundColor);

		this.menuiAideJeu.setForeground(foregroundColor);
		this.menuiAideJeu.setBackground(backgroundColor);

		if (this.panelAideMenu != null)
            this.panelAideMenu.appliquerTheme();

		if (this.panelAideJeu != null)
            this.panelAideJeu.appliquerTheme();
	}
}