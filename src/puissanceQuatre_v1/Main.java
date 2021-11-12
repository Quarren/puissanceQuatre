package puissanceQuatre_v1;

public class Main {

	public static void main(String[] args) {
		Jeu j = new Jeu();
		//System.out.println(j.toString());
		//j.colonnes[5].ajouterPion(2);
		
		j.ajouterPion(1, 1);
		System.out.println("nbp colonnes 1: " + j.getColonnes()[0].getNbp());
		//System.out.println("nbp colonnes 1: " + j.colonnes[0].getPions(j.colonnes[0].getNbp())-1);
		j.ajouterPion(1,2);
		System.out.println("nbp colonnes 1: " + j.getColonnes()[0].getNbp());
		//j.ajouterPion(1, 1);
		//j.ajouterPion(1, 1);
		//System.out.println(j.ajouterPion(1, 1));
		
		System.out.println(j.toString());
		
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		j.ajouterPion(1, 1);
		//System.out.println(j.colonnes[0].gagnerHorizontalement(joueurs[0], 0, 0));
		
		System.out.println(j.toString());
		
		/*j.colonnes[0].ajouterPion(1);
		j.colonnes[1].ajouterPion(2);
		j.colonnes[0].ajouterPion(1);
		j.colonnes[0].ajouterPion(2);*/
		//System.out.println(j.toString());
		//System.out.println("" + joueurs[0].getId() + joueurs[1].getId());
		//System.out.println()
	}

}
