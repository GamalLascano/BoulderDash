package game.model.item;

/**
 * Esto indica todos los estados de movimiento de un item Falling: cayendo abajo
 * slidingright: deslizandose a la derecha slidingleft: deslizandose a la
 * izquierda idle: quieto
 *
 */
public enum StatusFallableEnum
{
	FALLINGOFF, FALLING, SLIDINGRIGHT, SLIDINGLEFT, IDLE, DEAD
}
