package game.cell;

import game.Position;
import game.item.Diamond;
import game.item.Rock;
import game.item.StatusFallableEnum;
import game.map.MapItem;
import game.SpriteChar;

public class Wall extends Cell
{
	private SpriteChar spritechar = SpriteChar.W;

	public Wall(Position pos)
	{
		super(pos, 2);
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	// CONVERSION

	//corregir esto dsp
	public void conversion(Rock stone)
	{
		if (( stone.getPosition().getY() == this.getPosition().checkUp() ) && ( stone.getState() == StatusFallableEnum.FALLING) )
		{

			stone.setState(StatusFallableEnum.DEAD);
			Position diamondPos = this.getPosition();
			Diamond diamond = new Diamond(diamondPos, StatusFallableEnum.FALLING);
			MapItem.setItem(diamondPos, diamond);
		}

	}

}
