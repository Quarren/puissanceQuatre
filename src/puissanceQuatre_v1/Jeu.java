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
	
	// indice colonne = idColonne -1
	public boolean gagnerVerticalement(int idColonne, int idJoueur, int idx) {
		boolean bool = true;
		System.out.println("idx=" + idx);
			int i = 0;
			while (i < 4 && bool) {
				i++;
				System.out.println(idx-i);
				if (colonnes[idColonne].getPions()[idx-i] != idJoueur) {
					bool = false;
				}
				System.out.println(bool);
		}
		//System.out.println(bool);
		// TODO
		return bool;
	}
	
	public boolean gagnerHorizontalement(int idColonne, int idJoueur, int idx) {
		// TODO
		return false;
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

	public static void main(String[] args) {
		Jeu j = new Jeu();
		//System.out.println(j.toString());
		//j.colonnes[5].ajouterPion(2);
		
		j.ajouterPion(1, 1);
		System.out.println("nbp colonnes 1: " + j.colonnes[0].getNbp());
		//System.out.println("nbp colonnes 1: " + j.colonnes[0].getPions(j.colonnes[0].getNbp())-1);
		j.ajouterPion(1,2);
		System.out.println("nbp colonnes 1: " + j.colonnes[0].getNbp());
		//j.ajouterPion(1, 1);
		//j.ajouterPion(1, 1);
		//System.out.println(j.ajouterPion(1, 1));
		
		System.out.println(j.toString());
		
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		System.out.println(j.colonnes[0].gagnerHorizontal(joueurs[0], 0, 0));
		
		System.out.println(j.toString());
		
		/*j.colonnes[0].ajouterPion(1);
		j.colonnes[1].ajouterPion(2);
		j.colonnes[0].ajouterPion(1);
		j.colonnes[0].ajouterPion(2);*/
		//System.out.println(j.toString());
		//System.out.println("" + joueurs[0].getId() + joueurs[1].getId());
		//System.out.println()
	}

}
