package game.model;

import game.model.element.entity.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import java.util.Scanner;

/**
 * Hace el lanzamiento del juego sin interface grafica. Util para debugear.
 */
public class Console
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		final int STARTLEVEL = 1;

		MapInstance.start();

		MapInstance.buildSelectedLevel(STARTLEVEL);

		boolean quit = false;

		MapInstance.refresh();
		MapVisual.imprimirMapa();
		System.out.println("Usar las teclas (w)(a)(s)(d) para mover a Rockford, (e) para esperar, apretar (q) para quitar el nivel");

		Rockford player = Rockford.getRockford();
		while (!quit)
		{
			String dir = in.next();
			switch (dir)
			{
				case "w":
					player.move(Direction.UP);
					break;
				case "s":
					player.move(Direction.DOWN);
					break;
				case "d":
					player.move(Direction.RIGHT);
					break;
				case "a":
					player.move(Direction.LEFT);
					break;
				case "e":
					break;
				case "q":
					quit = true;
					break;
				default:
					break;
			}

			MapInstance.refresh();
			MapVisual.imprimirMapa();
			if (!quit)
			{
				quit = player.isInExit();
			}
		}

		System.out.println("FIN DEL NIVEL: " + MapInstance.getSelectedLevel());
		System.out.println("FIN DEL PROGRAMA");
		in.close();
	}

}