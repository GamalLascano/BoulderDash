package game.model.item;

import game.model.Position;
import game.model.SpriteChar;
import game.model.item.StatusAmoebaEnum;
import game.model.map.MapInstance;
import game.model.map.MapItem;
import game.model.map.MapVisual;

/**
 * 
 *
 */
public class Amoeba extends Item
{
	private SpriteChar spritechar = SpriteChar.A;
	private boolean expanding;
	private StatusAmoebaEnum state;

	/**
	 * 
	 * @param pos
	 */
	public Amoeba(Position pos)
	{
		super(pos, false, false, false, false, false);
		this.expanding = true;
		this.state = StatusAmoebaEnum.EXPANDUP;
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
		this.getPassable().put(SpriteChar.D.hashCode(), SpriteChar.D);
	}

	/**
	 * 
	 * @return
	 */
	public boolean check()
	{
		if (expanding = true)
		{
			expanding = true;
			return true;
		}
		else
		{
			expanding = false;
			return false;
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
	public boolean isExpanding()
	{
		return expanding;
	}

	/**
	 * 
	 * @param expanding
	 */
	public void setExpanding(boolean expanding)
	{
		this.expanding = expanding;
	}

	/**
	 * 
	 */
	public void die()
	{
		this.state = StatusAmoebaEnum.DEAD;
		MapInstance.kill(this.getPosition());
		MapItem.setItem(new Diamond(this.getPosition()));
	}

	/**
	 * 
	 */
	public void rotate()
	{
		switch (this.state)
		{
			case EXPANDUP:
				this.state = StatusAmoebaEnum.EXPANDRIGHT;
				break;
			case EXPANDRIGHT:
				this.state = StatusAmoebaEnum.EXPANDDOWN;
				break;
			case EXPANDDOWN:
				this.state = StatusAmoebaEnum.EXPANDLEFT;
				break;
			case EXPANDLEFT:
				this.state = StatusAmoebaEnum.EXPANDUP;
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 */
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.makeMove();
		MapItem.setItem(this);
	}

	/**
	 * 
	 */
	public void makeMove()
	{
		if (this.isExpanding())
		{
			switch (this.state)
			{
				case EXPANDUP:
					makeMoveUp();
					break;
				case EXPANDRIGHT:
					makeMoveRight();
					break;
				case EXPANDDOWN:
					makeMoveDown();
					break;
				case EXPANDLEFT:
					makeMoveLeft();
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 
	 */
	public void makeMoveUp()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkUp()).hashCode()))
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkUp())));
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveDown()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().getX(), this.getPosition().checkDown()).hashCode()))
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkDown())));
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveRight()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkRight(), this.getPosition().getY()).hashCode()))
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		}
		else
		{
			this.rotate();
		}
	}

	/**
	 * 
	 */
	public void makeMoveLeft()
	{
		if (this.getPassable().containsKey(MapVisual.getChar(this.getPosition().checkLeft(), this.getPosition().getY()).hashCode()))
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().checkLeft(), this.getPosition().getY())));
		}
		else
		{
			this.rotate();
		}
	}

}
