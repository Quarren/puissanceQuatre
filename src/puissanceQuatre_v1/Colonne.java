package puissanceQuatre_v1;

import java.util.ArrayList;

public class Colonne {
	
	private int position; // le numéro de la colonne
	
	// pions du joueur d'id 1 -> 1
	// pions du joueur d'id 2 -> 2
	// 0 marque l'absence de pions
	private int[] pions = new int[6];
	private int nbp = 0; // nombre de pions dans la colonne, sans compter les 0

	public Colonne() {
		// TODO Auto-generated constructor stub
	}
	
	public Colonne(int pos) {
		this.position = pos;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int[] getPions() {
		return this.pions;
	}
	
	public int getNbp() {
		return this.nbp;
	}
	
	// retourne l'index de la dernière
	public int ajouterPion(int i) {
		if (nbp < 6) {
			pions[nbp] = i;
			nbp += 1;
			return nbp;
		} else {
			System.out.println("La colonne contient déjà 6 pions.");
			throw new ActionJoueurImpossible("La colonne n°" + this.position + " est déjà pleine !");
		}
	}
	
	public String toString() {
		String res = "";
		for (int i = 5; i >= 0; i--) {
			if (i < nbp) {
				res += "" + pions[i] + '\r';
			} else {
				res += "" + 0 + '\r' ;
			}
		}
		return res;
	}
	
	
	public boolean gagnerVertical(Joueur j, int nbpAlignes, int idx) {
		if (nbpAlignes == 4) {
			return true;
		} else if (idx == pions.length) {
			return false;
		} else if (pions[idx] == j.getId()) {
			return this.gagnerVertical(j, nbpAlignes+1, idx+1);
		} else {
			return this.gagnerVertical(j, nbpAlignes, idx+1);
		}
	}
	
	
	public static void main(String[] args) {
		Colonne c1 = new Colonne(1);
		//System.out.println(c1.getPosition());
		c1.ajouterPion(1);
		c1.ajouterPion(2);
		c1.ajouterPion(1);
		c1.ajouterPion(2);
		c1.ajouterPion(1);
		c1.ajouterPion(2);
		System.out.println(c1.ajouterPion(1));
		System.out.println(c1.toString());

	}

}
