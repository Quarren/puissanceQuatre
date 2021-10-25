package puissanceQuatre_v1;

import java.util.ArrayList;

public class Colonne {
	
	private int position;
	
	// pions du joueur d'id 1 -> 1
	// pions du joueur d'id 2 -> 2
	// 0 marque l'absence de pions
	private int[] pions = new int[6];
	private int nbp = 0;
	//private ArrayList<Integer> pions = new ArrayList<Integer>(7);

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
	
	// retourne l'index de la dernière
	public boolean ajouterPion(int i) {
		if (nbp < 6) {
			pions[nbp] = i;
			nbp += 1;
			return true;
		} else {
			System.out.println("La colonne contient déjà 6 pions.");
			// TODO : throw exception ?
			return false;
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
