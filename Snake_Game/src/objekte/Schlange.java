package objekte;

public class Schlange {
	
	private int[] x_Koordinate = new int[500];
	private int[] y_Koordinate = new int[500];
	
	private int l�nge_K�rper;
	
	// 0 = up, 1 = down, 2 = right, 3 = left
	private int richtung;
	
	public Schlange(int l�nge_K�rper, int richtung) {
		setRichtung(richtung);
		setL�nge_K�rper(l�nge_K�rper);
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
			for (int i = l�nge_K�rper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			y_Koordinate[0] = getY_Koordinate(0) - 20;
		}
		if (getRichtung() == 1) {
			for (int i = l�nge_K�rper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			y_Koordinate[0] = getY_Koordinate(0) + 20;
		}
		if (getRichtung() == 2) {
			for (int i = l�nge_K�rper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			x_Koordinate[0] = getX_Koordinate(0) + 20;
		}
		if (getRichtung() == 3) {
			for (int i = l�nge_K�rper; i >= 1; i--) {
				y_Koordinate[i] = getY_Koordinate(i - 1);
				x_Koordinate[i] = getX_Koordinate(i - 1);
			}
			x_Koordinate[0] = getX_Koordinate(0) - 20;
		}
	}
	
	public void verl�ngerSchlange() {
		l�nge_K�rper++;
		x_Koordinate[l�nge_K�rper] = x_Koordinate[l�nge_K�rper - 1];
		y_Koordinate[l�nge_K�rper] = y_Koordinate[l�nge_K�rper - 1];
	}

	//Getter
	public int getL�nge_K�rper() {
		return l�nge_K�rper;
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
	public void setL�nge_K�rper(int l�nge_K�rper) {
		this.l�nge_K�rper = l�nge_K�rper;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}

}
