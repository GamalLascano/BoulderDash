package game.item;

import game.Position;
import game.Timer;
import game.SpriteChar;

public class Amoeba extends Item
{
	SpriteChar spritechar = SpriteChar.A;
	boolean expanding;
	Timer timer = new Timer();

	public Amoeba(StatusItem state, Position pos)
	{
		super(state, pos, false, false, false, false, false, true);
		this.expanding = true;
	}

	// COMPORTAMIENTO
	
	public void expand()
	{
	}

	public boolean check()
	{
		if (timer.getTime() < 300)
		{
			expanding = true;
			return true;
		} else
		{
			expanding = false;
			return false;
		}
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public boolean isExpanding()
	{
		return expanding;
	}

	// SETTERS
	
	public void setExpanding(boolean expanding)
	{
		this.expanding = expanding;
	}

	/**
	 * Se convierte en diamantes.
	 */
	public void diamonize()
	{
	}

}
