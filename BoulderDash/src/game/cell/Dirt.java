package game.cell;

import game.Position;
import game.SpriteChar;

public class Dirt extends Cell
{
	private SpriteChar spritechar;
	private boolean dirty; // True = is dirt, false = is normal

	// CONSTRUCTORS
	
	public Dirt(Position pos)
	{
		super(pos, 1);
		this.dirty = true;
		this.spritechar = SpriteChar.D;
	}

	public Dirt(Position pos, boolean dirty)
	{
		super(pos, 0);
		this.dirty = dirty;
		if (!dirty)
		{
			this.spritechar = SpriteChar._;
			this.setSolid(0);
		}
		else
		{
			this.spritechar = SpriteChar.D;
			this.setSolid(1);
		}
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}

	// SETTERS
	
	public void removeDirt()
	{
		this.dirty = false;
		this.setSolid(0);
		spritechar = SpriteChar._;
	}
	
	// DIE
	
	public void clear()
	{
		this.removeDirt();
	}
}
