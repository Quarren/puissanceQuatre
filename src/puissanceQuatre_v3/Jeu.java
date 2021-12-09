package puissanceQuatre_v3;

import java.util.Random;
import java.util.Scanner;

public class Jeu {

	private Planche planche;
	private static Joueur joueur1;
	private static Joueur joueur2;

	private boolean joueur1Tour;

	Scanner input;

	// créer un jeu entre 2 joueurs humains
	public Jeu(String couleur1, String couleur2) {
		this.planche = new Planche();
		this.joueur1 = new JoueurHumain(couleur1, planche);
		this.joueur2 = new JoueurHumain(couleur2, planche);

		this.joueur1Tour = (new Random()).nextBoolean();
		input = new Scanner(System.in);

	}

	// créer un jeu en choisissant un mix JoueurHumain / JoueurAuto
	public Jeu(Joueur j1, Joueur j2, Planche p) {
		this.planche = new Planche();
		this.joueur1 = j1;
		this.joueur2 = j2;
		this.planche = p;
		this.joueur1Tour = (new Random().nextBoolean());
		input = new Scanner(System.in);
	}

	public Joueur playing() {
		if(joueur1Tour) {
			return joueur1;
		} else {
			return joueur2;
		}
	}
	/*public boolean checkForWinner(int col) {
		// On détermine la première ligne disponible sur la colonne choisit en paramètre
		int ligne = this.planche.dernierPieceLigne(col);
		
		// Compteur de pièces de même couleur alignées
		int compt = 1;
		String couleur = this.planche.puissance4Planche[ligne][col].getCouleur();

		// Vertical : on regarde vers le bas
		for(int i = 1; i < 4; i++) {
			if(ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col] != null) {
					if(this.planche.puissance4Planche[ligne+i][col].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				} 
			}
			

			if(compt >= 4)	return true;
			else compt = 1;

		}
		
		// Horizontal
		for(int i = 1; i < 4; i++) {
			if(col + i < 7) {
				if(this.planche.puissance4Planche[ligne][col + i] != null) {
					if(this.planche.puissance4Planche[ligne][col + i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}
			if(col - i >= 0) {
				if(this.planche.puissance4Planche[ligne][col - i] != null) {
					if(this.planche.puissance4Planche[ligne][col - i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}	
		}

		if(compt >= 4)	return true;
		else compt = 1;


		// diagonale vers la droite
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col + i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}
			if(col - i >= 0 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col - i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}	
		}

		if(compt >= 4)	return true;
		else compt = 1;

		// diagonale vers la gauche
		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col + i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}
			if(col - i >= 0 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col - i].getCouleur() == couleur) {
						compt++;
					} else compt = 1;
				}
			}	
		}

		if(compt >= 4)	return true;
		else compt = 1;

		return false;

	}*/
	public boolean checkForWinner(int col) {

		int ligne = this.planche.dernierPieceLigne(col);
		int compt = 1;
		String couleur = this.planche.puissance4Planche[ligne][col].getCouleur();
		for(int i = 1; i < 4; i++) {
			if(ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col] != null) {
					if(this.planche.puissance4Planche[ligne+i][col].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne-i][col] != null) {
					if(this.planche.puissance4Planche[ligne-i][col].getCouleur() == couleur)
						compt++;
				} else break;
			}

		}

		if(compt >= 4) return true;
		else compt = 1;

		for(int i = 1; i < 4; i++) {
			if(col + i < 7) {
				if(this.planche.puissance4Planche[ligne][col + i] != null) {
					if(this.planche.puissance4Planche[ligne][col + i].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(col - i >= 0) {
				if(this.planche.puissance4Planche[ligne][col - i] != null) {
					if(this.planche.puissance4Planche[ligne][col - i].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}

		if(compt >= 4) return true;
		else compt = 1;

		for(int i = 1; i < 4; i++) {
			if(col + i < 7 && ligne + i < 6) {
				if(this.planche.puissance4Planche[ligne + i][col + i] != null) {
					if(this.planche.puissance4Planche[ligne + i][col + i].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}
		for(int i = 1; i < 4; i++) {
			if(col - i >= 0 && ligne - i >= 0) {
				if(this.planche.puissance4Planche[ligne - i][col - i] != null) {
					if(this.planche.puissance4Planche[ligne - i][col - i].getCouleur() == couleur)
						compt++;
				} else break;
			}
		}

		if(compt >= 4) return true;
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
				} else break;
			}
		}

		if(compt >= 4) return true;
		else compt = 1;

		return false;

	}



	public void commencerJeu() {

		boolean jeuEnCours = true;

		while(jeuEnCours) {
			
			planche.printPlanche();

			String couleur;
			int checkThisCol;
			
			if(joueur1Tour) {
				couleur = joueur1.getCouleur();
				System.out.println("Le tour de joueur 1 " + "(" + joueur1.getCouleur() + ")");
			} else {
				couleur = joueur2.getCouleur();
				System.out.println("Le tour de joueur 2 " + "(" + joueur2.getCouleur() + ")");
			}

			System.out.println("Choisissez la colonne où vous voulez ajouter votre pièce.");
			System.out.print("Choisir entre 1 et " + planche.getColonnes() + ": ");

			if(this.playing() instanceof JoueurAuto) {
				checkThisCol = ((JoueurAuto)this.playing()).makeMove();
				joueur1Tour = !joueur1Tour;
				
				if(checkForWinner(checkThisCol)) {
					planche.printPlanche();
					if(joueur1Tour) {
						System.out.println("Joueur 1 a gagné !");
					} else {
						System.out.println("Joueur 2 a gagné !");
					}
					System.out.println("Vous voulez jouer encore une fois ? (Y/N): ");

					if(input.next().toUpperCase().equals("Y")) {
						this.reinitialiser();
					} else {
						jeuEnCours = false;
					}
				}
				

			} else {
				Scanner input = new Scanner(System.in);
				int colonne = input.nextInt() - 1;
				int succes = planche.ajouterPiece(colonne, couleur);		
				
				while(succes == -1) {
					System.out.println("La colonne est pleine");
					System.out.print("Choisir entre 1 et " + planche.getColonnes() + ": ");
					colonne = input.nextInt() - 1;

					succes = planche.ajouterPiece(colonne, couleur);

				}
				if(checkForWinner(colonne)) {
					planche.printPlanche();
					if(joueur1Tour) {
						System.out.println("Joueur 1 a gagné !");
					} else {
						System.out.println("Joueur 2 a gagné !");
					}
					System.out.println("Vous voulez jouer encore une fois ? (Y/N): ");

					if(input.next().toUpperCase().equals("Y")) {
						this.reinitialiser();
					} else {
						jeuEnCours = false;
					}
				}
				//input.close();
				joueur1Tour = !joueur1Tour;
			}
		}
	}

	private void reinitialiser() {
		this.planche.newPlanche();
		this.joueur1Tour = (new Random()).nextBoolean();
	}


	/* Calculer le nombre de lignes de 2, 3 et 4 pièces dans tous les alignements possibles
	 * Puis multiplier par le score assigné à chacun dans l'attribut heuristics de JoueurAuto
	 * Le JoueurAuto jouera dans la colonne avec le score le plus élevé
	 * Warning : pos et col sont des indices, <=> num de ligne/colonne -1
	 */
	public int checkHorizontal(JoueurAuto j, int pos, int col) {
		int colScore = 0;
		int colStart;
		int colEnd;
		if (col <= 3) {
			colStart = 0;
			colEnd = col + 3;
		} else {
			colStart = col - 3;
			colEnd = planche.getColonnes()-1;
		}
		while (colStart + 3 <= colEnd)  {
			int pAlignees;
			for (int i = 0; i < 3; i++) {
				pAlignees = 0;
				if (this.planche.puissance4Planche[colStart+i][pos+1].getCouleur() != j.getCouleur() &&
						this.planche.puissance4Planche[colStart+i][pos+1].getCouleur() != null) {
					pAlignees = 0;
					break;
				} else {
					if (this.planche.puissance4Planche[colStart+i][pos+1].getCouleur() == j.getCouleur()) {
						pAlignees++;
					}
				}
				if (pAlignees == 0) {
					colScore = 0;
				} else {
					colScore += pAlignees * j.heuristics[pAlignees-1];
				}
			}
			colStart++;
		}
		return colScore;
	}

	public int checkVertical(JoueurAuto j, int pos, int col) {
		int s = 1;
		for (int i = pos; i > 0; i--) {
			if (this.planche.puissance4Planche[col][i].getCouleur() != j.getCouleur()) {
				break;
			} else {
				s++;
			}
		}
		return s * j.heuristics[s-1];
	}

	// Sud-Ouest vers Nord-Est
	public int checkDiagSOToNE(JoueurAuto j, int pos, int col) {
		int s = 0;
		int colStart, linStart;

		// trouver le point de départ de la diagonale
		if (col <= 3 && pos+1 <= 3) {
			if (pos+1 <= col) {
				linStart = 0;
				colStart = col + (0 - (pos+1)); 
			} else {
				colStart = 0;
				linStart = (pos+1) + (0-col);
			}
		} else {
			colStart = col-3;
			linStart = (pos+1)-3;
		}

		// on veut trouver le nombre d'itérations à effectuer
		int colEnd = col;
		int linEnd = pos+1;
		while (planche.getColonnes()-1 > colEnd && planche.getLignes()-1 > linEnd) {
			colEnd++;
			linEnd++;
		}

		for(int i = 0; i < colEnd-colStart; i++) {
			int nbp = 1;  // on compte le pion qu'on voudrait poser
			int idx = 0;
			while (linStart+i < planche.getLignes()-1 && colStart+i < planche.getColonnes()-1 && idx < 4) {
				if (this.planche.puissance4Planche[colStart+ i][linStart + i].getCouleur() != j.getCouleur()
						&& this.planche.puissance4Planche[colStart + i][linStart + i].getCouleur() != null) {
					nbp = 1; // on reset le compteur de pions alignés
				} else {
					if (this.planche.puissance4Planche[colStart + i][linStart + i].getCouleur() == j.getCouleur()) {
						nbp++;
					}
				}
				idx++;
			}
			if (nbp != 0) {
				s += nbp * j.heuristics[nbp-1];
			}
		}	
		return s;
	}

	// Nord-Ouest vers Sud-Est
	public int checkDiagNOToSE(JoueurAuto j, int pos, int col) {
		//TODO
		int s = 0;
		int colStart, linStart;

		// trouver le point de départ de la diagonale
		if (col <= 3 && pos+1 <= 3) {
			if (pos+1 <= col) {
				linStart = 0;
				colStart = col + (0 - (pos+1)); 
			} else {
				colStart = 0;
				linStart = (pos+1) + (0-col);
			}
		} else {
			colStart = col-3;
			linStart = (pos+1)-3;
		}

		// on veut trouver le nombre d'itérations à effectuer
		int colEnd = col;
		int linEnd = pos+1;
		while (planche.getColonnes()-1 > colEnd && planche.getLignes()-1 > linEnd) {
			colEnd++;
			linEnd++;
		}

		for(int i = 0; i < colEnd-colStart; i++) {
			int nbp = 1;  // on compte le pion qu'on voudrait poser
			int idx = 0;
			while (linStart+i < planche.getLignes()-1 && colStart+i < planche.getColonnes()-1 && idx < 4) {
				if (this.planche.puissance4Planche[colStart+ i][linStart + i].getCouleur() != j.getCouleur()
						&& this.planche.puissance4Planche[colStart + i][linStart + i].getCouleur() != null) {
					nbp = 1; // on reset le compteur de pions alignés
				} else {
					if (this.planche.puissance4Planche[colStart + i][linStart + i].getCouleur() == j.getCouleur()) {
						nbp++;
					}
				}
				idx++;
			}
			if (nbp != 0) {
				s += nbp * j.heuristics[nbp-1];
			}
		}	
		return s;
	}

	//TODO donner les poids pour chaques lignes et la colonne centrale



}


