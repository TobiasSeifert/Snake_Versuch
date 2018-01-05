package objekte;

public class Schlange {
	
	private int[] x_Koordinate = new int[500];
	private int[] y_Koordinate = new int[500];
	
	private int länge_Körper;
	
	// 0 = up, 1 = down, 2 = right, 3 = left
	private int richtung;
	
	public Schlange(int länge_Körper, int richtung) {
		setRichtung(richtung);
		setLänge_Körper(länge_Körper);
		erschaffeSchlange();
	}
	
	//Methoden
	public void erschaffeSchlange(){
		x_Koordinate[0] = 160;
		y_Koordinate[0] = 140;
		
		x_Koordinate[1] = 140;
		y_Koordinate[1] = 140;
		
		x_Koordinate[2] = 120;
		y_Koordinate[2] = 140;
		
	}
	
	public void bestimmeSchlange() {
		if (getRichtung() == 0) {
			for (int i = länge_Körper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			y_Koordinate[0] = getY_Koordinate(0) - 20;
		}
		if (getRichtung() == 1) {
			for (int i = länge_Körper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			y_Koordinate[0] = getY_Koordinate(0) + 20;
		}
		if (getRichtung() == 2) {
			for (int i = länge_Körper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			x_Koordinate[0] = getX_Koordinate(0) + 20;
		}
		if (getRichtung() == 3) {
			for (int i = länge_Körper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			x_Koordinate[0] = getX_Koordinate(0) - 20;
		}
	}
	
	public void verlängerSchlange() {
		länge_Körper++;
		x_Koordinate[länge_Körper] = x_Koordinate[länge_Körper - 1];
		y_Koordinate[länge_Körper] = y_Koordinate[länge_Körper - 1];
	}

	//Getter
	public int getLänge_Körper() {
		return länge_Körper;
	}

	public int getRichtung() {
		return richtung;
	}
	
	public int getX_Koordinate(int index) {
		return x_Koordinate[index];
	}
	
	public int getY_Koordinate(int index) {
		return y_Koordinate[index];
	}
	
	//Setter
	public void setLänge_Körper(int länge_Körper) {
		this.länge_Körper = länge_Körper;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}

}
