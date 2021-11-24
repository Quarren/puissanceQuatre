package puissanceQuatre_v3;

public abstract class JoueurAuto extends Joueur {
	
	public int [] heuristics = {0, 2, 3, 1000};
	public int colMilieu = 5;
	
	public JoueurAuto(String couleur, Planche planche) {
		super(couleur, planche);
	}
	
	// calcule le score sur la colonne col
	public int scoreVertical(int col) {
		//System.out.println("Appel méthode scoreVertical()");
		Planche p2 = new Planche(this.planche);
		//System.out.println("planche p2 copie de plance avant ajout");
		//p2.printPlanche();
		if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + " pleine.");
			return -1;
		}
		p2.ajouterPiece(col, this.getCouleur());
		//System.out.println("planche p2 après ajout");
		//p2.printPlanche();
		int idx = p2.dernierPieceLigne(col);
		int nbpAlignes = 0;
		//System.out.println("nbpAlignes : " + nbpAlignes);
		for (int i = idx; i <= idx+3 && i <= 5; i++) {
			//System.out.println("i = " + i);
			if (p2.puissance4Planche[i][col].getCouleur() == this.getCouleur()) {
				nbpAlignes++;
				//System.out.println("nbpAlignes : " + nbpAlignes);
			} else {
				break;
			}
		}
		return nbpAlignes == 0 ? 0 : heuristics[nbpAlignes-1];
	}
	
	// calcule le score horizontal quand on rajoute une pièce dans la colonne col
	public int scoreHorizontal(int col) {
		System.out.println("appel méthode scoreHorizontal");
		Planche p2 = new Planche(this.planche);
		if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + " pleine.");
			return -1;
		}
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		p2.printPlanche();
		int colDepart = col >= 3 ? col - 3 : 0;
		//System.out.println("coldepart: " + colDepart);
		int colFin = col >= 3 ? 6 : col + 3;
		//System.out.println("colfin : " + colFin);
		int score = 0;
		while (colDepart + 3 <= colFin) {
			int nbpAlignes = 0;
			for (int i = 0; i < 4; i++) {
				//System.out.println("itération n°" + i);
				if (p2.puissance4Planche[idx][colDepart+i] != null) {
					if (p2.puissance4Planche[idx][colDepart+i].getCouleur() == this.getCouleur()) {
						nbpAlignes++;
						//System.out.println(nbpAlignes);
					} else {
						break;
					}
				}
			}
			if (nbpAlignes != 0) {
				System.out.println("nbpAlignes : " + nbpAlignes);
				score += this.heuristics[nbpAlignes-1];
			}
			colDepart++;
		}
		return score;
	}
	
	//TODO scoreDiagSOToNE
	public int scoreDiagSOToNE(int col) {
		System.out.println("appel méthode scoreDiagSOToNE");
		Planche p2 = new Planche(this.planche);
		// colonne pleine
		if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + "pleine.");
			return -1;
		}
		p2.ajouterPiece(col, this.getCouleur());
		int idx = p2.dernierPieceLigne(col);
		System.out.println("idx = " + idx);
		p2.printPlanche();
		// TODO : vérifier si col >= 3
		// si oui colDepart = col - 3
		
		int colDepart;
		int lineDepart;
		if (col <= 5-idx) {
			colDepart = 0;
			lineDepart = idx + col;
		} else {
			colDepart = idx - (5- idx);
			lineDepart = 5;
		}
		System.out.println("colDepart = " + colDepart);
		System.out.println("lineDepart = " + lineDepart);

		int score = 0;
		
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
		
		int streak;
		int j = 0;
		int k = lineDepart;
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
		return score;
	}
	
	
	//TODO scoreDiagNOToSE
}
