package puissanceQuatre_v1;

public class Jeu {
	
	private Colonne[] colonnes = new Colonne[6];

	public Jeu() {
		Colonne c;
		for (int i = 0; i < 6; i++) {
			c = new Colonne(i+1);
			colonnes[i] = c;
			//System.out.println(this.colonnes[i].getPosition());
		}	
	}

	@Override
	public String toString() {
		String res = new String();
		for (int i = colonnes.length-1; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				res += colonnes[j].getPions()[i];
			}
			res += '\n';
		}
		return res;
	}
	
	// TO STRING

	public static void main(String[] args) {
		Jeu j = new Jeu();
		System.out.println(j.toString());
		j.colonnes[0].ajouterPion(1);
		j.colonnes[1].ajouterPion(2);
		j.colonnes[0].ajouterPion(1);
		System.out.println(j.toString());
		//System.out.println()
	}

}
