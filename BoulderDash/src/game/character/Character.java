package game.character;
import game.CurrentDirection;
import game.Position;

public abstract class Character {
	Position coordenada = new Position();
	
	/**
	 * Movimiento en el mapa, poner una direcion como parametro.
	 * usa game.Position y game.CurrentDirection.
	 */
	public boolean move(CurrentDirection direction) {
		switch (direction) {
			case UP :
				coordenada.setPosY(coordenada.getPosY() + 1);
				break;
			case DOWN :
				coordenada.setPosY(coordenada.getPosY() - 1);
				break;
			case LEFT :
				coordenada.setPosX(coordenada.getPosX() - 1);
				break;
			case RIGHT :
				coordenada.setPosX(coordenada.getPosX() + 1);
				break;
			default :
				break;
		}
		return true;
	}
	
	public boolean explode() {
		return true;
	}
	
}
