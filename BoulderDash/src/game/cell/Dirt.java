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
		super(pos, 1);
		this.isDirt = true;
		this.spritechar = SpriteChar.D;
	}

	public Dirt(Position pos, boolean isDirt)
	{
		super(pos, 0);
		this.isDirt = isDirt;
		if (isDirt == false)
		{
			this.spritechar = SpriteChar.E;
			super.setSolid(0);
		}
		else
		{
			this.spritechar = SpriteChar.D;
			super.setSolid(1);
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
		super.solid = 0;
		spritechar = SpriteChar.E;
	}
}
