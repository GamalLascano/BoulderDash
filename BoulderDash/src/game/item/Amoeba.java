package game.item;

import game.Position;
import game.SpriteChar;
import game.map.MapActor;
import game.map.MapCell;
import game.map.MapItem;

public class Amoeba extends Item
{
	private SpriteChar spritechar = SpriteChar.A;
	private boolean expanding;

	public Amoeba(StatusItem state, Position pos)
	{
		super(state, pos, false, false, false, false, false, 2);
		this.expanding = true;
	}

	// COMPORTAMIENTO
	
	public void expand()
	{
	}

	public boolean check()
	{
		if (expanding = true)
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
	
	// REFRESH POSITION
	
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.makeMove();
		MapItem.setItem(this.getPosition(), this);
	}
	
	public void makeMove()
	{
		if(this.isExpanding())
		{
			
		}
		else
		{
			
		}
	}

}
