package game.actor;

import game.Position;
import game.item.Item;
import game.item.Diamond;
import game.item.Rock;
import game.SpriteChar;
import game.cell.Dirt;

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
	private boolean pushing;

	/**
	 * Permite inicializar a Rockford con un status y posicion determinadas
	 * 
	 * @param state: Contiene movimiento y estado de vida
	 * @param pos: Posicion
	 */
	public Rockford(StatusActor state, Position pos)
	{
		super(state, pos, 1);
		this.score = 0;
		this.diamonds = 0;
		this.pushing = false;
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
		return pushing;
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
		this.pushing = pushing;
	}

	// SAVE

	public boolean save()
	{
		return true;
	}

	/**
	 * Este metodo hace que si esta empujando a un item, se le ponga el estado
	 * correspondiente
	 * 
	 * @param item: Item que se empujaria
	 */
	public void push(Item item)
	{
		while (super.getPosition().getX() == item.getPosition().checkRight())
		{
			this.pushing = true;
		}
	}

	/**
	 * Remueve la tierra del juego
	 * 
	 * @param dirt: Bloque de tierra
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
	 * @param diamond: Bloque de diamante
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
	 * @param rock: Bloque de roca
	 */
	public void push(Rock rock)
	{
		if (rock != null && rock.isMoveable())
		{
			this.pushing = true;
			rock.pushed(this);
			this.pushing = false;
		}
	}

}
