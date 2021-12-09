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
	
	public Piece[][] newPlanche() {
		Piece[][] newPlanche = new Piece[lignes][colonnes];
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) newPlanche[ligne][col] = null;
				for(int col = 0; col < colonnes; col++) puissance4Planche[ligne][col] = newPlanche[ligne][col];

		}
		
		return puissance4Planche;
	}
	
	public int getColonnes() {
		return colonnes;
	}
	
	public int getLignes() {
		return lignes;
	}
	
	public int dernierPieceLigne(int col) {
		// On récupère la première ligne non null sur la colonne désignée en paramètre
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
				System.err.println("Cette colonne est pleine");
				return -1;
			}
			
		} else {
			System.err.println("Vous avez choisi une colonne qui n'existe pas");
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
	
	public Piece[][] copyPlanche(Planche p) {
		Piece[][] NewPlanche = new Piece[6][7];
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++) NewPlanche[ligne][col] = p.puissance4Planche[ligne][col];
		}
		return NewPlanche;
	}
}
