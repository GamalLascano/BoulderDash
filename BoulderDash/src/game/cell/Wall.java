package game.cell;

import game.Position;
import game.item.Diamond;
import game.item.Rock;
import game.item.StatusItem;
import game.map.MapInstance;
import game.map.bdlevel.BDTile;
import game.SpriteChar;

public class Wall extends Cell
{
	SpriteChar spritechar = SpriteChar.W;

	public Wall(Position pos)
	{
		super(pos, true);
	}
	
	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	// CONVERSION
	
	public void conversion(Rock stone)
	{
		if ((stone.getPosition().posY == this.getPos().posY + 1) && (stone.getState().isFalling()))
		{

			stone.getState().setAlive(false);
			Position diamondPos = this.getPos();
			StatusItem diamondState = new StatusItem(false, false, false, true, true);
			Diamond diamond = new Diamond(diamondState, diamondPos);
			MapInstance.loadData(BDTile.DIAMOND, diamond.getPosition().getX(), diamond.getPosition().setY());
		}

	}

	public void conversion(Diamond diamond)
	{
		if ((diamond.getPosition().posY == this.getPos().posY + 1) && (diamond.getState().isFalling()))
		{

			diamond.getState().setAlive(false);
			Position stonePos = this.getPos();
			StatusItem stoneState = new StatusItem(false, false, false, true, true);
			Rock stone = new Rock(stoneState, stonePos);
			MapInstance.loadData(BDTile.ROCK, stone.getPosition().getX(), stone.getPosition().setY());
		}

	}

}
