package game.character;
import game.CurrentDirection;
import game.Position;
import game.Status;
import game.map.BDLevelReader;
import game.map.BDTile;

public abstract class Character {
	Status state = new Status();
	Position pos = new Position();
	
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
		BDLevelReader.field[pos.getPosX()][pos.getPosY()] = BDTile.EMPTY;
		
		BDLevelReader.field[pos.getPosX() + 1][pos.getPosY()] = BDTile.EMPTY;
		BDLevelReader.field[pos.getPosX() - 1][pos.getPosY()] = BDTile.EMPTY;
		
		BDLevelReader.field[pos.getPosX()][pos.getPosY() + 1] = BDTile.EMPTY;
		BDLevelReader.field[pos.getPosX()][pos.getPosY() - 1] = BDTile.EMPTY;
		
		BDLevelReader.field[pos.getPosX() + 1][pos.getPosY() + 1] = BDTile.EMPTY;
		BDLevelReader.field[pos.getPosX() - 1][pos.getPosY() - 1] = BDTile.EMPTY;
		
		BDLevelReader.field[pos.getPosX() + 1][pos.getPosY() - 1] = BDTile.EMPTY;
		BDLevelReader.field[pos.getPosX() - 1][pos.getPosY() + 1] = BDTile.EMPTY;
		
	}
	
}
