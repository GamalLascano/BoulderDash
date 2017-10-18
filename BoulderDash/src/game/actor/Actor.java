package game.character;
import game.CurrentDirection;
import game.Position;
import game.Status;
import game.map.BDTile;
import game.map.MapInstance;

public abstract class Actor {
	Status state = new Status();
	Position pos = new Position();
	
	public Actor(Status state, Position pos) {
		super();
		this.state = state;
		this.pos = pos;
	}

	/**
	 * Movimiento en el mapa, poner una direcion como parametro.
	 * usa game.Position y game.CurrentDirection.
	 */
	public void move(CurrentDirection direction) {
		switch (direction) {
			case UP :
				pos.setPosY(pos.getPosY() + 1);
				state.setMoving(true);
				break;
			case DOWN :
				pos.setPosY(pos.getPosY() - 1);
				state.setMoving(true);
				break;
			case LEFT :
				pos.setPosX(pos.getPosX() - 1);
				state.setMoving(true);
				break;
			case RIGHT :
				pos.setPosX(pos.getPosX() + 1);
				state.setMoving(true);
				break;
			default :
				break;
		}
		state.setMoving(false);
	}
	
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
