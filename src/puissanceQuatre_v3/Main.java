package puissanceQuatre_v3;

public class Main {

	public static void main(String[] args) {
		//Jeu jeu = new Jeu("x", "o");
		//jeu.commencerJeu();
		Planche p = new Planche();
		Joueur j2 = new JoueurAuto("X", p);
		Joueur j1 = new JoueurHumain("O", p);
		Jeu jeu = new Jeu(j1, j2, p);
		jeu.commencerJeu();
		//p.ajouterPiece(0, "X");
		/*
		p.ajouterPiece(1, "X");p.ajouterPiece(1, "X");
		p.ajouterPiece(2, "X");p.ajouterPiece(2, "X");p.ajouterPiece(2,  "X");
		p.ajouterPiece(3, "X");p.ajouterPiece(3, "X");p.ajouterPiece(3, "X");p.ajouterPiece(3, "X");
		p.ajouterPiece(4, "X");p.ajouterPiece(4, "X");p.ajouterPiece(4, "X");p.ajouterPiece(4, "X");//p.ajouterPiece(4, "X");
		p.ajouterPiece(5, "X");p.ajouterPiece(5, "X");p.ajouterPiece(5, "X");p.ajouterPiece(5, "X");p.ajouterPiece(5, "X");
		*/
		/*p.printPlanche();
		
		p.ajouterPiece(4, "X");//p.ajouterPiece(4, "X");
		p.ajouterPiece(3, "X");p.ajouterPiece(3, "X");//p.ajouterPiece(3, "X");
		p.ajouterPiece(2, "X");p.ajouterPiece(2, "X");p.ajouterPiece(2, "X");p.ajouterPiece(2, "X");
		p.ajouterPiece(1, "X");p.ajouterPiece(1, "X");p.ajouterPiece(1, "X");p.ajouterPiece(1, "X");p.ajouterPiece(1, "X");
		
		System.out.println("score diagonale : " + j2.fullScoreCol(3));*/

		//p.printPlanche();
	}

}
