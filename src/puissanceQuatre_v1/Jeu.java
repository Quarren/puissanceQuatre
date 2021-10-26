package puissanceQuatre_v1;

public class Jeu {
	
	private static Colonne[] colonnes = new Colonne[6];
	private static Joueur[] joueurs = new Joueur[2];

	public Jeu() {
		Colonne c;
		for (int i = 0; i < 6; i++) {
			c = new Colonne(i+1);
			colonnes[i] = c;
			//System.out.println(this.colonnes[i].getPosition());
		}
		Joueur p;
		for (int j = 0; j < 2; j++) {
			p = new Joueur(j);
			joueurs[j] = p;
		}
	}

	@Override
	public String toString() {
		String res = new String();
		for (int i = colonnes.length-1; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				res += colonnes[j].getPions()[i];
			}
			res += '\n';
		}
		return res;
	}
	
	public boolean gagnerVerticalement(int idJoueur) {
		// TODO
		return false;
	}
	
	public boolean gagnerHorizontalement(int idJoueur) {
		// TODO
		return false;
	}
	
	// Nord Ouest
	public boolean gagnerEnDiagonaleNO(int idJoueur) {
		// TODO
		return false;
	}
	
	// Nord Est
	public boolean gagnerEnDiagonaleNE(int idJoueur) {
		// TODO
		return false;
	}
	
	public boolean ajouterPion(int idColonne, int idJoueur) {
		// TODO
		if (this.gagnerVerticalement(idJoueur) || this.gagnerHorizontalement(idJoueur) || this.gagnerEnDiagonaleNO(idJoueur) || 
				this.gagnerEnDiagonaleNE(idJoueur)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Jeu j = new Jeu();
		System.out.println(j.toString());
		j.colonnes[0].ajouterPion(1);
		j.colonnes[1].ajouterPion(2);
		j.colonnes[0].ajouterPion(1);
		j.colonnes[0].ajouterPion(2);
		System.out.println(j.toString());
		//System.out.println("" + joueurs[0].getId() + joueurs[1].getId());
		//System.out.println()
	}

}
