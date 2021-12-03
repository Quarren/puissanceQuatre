package puissanceQuatre_v2.IA;

import java.util.*;

import puissanceQuatre_v2.*;

public class MinmaxJoueur extends IAJoueur {
	
	public boolean minmaxJoueurTour;
	
	
	public MinmaxJoueur(String couleurIA, String couleurAdv) {
		super(couleurIA, couleurAdv);
	}

    public int meilleurChoixCol() {
        return minmax(3, couleurIA);
    }
	
	private int minmax(int profondeur, String couleur) {
		
		int meilleurScore;
		int scoreCourant;
		int meilleurCol = -1;
		if(couleur.equals(couleurIA)) {
			meilleurScore = Integer.MIN_VALUE;
			minmaxJoueurTour = true;
		} else {
			meilleurScore = Integer.MAX_VALUE;
			minmaxJoueurTour = false;
		}
			
		List<Integer> prochainesAct = genererActions();
		
        if(prochainesAct.isEmpty() || profondeur == 0) {
            meilleurScore = getScore();
        } else {
            for(Integer action : prochainesAct) {
                planche.ajouterPiece(action, couleur);
                if(couleur.equals(couleurIA)) {
                    scoreCourant = minmax(profondeur - 1, couleur);
                    if (scoreCourant > meilleurScore) {
                        meilleurScore = scoreCourant;
                        meilleurCol = action;
                    }
                } else {
                    scoreCourant = minmax(profondeur - 1, couleur);
                    if (scoreCourant < meilleurScore) {
                        meilleurScore = scoreCourant;
                        meilleurCol = action;
                    }
                }
                
            }
            minmaxJoueurTour = !minmaxJoueurTour;
            if(couleur.equals(couleurIA)) {
            	couleur = couleurAdv;
    		} else {
    			couleur = couleurIA;
    		}
        }
        //System.err.println(couleur);
		return meilleurCol;
	}
	
	private List<Integer> genererActions() {
		
		List<Integer> prochaineActions = new ArrayList<>();
		Planche planche;
		Jeu jeu;
		int i = 0;
		
		do {
			planche = new Planche(this.planche);
			jeu = new Jeu(planche);
			if(planche.puissance4Planche[0][i] == null) {
				if(minmaxJoueurTour)
					planche.ajouterPiece(i, couleurIA);
				else
					planche.ajouterPiece(i, couleurAdv);
				prochaineActions.add(i);
				if(jeu.checkForWinner(i)) return prochaineActions;
			}
			i++;	
		} while(i < Planche.getColonnes());
		return prochaineActions;
	}
	
	private Map<String, List<int[]>> getCoordPieces() {
		Map<String, List<int[]>> res = new HashMap<>();
		res.put(couleurIA, new ArrayList<int[]>());
		res.put(couleurAdv, new ArrayList<int[]>());
		for(int ligne = 0; ligne < Planche.getLignes(); ligne++) {
			for(int col = 0; col < Planche.getColonnes(); col++) {
				if(planche.puissance4Planche[ligne][col] != null) {
					String couleur = planche.puissance4Planche[ligne][col].getCouleur();
					if(couleur.equals(couleurIA)) {
						res.get(couleurIA).add(new int[] {ligne, col});
					} else if(couleur.equals(couleurAdv)) {
						res.get(couleurAdv).add(new int[] {ligne, col});
					}
				}
			}
		}
		return res;
	}

	private int getScore() {
		int scoreIA = 1;
        int scoreAdv = -1;
		for (int[] coord : getCoordPieces().get(couleurIA)) {
			scoreIA = 1;
			int ligne = coord[0];
			int col = coord[1];
            int nbPiecesLigne = verifAlignLigne(couleurIA, ligne, col);
            int nbPiecesCol = verifAlignCol(couleurIA, ligne, col);
            int nbPiecesDiag = verifAlignDiag(couleurIA, ligne, col);
            int nbPiecesAntiDiag = verifAlignAntiDiag(couleurIA, ligne, col);
            //System.err.println(scoreIA +  " - " +  nbPiecesLigne +  " - " + nbPiecesCol +  " - " + nbPiecesDiag +  " - " + nbPiecesAntiDiag);
			int score = Math.max(Math.max(nbPiecesLigne, nbPiecesCol), Math.max(nbPiecesDiag, nbPiecesAntiDiag)) * 10;
            scoreIA = Math.max(scoreIA * score, scoreIA);
		}

        for (int[] coord : getCoordPieces().get(couleurAdv)) {
        	scoreAdv = -1;
            int ligne = coord[0];
            int col = coord[1];
            int nbPiecesLigne = verifAlignLigne(couleurAdv, ligne, col);
            int nbPiecesCol = verifAlignCol(couleurAdv, ligne, col);
            int nbPiecesDiag = verifAlignDiag(couleurAdv, ligne, col);
            int nbPiecesAntiDiag = verifAlignAntiDiag(couleurAdv, ligne, col);
            int score = Math.max(Math.max(nbPiecesLigne, nbPiecesCol), Math.max(nbPiecesDiag, nbPiecesAntiDiag)) * 10;
            scoreAdv = Math.min(scoreAdv * score, scoreAdv);
        }

		return (scoreIA + scoreAdv);
	}

    private int verifAlignLigne(String couleur, int ligne, int col) {
        int compt = 1;
        for(int i = 1; i < 4; i++) {
            if (ligne + i < Planche.getLignes()) {
                if (this.planche.puissance4Planche[ligne + i][col] != null) {
                    if (this.planche.puissance4Planche[ligne + i][col].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(ligne - i >= 0) {
                if(this.planche.puissance4Planche[ligne-i][col] != null) {
                    if(this.planche.puissance4Planche[ligne-i][col].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        return compt;
    }

    private int verifAlignCol(String couleur, int ligne, int col) {
        int compt = 1;
        for(int i = 1; i < 4; i++) {
            if (col + i < Planche.getColonnes()) {
                if (this.planche.puissance4Planche[ligne][col+i] != null) {
                    if (this.planche.puissance4Planche[ligne][col+i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col - i >= 0) {
                if(this.planche.puissance4Planche[ligne][col-i] != null) {
                    if(this.planche.puissance4Planche[ligne][col-i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        return compt;
    }

    private int verifAlignDiag(String couleur, int ligne, int col) {
        int compt = 1;
        for(int i = 1; i < 4; i++) {
            if (col - i >= 0 && ligne - i >= 0) {
                if (this.planche.puissance4Planche[ligne-i][col-i] != null) {
                    if (this.planche.puissance4Planche[ligne-i][col-i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col + i < Planche.getColonnes() && ligne + i < Planche.getLignes()) {
                if(this.planche.puissance4Planche[ligne+i][col+i] != null) {
                    if(this.planche.puissance4Planche[ligne+i][col+i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        return compt;
    }

    private int verifAlignAntiDiag(String couleur, int ligne, int col) {
        int compt = 1;
        for(int i = 1; i < 4; i++) {
            if (col + i < Planche.getColonnes() && ligne - i >= 0) {
                if (this.planche.puissance4Planche[ligne-i][col+i] != null) {
                    if (this.planche.puissance4Planche[ligne-i][col+i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col - i >= 0 && ligne + i < Planche.getLignes()) {
                if(this.planche.puissance4Planche[ligne+i][col-i] != null) {
                    if(this.planche.puissance4Planche[ligne+i][col-i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        return compt;
    }






    /*
	public static void main(String[] args) {
		String feed;
		Planche planche = new Planche();
		MinmaxJoueur player = new MinmaxJoueur("x", "o");
		for (int i = 0; i < 20; i++) {
			if(i % 2 == 0) feed = "o";
			else feed = "x";
			int col = (int)((Math.random()) * 7);
			System.out.println(col);
			planche.ajouterPiece(col, feed);
		}
		planche.printPlanche();
		player.setPlanche(planche);
		System.err.println("The score is ====> " + player.getScore());
	}
	*/
}
