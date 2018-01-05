package objekte;

public abstract class Items {
	
	private int x_Koordinate;
	private int y_Koordinate;
	
	public Items(int x_Koordinate, int y_Koordinate) {
		setX_Koordinate(x_Koordinate);
		setY_Koordinate(y_Koordinate);
	}
	
	//Getter
	public int getX_Koordinate() {
		return x_Koordinate;
	}
	
	public int getY_Koordinate() {
		return y_Koordinate;
	}
	
	//Setter
	public void setX_Koordinate(int x_Koordinate) {
		this.x_Koordinate = x_Koordinate;
	}

	public void setY_Koordinate(int y_Koordinate) {
		this.y_Koordinate = y_Koordinate;
	}

}
