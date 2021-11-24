package puissanceQuatre_v3;


public class Planche {
	
	private static final int lignes = 6;
	private static final int colonnes = 7;
	
	
	Piece [][] puissance4Planche = new Piece[lignes][colonnes];
	
	//Constructor
	public Planche() {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) puissance4Planche[ligne][col] = null;
		}
	}
	
	
	public Planche(Planche p) {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) puissance4Planche[ligne][col] = p.puissance4Planche[ligne][col];
		}
	}
	
	public int getColonnes() {
		return colonnes;
	}
	
	public int getLignes() {
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
						//System.err.println(puissance4Planche[ligne][colAjoute].getCouleur());
						pieceAjoutee = true;
						ligneAjoutee = ligne;
						break;
					}
				}
				return ligneAjoutee;
			} else {
				System.out.println("Cette colonne est pleine");
				return -1;
			}
			
		} else {
			System.out.println("Vous avez choisi une colonne qui n'existe pas");
			return -1;
		}
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
	
	public void copyPlanche(Planche p) {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) puissance4Planche[ligne][col] = p.puissance4Planche[ligne][col];
		}
	}
}
