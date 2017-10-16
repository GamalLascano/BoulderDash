package game.item;

import game.Position;
import game.Status;
import game.Timer;

public class Ameoba extends Item {
	public Ameoba(Status state, Position pos, boolean collectable, boolean moveable, boolean fallable, boolean explodable) {
		super(state, pos, collectable, moveable, fallable, explodable);
	}

	Timer timer = new Timer();

	public void expand() {
		while(state.getStatus() == true)
			state.setExpanding(true);
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
}
