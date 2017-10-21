package game.cell;

import game.Position;
import game.actor.Rockford;
import game.map.bdlevel.BDLevelReader;
import game.SpriteChar;

public class Exit extends Cell
{
	SpriteChar spritechar = SpriteChar.S;

	public Exit(Position pos)
	{
		super(pos, true);
	}

	public void open(BDLevelReader levelReader, Rockford player)
	{
		if (player.getDiamonds() == levelReader.getDiamondsNeeded())
		{
			this.setSolid(false);
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

}
