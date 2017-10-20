package game.item;

import game.Position;
import game.StatusItem;
import game.actor.Rockford;

public class Rock extends Item {

	
	
	public Rock(StatusItem state, Position pos)
	{
		super(state, pos, false, true, true, false, true);
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
