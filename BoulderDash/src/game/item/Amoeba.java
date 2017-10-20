package game.item;

import game.Position;
import game.Timer;

public class Amoeba extends Item {
	boolean expanding;
	Timer timer = new Timer();
	
	public Amoeba(StatusItem state, Position pos) {
		super(state, pos, false, false, false, false, false);
		this.expanding = true;
	}


	public void expand() {
	}
	
	public boolean check() {
		 if(timer.getTime() < 300)
		 {
			 expanding = true;
			 return true;
		 }
		 else
		 {
			 expanding = false;
			 return false;
		 }
	}

	public boolean isExpanding() {
		return expanding;
	}

	public void setExpanding(boolean expanding) {
		this.expanding = expanding;
	}
	
	/**
	 * Se convierte en diamantes.
	 */
	public void diamonize() {
	}
	
}
