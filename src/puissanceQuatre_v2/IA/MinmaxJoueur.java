package puissanceQuatre_v2.IA;

import java.util.*;

import puissanceQuatre_v2.*;

public class MinmaxJoueur extends IAJoueur {
	
	public boolean minmaxJoueurTour;
	
	//Profondeur de l'arbre de recherche
	private final int profondeur = 9;
	//Constructeurs
	/********************************************************************************************************************/
	public MinmaxJoueur() {
		super();
	}
	
	
	public MinmaxJoueur(String couleurIA, String couleurAdv) {
		super(couleurIA, couleurAdv);
		this.planche = new PlancheArtificielle();
	}
	/********************************************************************************************************************/
	
	//meilleurChoixCol() renvoie le choix optimal du joueurMinMax
	/********************************************************************************************************************/
	public int meilleurChoixCol() {
		return max(profondeur, IAJoueur.couleurIA, this.planche).getAction();
	}  
	/********************************************************************************************************************/
	
	//setPlanche(Planche plance) recopie la planche p dans this.planche
	public void setPlanche(Planche planche) {
		for(int ligne = 0; ligne < Planche.getLignes(); ligne++) {
			for(int col = 0; col < Planche.getColonnes(); col++)
				if(planche.puissance4Planche[ligne][col] != null)
					this.planche.puissance4Planche[ligne][col] = planche.puissance4Planche[ligne][col];
		}
	}
	
	
	//Les methode min et max s'appelle une dans la methode de l'autre jusqu'à que le profondeur == 0 ou pas d'action possible
	/************************************************************************************************************************************/
	protected PlancheArtificielle max(int profondeur, String couleur, PlancheArtificielle planche) {
		
		PlancheArtificielle p = new PlancheArtificielle();
		//Generer touts les actions possibles
		List<Integer> prochainesAct = genererActions(couleur);
		//Cas de base
		if (prochainesAct.isEmpty() || profondeur == 0) {
			p.dupliquer(planche);
			p.setScore(planche.scoreDePlanche());
			return p;
		} else {
			//nvPlanche est une planche intermédiaire
			PlancheArtificielle nvPlanche = new PlancheArtificielle(planche);
			//meilleurePlanche est la planche qui contien le meilleur score avec la meilleur action (colonne)
			PlancheArtificielle meilleurePlanche = new PlancheArtificielle(planche);
			nvPlanche.setScore(Integer.MIN_VALUE);
			//pour tout les actions possible on recupere la meilleur planche
			for(Integer action: prochainesAct) {
				//ajouter une piece
				nvPlanche.ajouterPiece(action, couleur);
				nvPlanche.setAction(action);
				p.dupliquer(min(profondeur - 1, couleurAdv, nvPlanche));
				if(nvPlanche.getScore() <= p.getScore()) {
					int [] swap = {p.getScore(), p.getAction()};
					meilleurePlanche.dupliquer(p);
					if(nvPlanche.getScore() == p.getScore()) {
						if((new Random()).nextBoolean()) {
							p.setScore(nvPlanche.getScore());
							p.setAction(action);
						}
					} else {
						p.setScore(nvPlanche.getScore());
						p.setAction(action);
					}
					nvPlanche.setScore(swap[0]);
					nvPlanche.setAction(swap[1]);
				}
				//enlever la pièce ajoutée pour passer à la prochaine action
				nvPlanche.puissance4Planche[nvPlanche.dernierPieceLigne(action)][action] = null;
			}
			
			//la meilleure planche possible 
			return meilleurePlanche;
		}

		
	}
	
	//La meme chose pour le methode min
	protected PlancheArtificielle min(int profondeur, String couleur, PlancheArtificielle planche) {
		PlancheArtificielle p = new PlancheArtificielle();
		
		
		List<Integer> prochainesAct = genererActions(couleur);
		if (prochainesAct.isEmpty() || profondeur == 0) {
			p.dupliquer(planche);
			p.setScore(planche.scoreDePlanche());
			return p;
		} else {
			PlancheArtificielle nvPlanche = new PlancheArtificielle(planche);
			PlancheArtificielle meilleurePlanche = new PlancheArtificielle(planche);
			nvPlanche.setScore(Integer.MAX_VALUE);
			int [] swap = new int[2];
			
			for(Integer action: prochainesAct) {
				nvPlanche.ajouterPiece(action, couleur);
				nvPlanche.setAction(action);
				
				p.dupliquer(max(profondeur - 1, couleurAdv, nvPlanche));
				if(nvPlanche.getScore() >= p.getScore()) {
					swap[0] = p.getScore();
					swap[1] = p.getAction();
					meilleurePlanche.dupliquer(p);
					if(nvPlanche.getScore() == p.getScore()) {
						if((new Random()).nextBoolean()) {
							p.setScore(nvPlanche.getScore());
							p.setAction(action);
						}
					} else {
						p.setScore(nvPlanche.getScore());
						p.setAction(action);
					}
					nvPlanche.setScore(swap[0]);
					nvPlanche.setAction(swap[1]);
					
				}
				
				nvPlanche.puissance4Planche[nvPlanche.dernierPieceLigne(action)][action] = null;
				
			}
			
			return meilleurePlanche;
		}

		
	}
	
	/************************************************************************************************************************************/
	
	//Generateur des action possibe depuis un planche donnée
	protected List<Integer> genererActions(String couleur) {
		
		List<Integer> prochaineActions = new ArrayList<>();
		Planche planche;
		Jeu jeu;
		int i = 0;
		
		do {
			planche = new Planche(this.planche);
			jeu = new Jeu(planche);
			if(planche.puissance4Planche[0][i] == null) {
				planche.ajouterPiece(i, couleur);
				prochaineActions.add(i);
				if(jeu.checkForWinner(i)) return prochaineActions;
			}
			i++;	
		} while(i < Planche.getColonnes());
		return prochaineActions;
	}


}
