package game.cell;

import game.Position;

public class Normal extends Cell {
	private boolean isDirt; //True = is dirt, false = is normal
	public Normal(Position pos)
	{
		super(pos, false);
		this.isDirt=true;
	}
	public Normal(Position pos, boolean isDirt){
		super(pos, false);		 
		this.isDirt=isDirt;
	}
	pubublic boolean getIsDirt(){
		return isDirt;
	}
	public void setNormal(){
		this.isDirt=false;
	}
}
