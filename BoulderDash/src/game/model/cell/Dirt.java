package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;

public class Dirt extends Cell
{
	private SpriteChar spritechar;
	private boolean dirty; // True = is dirt, false = is normal

	// CONSTRUCTORS
	
	public Dirt(Position pos)
	{
		super(pos, SolidTo.ENEMY);
		this.dirty = true;
		this.spritechar = SpriteChar.D;
	}

	public Dirt(Position pos, boolean dirty)
	{
		super(pos, SolidTo.NONE);
		this.dirty = dirty;
		if (!dirty)
		{
			this.spritechar = SpriteChar._;
			this.setSolid(SolidTo.NONE);
		}
		else
		{
			this.spritechar = SpriteChar.D;
			this.setSolid(SolidTo.ENEMY);
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
		this.setSolid(SolidTo.NONE);
		spritechar = SpriteChar._;
	}
	
	// DIE
	
	public void clear()
	{
		this.removeDirt();
	}
}
