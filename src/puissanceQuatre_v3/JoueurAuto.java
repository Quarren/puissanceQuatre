package puissanceQuatre_v3;

public abstract class JoueurAuto extends Joueur {
	
	public int [] heuristics = {0, 2, 3, 1000};
	public int colMilieu = 5;
	
	public JoueurAuto(String couleur, Planche planche) {
		super(couleur, planche);
	}
	
	// calcule le score sur la colonne col
	public int scoreVertical(int col) {
		//System.out.println("Appel m�thode scoreVertical()");
		Planche p2 = new Planche(this.planche);
		//System.out.println("planche p2 copie de plance avant ajout");
		//p2.printPlanche();
		if (p2.dernierPieceLigne(col) == 0) {
			System.out.println("colonne " + col + " pleine.");
			return -1;
		}
		p2.ajouterPiece(col, this.getCouleur());
		//System.out.println("planche p2 apr�s ajout");
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
	
	//TODO � tester
	public int scoreHorizontal(int col) {
		System.out.println("appel m�thode scoreHorizontal");
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
				//System.out.println("it�ration n�" + i);
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
	//TODO scoreDiagNOToSE
}
