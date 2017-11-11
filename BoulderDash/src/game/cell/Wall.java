package game.cell;

import game.Position;
import game.item.Diamond;
import game.item.Rock;
import game.item.StatusItem;
import game.item.StatusItemEnum;
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
		if (( stone.getPosition().getY() == this.getPosition().checkUp() ) && ( stone.getState().getStateEnum() == StatusItemEnum.FALLING) )
		{

			stone.getState().setAlive(false);
			Position diamondPos = this.getPosition();
			StatusItem diamondState = new StatusItem(StatusItemEnum.FALLING);
			Diamond diamond = new Diamond(diamondState, diamondPos);
			MapItem.setItem(diamondPos, diamond);
		}

	}

}
