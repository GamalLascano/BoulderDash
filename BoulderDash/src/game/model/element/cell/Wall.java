package game.model.element.cell;

import game.model.element.Position;
import game.model.element.SpriteChar;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Rock;
import game.model.element.entity.item.StatusFallableEnum;
import game.model.map.MapItem;

/**
 * Clase que representa el muro y muro magico.
 * El magic timer es el tiempo del muro magico antes
 * de desactivarse.
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
	 * @return tiempo del muro magico
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
