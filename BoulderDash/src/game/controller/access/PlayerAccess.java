package game.controller.access;

import game.controller.input.Keyboard;
import game.model.Direction;
import game.model.element.entity.actor.Rockford;

/**
 * 
 * Acceso del jugador del modelo.
 *
 */
public class PlayerAccess
{
	/**
	 * Hace que el jugador se mueva de manera fluida con el teclado. Y actualiza
	 * el movimiento.
	 */
	public static void updateMove()
	{
		Rockford player = Rockford.getInstance(); // try catch sonido
		if (Keyboard.isUp())
		{
			player.move(Direction.UP);
		}
		if (Keyboard.isDown())
		{
			player.move(Direction.DOWN);
		}
		if (Keyboard.isLeft())
		{
			player.move(Direction.LEFT);
		}
		if (Keyboard.isRight())
		{
			player.move(Direction.RIGHT);
		}
	}

	/**
	 * Resetea a rockford a sus valores iniciales.
	 */
	public static void resetPlayer()
	{
		Rockford.getInstance().reset();
	}

	/**
	 * 
	 * @return devuelve a rockford
	 */
	public static Rockford getPlayer()
	{
		return Rockford.getInstance();
	}

	/**
	 *
	 * @return diamantes de rockford
	 */
	public static Integer getDiamonds()
	{
		return Rockford.getInstance().getDiamonds();
	}

	/**
	 * 
	 * @return vidas de rockford
	 */
	public static Integer getLives()
	{
		return Rockford.getInstance().getLives();
	}

	/**
	 * 
	 * @return score de rockford ganado en el nivel
	 */
	public static Integer getScore()
	{
		return Rockford.getInstance().getScore();
	}

}
