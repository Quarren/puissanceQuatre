package puissanceQuatre_v3;

public abstract class JoueurAuto extends Joueur {
	
	public int [] heuristics = {0, 2, 3, 1000};
	public int colMilieu = 5;
	
	public JoueurAuto(String couleur, Planche planche) {
		super(couleur, planche);
	}
	
	// calcule le score sur chacune des colonnes et renvoie l'indice de la colonne dont le score est le plus élevé
	public int scoreVertical(int col) {
		System.out.println("Appel méthode scoreVertical()");
		Planche p2 = new Planche(this.planche);
		System.out.println("planche p2 copie de plance avant ajout");
		p2.printPlanche();
		p2.ajouterPiece(col, this.getCouleur());
		System.out.println("planche p2 après ajout");
		p2.printPlanche();
		int idx = p2.dernierPieceLigne(col);
		int nbpAlignes = 0;
		System.out.println("nbpAlignes : " + nbpAlignes);
		for (int i = idx; i <= idx+3 && i <= 5; i++) {
			System.out.println("i = " + i);
			if (p2.puissance4Planche[i][col].getCouleur() == this.getCouleur()) {
				nbpAlignes++;
				System.out.println("nbpAlignes : " + nbpAlignes);
			} else {
				break;
			}
		}
		return nbpAlignes == 0 ? 0 : heuristics[nbpAlignes-1];
	}
	
	/*
	public int scoreHorizontal() {
		Planche p2 = new Planche();
	}*/
}
