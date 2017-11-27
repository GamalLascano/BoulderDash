package game.model.cell;

import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapInstance;

/**
 * 
 *
 */
public class Exit extends Cell
{

	private static SpriteChar spritechar;
	private static boolean isOpen;
	private static Exit exit;

	/**
	 * 
	 * @param pos
	 */
	private Exit()
	{
		super(null);
		spritechar = SpriteChar.E;
		isOpen = false;
	}

	/**
	 * 
	 * @return
	 */
	public static Exit getInstance()
	{
		if (exit == null)
		{
			exit = new Exit();
		}
		return exit;
	}

	/**
	 * 
	 */
	public static void open()
	{
		Rockford player = Rockford.getRockford();
		if (player.getDiamonds() >= MapInstance.getLevelReader().getDiamondsNeeded())
		{
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
