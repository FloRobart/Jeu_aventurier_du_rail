package ihm.menuBarre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	private JMenu menuFichier;
	private JMenu menuExporterSous;
	private JMenu menuPreferences;
	private JMenu menuAide;

	private JMenuItem menuiFichierNouveau;
	private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierEnregistrer;
	private JMenuItem menuiFichierEnregistrerSous;

	private JMenuItem menuiFichierExporterSousGif;
	private JMenuItem menuiFichierExporterSousPng;
	private JMenuItem menuiFichierExporterSousJpeg;
	private JMenuItem menuiFichierExporterSousJpg;

	private JMenuItem menuiFichierFermer;

	private JMenuItem menuiPreferencesThemes;
	private JMenuItem menuiPreferencesThemesClair;
	private JMenuItem menuiPreferencesThemesSombre;
	private JMenuItem menuiPreferencesThemesDark;


	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;


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
		this.add(menuAide);



		/*============================*/
		/* Activations des composants */
		/*============================*/
		this.menuiPreferencesThemesClair .addActionListener(this);
		this.menuiPreferencesThemesSombre.addActionListener(this);
		this.menuiPreferencesThemesDark  .addActionListener(this);
		this.menuAide                    .addActionListener(this);
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


			if (e.getSource() == this.menuAide)
			{
				/* TODO : Afficher l'aide */
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
	}
}