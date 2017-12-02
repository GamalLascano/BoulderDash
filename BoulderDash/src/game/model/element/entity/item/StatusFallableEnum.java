package game.model.element.entity.item;

/**
 * Esto indica todos los estados de movimiento de un item <p>
 * Falling: cayendo abajo <bz>
 * slidingright/left: deslizandose <bz>
 * idle: quieto.
 *
 */
public enum StatusFallableEnum
{
	FALLINGOFF,
	FALLING,
	SLIDINGRIGHT,
	SLIDINGLEFT,
	PUSHEDRIGHT,
	PUSHEDLEFT,
	CONVERT,
	IDLE,
	DEAD
}
