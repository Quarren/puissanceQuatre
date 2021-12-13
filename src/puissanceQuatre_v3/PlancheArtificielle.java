package puissanceQuatre_v3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puissanceQuatre_v2.Planche;

public class PlancheArtificielle extends Planche{
	
	private int score;
	private int action;
	//Constructeurs
	/********************************************************************************************************************/
	public PlancheArtificielle() {
		super();
	}

	public PlancheArtificielle(PlancheArtificielle p) {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++)
				
					this.puissance4Planche[ligne][col] = p.puissance4Planche[ligne][col];
		}
		this.setAction(p.getAction());
		this.setScore(p.getScore());
	}
	/********************************************************************************************************************/
	//copier la planche artificille p dans this
	public void dupliquer(PlancheArtificielle p) {
		reinitialserPlanche();
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++)
				if(p.puissance4Planche[ligne][col] != null)
					this.puissance4Planche[ligne][col] = p.puissance4Planche[ligne][col];
		}
		this.score = p.getScore();
		this.action = p.getAction();
	}
	
	//getters et setters des attributs
	/********************************************************************************************************************/
	public void setAction(int action) {
		this.action = action;
	}
	
	public int getAction() {
		return action;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	/********************************************************************************************************************/

	//getCoordPieces() récupère toutes les coordonnées des "x" et "o" dans this.planche sous form de Map
	private Map<String, List<int[]>> getCoordPieces() {
		Map<String, List<int[]>> res = new HashMap<>();
		res.put(IAJoueur.couleurIA, new ArrayList<int[]>());
		res.put(IAJoueur.couleurAdv, new ArrayList<int[]>());
		for(int ligne = 0; ligne < Planche.getLignes(); ligne++) {
			for(int col = 0; col < Planche.getColonnes(); col++) {
				if(this.puissance4Planche[ligne][col] != null) {
					String couleur = this.puissance4Planche[ligne][col].getCouleur();
					if(couleur.equals(IAJoueur.couleurIA)) {
						res.get(IAJoueur.couleurIA).add(new int[] {ligne , col});
					} else if(couleur.equals(IAJoueur.couleurAdv)) {
						res.get(IAJoueur.couleurAdv).add(new int[] {ligne , col});
					}
				}
			}
		}
		return res;
	}
	/********************************************************************************************************************/
	//scoreDePlanche() donne un score à une planche artificielle donnée en se basant sur l'alignement des pièces des deux joueurs
	protected int scoreDePlanche() {
		int scoreIA = 0;
        int scoreAdv = 0;
		for (int[] coord : getCoordPieces().get(IAJoueur.couleurIA)) {
			int ligne = coord[0];
			int col = coord[1] ;
			int[] nbPiecesAlign = this.verifAlign(IAJoueur.couleurIA, ligne, col);
			scoreIA += 50 * nbPiecesAlign[3];
			scoreIA += nbPiecesAlign[1] * 4 + nbPiecesAlign[2] * 8;
		}
		

        for (int[] coord : getCoordPieces().get(IAJoueur.couleurAdv)) {
			int ligne = coord[0];
			int col = coord[1];
			int[] nbPiecesAlign = this.verifAlign(IAJoueur.couleurAdv, ligne, col);
			scoreAdv -= 50 * nbPiecesAlign[3];
			scoreAdv -= nbPiecesAlign[1] * 2 + nbPiecesAlign[2] * 4;
			
        }
        
		return (scoreIA + scoreAdv);
	}
	/********************************************************************************************************************/
	//Les méthodes ci-dessous revoient le nombres des pièces alignées de une ligne, colonne, diagonale et l'antidiagonale
    private int verifAlignLigne(String couleur, int ligne, int col) {
        int compt = 1;
        for(int i = 1; i < 4; i++) {
            if (ligne + i < Planche.getLignes()) {
                if (this.puissance4Planche[ligne + i][col] != null) {
                    if (this.puissance4Planche[ligne + i][col].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(ligne - i >= 0) {
                if(this.puissance4Planche[ligne-i][col] != null) {
                    if(this.puissance4Planche[ligne-i][col].getCouleur().equals(couleur))
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
                if (this.puissance4Planche[ligne][col+i] != null) {
                    if (this.puissance4Planche[ligne][col+i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col - i >= 0) {
                if(this.puissance4Planche[ligne][col-i] != null) {
                    if(this.puissance4Planche[ligne][col-i].getCouleur().equals(couleur))
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
                if (this.puissance4Planche[ligne-i][col-i] != null) {
                    if (this.puissance4Planche[ligne-i][col-i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col + i < Planche.getColonnes() && ligne + i < Planche.getLignes()) {
                if(this.puissance4Planche[ligne+i][col+i] != null) {
                    if(this.puissance4Planche[ligne+i][col+i].getCouleur().equals(couleur))
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
                if (this.puissance4Planche[ligne-i][col+i] != null) {
                    if (this.puissance4Planche[ligne-i][col+i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        for(int i = 1; i < 4; i++) {
            if(col - i >= 0 && ligne + i < Planche.getLignes()) {
                if(this.puissance4Planche[ligne+i][col-i] != null) {
                    if(this.puissance4Planche[ligne+i][col-i].getCouleur().equals(couleur))
                        compt++;
                    else break;
                } else break;
            }
        }
        return compt;
    }
    /********************************************************************************************************************/
    //la methode verifAlign() renvoie un tableau contiens combien des pièces de 1 et 2 et 3 et 4 sont alignés
    //Si le tableaux renvoyé est [4, 2, 3, 1] :
    //ça veut dire qu'il y 4 pièces seules et 2 alignement de deux piéces et 3 alignement de trois péces et 1 alignement de quatre pèces 
    private int[] verifAlign(String couleur, int ligne, int col) {
		
    	int[] res = new int[4];
    	int[] nbAlign = new int[4];
    	
    	nbAlign[0] = verifAlignLigne(couleur, ligne, col);
    	nbAlign[1] = verifAlignCol(couleur, ligne, col);
    	nbAlign[2] = verifAlignDiag(couleur, ligne, col);
    	nbAlign[3] = verifAlignAntiDiag(couleur, ligne, col);
        
    	for(int i = 0; i < nbAlign.length; i++) {
    		
    			if(nbAlign[i] == 1) res[0]++;
        		else if(nbAlign[i] == 2) res[1]++;
        		else if(nbAlign[i] == 3) res[2]++;
        		else if(nbAlign[i] >= 4) res[3]++;
    		
    	}
    	return res;
    }
    /********************************************************************************************************************/
    //réinitialser this.planche avec des null dans la grille
	private void reinitialserPlanche() {
		for(int ligne = 0; ligne < lignes; ligne++) {
			for(int col = 0; col < colonnes; col++)
					this.puissance4Planche[ligne][col] = null;
		}
	}

	
}
