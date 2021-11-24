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
		p.ajouterPiece(2, "X");
		p.printPlanche();

		//j2.scoreVertical(1);
		//System.out.println("score vertical : " + j2.scoreVertical(1));
		System.out.println("score horizontal : " + j2.scoreHorizontal(4));
		//p.printPlanche();
	}

}
