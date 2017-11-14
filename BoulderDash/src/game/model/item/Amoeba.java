package game.model.item;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.item.StatusAmoebaEnum;
import game.model.map.MapInstance;
import game.model.map.MapItem;

public class Amoeba extends Item
{
	private SpriteChar spritechar = SpriteChar.A;
	private boolean expanding;
	private StatusAmoebaEnum state;

	// CONSTRUCTORS
	
	public Amoeba(Position pos)
	{
		super(pos, false, false, false, false, false, SolidTo.ALL);
		this.expanding = true;
		this.state = StatusAmoebaEnum.IDLE;
	}
	
	public Amoeba(Position pos, StatusAmoebaEnum state)
	{
		super(pos, false, false, false, false, false, SolidTo.ALL);
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

	// DIE
	
	public void die()
	{
		MapInstance.kill(this.getPosition());
		MapItem.setItem(new Diamond(this.getPosition()));
	}
	
	// ROTATE
	
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
	
	// REFRESH POSITION
	
	public void changePosition()
	{
		MapItem.removeItem(this.getPosition());
		this.makeMove();
		MapItem.setItem(this);
	}
	
	public void makeMove()
	{
		if(this.isExpanding())
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
	
	public void makeMoveUp()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkUp()) != SolidTo.ALL)
		{
			MapItem.setItem( new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkUp())) );
		}
		else
		{
			this.rotate();
		}
	}
	
	public void makeMoveDown()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) != SolidTo.ALL)
		{
			MapItem.setItem( new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkDown())) );
		}
		else
		{
			this.rotate();
		}
	}
	
	public void makeMoveRight()
	{
		if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) != SolidTo.ALL)
		{
			MapItem.setItem( new Amoeba(new Position(this.getPosition().checkRight(), this.getPosition().getY())) );
		}
		else
		{
			this.rotate();
		}
	}
	
	public void makeMoveLeft()
	{
		if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) != SolidTo.ALL)
		{
			MapItem.setItem( new Amoeba(new Position(this.getPosition().checkLeft(), this.getPosition().getY())) );
		}
		else
		{
			this.rotate();
		}
	}

}
