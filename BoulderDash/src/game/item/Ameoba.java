package game.item;

import game.Position;
import game.Status;
import game.Timer;

public class Ameoba extends Item {
	boolean expanding;
	Timer timer = new Timer();
	
	public Ameoba(Status state, Position pos) {
		super(state, pos, false, false, false, false);
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
	
	
}
