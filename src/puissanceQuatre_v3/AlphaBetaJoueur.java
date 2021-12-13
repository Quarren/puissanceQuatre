package puissanceQuatre_v3;

import java.util.List;
import java.util.Random;

public class AlphaBetaJoueur extends MinmaxJoueur {

	public AlphaBetaJoueur(String couleur1, String couleur2) {
		// TODO Auto-generated constructor stub
		super(couleur1, couleur2);
	}

	protected PlancheArtificielle max(int profondeur, String couleur, PlancheArtificielle planche, int alpha, int beta) {
		PlancheArtificielle p = new PlancheArtificielle();
		
		
		List<Integer> prochainesAct = genererActions(couleur);
		if (prochainesAct.isEmpty() || profondeur == 0) {
			p.dupliquer(planche);
			p.setScore(planche.scoreDePlanche());
			return p;
		} else {
			PlancheArtificielle nvPlanche = new PlancheArtificielle(planche);
			PlancheArtificielle meilleurePlanche = new PlancheArtificielle(planche);
			nvPlanche.setScore(Integer.MIN_VALUE);
			
			for(Integer action: prochainesAct) {
				nvPlanche.ajouterPiece(action, couleur);
				nvPlanche.setAction(action);
				p.dupliquer(min(profondeur - 1, couleurAdv, nvPlanche, alpha, beta));
				
				
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
				nvPlanche.puissance4Planche[nvPlanche.dernierPieceLigne(action)][action] = null;
				//l'Élagage alpha-bêta
				if (meilleurePlanche.getScore() >= beta) {
					break;
				}
				alpha = Math.max(alpha, meilleurePlanche.getScore());

				
			}
			
			return meilleurePlanche;
		}
	}

	protected PlancheArtificielle min(int profondeur, String couleur, PlancheArtificielle planche, int alpha, int beta) {
		
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
				
				p.dupliquer(max(profondeur - 1, couleurAdv, nvPlanche, alpha, beta));
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
				//l'Élagage alpha-bêta
				if (meilleurePlanche.getScore() <= alpha) {
					break;
				}
				beta = Math.min(beta, meilleurePlanche.getScore());
			}
			
			return meilleurePlanche;
		}
	}
	
}
