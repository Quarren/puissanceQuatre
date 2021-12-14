package puissanceQuatre_v2;

public class Joueur {
	
	//private int id;
	private Piece piece;

	public Joueur(String couleur) {
		piece = new Piece();
		piece.setCouleur(couleur);
	}
	
	/*
	public Joueur(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}
	*/
	public String getCouleur() {
		return piece.getCouleur();
	}

}