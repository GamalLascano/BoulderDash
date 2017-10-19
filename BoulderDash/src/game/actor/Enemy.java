package game.actor;

import game.Position;
import game.Status;

public abstract class Enemy extends Actor {
	
	public Enemy(Status state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	protected abstract boolean rotate();
	
	public void explode() {
		//Hay bloques que no pueden desparecer! ej: bloque salida
		//Hay que ver como tratar con las instancias de las cells
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX(), pos.getPosY());
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX() + 1, pos.getPosY());
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX()-1, pos.getPosY());
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX(), pos.getPosY() + 1);
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX(), pos.getPosY() - 1);
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX() + 1, pos.getPosY() + 1);
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX() - 1, pos.getPosY() - 1);
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX() - 1, pos.getPosY() + 1);
		MapInstance.loadData(BDTile.EMPTY, pos.getPosX() + 1, pos.getPosY() - 1);
		
	}

}
