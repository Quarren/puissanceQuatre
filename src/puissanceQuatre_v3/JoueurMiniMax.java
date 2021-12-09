package puissanceQuatre_v3;

public class JoueurMiniMax extends JoueurAuto {

	public JoueurMiniMax(String couleur, Planche planche) {
		super(couleur, planche);
		// TODO Auto-generated constructor stub
	}
	
	public int minMax() {
		int bestCol = -1;

		for(int col = 0; col < 7; col++) {
			this.fullScoreCol(col);
			if(bestCol < col) {
				bestCol = col;
			}
			
			/*for(int profondeur = 0; profondeur < 4; profondeur++) {
				Piece[][] NewPlanche = planche.copyPlanche(this.planche);
				//NewPlanche.
			}*/
		}
		
		
		
		return bestCol;
	}

}
