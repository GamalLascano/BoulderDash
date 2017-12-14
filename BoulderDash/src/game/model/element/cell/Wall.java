package game.model.element.cell;

import game.model.element.Position;
import game.model.element.ElementChar;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Fallable;
import game.model.element.entity.item.Rock;
import game.model.element.entity.item.StatusFallableEnum;
import game.model.map.MapElement;

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
		this.setSpritechar(ElementChar.W);
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
		this.setSpritechar(ElementChar.W);
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
		if (canConvert(stone))
		{

			this.setSpritechar(ElementChar.w);
			rockToDiamond(stone);
			this.magicTimer--;
		}
		else
		{
			this.setSpritechar(ElementChar.W);
		}

	}

	/**
	 * Convierte de diamantes a rocas.
	 * 
	 * @param diamond
	 */
	public void conversion(Diamond diamond)
	{
		if (canConvert(diamond))
		{
			this.setSpritechar(ElementChar.w);
			diamondToRock(diamond);
			this.magicTimer--;
		}
		else
		{
			this.setSpritechar(ElementChar.W);
		}

	}



	//////////////
	
	/**
	 * 
	 * @param fallableitem
	 * @return si se puede convertir el fallableitem
	 */
	private boolean canConvert(Fallable fallableitem)
	{
		return (fallableitem.getPosition().getY() == this.getPosition().checkUp()) && (fallableitem.getState() == StatusFallableEnum.FALLING) && this.magicTimer > 0;
	}
	
	/**
	 * Transforma roca a diamante
	 * @param stone
	 */
	private void rockToDiamond(Rock stone)
	{
		stone.die();
		Position diamondPos = new Position(this.getPosition().getX(), this.getPosition().checkDown());
		Diamond diamond = new Diamond(diamondPos, StatusFallableEnum.FALLINGOFF);
		MapElement.setItem(diamond);
	}
	
	/**
	 * Transforma diamante a roca
	 * @param diamond
	 */
	private void diamondToRock(Diamond diamond)
	{
		diamond.die();
		Position rockPos = new Position(this.getPosition().getX(), this.getPosition().checkDown());
		Rock rock = new Rock(rockPos, StatusFallableEnum.FALLINGOFF);
		MapElement.setItem(rock);
	}
}
