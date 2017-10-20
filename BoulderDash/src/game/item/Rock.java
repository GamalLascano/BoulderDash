package game.item;

import game.Position;
import game.actor.Rockford;

public class Rock extends Item {
	boolean pushed;
	
	
	public Rock(StatusItem state, Position pos)
	{
		super(state, pos, false, true, true, false, true);
		this.pushed = false;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Rockford empuja la roca. direciones?
	 * @return
	 */
	private void movement(Rockford player) {
		if (player.isPushing() && this.moveable) {
			this.pos.setPosX(this.pos.posX++);
			this.pushed = true;
		}
	}
	
	private boolean checkEmpty() {
		 return true;
	}
}
