package game.actor;

import game.Position;
import game.item.Diamond;
import game.item.Rock;
import game.SpriteChar;
import game.cell.Dirt;
import game.map.MapActor;
import game.map.MapItem;
import game.map.MapCell;

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
	public void makeMove()
	{
		// En el cado de moverse arriba, abajo, izquierda o derecha, se mueve de
		// forma diferente
		switch (this.state)
		{
			case MOVINGUP:
				// Si las celdas e items de arriba de rockford no son solidos...
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkUp()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkUp()).isSolid() < 2)
				{
					// ..rompo la tierra arriba de rockford y agarro el diamante
					// en el caso de que haya
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
				// Si las celdas e items de abajo de rockford no son solidos...
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).isSolid() < 2)
				{
					// ..rompo la tierra abajo de rockford y agarro el diamante
					// en el caso de que haya
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
				// Si las celdas e items en la derecha de rockford no son
				// solidos...
				if (MapCell.getCell(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 2)
				{
					// ..rompo la tierra en la derecha de rockford y agarro el
					// diamante en el caso de que haya
					this.getPosition().goRight();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				// Si no es solido, es movible, y no hay tierra al lado.
				else if (MapCell.getCell(this.getPosition().checkRight(), this.getPosition().getY()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY())
								.isMoveable() == true
						&& MapItem.getItem(this.getPosition().checkRight() + 1, this.getPosition().getY()).isSolid() < 1
						&& MapCell.getCell(this.getPosition().checkRight() + 1, this.getPosition().getY())
								.isSolid() < 1)
				{
					// Se pushea lo que haya
					this.getPosition().goRight();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
					this.push(MapItem.getRock(this.getPosition()));
				}
				else
				{
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				break;
			case MOVINGLEFT:
				// Si las celdas e items en la izquierda de rockford no son
				// solidos...
				if (MapCell.getCell(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 2)
				{
					// ..rompo la tierra en la derecha de rockford y agarro el
					// diamante en el caso de que haya
					this.getPosition().goLeft();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
				}
				// Si no es solido, es movible, y no hay tierra al lado
				else if (MapCell.getCell(this.getPosition().checkLeft(), this.getPosition().getY()).isSolid() < 2
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY())
								.isMoveable() == true
						&& MapItem.getItem(this.getPosition().checkLeft() - 1, this.getPosition().getY()).isSolid() < 1
						&& MapCell.getCell(this.getPosition().checkLeft() - 1, this.getPosition().getY()).isSolid() < 1)
				{
					// Se pushea lo que haya
					this.getPosition().goLeft();
					this.dig(MapCell.getDirt(this.getPosition()));
					this.collect(MapItem.getDiamond(this.getPosition()));
					this.push(MapItem.getRock(this.getPosition()));
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
