package puissanceQuatre_v3;


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
