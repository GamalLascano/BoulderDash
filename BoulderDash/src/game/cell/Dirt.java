package game.cell;

import game.Position;
import game.SpriteChar;

public class Dirt extends Cell
{
	SpriteChar spritechar;
	private boolean isDirt; // True = is dirt, false = is normal

	// CONSTRUCTORS
	
	public Dirt(Position pos)
	{
		super(pos, false);
		this.isDirt = true;
		this.spritechar = SpriteChar.D;
	}

	public Dirt(Position pos, boolean isDirt)
	{
		super(pos, false);
		this.isDirt = isDirt;
		if (isDirt == false)
		{
			this.spritechar = SpriteChar.E;
		}
		else
		{
			this.spritechar = SpriteChar.D;
		}
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public boolean IsDirt()
	{
		return isDirt;
	}

	// SETTERS
	
	public void removeDirt()
	{
		this.isDirt = false;
		spritechar = SpriteChar.E;
	}
}
