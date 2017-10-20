package game.cell;

import game.Position;
import game.item.Diamond;
import game.item.Rock;
import game.item.StatusItem;
import game.map.MapInstance;
import game.map.BDLevel.BDTile;

public class Wall extends Cell {

	public Wall(Position pos)
	{
		super(pos, true);
	}
	
	public void conversion(Rock stone) {
		if ( ( stone.getPos().posY == this.getPos().posY + 1) 
				&& ( stone.getState().isFalling() ) ) {
			
			stone.getState().setAlive(false);
			Position diamondPos = this.getPos();
			StatusItem diamondState = new StatusItem(false,false,false,true,true);
			Diamond diamond = new Diamond(diamondState, diamondPos);
			MapInstance.loadData(BDTile.DIAMOND, diamond.getPos().getPosX(), diamond.getPos().getPosY());
		}
		
	}
	
	public void conversion(Diamond diamond) {
		if ( ( diamond.getPos().posY == this.getPos().posY + 1) 
				&& ( diamond.getState().isFalling() ) ) {
			
			diamond.getState().setAlive(false);
			Position stonePos = this.getPos();
			StatusItem stoneState = new StatusItem(false,false,false,true,true);
			Rock stone = new Rock(stoneState, stonePos);
			MapInstance.loadData(BDTile.ROCK, stone.getPos().getPosX(), stone.getPos().getPosY());
		}
		
	}
	
}
