package puissanceQuatre_v3;

public class Main {

	public static void main(String[] args) {
		//Jeu jeu = new Jeu("x", "o");
		//jeu.commencerJeu();
		Planche p = new Planche();
		JoueurAutoFacile j2 = new JoueurAutoFacile("X", p);
		Joueur j1 = new JoueurHumain("O", p);
		Jeu jeu = new Jeu(j1, j2, p);
		//jeu.commencerJeu();
		p.ajouterPiece(1, "X");
		p.ajouterPiece(1, "X");
		p.ajouterPiece(1, "X");
		p.printPlanche();
		//System.out.println("derni�re pi�ce de col 1 : " + p.dernierPieceLigne(1));
		//j2.scoreVertical(1);
		System.out.println(j2.scoreVertical(1));
		p.printPlanche();
	}

}
