package game.item;

import game.Position;
import game.SpriteChar;
import game.map.MapActor;
import game.map.MapCell;
import game.map.MapItem;
import game.item.StatusAmoebaEnum;

public class Amoeba extends Item
{
	private SpriteChar spritechar = SpriteChar.A;
	private boolean expanding;
	private StatusAmoebaEnum state;

	// CONSTRUCTORS
	
	public Amoeba(Position pos)
	{
		super(pos, false, false, false, false, false, 2);
		this.expanding = true;
		this.state = StatusAmoebaEnum.IDLE;
	}
	
	public Amoeba(Position pos, StatusAmoebaEnum state)
	{
		super(pos, false, false, false, false, false, 2);
		this.expanding = true;
		this.state = state;
	}

	// CHECK

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
	
	public void expand()
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
			switch (this.state)
			{
				case EXPANDUP:
					break;
				case EXPANDRIGHT:
					break;
				case EXPANDDOWN:
					break;
				case EXPANDLEFT:
					break;
				default:
					break;
			}
		}
	}

}
