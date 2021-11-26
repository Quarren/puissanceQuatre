package puissanceQuatre_v3;

public abstract class JoueurAuto extends Joueur {
	
	public int [] heuristics = {0, 2, 3, 1000};
	public int prioMilieu = 5;
	
	public JoueurAuto(String couleur, Planche planche) {
		super(couleur, planche);
	}
	
	// calcule le score sur la colonne col
	public int scoreVertical(int col) {
		//System.out.println("Appel méthode scoreVertical()");
		Planche p2 = new Planche(this.planche);
		//System.out.println("planche p2 copie de plance avant ajout");
		//p2.printPlanche();
		/*if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + " pleine.");
			return -1;
		}*/
		p2.ajouterPiece(col, this.getCouleur());
		//System.out.println("planche p2 après ajout");
		//p2.printPlanche();
		int idx = p2.dernierPieceLigne(col);
		int streak = 0;
		//System.out.println("streak : " + streak);
		for (int i = idx; i <= idx+3 && i <= 5; i++) {
			//System.out.println("i = " + i);
			if (p2.puissance4Planche[i][col].getCouleur() == this.getCouleur()) {
				streak++;
				//System.out.println("streak : " + streak);
			} else {
				break;
			}
		}
		int score = 0;
		if (streak != 0) {
			score += heuristics[streak-1];
		}
		System.out.println("scoreVertical (col n°" + col + "): " + score);
		return score;
	}
	
	// calcule le score horizontal quand on rajoute une pièce dans la colonne col
	public int scoreHorizontal(int col) {
		System.out.println("appel méthode scoreHorizontal");
		Planche p2 = new Planche(this.planche);
		/*if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + " pleine.");
			return -1;
		}*/
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		p2.printPlanche();
		int colDepart = col >= 3 ? col - 3 : 0;
		//System.out.println("coldepart: " + colDepart);
		int colFin = col >= 3 ? 6 : col + 3;
		//System.out.println("colfin : " + colFin);
		int score = 0;
		while (colDepart + 3 <= colFin) {
			int streak = 0;
			for (int i = 0; i < 4; i++) {
				//System.out.println("itération n°" + i);
				if (p2.puissance4Planche[idx][colDepart+i] != null) {
					if (p2.puissance4Planche[idx][colDepart+i].getCouleur() == this.getCouleur()) {
						streak++;
						//System.out.println(streak);
					} else {
						break;
					}
				}
			}
			if (streak != 0) {
				System.out.println("streak : " + streak);
				score += this.heuristics[streak-1];
			}
			colDepart++;
		}
		System.out.println("scoreHorizontal (col n°" + col + "): " + score);
		return score;
	}
	
	public int scoreDiagSOToNE(int col) {
		System.out.println("appel méthode scoreDiagSOToNE");
		Planche p2 = new Planche(this.planche);
		// colonne pleine
		/*if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + "pleine.");
			return -1;
		}*/
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		System.out.println("idx = " + idx);
		p2.printPlanche();
		
		// éliminer les configurations où on a pas 4 cases alignées en diagonale
		if (idx == 0 && col == 0
				|| idx == 0 && col == 1
				|| idx == 0 && col == 2) {
			return 0;
		}
		if (idx == 1 && col == 0
				|| idx == 1 && col == 1) {
			return 0;
		}
		if (idx == 2 && col == 0) {
			return 0;
		}
		
		if (idx == 5 && col == 4
				|| idx == 5 && col == 5
				|| idx == 5 && col == 6) {
			return 0;
		}
		if (idx == 4 && col == 5
				|| idx == 4 && col == 6) {
			return 0;
		}
		if (idx == 3 && col == 6) {
			return 0;
		}
		
		// trouver coordonnées départ de la diagonale
		int colDepart;
		int lineDepart;
		if (col <= 3 || idx >= 2) {
			if (col <= 5-idx) {
				colDepart = 0;
				lineDepart = idx + col;
			} else {
				colDepart = idx - (5- idx);
				lineDepart = 5;
			}
		} else {
			colDepart = col - 3; lineDepart = idx + 3;
		}

		System.out.println("colDepart = " + colDepart);
		System.out.println("lineDepart = " + lineDepart);

		int score = 0;
		
		int streak;
		// TODO vérifier si c'est bien <= ou < strict pour (colDepart + 3 <= 7 && lineDepart - 3 >= 0)
		while (colDepart + 3 <= 7 && lineDepart - 3 >= 0 && colDepart <= col && lineDepart >= idx) {
			streak = 0;
			for (int i = 0; i < 4; i++) {
				if (p2.puissance4Planche[lineDepart-i][colDepart+i] != null) {
					if (p2.puissance4Planche[lineDepart-i][colDepart+i].getCouleur() == this.getCouleur()) {
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
			colDepart++; lineDepart--;
		}
		System.out.println("scoreDiagSOToNE (col n°" + col + "): " + score);
		return score;
	}
	
	
	//TODO scoreDiagNOToSE
	public int scoreDiagNOToSE(int col) {
		System.out.println("appel méthode scoreDiagSOToNE");
		Planche p2 = new Planche(this.planche);
		// colonne pleine
		/*if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + "pleine.");
			return -1;
		}*/
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		System.out.println("idx = " + idx);
		p2.printPlanche();
		
		// éliminer les configurations où on a pas 4 cases alignées en diagonale
		if (idx == 0 && col == 4
				|| idx == 0 && col == 5
				|| idx == 0 && col == 6) {
			return 0;
		}
		if (idx == 1 && col == 5
				|| idx == 1 && col == 6) {
			return 0;
		}
		if (idx == 2 && col == 6) {
			return 0;
		}
		
		if (idx == 5 && col == 0
				|| idx == 5 && col == 1
				|| idx == 5 && col == 2) {
			return 0;
		}
		if (idx == 4 && col == 0
				|| idx == 4 && col == 1) {
			return 0;
		}
		if (idx == 3 && col == 0) {
			return 0;
		}
		
		// trouver coordonnées départ de la diagonale
		int colDepart;
		int lineDepart;
		if (col <= 3 || idx <= 3) { // la diagonale commence à une des extrémités de la planche
			if (col <= idx) {
				colDepart = 0;
				lineDepart = idx - col;
			} else {
				colDepart = col - idx;
				lineDepart = 0;
			}
		} else { // la diagonale ne commence pas à une extrémité de la planche
			colDepart = col - 3; lineDepart = idx - 3;
		}

		System.out.println("colDepart = " + colDepart);
		System.out.println("lineDepart = " + lineDepart);

		int score = 0;
		
		int streak;
		while (colDepart + 3 < 7 && lineDepart + 3 < 6 && colDepart <= col && lineDepart <= idx) {
			streak = 0;
			for (int i = 0; i < 4; i++) {
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
		System.out.println("scoreDiagNOToSE (col n°" + col + "): " + score);
		return score;
	}
	
	public int fullScoreCol(int col) {
		if (this.planche.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + "pleine.");
			return -100;
		} else {
			int score = col == 3 ? this.prioMilieu : 0;
			score += this.scoreVertical(col) + this.scoreVertical(col) + this.scoreDiagNOToSE(col) + this.scoreDiagSOToNE(col);
			return score;
		}
	}
	
	public int makeMove() {
		int max = 0;
		int idxCol = -1;
		int itScore;
		for (int i = 0; i < 7; i++) {
			itScore = this.fullScoreCol(i) ;
			if (itScore > max) {
				idxCol = i;
				max = itScore;
			}
		}
		if (idxCol < 0) {
			throw new ActionJoueurImpossible("Toutes les colonnes sont pleines.");
		} else {
			return idxCol;
		}
	}
}
