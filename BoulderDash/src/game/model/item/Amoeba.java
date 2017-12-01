package game.model.item;

import game.model.Moveable;
import game.model.Position;
import game.model.SpriteChar;
import game.model.item.StatusAmoebaEnum;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * Clase que representa el Amoeba. Se mueve y se copia a si mismo.
 */
public class Amoeba extends Item implements Moveable
{
	private boolean expanding;
	private StatusAmoebaEnum state;

	/**
	 * Constructor del Amoeba.
	 * 
	 * @param pos
	 */
	public Amoeba(Position pos)
	{
		super(pos, false, false, false);
		this.setSpritechar(SpriteChar.A);
		this.expanding = true;
		this.state = StatusAmoebaEnum.EXPANDUP;
		this.getPassable().put(SpriteChar._.hashCode(), SpriteChar._);
		this.getPassable().put(SpriteChar.D.hashCode(), SpriteChar.D);
	}

	/**
	 * Verifica si esta expandiandose.
	 * 
	 * @return boolean
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

	@Override
	public void die()
	{
		this.state = StatusAmoebaEnum.DEAD;
		MapInstance.kill(this.getPosition());
		MapItem.setItem(new Diamond(this.getPosition()));
	}

	@Override
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

	@Override
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.makeMove();
		MapItem.setItem(this);
	}

	@Override
	public void makeMove()
	{
		if (this.expanding)
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

	@Override
	public void makeMoveUp()
	{
		if (this.canGoUp())
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkUp())));
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveDown()
	{
		if (this.canGoDown())
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkDown())));
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveRight()
	{
		if (this.canGoRight())
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveLeft()
	{
		if (this.canGoDownLeft())
		{
			MapItem.setItem(new Amoeba(new Position(this.getPosition().checkLeft(), this.getPosition().getY())));
		}
		else
		{
			this.rotate();
		}
	}

}
