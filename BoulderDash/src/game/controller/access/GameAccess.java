package game.controller.access;

import game.controller.LaunchGame;

/**
 * Accede al juego.
 *
 */
public class GameAccess
{
	/**
	 * Ejecuta el main de Game para empezar a jugar.
	 */
	public static void launch()
	{
		LaunchGame.runGameThread();
	}
}
