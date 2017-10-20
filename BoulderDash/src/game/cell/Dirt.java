package game.cell;

import game.Position;

public class Dirt extends Cell
{
	private boolean isDirt; // True = is dirt, false = is normal

	public Dirt(Position pos)
	{
		super(pos, false);
		this.isDirt = true;
	}

	public Dirt(Position pos, boolean isDirt)
	{
		super(pos, false);
		this.isDirt = isDirt;
	}

	public boolean getIsDirt()
	{
		return isDirt;
	}

	public void setNormal()
	{
		this.isDirt = false;
	}
}
