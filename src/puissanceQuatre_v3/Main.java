package puissanceQuatre_v3;

public class Main {

	public static void main(String[] args) {
		//Jeu jeu = new Jeu("x", "o");
		//jeu.commencerJeu();
		Planche p = new Planche();
		Joueur j2 = new JoueurAutoDifficile("X", p, "O");
		Joueur j1 = new JoueurHumain("O", p);
		Jeu jeu = new Jeu(j1, j2, p);
		jeu.commencerJeu();

	}

}
