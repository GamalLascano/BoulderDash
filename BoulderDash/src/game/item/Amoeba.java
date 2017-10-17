package game.item;

import game.Position;
import game.Status;
import game.Timer;

public class Amoeba extends Item {
	boolean expanding;
	Timer timer = new Timer();
	
	public Amoeba(Status state, Position pos, boolean expanding) {
		super(state, pos, false, false, false, false);
		this.expanding = expanding;
	}


	public void expand() {
		while(state.getStatus() == true)
			this.expanding = true;
	}
	
	public boolean check() {
		 if(timer.getTime() < 300)
		 {
			 state.setStatus(true);
			 return true;
		 }
		 else
		 {
			 state.setStatus(false);
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
