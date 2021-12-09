package puissanceQuatre_v3;

public class JoueurAuto extends Joueur {
	
	public int [] heuristics = {0, 3, 20, 1000};
	
	public int prioMilieu = 5;
	
	public JoueurAuto(String couleur, Planche planche) {
		super(couleur, planche);
	}
	
	public JoueurAuto(String couleur, Planche p, String opp) {
		super(couleur, p, opp);
	}
	
	// OK
	public int scoreVertical(int col) {
		Planche p2 = new Planche(this.planche);
		p2.ajouterPiece(col, this.getCouleur());

		// on instancie une variable par le numéro de ligne où la pièce va se trouver en fonction de la colonne en paramètre
		int idx = p2.dernierPieceLigne(col);
		int streak = 0;
		// TODO: vérifier qu'il reste bien la place pour gagner dans la colonne (inutile de faire une ligne de 3 si on ne peut pas
		// poser la 4e)
		for (int i = idx; i <= idx+3 && i <= 5; i++) {
			if (p2.puissance4Planche[i][col].getCouleur() == this.getCouleur()) {
				streak++;
			} else {
				break;
			}
		}
		int score = 0;
		if (streak != 0) {
			score += heuristics[streak-1];
		}
		return score;
	}
	
	// calcule le score horizontal quand on rajoute une pièce dans la colonne col
	public int scoreHorizontal(int col) {
		Planche p2 = new Planche(this.planche);
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);

		int colDepart = col >= 3 ? col - 3 : 0;
		int colFin = col >= 3 ? 6 : col + 3;
		int score = 0;
		while (colDepart + 3 <= colFin) {
			int streak = 0;
			for (int i = 0; i < 4; i++) {
				if (p2.puissance4Planche[idx][colDepart+i] != null) {
					if (p2.puissance4Planche[idx][colDepart+i].getCouleur() == this.getCouleur()) {
						streak++;
					} else {
						break;
					}
				}
			}
			if (streak != 0) {
				score += this.heuristics[streak-1];
			}
			colDepart++;
		}
		return score;
	}
	
	
	// Sud Ouest - Nord Est / Bas gauche - haut droite
	public int scoreDiagSOToNE(int col) {
		Planche p2 = new Planche(this.planche);
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		
		// éliminer les configurations où on a pas 4 cases alignées en diagonale
		if ((idx == 0 && col == 0)
				|| (idx == 0 && col == 1)
				|| (idx == 0 && col == 2)) {
			return 0;
		}
		if ((idx == 1 && col == 0)
				|| (idx == 1 && col == 1)) {
			return 0;
		}
		if (idx == 2 && col == 0) {
			return 0;
		}
		
		if ((idx == 5 && col == 4)
				|| (idx == 5 && col == 5)
				|| (idx == 5 && col == 6)) {
			return 0;
		}
		if ((idx == 4 && col == 5)
				|| (idx == 4 && col == 6)) {
			return 0;
		}
		if (idx == 3 && col == 6) {
			return 0;
		}
		
		// trouver coordonnées départ de la diagonale
		int colDepart;
		int lineDepart;
		if (col >= 3 && idx <= 2) {
			colDepart = col - 3;
			lineDepart = idx + 3;
			//System.out.println("boucle part1");

		} else if (-col >= idx-5) {
			colDepart = 0;
			lineDepart = idx + col;
			//System.out.println("boucle part2");
		} else {
			colDepart = col - (idx -5);
			lineDepart = 5;
			//System.out.println("boucle part3");
		}
		//System.out.println("On teste scoreDiagSOToNE avec col=" + col);
		//System.out.println("col depart : " + colDepart);
		//System.out.println("ligne départ : " + lineDepart);

		int score = 0;
		
		int streak;
		// TODO vérifier si c'est bien <= ou < strict pour (colDepart + 3 <= 7 && lineDepart - 3 >= 0)
		while (colDepart + 3 < 7 && lineDepart - 3 > 0 && colDepart < col && lineDepart > idx) {
			streak = 0;
			// TODO i < 3 ou 1 < 4
			for (int i = 0; i < 3; i++) {
				if (p2.puissance4Planche[lineDepart-i][colDepart+i] != null) {
					if (p2.puissance4Planche[lineDepart-i][colDepart+i].getCouleur() == this.getCouleur()) {
						streak++;
					} else {
						break;
					}
				}
			}
			if (streak != 0) {
				score += this.heuristics[streak-1];
			}
			colDepart++; lineDepart--;
		}
		return score;
	}
	
	
	// Nord Ouest - Sud Est / Haut gauche - bas droite
	public int scoreDiagNOToSE(int col) {
		Planche p2 = new Planche(this.planche);
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		
		// éliminer les configurations où on a pas 4 cases alignées en diagonale
		if ((idx == 0 && col == 4)
				|| (idx == 0 && col == 5)
				|| (idx == 0 && col == 6)) {
			return 0;
		}
		if ((idx == 1 && col == 5)
				|| (idx == 1 && col == 6)) {
			return 0;
		}
		if (idx == 2 && col == 6) {
			return 0;
		}
		
		if ((idx == 5 && col == 0)
				|| (idx == 5 && col == 1)
				|| (idx == 5 && col == 2)) {
			return 0;
		}
		if ((idx == 4 && col == 0)
				|| (idx == 4 && col == 1)) {
			return 0;
		}
		if (idx == 3 && col == 0) {
			return 0;
		}
		
		// trouver coordonnées départ de la diagonale
		int colDepart;
		int lineDepart;
		if (col >= 3 && idx > 2) {
			colDepart = col - 3;
			lineDepart = idx - 3;
			//System.out.println("boucle part1");

		} else if (col <= idx) {
			colDepart = 0;
			lineDepart = idx - col;
			//System.out.println("boucle part2");
		} else {
			colDepart = col - idx;
			lineDepart = 0;
			//System.out.println("boucle part3");
		}
		//System.out.println("On teste scoreDiagSOToNE avec col=" + col);
		//System.out.println("col depart : " + colDepart);
		//System.out.println("ligne départ : " + lineDepart);

		int score = 0;
		
		int streak;
		while (colDepart + 3 < 7 && lineDepart + 3 < 6 && colDepart <= col && lineDepart <= idx) {
			streak = 0;
			// TODO i < 3 ou 1 < 4
			for (int i = 0; i < 3; i++) {
				if (p2.puissance4Planche[lineDepart+i][colDepart+i] != null) {
					if (p2.puissance4Planche[lineDepart+i][colDepart+i].getCouleur() == this.getCouleur()) {
						streak++;
						//System.out.println(streak);
					} else {
						break;
					}
				}
			}
			if (streak != 0) {
				score += this.heuristics[streak-1];
			}
			colDepart++; lineDepart++;
		}
	//	System.out.println("scoreDiagNOToSE (col n°" + col + "): " + score);
		return score;
	}
	
	
	
	public int fullScoreCol(int col) {
		if (this.planche.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + "pleine.");
			return -100;
		} else {
			int score = col == 3 ? this.prioMilieu : 0;
			score += this.scoreVertical(col) + this.scoreHorizontal(col) + this.scoreDiagNOToSE(col) + this.scoreDiagSOToNE(col);
			return score;
		}
	}
	
	public int makeMove() {
		//System.out.print("Appel méthode MakeMove()");
		int max = 0;
		int idxCol = -1;
		int itScore;
		for (int i = 0; i < 7; i++) {
			System.out.println("calcul move auto col n°:" + i);
			itScore = this.fullScoreCol(i);
			if (itScore > max) {
				idxCol = i;
				max = itScore;
			}
		}
		if (idxCol < 0) {
			throw new ActionJoueurImpossible("Toutes les colonnes sont pleines.");
		} else {
			System.out.println("idxCol : " + idxCol);
			this.planche.ajouterPiece(idxCol, this.getCouleur());
		
			return idxCol;
		}
		
	}
}
