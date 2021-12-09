package puissanceQuatre_v3;

public abstract class Joueur {
	
	//private int id;
	private Piece piece;
	protected Planche planche;
	private String oppCouleur;

	public Joueur(String couleur, Planche p) {
		piece = new Piece();
		piece.setCouleur(couleur);
		planche = p;
	}
	
	public Joueur(String couleur, Planche p, String oppCouleur) {
		piece = new Piece();
		piece.setCouleur(couleur);
		planche = p;
		this.oppCouleur = oppCouleur;
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
	
	public String getOppCouleur() {
		return oppCouleur;
	}

}
