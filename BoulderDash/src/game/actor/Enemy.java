package game.actor;

import game.Position;
import game.item.StatusItem;
import game.map.MapInstance;

public abstract class Enemy extends Actor
{

	public Enemy(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	protected abstract boolean rotate();

	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public void explode()
	{
		// Hay bloques que no pueden desparecer! ej: bloque salida
		// Hay que ver como tratar con las instancias de las cells
		MapInstance.setTile(BDTile.EMPTY, pos.getX(), pos.setY());
		MapInstance.setTile(BDTile.EMPTY, pos.getX() + 1, pos.setY());
		MapInstance.setTile(BDTile.EMPTY, pos.getX() - 1, pos.setY());
		MapInstance.setTile(BDTile.EMPTY, pos.getX(), pos.setY() + 1);
		MapInstance.setTile(BDTile.EMPTY, pos.getX(), pos.setY() - 1);
		MapInstance.setTile(BDTile.EMPTY, pos.getX() + 1, pos.setY() + 1);
		MapInstance.setTile(BDTile.EMPTY, pos.getX() - 1, pos.setY() - 1);
		MapInstance.setTile(BDTile.EMPTY, pos.getX() - 1, pos.setY() + 1);
		MapInstance.setTile(BDTile.EMPTY, pos.getX() + 1, pos.setY() - 1);

	}

}
