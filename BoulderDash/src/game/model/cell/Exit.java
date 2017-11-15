package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.bdlevel.BDLevelReader;

public class Exit extends Cell
{
	
	private SpriteChar spritechar = SpriteChar.E;
	// CONSTRUCTORS
	
	public Exit(Position pos)
	{
		super(pos, SolidTo.ALL);
	}

	// OPEN
	
	public void open(BDLevelReader levelReader, Rockford player)
	{
		if (player.getDiamonds() >= levelReader.getDiamondsNeeded())
		{
			this.setSolid(SolidTo.ACTOR);
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	// DIE
	
	public void clear()
	{
		
	}

}
