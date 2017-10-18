package game.item;

import game.Position;
import game.Status;
import game.actor.Rockford;

public class Rock extends Item {

	
	
	public Rock(Status state, Position pos)
	{
		super(state, pos, false, true, true, false);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Rockford empuja la roca. direciones?
	 * @return
	 */
	private void movement(Rockford player) {
		if (player.isPushing() && this.moveable) {
			this.pos.setPosX(this.pos.posX++);
			this.state.setMoving(true);
		}
	}
	
	private boolean checkEmpty() {
		 return true;
	}
}
