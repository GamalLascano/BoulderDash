package game.controller;

import game.model.Direction;
import game.model.element.entity.actor.Rockford;

/**
 * 
 * Acceso del jugador.
 *
 */
public class PlayerAccess
{
	/**
	 * Hace que el jugador se mueva de manera fluida.
	 */
	public static void updateMove()
	{
		Rockford player = Rockford.getRockford();	// try catch sonido
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
	 * Resetea a rockford.
	 */
	public static void resetPlayer()
	{
		Rockford.getRockford().reset();
	}

	/**
	 * 
	 * @return
	 */
	public static Rockford getPlayer()
	{
		return Rockford.getRockford();
	}
	
	/**
	 * Devuelve los diamantes.
	 *
	 * @return
	 */
	public static Integer getDiamonds()
	{
		return Rockford.getRockford().getDiamonds();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Integer getLives()
	{
		return Rockford.getRockford().getLives();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Integer getScore()
	{
		return Rockford.getRockford().getScore();
	}


}
