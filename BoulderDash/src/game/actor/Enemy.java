package game.actor;

import game.Position;
import game.StatusItem;

public abstract class Enemy extends Actor {
	
	public Enemy(StatusItem state, Position pos) {
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	protected abstract boolean rotate();
		/**
	 * Explosion, pone celda vacias alrededor del personaje.
	 * Explosion cuadrada 3x3.
	 */
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
