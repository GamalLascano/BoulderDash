package game.model.actor;

import game.model.ListOfEntities;
import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.cell.Dirt;
import game.model.cell.Exit;
import game.model.item.Diamond;
import game.model.item.Rock;
import game.model.map.MapActor;
import game.model.map.MapCell;
import game.model.map.MapInstance;
import game.model.map.MapItem;

/**
 * Esta clase es la que contiene al personaje principal: Rockford Contiene un
 * spritechar para representacion visual, su puntuacion, sus diamantes y si esta
 * empujando o no, ademas de las otras propiedades de otros actores
 */
public class Rockford extends Actor
{
	private SpriteChar spritechar = SpriteChar.R;
	private int score;
	private int lives;
	private int diamonds;
	private boolean isPushing;
	private static Rockford player;

	/**
	 * 
	 * @param pos
	 */
	private Rockford(Position pos)
	{
		super(pos);
		score = 0;
		diamonds = 0;
		lives = 3;
		isPushing = false;
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public static Rockford getInstance(Position pos)
	{
		if (player == null)
		{
			player = new Rockford(pos);
		}
		return player;
	}

	/**
	 * 
	 * @return
	 */
	public static Rockford getInstance()
	{
		return player;
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
	public int getScore()
	{
		return score;
	}

	/**
	 * 
	 * @return
	 */
	public int getDiamonds()
	{
		return diamonds;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPushing()
	{
		return isPushing;
	}

	/**
	 * 
	 * @param points
	 */
	public void setScore(int points)
	{
		this.score = points;
	}

	/**
	 * 
	 * @param diamond
	 */
	public void setDiamonds(int diamond)
	{
		this.diamonds = diamond;
	}

	/**
	 * 
	 * @param pushing
	 */
	public void setPushing(boolean pushing)
	{
		this.isPushing = pushing;
	}

	/**
	 * 
	 */
	public void die()
	{
		if (state != StatusActorEnum.DEAD)
		{
			state = StatusActorEnum.DEAD;
			if (this.lives != 0)
				this.lives = lives--;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(getPosition());
	}

	/**
	 * 
	 * @return
	 */
	public boolean save()
	{
		return true;
	}

	/**
	 * Remueve la tierra del juego
	 * 
	 * @param dirt:
	 *            Bloque de tierra
	 */
	public void dig(Dirt dirt)
	{
		if (dirt != null)
		{
			dirt.removeDirt();
		}
	}

	/**
	 * Si es un diamante, lo recolecta
	 * 
	 * @param diamond:
	 *            Bloque de diamante
	 */
	public void collect(Diamond diamond)
	{
		if (diamond != null && diamond.isCollectable())
		{
			diamonds++;
			diamond.collected();
		}
	}

	/**
	 * Este metodo hace que si esta empujando a una roca, se le ponga el estado
	 * correspondiente
	 * 
	 * @param rock:
	 *            Bloque de roca
	 */
	public void push(Rock rock)
	{
		if (rock != null && rock.isMoveable())
		{
			isPushing = true;
			rock.pushed(this);
			isPushing = false;
		}
	}

	/**
	 * 
	 */
	public void changePosition()
	{
		MapActor.removeActor(getPosition());
		this.makeMove();
		MapActor.setActor(this);
	}

	/**
	 * Se occupa de mover a Rockford en la matriz, tambien verifica si la celda
	 * destino es solida para moverse. Rockford cava automaticamente la tierra.
	 * 
	 */
	public boolean isInExit()
	{
		Exit door = Exit.getInstance();
		if (player.getPosition().equals(door.getPosition()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 */
	public void makeMove()
	{
		switch (state)
		{
			case MOVINGUP:
				makeMoveUp();
				break;
			case MOVINGDOWN:
				makeMoveDown();
				break;
			case MOVINGRIGHT:
				makeMoveRight();
				break;
			case MOVINGLEFT:
				makeMoveLeft();
				break;
			default:
				this.collect(MapItem.getDiamond(getPosition()));
				break;
		}
		state = StatusActorEnum.IDLE;
	}

	/**
	 * 
	 */
	public void makeMoveUp()
	{
		if (MapInstance.solid(getPosition().getX(), getPosition().checkUp()) == SolidTo.NONE
				|| MapInstance.solid(getPosition().getX(), getPosition().checkUp()) == SolidTo.ITEM
				|| MapItem.getDiamond(getPosition().getX(), getPosition().checkUp()) != null)
		{
			getPosition().goUp();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveDown()
	{
		if (MapInstance.solid(getPosition().getX(), getPosition().checkDown()) == SolidTo.NONE
				|| MapInstance.solid(getPosition().getX(), getPosition().checkDown()) == SolidTo.ITEM
				|| MapItem.getDiamond(getPosition().getX(), getPosition().checkDown()) != null)
		{
			getPosition().goDown();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveRight()
	{
		if (MapInstance.solid(getPosition().checkRight(), getPosition().getY()) == SolidTo.NONE
				|| MapInstance.solid(getPosition().checkRight(), getPosition().getY()) == SolidTo.ITEM
				|| MapItem.getDiamond(getPosition().checkRight(), getPosition().getY()) != null)
		{
			getPosition().goRight();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else if (MapInstance.solid(getPosition().checkRight() + 1, getPosition().getY()) == SolidTo.NONE
				&& MapItem.getItem(getPosition().checkRight(), getPosition().getY()).isMoveable() == true)
		{
			this.push(MapItem.getRock(getPosition().checkRight(), getPosition().getY()));
			getPosition().goRight();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

	/**
	 * 
	 */
	public void makeMoveLeft()
	{
		if (MapInstance.solid(getPosition().checkLeft(), getPosition().getY()) == SolidTo.NONE
				|| MapInstance.solid(getPosition().checkLeft(), getPosition().getY()) == SolidTo.ITEM
				|| MapItem.getDiamond(getPosition().checkLeft(), getPosition().getY()) != null)
		{
			getPosition().goLeft();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else if (MapInstance.solid(getPosition().checkLeft() - 1, getPosition().getY()) == SolidTo.NONE
				&& MapItem.getItem(getPosition().checkLeft(), getPosition().getY()).isMoveable() == true)
		{
			this.push(MapItem.getRock(getPosition().checkLeft(), getPosition().getY()));
			getPosition().goLeft();
			this.dig(MapCell.getDirt(getPosition()));
			this.collect(MapItem.getDiamond(getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(getPosition()));
		}
	}

}
