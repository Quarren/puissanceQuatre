package puissanceQuatre_v2.IA;


public class IAJoueur {
	
	PlancheArtificielle planche;
	
	protected static String couleurIA;
	protected static String couleurAdv;
	
	public IAJoueur() {
		super();
	}

	public IAJoueur(String couleurIA, String couleurAdv) {
		IAJoueur.couleurIA = couleurIA;
		IAJoueur.couleurAdv = couleurAdv;
	}

	
}