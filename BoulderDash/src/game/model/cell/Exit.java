package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.bdlevel.BDLevelReader;
import game.util.Singleton;

public class Exit extends Cell
{
	
	private static SpriteChar spritechar = SpriteChar.E;
	private static boolean isOpen;
	private static Exit exit;
	
	private Exit(Position pos)
	{
		super(pos, SolidTo.ALL);
		isOpen = false;
	}
	
	public static Exit getInstance(Position pos)
	{
		if (exit == null)
		{
			exit = new Exit(pos);
		}
		return exit;
	}
	

	// OPEN
	
	public static void open(Rockford player)
	{
		if (player.getDiamonds() >= MapInstance.getLevelReader().getDiamondsNeeded())
		{
			exit.setSolid(SolidTo.ACTOR);
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
