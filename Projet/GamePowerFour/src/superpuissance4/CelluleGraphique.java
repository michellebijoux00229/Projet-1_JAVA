/*
 *
 * Clara THEODOLY et Émile ROYER
 * TP 3, v1.4
 *
 */
package superpuissance4;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Clara THEODOLY et Émile ROYER
 */
public class CelluleGraphique extends JButton {

	// éléments qu'on va pouvoir afficher sur la fenêtre de jeu
	Cellule celluleAssociee;
	// on charge les 5 images
	ImageIcon img_vide = new javax.swing.ImageIcon(getClass().getResource("/images/celluleVide.png"));
	ImageIcon img_jaune = new javax.swing.ImageIcon(getClass().getResource("/images/jetonJaune.png"));
	ImageIcon img_rouge = new javax.swing.ImageIcon(getClass().getResource("/images/jetonRouge.png"));
	   
        
	// constructeur avec en paramètre lacellule
	public CelluleGraphique(Cellule laCellule) {
		celluleAssociee = laCellule;
	}

	// méthode pour les interfaces graphiques pour déterminer la façon dont on veut afficher nos objets
	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);

		String couleur_jeton = celluleAssociee.lireCouleurDuJeton();
			switch (couleur_jeton) {
				case "vide":
					setIcon(img_vide); // on attribue l'image celluleVide.png
					break;
				case "rouge":
					setIcon(img_rouge);
					break;
				case "jaune":
					setIcon(img_jaune);
					break;
		}
	}

}
