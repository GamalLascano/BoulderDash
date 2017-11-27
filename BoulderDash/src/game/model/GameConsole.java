package game.model;

import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import java.util.Scanner;

/**
 * Esta es la clase principal del juego De aqui se maneja la logica del juego,
 * explicacion acerca de funcionamiento adentro del metodo
 */
public class GameConsole
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
	
			final int STARTLEVEL = 1;
	
			MapInstance.start();
			MapVisual.start();
			
			MapInstance.buildSelectedLevel(STARTLEVEL);

			boolean quit = false;

			MapInstance.refresh();
			MapVisual.imprimirMapa();
			System.out.println(
					"Usar las teclas (w)(a)(s)(d) para mover a Rockford, (e) para esperar, apretar (q) para quitar el nivel");

			Rockford player = Rockford.getRockford();
			while (!quit)
			{
				String dir = in.next();
				switch (dir)
				{
					case "w":
						player.move(CurrentDirection.UP);
						break;
					case "s":
						player.move(CurrentDirection.DOWN);
						break;
					case "d":
						player.move(CurrentDirection.RIGHT);
						break;
					case "a":
						player.move(CurrentDirection.LEFT);
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