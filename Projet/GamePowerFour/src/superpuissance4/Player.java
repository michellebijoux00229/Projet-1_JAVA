/*
 * 
 * Clara THEODOLY et Émile ROYER
 * TP 3, v1.4
 * 
 */
package superpuissance4;

/**
 * Implémente les joueurs. Ils ont un nom, une couleur, une quantité limitée de
 * jetons, et peuvent avoir des désintégrateurs.
 * @author Clara THEODOLY et Émile ROYER
 */
public class Player {
    
    String Nom;
    String Couleur;
    Jeton [] ListeJetons = new Jeton[21];
    int nombreDesintegrateurs;
    int nombreJetonsRestants;
    
	/**
	 * Constructeur initialisant le nom du joueur.
	 * @param nom le nom du joueur
	 */
    public Player(String nom) {
        Nom = nom;
    }
    
	/**
	 * Affecte une couleur au joueur.
	 * @param couleur La couleur que doit prendre le joueur
	 */
    public void affecterCouleur(String couleur){
        Couleur = couleur;
    }
	
	/**
	 * Donne la couleur du joueur.
	 * @return La couleur du joueur
	 */
	public String lireCouleur() {
		return Couleur;
	}
	
	/**
	 * Ajoute un jeton au joueur.
	 * @param jeton Le jeton à ajouter
	 * @return Succès de l'opération
	 */
    public boolean ajouterJeton(Jeton jeton) {
        if (nombreJetonsRestants == ListeJetons.length){
            return false;
        }
        else {
         ListeJetons[nombreJetonsRestants] = jeton;
         nombreJetonsRestants++;
         return true;
        }
    }
	
	/**
	 * Enlève un jeton au joueur.
	 * @return Succès de l'opération
	 */
	public boolean enleverJeton() {
		if (nombreJetonsRestants == 0) {
			return false;
		} else {
			nombreJetonsRestants--;
			ListeJetons[nombreJetonsRestants] = null;
			return true;
		}
	}
    
	
    
}
