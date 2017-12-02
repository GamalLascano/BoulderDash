package game.model.element.entity;

/**
 * Interface moveable para los elementos que se mueven
 * en todas las direciones.
 */
public interface Moveable
{

	/**
	 * Hace la rotacion de la entidad. Utilizando estados.
	 */
	public abstract void rotate();

	/**
	 * Comportamiento para mover el actor hacia arriba.
	 */
	public abstract void makeMoveUp();

	/**
	 * Comportamiento para mover el actor hacia abajo.
	 */
	public abstract void makeMoveDown();

	/**
	 * Comportamiento para mover el actor hacia la izquierda.
	 */
	public abstract void makeMoveLeft();

	/**
	 * Comportamiento para mover el actor hacia la derecha.
	 */
	public abstract void makeMoveRight();
}
