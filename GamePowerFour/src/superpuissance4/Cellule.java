package superpuissance4;

/**
 * Implémente les cellules, qui peuvent contenir un jeton*

 */
public class Cellule {

	Jeton jetonCourant;
	

	/**
	 * Construteur initialisant les attributs avec des valeurs par défaut
	 */
	public Cellule() {
		jetonCourant = null;
	}

	/**
	 * Ajoute le jeton en paramètre à la cellule, et retourne vrai si l’ajout
	 * s’est bien passé, ou faux sinon
	 *
	 * @param jeton Le jeton à mettre dans la cellule
	 * @return Succès de l'opération
	 */
	public boolean affecterJeton(Jeton jeton) {
		if (jetonCourant != null) {
			return false;
		} else {
			jetonCourant = jeton;
			return true;
		}
	}

	/**
	 * Donne le jeton contenu dans la cellule, sans le supprimer.
	 *
	 * @return Le jeton contenu dans la cellule
	 */
	public Jeton recupererJeton() {
		return jetonCourant;
	}

	/**
	 * Supprime le jeton contenu dans la cellule, si elle en contient.
	 *
	 * @return Succès de l'opération
	 */
	public boolean supprimerJeton() {
		if (jetonCourant == null) {
			return false;
		} else {
			jetonCourant = null;
			return true;
		}
	}

	/**
	 * Renvoie la couleur du jeton qui occupe la cellule.
	 *
	 * @return La couleur du jeton
	 */
	public String lireCouleurDuJeton() {
		if (jetonCourant == null) {
			return "vide";
		}
		return jetonCourant.Couleur;
	}

	

}
