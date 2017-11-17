package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.item.Diamond;
import game.model.item.Rock;
import game.model.item.StatusFallableEnum;
import game.model.map.MapCell;
import game.model.map.MapItem;

/**
 * 
 *
 */
public class Wall extends Cell
{
	private SpriteChar spritechar = SpriteChar.W;

	/**
	 * 
	 * @param pos
	 */
	public Wall(Position pos)
	{
		super(pos, SolidTo.ALL);
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
	 * @param stone
	 */
	public void conversion(Rock stone)
	{
		if ((stone.getPosition().getY() == this.getPosition().checkUp())
				&& (stone.getState() == StatusFallableEnum.FALLING))
		{

			stone.setState(StatusFallableEnum.DEAD);
			Position diamondPos = this.getPosition();
			Diamond diamond = new Diamond(diamondPos, StatusFallableEnum.FALLING);
			MapItem.setItem(diamond);
		}

	}

}
