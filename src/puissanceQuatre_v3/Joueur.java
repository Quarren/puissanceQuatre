package puissanceQuatre_v3;

public abstract class Joueur {
	
	//private int id;
	private Piece piece;
	protected Planche planche;

	public Joueur(String couleur, Planche p) {
		piece = new Piece();
		piece.setCouleur(couleur);
		planche = p;
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
