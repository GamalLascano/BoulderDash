package game.cell;

import game.Position;

public class Normal extends Cell {
	private boolean isDirt;
	public Normal(Position pos)
	{
		super(pos, false, false);
		this.isDirt=false;
	}
	public Normal(Position pos, boolean isDirt){
		super(pos, false, false);		 
		this.isDirt=isDirt;
	}
	pubublic boolean getIsDirt(){
		return isDirt;
	}
	public void setNormal(){
		this.isDirt=false;
	}
}
