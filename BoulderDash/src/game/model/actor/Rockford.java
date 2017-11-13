package game.model.actor;

import game.model.Position;
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
	public boolean leaveLevel()
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

		// En el cado de moverse arriba, abajo, izquierda o derecha, se mueve de
		// forma diferente
		switch (this.state)
		{
			case MOVINGUP:
				// Si las celdas e items de arriba de rockford no son solidos...
				// ..rompo la tierra arriba de rockford y agarro el diamante
				// en el caso de que haya
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkUp()) < 2)
				{
					this.getPosition().goUp();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				else
				{
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				break;
			case MOVINGDOWN:
				if (MapInstance.solid(this.getPosition().getX(), this.getPosition().checkDown()) < 2)
				{
					this.getPosition().goDown();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				else
				{
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				break;
			case MOVINGRIGHT:
				if (MapInstance.solid(this.getPosition().checkRight(), this.getPosition().getY()) < 2)
				{
					this.getPosition().goRight();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				// Si no es solido, es movible, y no hay tierra al lado.
				else if (MapInstance.solid(this.getPosition().checkRight() + 1, this.getPosition().getY()) < 1
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY()).isMoveable() == true)
				{
					// Se pushea lo que haya
					this.push(MapItem.getRock(this.getPosition().checkRight(), this.getPosition().getY()));
					this.getPosition().goRight();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				else
				{
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				break;
			case MOVINGLEFT:
				if (MapInstance.solid(this.getPosition().checkLeft(), this.getPosition().getY()) < 2)
				{
					this.getPosition().goLeft();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				else if (MapInstance.solid(this.getPosition().checkLeft() - 1, this.getPosition().getY()) < 1
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).isMoveable() == true)
				{
					// Se pushea lo que haya
					this.push(MapItem.getRock(this.getPosition().checkLeft(), this.getPosition().getY()));
					this.getPosition().goLeft();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				else
				{
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				break;
			default:
				this.collect(MapItem.getDiamond(this.getPosition()));
				break;
		}
		// Por ultimo se setea al jugador en idle
		this.state = StatusActorEnum.IDLE;
	}

}
