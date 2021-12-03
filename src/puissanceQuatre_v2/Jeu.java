package puissanceQuatre_v2;
 
import java.util.Random;
import java.util.Scanner;

import puissanceQuatre_v3.JoueurAuto;
import puissanceQuatre_v3.JoueurAutoFacile;

public class Jeu {
	
	private Planche planche;
	private static Joueur joueur1;
	private static Joueur joueur2;
	
	private boolean joueur1Tour;
	
	Scanner input;

	public Jeu(String couleur1, String couleur2) {
		this.planche = new Planche();
		this.joueur1 = new Joueur(couleur1);
		this.joueur2 = new Joueur(couleur2);
		
		this.joueur1Tour = (new Random()).nextBoolean();
		input = new Scanner(System.in);
		
	}
	
	public boolean checkForWinner(int col) {
		
		int ligne = this.planche.dernierPieceLigne(col);
		int compt = 1;
		String couleur = this.planche.puissance4Planche[ligne][col].getCouleur();
		
		// horizontal
		for(int i = 1; i < 4; i++) {
			if(ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col] != null) {
					if(this.planche.puissance4Planche[ligne+i][col].getCouleur() == couleur)
						compt++;
				}
			}
			if(ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne-i][col] != null) {
					if(this.planche.puissance4Planche[ligne-i][col].getCouleur() == couleur)
						compt++;
				}
			}	
		}
		
		if(compt == 4)	return true;
		else compt = 1;
		
		// vertical
		for(int i = 1; i < 4; i++) {
			if(col + i < 7) {
				if(this.planche.puissance4Planche[ligne][col + i] != null) {
					if(this.planche.puissance4Planche[ligne][col + i].getCouleur() == couleur)
						compt++;
				}
			}
			if(col - i >= 0) {
				if(this.planche.puissance4Planche[ligne][col - i] != null) {
					if(this.planche.puissance4Planche[ligne][col - i].getCouleur() == couleur)
						compt++;
				}
			}	
		}
		
		if(compt == 4)	return true;
		else compt = 1;
		
		
		// diagonale vers la droite
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col + i].getCouleur() == couleur)
						compt++;
				}
			}
			if(col - i >= 0 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col - i].getCouleur() == couleur)
						compt++;
				}
			}	
		}
		
		if(compt == 4)	return true;
		else compt = 1;
		
		// diagonale vers la gauche
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col + i].getCouleur() == couleur)
						compt++;
				}
			}
			if(col - i >= 0 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col - i].getCouleur() == couleur)
						compt++;
				}
			}	
		}
		
		if(compt == 4)	return true;
		else compt = 1;

		return false;
		
	}
	
	
	
	public void commencerJeu() {
		
		boolean jeuEnCours = true;
		
		while(jeuEnCours) {
			
			planche.printPlanche();
			
			String couleur;
			if(joueur1Tour) {
				couleur = joueur1.getCouleur();
				System.out.println("Le tour de joueur 1");
			} else {
				couleur = joueur2.getCouleur();
				System.out.println("Le tour de joueur 2");
			}
			
			System.out.println("Choisissez la colonne où vous voulez ajouter votre piièce.");
			System.out.print("Choisir entre 1 and " + planche.getColonnes() + ": ");
			
			Scanner input = new Scanner(System.in);
			int colonne = input.nextInt() - 1;
			
			int succes = planche.ajouterPiece(colonne, couleur);		
			while(succes == -1) {
				System.out.println("La colonne est pleine");
				System.out.print("Choisir entre 1 and " + planche.getColonnes() + ": ");
				colonne = input.nextInt() - 1;
				
				succes = planche.ajouterPiece(colonne, couleur);
			}
			
			if(this.checkForWinner(colonne)) {
				planche.printPlanche();
				if(joueur1Tour)
					System.out.println("Joueur 1 a gagné!");
				else
					System.out.println("Joueur 2 a gagné!");
				
				System.out.println("Vous voulez jouer encore une fois ? (Y/N): ");
				
				if(input.next().toUpperCase().equals("Y"))
					this.reinitialiser();
				else
					jeuEnCours = false;
			}
			
			joueur1Tour = !joueur1Tour;
		}
		input.close();
	}

	private void reinitialiser() {
		this.planche = new Planche();
		this.joueur1Tour = (new Random()).nextBoolean();
	}
}


