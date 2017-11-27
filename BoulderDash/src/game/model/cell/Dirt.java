package game.model.cell;

import game.model.Position;
import game.model.SpriteChar;

/**
 * 
 *
 */
public class Dirt extends Cell
{
	private SpriteChar spritechar;
	private boolean dirty; // True = is dirt, false = is normal

	/**
	 * 
	 * @param pos
	 */
	public Dirt(Position pos)
	{
		super(pos);
		this.dirty = true;
		this.spritechar = SpriteChar.D;
	}

	/**
	 * 
	 * @param pos
	 * @param dirty
	 */
	public Dirt(Position pos, boolean dirty)
	{
		super(pos);
		this.dirty = dirty;
		if (!dirty)
		{
			this.spritechar = SpriteChar._;
		}
		else
		{
			this.spritechar = SpriteChar.D;
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
	public boolean isDirty()
	{
		return dirty;
	}

	/**
	 * 
	 */
	public void removeDirt()
	{
		this.dirty = false;
		spritechar = SpriteChar._;
	}

	/**
	 * 
	 */
	public void clear()
	{
		this.removeDirt();
	}
}
