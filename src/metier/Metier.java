package metier;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;

import controleur.Controleur;
import metier.reseau.Server;

public class Metier
{
	private static final int TAILLE_TAB_WAGON = 5;
	private static final int TAILLE_TAB_OBJECTIF = 3;

    private Controleur          ctrl;

    private List<Joueur>        lstJoueurs;
    private List<CarteWagon>    lstCartesWagon;
	private List<CarteWagon>    lstDefausseCartesWagon;
    private List<CarteObjectif> lstCartesObjectif;
    private List<Arete>         lstAretes;
    private List<Noeud>         lstNoeuds;

	private CarteWagon[]		tabCarteWagon;
	private CarteObjectif[]		tabCarteObjectif;
    
    private int[]         taillePlateau;
	private BufferedImage imagePlateau;
	private Color         couleurPlateau;
	private Font          policePlateau;

    private int nbJoueursMin;
	private int nbJoueursMax;
	private int nbCarteCoul;
	private int nbCarteLocomotive;
	private int nbJetonJoueur;
	private int nbJetonFin;

    private List<Color>         lstCouleurs;
	private BufferedImage       imageVersoCouleur;
	private BufferedImage       imageRectoLocomotive;
	private List<BufferedImage> lstImagesRectoCouleur;
	private List<Integer>       lstPoints;

	private BufferedImage       imageVersoObjectif;

	private HashMap<String, List<Color>> hmColorThemes;

	private String nomPartie;
	private Server server;



    public Metier(Controleur ctrl)
    {
        this.ctrl = ctrl;

		
		//this.lireFichier(new File("./bin/donnees/France.xml"));
		this.initCartes();
		this.hmColorThemes = new HashMap<String, List<Color>>();
		this.chargerThemes(getThemeUsed());
    }

    public boolean ajouterJoueur(Joueur joueur)
    {
        if(this.lstJoueurs.size() < this.nbJoueursMax)
		{
            this.lstJoueurs.add(joueur);
			return true;
		}

		return false;
    }

	//initialise les decks
	public void initCartes()
	{
		/*this.lstCartesObjectif = new ArrayList<CarteObjectif>();
		Collections.shuffle(this.lstCartesObjectif);

		this.lstCartesWagon = new ArrayList<CarteWagon>();
		Collections.shuffle(this.lstCartesWagon);

		this.lstDefausseCartesWagon = new ArrayList<CarteWagon>();
		
		this.tabCarteWagon = new CarteWagon[TAILLE_TAB_WAGON];
		this.tabCarteObjectif = new CarteObjectif[TAILLE_TAB_OBJECTIF];

		for (int cpt = 0; cpt < this.tabCarteWagon.length; cpt++)
			this.tabCarteWagon[cpt] = this.lstCartesWagon.remove(0);*/
	}
	
	//Permet de choisir parmi les trois cartes objectifs
	public void piocherTabObjectif (boolean[] tabBool, String nomJoueur)
	{
		int cptJoueur = 0;
		for ( Joueur joueur : this.lstJoueurs )
		{
			if ( nomJoueur == joueur.getNom()  )
			{
				for ( int cpt = 0; cpt < tabBool.length; cpt++ )
				{
					if ( tabBool[cpt] == true )
					{
						this.lstJoueurs.get(cptJoueur).ajouterCarteObjectif(this.tabCarteObjectif[cpt]);
					}
					else
					{
						this.lstCartesObjectif.add(this.tabCarteObjectif[cpt]);
					}
				}
			}
			cptJoueur++;
		}
	}

	//Permet de choisir parmi les 5 cartes wagons
	public void piocherTabWagon (int indiceTab, String nomJoueur)
	{
		int cptWagon = 0;

		for ( Joueur joueur : this.lstJoueurs )
		{
			if ( nomJoueur == joueur.getNom()  )
			{
				//if ( this.lstJoueurs.get(cptWagon).aJouer() && this.tabCarteWagon[indiceTab].isJoker() )	/*Creer un attribut dans joueur qui indique si il joue ou pas*/
					//le joueur ne peut plus piocher

				this.lstJoueurs.get(cptWagon).ajouterCarteWagon(this.tabCarteWagon[indiceTab]);
				if ( this.tabCarteWagon[indiceTab].isJoker() )
					//le joueur ne peut plus piocher

				this.tabCarteWagon[indiceTab] = this.lstCartesWagon.remove(0);
			}
			cptWagon++;
		}
	}

	//Permet de piocher dans le deck de wagon, ou de piocher les trois cartes objectifs
	public void piocherDeck (char typeCarte, String nomJoueur)
	{
		switch(typeCarte)
		{
			case 'O':
					for (int cpt = 0; cpt < this.tabCarteObjectif.length; cpt++)
						this.tabCarteObjectif[cpt] = this.lstCartesObjectif.remove(0);
				break;
			
			case 'W': 
				if (this.lstCartesWagon.isEmpty())
				{
					Collections.shuffle(this.lstDefausseCartesWagon);
					this.lstCartesWagon = this.lstDefausseCartesWagon;
				}

				int cptWagon = 0;
				for ( Joueur joueur : this.lstJoueurs )
				{
					if ( nomJoueur == joueur.getNom()  )
					{
						this.lstJoueurs.get(cptWagon).ajouterCarteWagon(this.lstCartesWagon.get(0));
						this.lstDefausseCartesWagon.add(this.lstCartesWagon.remove(0));
					}
					cptWagon++;
				}
				break;
		}
	}


	/* --------------------------- */
	/*          Getters            */
	/* --------------------------- */
	public List<Joueur>        getJoueurs             () { return this.lstJoueurs;           }
	public List<CarteWagon>    getCarteWagon          () { return this.lstCartesWagon;       }
	public List<CarteObjectif> getCarteObjectif       () { return this.lstCartesObjectif;    }
	public List<Noeud>         getNoeuds              () { return this.lstNoeuds;            }
	public List<Arete>         getAretes              () { return this.lstAretes;            }

	public int[]               getTaillePlateau       () { return this.taillePlateau;        }
	public BufferedImage       getImagePlateau        () { return this.imagePlateau;         }
	public Color               getCouleurPlateau      () { return this.couleurPlateau;       }
	public Font                getPolicePlateau       () { return this.policePlateau;        }

	public int                 getNbJoueursMin        () { return this.nbJoueursMin;         }
	public int                 getNbJoueursMax        () { return this.nbJoueursMax;         }
	public int                 getNbCarteCoul         () { return this.nbCarteCoul;          }
	public int                 getNbCarteLocomotive   () { return this.nbCarteLocomotive;    }
	public int                 getNbJetonJoueur       () { return this.nbJetonJoueur;        }
	public int                 getNbJetonFin          () { return this.nbJetonFin;           }

	public List<Color>         getCouleurs            () { return this.lstCouleurs;          }
	public BufferedImage       getImageVersoCouleur   () { return this.imageVersoCouleur;    }
	public BufferedImage       getImageRectoLocomotive() { return this.imageRectoLocomotive; }
	public List<BufferedImage> getImagesRectoCouleur  () { return this.lstImagesRectoCouleur;}
	public List<Integer>       getPoints              () { return this.lstPoints;            }

	public BufferedImage       getImageVersoObjectif  () { return this.imageVersoObjectif;   }
	
	public String 			   getNomPartie           () { return this.nomPartie;            }
	public Server 			   getServer              () { return this.server;               }

    public boolean chargerXML(Reader cs)
	{
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			Document document = sxb.build(cs);

			/* <jeu> */
			Element racine = document.getRootElement();

			/* <information> */
			Element information = racine.getChild("information");

			Element dimension = information.getChild("dimension");
			this.taillePlateau    = new int[2];
			this.taillePlateau[0] = Integer.parseInt(dimension.getAttributeValue("x"));
			this.taillePlateau[1] = Integer.parseInt(dimension.getAttributeValue("y"));
			this.imagePlateau     = this.base64ToImage(information.getChild("image-fond").getText()); 
			this.couleurPlateau   = hexaToColor(information.getChild("couleur-fond").getText());   
			this.policePlateau    = new Font (information.getChild("police").getText(), Font.PLAIN, 12);
			
			Element nbJoueurs = information.getChild("nombre-joueurs");
			this.nbJoueursMin = Integer.parseInt(nbJoueurs.getAttributeValue("min"));
			this.nbJoueursMax = Integer.parseInt(nbJoueurs.getAttributeValue("max"));

			Element nbCarte = information.getChild("nombre-carte");
			this.nbCarteCoul       = Integer.parseInt(nbCarte.getAttributeValue("couleur"));
			this.nbCarteLocomotive = Integer.parseInt(nbCarte.getAttributeValue("multicouleur"));

			Element nbJeton = information.getChild("nombre-jeton");
			this.nbJetonJoueur = Integer.parseInt(nbJeton.getAttributeValue("joueur"));
			this.nbJetonFin    = Integer.parseInt(nbJeton.getAttributeValue("fin"));
			
			Element plateau = racine.getChild("plateau");
			
			/* <liste-lstCouleurs> */
			this.lstCouleurs = new ArrayList<Color>();
			List<Element> listlstCouleurs = plateau.getChild("liste-lstCouleurs").getChildren("couleur");
			Iterator<Element> itlstCouleurs = listlstCouleurs.iterator();
			
			while(itlstCouleurs.hasNext())
			{
				Element couleur = (Element)itlstCouleurs.next();
				this.lstCouleurs.add(Color.decode(couleur.getText()));
			}

			/* <liste-image_cartes> */
			this.imageVersoCouleur = this.base64ToImage(plateau.getChild("liste-image-cartes")
										.getChild("image-verso").getText());

			this.lstImagesRectoCouleur = new ArrayList<BufferedImage>();
			List<Element> listImagesCartes = plateau.getChild("liste-image-cartes").getChildren("image-recto");
			Iterator<Element> itImagesCartes = listImagesCartes.iterator();

			this.imageRectoLocomotive = this.base64ToImage(itImagesCartes.next().getText());
			while(itImagesCartes.hasNext())
			{
				Element imageCarte = (Element)itImagesCartes.next();
				BufferedImage image = this.base64ToImage(imageCarte.getText());
				this.lstImagesRectoCouleur.add(image);
			}
			
			/* <tableau-lstPoints */
			this.lstPoints = new ArrayList<Integer>();
			List<Element> listlstPoints = plateau.getChild("tableau-lstPoints").getChildren("distance");
			Iterator<Element> itlstPoints = listlstPoints.iterator();

			while(itlstPoints.hasNext())
			{
				Element point = (Element)itlstPoints.next();
				this.lstPoints.add(Integer.parseInt(point.getText()));
			}

			/* <liste-lstNoeuds> */
			this.lstNoeuds = new ArrayList<Noeud>();
			Noeud.reinitialiserId();
			List<Element> listlstNoeuds = plateau.getChild("liste-lstNoeuds").getChildren("noeud");
			Iterator<Element> itlstNoeuds = listlstNoeuds.iterator();

			while(itlstNoeuds.hasNext())
			{
				Element noeud = (Element)itlstNoeuds.next();

				Element position = noeud.getChild("position");
				int x = Integer.parseInt(position.getAttributeValue("x"));
				int y = Integer.parseInt(position.getAttributeValue("y"));

				String nom = noeud.getChild("nom").getText();

				Element positionNom = noeud.getChild("position-nom");
				int xNom = Integer.parseInt(positionNom.getAttributeValue("x"));
				int yNom = Integer.parseInt(positionNom.getAttributeValue("y"));

				Color couleur = Color.decode(noeud.getChild("couleur").getText());

				this.lstNoeuds.add(new Noeud(nom, x, y, xNom, yNom, couleur));
			}

			/* <liste-lstAretes> */
			this.lstAretes = new ArrayList<Arete>();
			List<Element> listlstAretes = plateau.getChild("liste-lstAretes").getChildren("arete");
			Iterator<Element> itlstAretes = listlstAretes.iterator();

			while(itlstAretes.hasNext())
			{
				Element arete = (Element)itlstAretes.next();

				Element noeud = arete.getChild("noeud");
				Noeud n1 = this.lstNoeuds.get(Integer.parseInt(noeud.getAttributeValue("n1"))-1);
				Noeud n2 = this.lstNoeuds.get(Integer.parseInt(noeud.getAttributeValue("n2"))-1);

				Color couleur1 = Color.decode(arete.getChild("couleur1").getText());

				Color couleur2;
				if(arete.getChild("couleur2").getText().equals("NULL"))
					couleur2 = null;
				else
					couleur2 = Color.decode(arete.getChild("couleur2").getText());

				int distance = Integer.parseInt(arete.getChild("distance").getText());

				this.lstAretes.add(new Arete(n1, n2, distance, couleur1, couleur2));
			}

			/* <liste-objectifs> */
			this.imageVersoObjectif = this.base64ToImage(racine.getChild("liste-objectifs")
										.getChild("image-verso").getText());

			this.lstCartesObjectif = new ArrayList<CarteObjectif>();
			List<Element> listObjectifs = racine.getChild("liste-objectifs").getChildren("objectif");
			Iterator<Element> itObjectifs = listObjectifs.iterator();

			while(itObjectifs.hasNext())
			{
				Element objectif = (Element)itObjectifs.next();

				Element noeud = objectif.getChild("noeud");
				Noeud n1 = this.lstNoeuds.get(Integer.parseInt(noeud.getAttributeValue("n1"))-1);
				Noeud n2 = this.lstNoeuds.get(Integer.parseInt(noeud.getAttributeValue("n2"))-1);

				int lstPoints = Integer.parseInt(objectif.getChild("lstPoints").getText());

				BufferedImage imageRecto = this.base64ToImage(objectif.getChild("image-recto").getText());

				this.lstCartesObjectif.add(new CarteObjectif(n1, n2, lstPoints, imageRecto));
			}

			return true;
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
			return false;
		}
	}

	public String getXml()
	{
		try {
			Document document = new Document();
			
			/* <jeu> */
			Element racine = new Element("jeu");
			document.setRootElement(racine);
			
			/* <information> */
			Element information = new Element("information");
			racine.addContent(information);

			Element dimension = new Element("dimension");
			information.addContent(dimension);
			dimension.setAttribute("x", Integer.toString(this.taillePlateau[0]));
			dimension.setAttribute("y", Integer.toString(this.taillePlateau[1]));

			Element imageFond = new Element("image-fond");
			information.addContent(imageFond);
			imageFond.setText(this.imageToBase64(this.imagePlateau));

			Element couleurFond = new Element("couleur-fond");
			information.addContent(couleurFond);
			couleurFond.setText(this.colorToHexa(this.couleurPlateau));

			Element police = new Element("police");
			information.addContent(police);
			police.setText(this.policePlateau.getFontName());

			Element nbJoueurs = new Element("nombre-joueurs");
			information.addContent(nbJoueurs);
			nbJoueurs.setAttribute("min", Integer.toString(this.nbJoueursMin));
			nbJoueurs.setAttribute("max", Integer.toString(this.nbJoueursMax));

			Element nbCarte = new Element("nombre-carte");
			information.addContent(nbCarte);
			nbCarte.setAttribute("couleur", Integer.toString(this.nbCarteCoul));
			nbCarte.setAttribute("multicouleur", Integer.toString(this.nbCarteLocomotive));

			Element nbJeton = new Element("nombre-jeton");
			information.addContent(nbJeton);
			nbJeton.setAttribute("joueur", Integer.toString(this.nbJetonJoueur));
			nbJeton.setAttribute("fin", Integer.toString(this.nbJetonFin));
	
			Element plateau = new Element("plateau");
			racine.addContent(plateau);

			/* <liste-lstCouleurs> */
			Element lstCouleurs = new Element("liste-lstCouleurs");
			plateau.addContent(lstCouleurs);

			for (int i = 0; i < this.lstCouleurs.size(); i++)
			{
				Element couleur = new Element("couleur");
				lstCouleurs.addContent(couleur);
				couleur.setAttribute("id", Integer.toString(i+1));
				couleur.setText(this.colorToHexa(this.lstCouleurs.get(i)));
			}

			/* <liste-image-cartes> */
			Element imagesCartes = new Element("liste-image-cartes");
			plateau.addContent(imagesCartes);

			Element imageVersoCoul = new Element("image-verso");
			imagesCartes.addContent(imageVersoCoul);
			imageVersoCoul.setText(imageToBase64(this.imageVersoCouleur));

			Element imageRectoLoco = new Element("image-recto");
			imagesCartes.addContent(imageRectoLoco);
			imageRectoLoco.setAttribute("id", "locomotive");
			imageRectoLoco.setText(imageToBase64(this.imageRectoLocomotive));

			for (int i = 0; i < this.lstImagesRectoCouleur.size(); i++)
			{
				Element imageRecto = new Element("image-recto");
				imagesCartes.addContent(imageRecto);
				imageRecto.setAttribute("id", Integer.toString(i+1));
				imageRecto.setText(this.imageToBase64(this.lstImagesRectoCouleur.get(i)));
			}
			
			/* <tableau-lstPoints> */
			Element tablstPoints = new Element("tableau-lstPoints");
			plateau.addContent(tablstPoints);

			for (int i = 0; i < this.lstPoints.size(); i++)
			{
				Element distance = new Element("distance");
				tablstPoints.addContent(distance);
				distance.setAttribute("id", Integer.toString(i+1));
				distance.setText(Integer.toString(this.lstPoints.get(i)));
			}

			/* <liste-lstNoeuds> */
			Element lstNoeuds = new Element("liste-lstNoeuds");
			plateau.addContent(lstNoeuds);

			for (int i = 0; i < this.lstNoeuds.size(); i++)
			{
				Element noeud = new Element("noeud");
				lstNoeuds.addContent(noeud);
				noeud.setAttribute("id", Integer.toString(i+1));
				
				Element position = new Element("position");
				noeud.addContent(position);
				position.setAttribute("x", Integer.toString(this.lstNoeuds.get(i).getX()));
				position.setAttribute("y", Integer.toString(this.lstNoeuds.get(i).getY()));

				Element nom = new Element("nom");
				noeud.addContent(nom);
				nom.setText(this.lstNoeuds.get(i).getNom());

				Element position_nom = new Element("position-nom");
				noeud.addContent(position_nom);
				position_nom.setAttribute("x", Integer.toString(this.lstNoeuds.get(i).getXNom()));
				position_nom.setAttribute("y", Integer.toString(this.lstNoeuds.get(i).getYNom()));

				Element couleur = new Element("couleur");
				noeud.addContent(couleur);
				couleur.setText(this.colorToHexa(this.lstNoeuds.get(i).getCouleur()));
			}

			/* <liste-lstAretes> */
			Element arrets = new Element("liste-lstAretes");
			plateau.addContent(arrets);

			for (int i = 0; i < this.lstAretes.size(); i++)
			{
				Element arret = new Element("arete");
				arrets.addContent(arret);

				Element noeud = new Element("noeud");
				arret.addContent(noeud);
				noeud.setAttribute("n1", Integer.toString(this.lstAretes.get(i).getNoeud1().getId()));
				noeud.setAttribute("n2", Integer.toString(this.lstAretes.get(i).getNoeud2().getId()));

				Element couleur1 = new Element("couleur1");
				arret.addContent(couleur1);
				couleur1.setText(this.colorToHexa(this.lstAretes.get(i).getCouleur1()));

				Element couleur2 = new Element("couleur2");
				arret.addContent(couleur2);
				if (this.lstAretes.get(i).getCouleur2() == null)
					couleur2.setText("NULL");
				else
					couleur2.setText(this.colorToHexa(this.lstAretes.get(i).getCouleur2()));

				Element distance = new Element("distance");
				arret.addContent(distance);
				distance.setText(Integer.toString(this.lstAretes.get(i).getDistance()));
			}

			/* <liste-objectifs> */
			Element objectifs = new Element("liste-objectifs");
			racine.addContent(objectifs);

			Element versoObjectif = new Element("image-verso");
			objectifs.addContent(versoObjectif);
			versoObjectif.setText(imageToBase64(this.imageVersoObjectif));

			/*
			for (int i = 0; i < this.carteObjectif.size(); i++)
			{
				Element objectif = new Element("objectif");
				objectifs.addContent(objectif);

				Element noeud = new Element("noeud");
				objectif.addContent(noeud);
				noeud.setAttribute("n1", Integer.toString(this.carteObjectif.get(i).getNoeud1().getId()));
				noeud.setAttribute("n2", Integer.toString(this.carteObjectif.get(i).getNoeud2().getId()));

				Element lstPoints = new Element("lstPoints");
				objectif.addContent(lstPoints);
				lstPoints.setText(Integer.toString(this.carteObjectif.get(i).getPoints()));

				Element rectoObjectif = new Element("image-recto");
				objectif.addContent(rectoObjectif);
				rectoObjectif.setText(imageToBase64(this.carteObjectif.get(i).getImageRecto()));
			}
			*/

			XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
			String xml = sortie.outputString(document);
			return xml;
			
		} catch (Exception e){
			e.printStackTrace();
			return "ERREUR";
		}

	}
	
	private String colorToHexa(Color color)
	{
		if (color == null) return "#0F0F0F";
		return "#" + Integer.toHexString(color.getRGB()).substring(2);
	}

	/*Lecture du fichier XML afin de récupérer les infos du plateau */
    public boolean lireFichier(File fichier)
	{
		// read file into a reader
		try {
			this.chargerXML(new FileReader(fichier));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String imageToBase64(BufferedImage image) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return Base64.getEncoder().encodeToString(imageInByte);
	}

    public BufferedImage base64ToImage(String base64) throws IOException 
	{
		if (base64.equals("NULL_IMAGE"))
		{
			BufferedImage imageIO = new BufferedImage(10, 50, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = imageIO.createGraphics();
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, 10, 50);
			g2d.drawString("Image invalide", 0, 10);
			g2d.dispose();
			return imageIO;
		}
		byte[] bytes = Base64.getDecoder().decode(base64);
		return ImageIO.read(new ByteArrayInputStream(bytes));
	}
    
    private Color hexaToColor(String hexa)
	{
		if (hexa.charAt(0) != '#') return null;

		return new Color( Integer.parseInt(hexa.substring(1, 3), 16),
		                  Integer.parseInt(hexa.substring(3, 5), 16),
		                  Integer.parseInt(hexa.substring(5, 7), 16) );
	}

	public HashMap<String, List<Color>> getTheme() { return this.hmColorThemes;}


	/**
	 * Récupère le thème utilisé dans le fichier xml de sauvegarde
	 * @return String : thème à utilisé
	 */
	public String getThemeUsed()
	{
		String themeUsed = "";
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			themeUsed = sxb.build("./bin/donnees/themes/theme_sauvegarde.xml").getRootElement().getText();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de la lecture du fichier XML du themes utilisé"); }

		return themeUsed;
	}

	/**
	 * Sauvegarde le thème selectionné par l'utilisateur dans le fichier xml de sauvegarde
	 * @param theme : thème à sauvegarder
	 */
	public void setThemeUsed(String theme)
	{
		try
		{
			PrintWriter pw = new PrintWriter("./bin/donnees/themes/theme_sauvegarde.xml");
			pw.println("<theme>" + theme + "</theme>");
			pw.close();

			// temporaire
			//pw = new PrintWriter("./donnees/themes/theme_sauvegarde.xml");
			//pw.println("<theme>" + theme + "</theme>");
			//pw.close();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de l'écriture du fichier XML du themes utilisé"); }

		this.chargerThemes(theme);

		this.ctrl.appliquerTheme();
	}


	/**
	 * Charge les couleurs du thème choisi par l'utilisateur dans la HashMap
	 * @param theme : thème à charger
	 * @return HashMap contenant les couleurs du thème
	 */
	public void chargerThemes(String theme)
	{
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			Element racine = sxb.build("./bin/donnees/themes/theme_" + theme + ".xml").getRootElement();

			/*----------------------------*/
			/* BacKground Générale (=bkg) */
			/*----------------------------*/
			Element bkg = racine.getChild("background");

			List<Color> lst = new ArrayList<Color>();
			lst.add(new Color(Integer.parseInt(bkg.getAttributeValue("red")), Integer.parseInt(bkg.getAttributeValue("green")), Integer.parseInt(bkg.getAttributeValue("blue"))));
			this.hmColorThemes.put("background", lst);


			/*------------------------------------------*/
			/* Récupération de tout les autres éléments */
			/*------------------------------------------*/
			String[] lstCles = new String[] {"titles", "labels", "saisies", "bottuns", "menuBar"};
			for (int i = 0; i < lstCles.length; i++)
			{
				lst = new ArrayList<Color>();
				Element foreground = racine.getChild(lstCles[i]).getChild("foreground");
				Element background = racine.getChild(lstCles[i]).getChild("background");

				lst.add(0, new Color(Integer.parseInt(foreground.getAttributeValue("red")), Integer.parseInt(foreground.getAttributeValue("green")), Integer.parseInt(foreground.getAttributeValue("blue"))));
				lst.add(1, new Color(Integer.parseInt(background.getAttributeValue("red")), Integer.parseInt(background.getAttributeValue("green")), Integer.parseInt(background.getAttributeValue("blue"))));

				/* Récupération de la couleur du PlaceHolder */
				if (lstCles[i].equals("saisies"))
				{
					Element placeholder = racine.getChild(lstCles[i]).getChild("placeholder");
					lst.add(2, new Color(Integer.parseInt(placeholder.getAttributeValue("red")), Integer.parseInt(placeholder.getAttributeValue("green")), Integer.parseInt(placeholder.getAttributeValue("blue")), Integer.parseInt(placeholder.getAttributeValue("alpha"))));
				}


				this.hmColorThemes.put(lstCles[i], lst);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Erreur lors de la lecture du fichier XML des informations du theme");
		}
	}
}