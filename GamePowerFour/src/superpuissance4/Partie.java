/*
 *
 * Clara THEODOLY et Émile ROYER
 * TP 3, v1.4
 *
 */
package superpuissance4;

import java.util.Random;
import java.util.Scanner;

/**
 * Implémente les parties. Elles ont une grille et deux joueurs.
 *
 * @author Clara THEODOLY et Émile ROYER
 */
public class Partie {

	Player[] ListeJoueur = new Player[2];
	Grille grilleJeu;
	Player joueurCourant;

	/**
	 * Attribue aléatoirement des couleurs aux joueurs
	 */
	public void attribuerCouleursAuxJoueurs() {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		int index;

		System.out.print("Entrez le nom du premier joueur : ");
		ListeJoueur[0] = new Player(sc.nextLine());

		System.out.print("Entrez le nom du deuxième joueur : ");
		ListeJoueur[1] = new Player(sc.nextLine());

		index = rnd.nextInt(2);

		/* On colore le nom des couleurs pour plus de lisibilité */
		ListeJoueur[index].affecterCouleur("\033[91mrouge\033[0m");
		ListeJoueur[1 - index].affecterCouleur("\033[93mjaune\033[0m");

		System.out.println(ListeJoueur[0].Nom + " prend la couleur "
				+ ListeJoueur[0].lireCouleur() + ", tandis que " + ListeJoueur[1].Nom
				+ " prend la couleur " + ListeJoueur[1].lireCouleur() + ".");

		/*
		* On colore le nom des joueurs avec leur couleur pour plus de lisibilité
		 */
		ListeJoueur[index].Nom = "\033[91m" + ListeJoueur[index].Nom + "\033[0m";
		ListeJoueur[1 - index].Nom = "\033[93m" + ListeJoueur[1 - index].Nom + "\033[0m";

		sc.close();
	}

	/**
	 * Crée la grille, la vide si elle existait déjà, place les trous noirs et
	 * les désintégrateurs, crée les jetons et les attribue aux joueurs
	 * correspondants.
	 */
	public void initialiserPartie() {

		/* Crée la grille si elle n'existait pas, et la vide si elle existait */
		grilleJeu = new Grille();

		for (int i = 0; i < ListeJoueur.length; i++) {
			for (int j = 0; j < ListeJoueur[i].ListeJetons.length; j++) {
				ListeJoueur[i].ListeJetons[j] = new Jeton(ListeJoueur[i].lireCouleur());
			}
			ListeJoueur[i].nombreJetonsRestants = 21;
		}
		
	
	}

	/**
	 * Lance la partie en tirant au hasard le joueur qui commence.
	 */
	public void debuterPartie() {
		Random rnd = new Random();
		int index;
		boolean finDePartie = false;

		attribuerCouleursAuxJoueurs();
		initialiserPartie();

		index = rnd.nextInt(2);
		joueurCourant = ListeJoueur[index];
		System.out.println("Le joueur " + joueurCourant.lireCouleur()
				+ " commence la partie !");
		grilleJeu.afficherGrillesurConsole();

		while (!finDePartie) {
			joueurCourant = ListeJoueur[index];
			finDePartie = tourDeJeu(joueurCourant);
			if (!finDePartie) {
				if (grilleJeu.estRemplie()) {
					/* Si la grille est remplie, la partie ne peut pas continuer */
					finDePartie = true;
					System.out.print("La grille est pleine ! Félicitations aux deux joueurs, le match est nul.");
				} else {
					/* Changement de joueur */
					index = ++index % 2;
				}
			}
		}
	}

	/**
	 * Effectue le tour de jeu d'un joueur, en le guidant dans les actions.
	 *
	 * @param joueur Le joueur dont c'est le tour
	 * @return Si la partie a été gagnée par le joueur
	 */
	/*
	* Si le joueur essaie de jouer en dehors de la zone de jeu, il ne pert pas
	* son jeton mais passe son tour et est invité à recommencer.
	* Le comportement est similaire s'il essaie de récupérer un jeton qui
	* n'existe pas.
	 */
	public boolean tourDeJeu(Player joueur) {
		Scanner sc = new Scanner(System.in);
		String reponse;
		boolean partieGagnee;

		System.out.println(joueur.Nom + ", c'est votre tour de jouer.");
		reponse = sc.nextLine();

		sc.close();
		/* Jouer un jeton */
		if (reponse.equals("jouer") || reponse.equals("j")) {
			int colonne_jouee;
			/* le numéro de la clonne jouée */
			boolean succes_jeu;
			/* si le jeton a pu être placé (colonne pleine) */
			Jeton jeton_joue;

			System.out.print("Où voulez-vous placer votre jeton ? (1-" + grilleJeu.nb_colonnes + ") ");
			colonne_jouee = sc.nextInt() - 1;
			if (colonne_jouee >= grilleJeu.nb_colonnes) {
				System.out.println("Votre jeton tombe en dehors de l'espace de jeu."
						+ " Vous le conservez, mais visez mieux la prochaine fois ! ;)");
				return false;
			} else if (joueur.nombreJetonsRestants == 0) {
				System.out.println("Vous n'avez plus de jetons. Mais vérifiez "
						+ "quand même au fond de vos poches, on ne sait jamais.");
				return false;
			} else {
				jeton_joue = joueur.ListeJetons[joueur.nombreJetonsRestants - 1];
				succes_jeu = grilleJeu.ajouterJetonDansColonne(jeton_joue, joueur, colonne_jouee);
				if (!succes_jeu) {
					System.out.println("Il n'y a plus de place dans cette colonne. Gardez votre jeton, "
							+ "mais jouez-le mieux la prochaine fois !");
					return false;
				} else {
					grilleJeu.tasserColonne(colonne_jouee);
					grilleJeu.afficherGrillesurConsole();
					partieGagnee = grilleJeu.estGagnantePourJoueur(joueur);
					if (partieGagnee) {
						System.out.println("Félicitations " + joueur.Nom + " ! Vous avez gagné la partie.");
					}
					return partieGagnee;
				}
			}

		
		}else {
			System.out.println("Cette option n'est pas valide. Vérifiez votre clavier pour le prochain tour !");
			return false;
		}
	}

}
