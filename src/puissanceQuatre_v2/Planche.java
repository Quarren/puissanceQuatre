package puissanceQuatre_v2;


public class Planche {
	
	protected static final int lignes = 6;
	protected static final int colonnes = 7;
	
	
	public Piece [][] puissance4Planche = new Piece[lignes][colonnes];
	
	//Constructeur
	public Planche() {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) puissance4Planche[ligne][col] = null;
		}
	}
	
	//Constructeur de copie
	public Planche(Planche p) {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++)
				puissance4Planche[ligne][col] = p.puissance4Planche[ligne][col];
		}
	}
	
	public static int getColonnes() {
		return colonnes;
	}
	
	public static int getLignes() {
		return lignes;
	}
	
	public int dernierPieceLigne(int col) {
		int ligne;
		for(ligne = 0; ligne < lignes; ligne++) {
			if(puissance4Planche[ligne][col] != null) break;
		}
		return ligne;
	}
	
	public int ajouterPiece(int colAjoute, String couleur) {
		if(colAjoute >= 0 && colAjoute < colonnes) {
			boolean pieceAjoutee = false;
			if(puissance4Planche[0][colAjoute] == null) {
				int ligneAjoutee = -1;
				for(int ligne = lignes - 1; ligne >= 0; ligne--) {
					if(puissance4Planche[ligne][colAjoute] == null) {
						puissance4Planche[ligne][colAjoute] = new Piece();
						puissance4Planche[ligne][colAjoute].setCouleur(couleur);
						//System.err.println(colAjoute);
						pieceAjoutee = true;
						ligneAjoutee = ligne;
						break;
					}
				}
				return ligneAjoutee;
			} else {
				return -1;
			}
			
		} else {
			System.out.println("Vous avez coisis une colonne erronée");
			return -1;
		}
	}
	
	//planchePleine() renvoie true si la planche est pleine
	public boolean planchePleine() {
		if(this.remplissagePlanche() == lignes * colonnes) return true;
		return false;
	}
	
	
	
	
	//remplissagePlanche() renvoie le nombre des cases remplis dans le jeu
	public int remplissagePlanche() {
		int compt = lignes * colonnes;
		for (int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++)
				if(puissance4Planche[ligne][col] == null) compt--;
		}
		return compt;
	}
	
	public void printPlanche() {
		for(int ligne = 0; ligne < lignes; ligne++) {
			System.out.print("|");
			for(int col = 0; col < colonnes; col++) {
				if(puissance4Planche[ligne][col] == null) {
					System.out.print(".");
				} else {
					System.out.print(puissance4Planche[ligne][col].getCouleur());
				}
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println("<===========================>");
	}
}
