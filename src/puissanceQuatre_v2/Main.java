package puissanceQuatre_v2;


import java.util.Scanner;

public class Main {


	public static void main(String[] args) {

		Jeu jeu = new Jeu("x", "o");
		Scanner input = new Scanner(System.in);
		jeu.activerIA();
		jeu.commencerJeu();

	}

}
