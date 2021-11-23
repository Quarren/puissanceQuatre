package puissanceQuatre_v1;

public class Jeu {
	
	private static Colonne[] colonnes = new Colonne[7];
	private static Joueur[] joueurs = new Joueur[2];

	public Jeu() {
		Colonne c;
		for (int i = 0; i < 7; i++) {
			c = new Colonne(i+1);
			colonnes[i] = c;
			//System.out.println(this.colonnes[i].getPosition());
		}
		Joueur p;
		for (int j = 0; j < 2; j++) {
			p = new Joueur(j+1);
			joueurs[j] = p;
		}
	}
	
	

	public static Colonne[] getColonnes() {
		return colonnes;
	}


	public static Joueur[] getJoueurs() {
		return joueurs;
	}


	@Override
	public String toString() {
		String res = new String();
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				res += colonnes[j].getPions()[i];
			}
			res += '\n';
		}
		return res;
	}
		
	/*
	 * avec ndp le nombre de pions dans la colonne où j à joué (entre 0 et 5)
	 * j le joueur qui vient de jouer
	 * idx l'indice de la colonne pour faire un parcours horizontal de 0 à 6
	 * nbpÂlignes le compteur de pions alignés
	 */
	public boolean gagnerHorizontal(int ndp, Joueur j, int idx, int nbpAlignes) {
		// On appelle cette méthode avec ndp = idColonne.getNbp() après ajouterPion
		// idx = 0 et nbpAlignes = 0
		if (nbpAlignes == 4) {
			return true;
		} else if (idx == 6) {
			return false;
		} else if (colonnes[idx].getPions()[ndp] == j.getId()) {
			return gagnerHorizontal(ndp, j, idx+1, nbpAlignes+1);
		} else {
			return gagnerHorizontal(ndp, j, idx+1, nbpAlignes);
		}
	}
	
	// Nord Ouest
	public boolean gagnerEnDiagonaleNO(int idColonne, int idJoueur, int idx) {
		// TODO
		return false;
	}
	
	// Nord Est
	public boolean gagnerEnDiagonaleNE(int idColonne, int idJoueur, int idx) {
		// TODO
		return false;
	}
	
	public boolean ajouterPion(int idColonne, int idJoueur) {
		int idx = this.colonnes[idColonne-1].ajouterPion(idJoueur);
		//TODO
		/*if (this.gagnerVerticalement(idColonne, idJoueur, idx) || this.gagnerHorizontalement(idColonne, idJoueur, idx) || 
				this.gagnerEnDiagonaleNO(idColonne, idJoueur, idx) || this.gagnerEnDiagonaleNE(idColonne, idJoueur, idx)) {
			return true;
		} else {
			return false;
		}*/
		return false; // TODO à supprimer
	}

}
