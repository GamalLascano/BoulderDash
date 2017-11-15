package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.bdlevel.BDLevelReader;

public class Exit extends Cell
{
	
	private SpriteChar spritechar = SpriteChar.E;
	private boolean isOpen;
	// CONSTRUCTORS
	
	public Exit(Position pos)
	{
		super(pos, SolidTo.ALL);
		this.isOpen = false;
	}

	// OPEN
	
	public void open(BDLevelReader levelReader, Rockford player)
	{
		if (player.getDiamonds() >= levelReader.getDiamondsNeeded())
		{
			this.setSolid(SolidTo.ACTOR);
			spritechar = SpriteChar.e;
			isOpen = true;
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public boolean isOpen()
	{
		return isOpen;
	}

	public void clear()
	{
		
	}

}
