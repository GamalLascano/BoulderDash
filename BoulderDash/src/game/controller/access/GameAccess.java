package game.controller.access;

import game.controller.Game;

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
		Game.main(new String[0]);
	}
}
