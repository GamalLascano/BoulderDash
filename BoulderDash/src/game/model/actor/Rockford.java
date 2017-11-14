package game.model.actor;

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
	private int diamonds;
	private boolean isPushing;
	// private static Rockford player;

	/**
	 * Permite inicializar a Rockford con un status y posicion determinadas
	 * 
	 * @param state:
	 *            Contiene movimiento y estado de vida
	 * @param pos:
	 *            Posicion
	 */
	public Rockford(Position pos)
	{
		super(pos);
		this.score = 0;
		this.diamonds = 0;
		this.isPushing = false;
	}

	// public static Rockford getInstance()
	// {
	// if (player == null)
	// {
	// player = new Rockford();
	// }
	// return player;
	// }

	// GETTERS

	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	public int getScore()
	{
		return score;
	}

	public int getDiamonds()
	{
		return diamonds;
	}

	public boolean isPushing()
	{
		return isPushing;
	}

	// SETTTERS

	public void setScore(int score)
	{
		this.score = score;
	}

	public void setDiamonds(int diamonds)
	{
		this.diamonds = diamonds;
	}

	public void setPushing(boolean pushing)
	{
		this.isPushing = pushing;
	}

	// SAVE

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
			this.diamonds++;
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
			this.isPushing = true;
			rock.pushed(this);
			this.isPushing = false;
		}
	}

	// REFRESH POSITION

	public void changePosition()
	{
		MapActor.removeActor(this.getPosition());
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
		Exit salida = MapCell.findExit();
		if ((this.getPosition().getX() == salida.getPosition().getX())
				&& (this.getPosition().getY() == salida.getPosition().getY()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void makeMove()
	{
		switch (this.state)
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
				this.collect(MapItem.getDiamond(this.getPosition()));
				break;
		}
		this.state = StatusActorEnum.IDLE;
	}
	
	// MAKEMOVEDIR
	
	public void makeMoveUp()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkUp()) != SolidTo.ALL)
		{
			this.getPosition().goUp();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
	}
	
	public void makeMoveDown()
	{
		if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) != SolidTo.ALL)
		{
			this.getPosition().goDown();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
	}
	
	// Si no es solido, es movible, y no hay tierra al lado.
	// Se pushea lo que haya
	public void makeMoveRight()
	{
		if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) != SolidTo.ALL)
		{
			this.getPosition().goRight();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else if (MapInstance.solid(this.getPosition().checkRight() + 1, this.getPosition().getY()) == SolidTo.NONE
				&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY())
						.isMoveable() == true)
		{
			this.push(MapItem.getRock(this.getPosition().checkRight(), this.getPosition().getY()));
			this.getPosition().goRight();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
	}
	
	public void makeMoveLeft()
	{
		if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) != SolidTo.ALL)
		{
			this.getPosition().goLeft();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else if (MapInstance.solid(this.getPosition().checkLeft() - 1, this.getPosition().getY()) == SolidTo.NONE && MapItem
				.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).isMoveable() == true)
		{
			this.push(MapItem.getRock(this.getPosition().checkLeft(), this.getPosition().getY()));
			this.getPosition().goLeft();
			this.dig(MapCell.getDirt(this.getPosition()));
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
		else
		{
			this.collect(MapItem.getDiamond(this.getPosition()));
		}
	}

}
