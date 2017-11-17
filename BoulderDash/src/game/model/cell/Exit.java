package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapInstance;

/**
 * 
 *
 */
public class Exit extends Cell
{

	private static SpriteChar spritechar = SpriteChar.E;
	private static boolean isOpen;
	private static Exit exit;

	/**
	 * 
	 * @param pos
	 */
	private Exit(Position pos)
	{
		super(pos, SolidTo.ALL);
		isOpen = false;
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Exit getInstance(Position pos)
	{
		if (exit == null)
		{
			exit = new Exit(pos);
		}
		return exit;
	}

	/**
	 * 
	 * @return
	 */
	public static Exit getInstance()
	{
		return exit;
	}

	/**
	 * 
	 */
	public static void open()
	{
		Rockford player = Rockford.getInstance();
		if (player.getDiamonds() >= MapInstance.getLevelReader().getDiamondsNeeded())
		{
			exit.setSolid(SolidTo.ACTOR);
			spritechar = SpriteChar.e;
			isOpen = true;
		}
	}

	/**
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOpen()
	{
		return isOpen;
	}

}
