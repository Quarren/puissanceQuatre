package puissanceQuatre_v2.IA;

import puissanceQuatre_v2.Planche;

public class IAJoueur {
	
	Planche planche;
	
	protected String couleurIA;
	protected String couleurAdv;
	
	public IAJoueur(String couleurIA, String couleurAdv) {
		this.couleurIA = couleurIA;
		this.couleurAdv = couleurAdv;
	}

	public void setPlanche(Planche planche) {
		this.planche = new Planche(planche);
	}
}
