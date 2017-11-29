package game.model.cell;

import game.model.Position;
import game.model.SpriteChar;
import game.model.item.Diamond;
import game.model.item.Rock;
import game.model.item.StatusFallableEnum;
import game.model.map.MapItem;

/**
 * Clase que representa el muro y muro magico.
 */
public class Wall extends Cell
{
	private int magicTimer;

	/**
	 * Constructor de muro.
	 * 
	 * @param pos
	 */
	public Wall(Position pos)
	{
		super(pos);
		this.magicTimer = 0;
		this.setSpritechar(SpriteChar.W);
	}

	/**
	 * Constructor del muro para muros magicos.
	 * 
	 * @param pos
	 * @param magicTime
	 */
	public Wall(Position pos, int magicTime)
	{
		super(pos);
		this.magicTimer = magicTime;
		this.setSpritechar(SpriteChar.W);
	}

	/**
	 * Retorna el tiempo del muro magico.
	 * 
	 * @return magicTimer
	 */
	public int getMagicTimer()
	{
		return magicTimer;
	}

	/**
	 * Convierte de rocas a diamantes.
	 * 
	 * @param stone
	 */
	public void conversion(Rock stone)
	{
		if ((stone.getPosition().getY() == this.getPosition().checkUp()) && (stone.getState() == StatusFallableEnum.FALLING) && this.magicTimer > 0)
		{

			this.setSpritechar(SpriteChar.w);
			stone.die();
			Position diamondPos = new Position(this.getPosition().getX(), this.getPosition().checkDown());
			Diamond diamond = new Diamond(diamondPos, StatusFallableEnum.FALLINGOFF);
			MapItem.setItem(diamond);
			this.magicTimer--;
		}
		else
		{
			this.setSpritechar(SpriteChar.W);
		}

	}

	/**
	 * Convierte de diamantes a rocas.
	 * 
	 * @param diamond
	 */
	public void conversion(Diamond diamond)
	{
		if ((diamond.getPosition().getY() == this.getPosition().checkUp()) && (diamond.getState() == StatusFallableEnum.FALLING) && this.magicTimer > 0)
		{
			this.setSpritechar(SpriteChar.w);
			diamond.die();
			Position rockPos = new Position(this.getPosition().getX(), this.getPosition().checkDown());
			Diamond rock = new Diamond(rockPos, StatusFallableEnum.FALLINGOFF);
			MapItem.setItem(rock);
			this.magicTimer--;
		}
		else
		{
			this.setSpritechar(SpriteChar.W);
		}

	}

}
