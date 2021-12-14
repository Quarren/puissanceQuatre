package puissanceQuatre_v2;
 
import puissanceQuatre_v2.IA.AlphaBetaJoueur;
import puissanceQuatre_v2.IA.MinmaxJoueur;

import java.util.Random;
import java.util.Scanner;

public class Jeu {

	private Planche planche;
	private MinmaxJoueur minmaxJoueur;
	private AlphaBetaJoueur alphaBetaJoueur;
	private static Joueur joueur1;
	private static Joueur joueur2;

	private boolean joueur1Tour;
	private boolean modeMinMax;
	private boolean modeAlphaBeta;

	Scanner input;
	
	public Jeu(Planche planche) {
		this.planche = planche;
	}

	public Jeu(String couleur1, String couleur2) {
		this.planche = new Planche();
		this.joueur1 = new Joueur(couleur1);
		this.joueur2 = new Joueur(couleur2);
		this.joueur1Tour = (new Random()).nextBoolean();
		this.modeMinMax = false;
		input = new Scanner(System.in);
	}

	public boolean checkForWinner(int col) {

		int ligne = this.planche.dernierPieceLigne(col);
		int compt = 1;
		String couleur = this.planche.puissance4Planche[ligne][col].getCouleur();
		for(int i = 1; i < 4; i++) {
			if(ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col] != null) {
					if(this.planche.puissance4Planche[ligne+i][col].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne-i][col] != null) {
					if(this.planche.puissance4Planche[ligne-i][col].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}	
			
		}
		
		if(compt >= 4)	return true;
		else compt = 1;
		
		for(int i = 1; i < 4; i++) {
			if(col + i < 7) {
				if(this.planche.puissance4Planche[ligne][col + i] != null) {
					if(this.planche.puissance4Planche[ligne][col + i].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(col - i >= 0) {
				if(this.planche.puissance4Planche[ligne][col - i] != null) {
					if(this.planche.puissance4Planche[ligne][col - i].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}
		}

		if(compt >= 4)	return true;
		else compt = 1;
		
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col + i].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(col - i >= 0 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col - i].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}	
		}

		if(compt >= 4)	return true;
		else compt = 1;
		
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col + i].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(col - i >= 0 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col - i].getCouleur() == couleur)
						compt++;
					else break;
				} else break;
			}	
		}
		
		if(compt >= 4)	return true;
		else compt = 1;

		return false;
		
	}
	
	
	
	public void commencerJeu() {
		
		boolean jeuEnCours = true;
		
		while(jeuEnCours) {
			
			planche.printPlanche();
			
			String couleur;
			if (!modeMinMax || !modeAlphaBeta) {
				if(joueur1Tour) {
					couleur = joueur1.getCouleur();
					System.out.println("Le tour de joueur 1");
				} else {
					couleur = joueur2.getCouleur();
					System.out.println("Le tour de joueur 2");
				}
			} else {
				
				if(joueur1Tour) {
					couleur = joueur1.getCouleur();
					System.out.println("Le tour de joueur 1");
				} else {
					couleur = joueur2.getCouleur();
					System.out.println("Le tour de l'ordinateur");
				}
			}

			int colonne;
			if(modeMinMax && !joueur1Tour) {
				minmaxJoueur.setPlanche(planche);
				long startTime = System.currentTimeMillis();
				colonne = minmaxJoueur.meilleurChoixCol();
				long elapsedTime = System.currentTimeMillis() - startTime;
			    System.out.println("temps de réponse : " + elapsedTime);
			} else if(modeAlphaBeta && !joueur1Tour) {
				alphaBetaJoueur.setPlanche(planche);
				long startTime = System.currentTimeMillis();
				colonne = alphaBetaJoueur.meilleurChoixCol();
				long elapsedTime = System.currentTimeMillis() - startTime;
			    System.out.println("temps de réponse : " + elapsedTime);
			} else {
				System.out.println("Choisissez la colonne où vous voulez ajouter votre pièce.");
				System.out.print("Choisir entre 1 and " + Planche.getColonnes() + ": ");
				Scanner input = new Scanner(System.in);
				colonne = input.nextInt() - 1;
			}
			
			int succes = planche.ajouterPiece(colonne, couleur);

			while(succes == -1) {
				System.out.println("La colonne est pleine");
				if(modeMinMax && !joueur1Tour) colonne = minmaxJoueur.meilleurChoixCol();
				else {
					System.out.print("Choisir entre 1 and " + Planche.getColonnes() + ": ");
					colonne = input.nextInt() - 1;
				}
				succes = planche.ajouterPiece(colonne, couleur);
			}
			
			if(this.checkForWinner(colonne)) {
				planche.printPlanche();
				if(modeMinMax || modeAlphaBeta) {
					if(joueur1Tour)
						System.out.println("Joueur 1 a gagné!");
					else
						System.out.println("L'Ordinateur a gagner");
				} else {
					if(joueur1Tour)
						System.out.println("Joueur 1 a gagné!");
					else
						System.out.println("Joueur 2 a gagné!");
				}
				
				System.out.println("Vous voulez jouer encore une fois ? (Y/N): ");
				
				if(input.next().toUpperCase().equals("Y"))
					this.reinitialiser();
				else
					jeuEnCours = false;
			}
			
			if(planche.planchePleine()) {
				
				System.out.println("La planche est pleine, Voulez jouer encore une fois ? (Y/N): ");
				
				if(input.next().toUpperCase().equals("Y"))
					this.reinitialiser();
				else
					jeuEnCours = false;
			}
			//if(modeIA) minmaxJoueur.minmaxJoueurTour = joueur1Tour;
			joueur1Tour = !joueur1Tour;
		}
		input.close();
	}

	private void reinitialiser() {
		this.planche = new Planche();
		this.joueur1Tour = (new Random()).nextBoolean();
	}
	
	public void activerAlphaBeta() {
		alphaBetaJoueur = new AlphaBetaJoueur(joueur2.getCouleur(), joueur1.getCouleur());
		alphaBetaJoueur.minmaxJoueurTour = !joueur1Tour;
		this.modeAlphaBeta = true;
	}
	
	public void activerMinMax() {
		minmaxJoueur = new MinmaxJoueur(joueur2.getCouleur(), joueur1.getCouleur());
		minmaxJoueur.minmaxJoueurTour = !joueur1Tour;
		this.modeMinMax = true;
	}
}