package puissanceQuatre_v2;


import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void message1() {
		System.out.println("Bonjoue, voulez-vous joueur contre l'ordinateur ?");
		System.out.println("Pour OUI, tapez 1");
		System.out.println("Pour NON, tapez 0");
	}
	
	public static void message2() {
		System.out.println("Vous avez choisi de jouer contre l'ordinateur");
		System.out.println("Voulez-vous activer l'Élagage alpha-bêta ?");
		System.out.println("Pour OUI, tapez 1");
		System.out.println("Pour NON, tapez 0");
	}

	public static void main(String[] args) {
		
		message1();
		int decision = input.nextInt();
		
		while (decision != 1 && decision != 0) {
			message1();
			decision = input.nextInt();
		}
		
		if(decision == 1) {
			Jeu jeu = new Jeu("x", "o");
			message2();
			decision = input.nextInt();
			while (decision != 1 && decision != 0) {
				message1();
				decision = input.nextInt();
			}
			if(decision == 0) {
				jeu.activerMinMax();
				jeu.commencerJeu();
			} else {
				jeu.activerAlphaBeta();
				jeu.commencerJeu();
			}
		} else {
			Jeu jeu = new Jeu("x", "o");
			jeu.commencerJeu();
		}
		
		

	}

}
