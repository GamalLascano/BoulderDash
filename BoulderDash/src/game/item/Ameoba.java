package game.item;

import game.Position;
import game.Status;
import java.util.Timer;

public class Ameoba extends Item {
	Timer timer = new Timer();
	
	private void expand() {
		while(state.getStatus() == true)
			state.setExpanding(true);
	}
	
	private boolean check() {
		 if(timer < 300)
		 {
			 state.setStatus(true);
		 }
		 else
		 {
			 state.setStatus(false);
		 }
	}
}
