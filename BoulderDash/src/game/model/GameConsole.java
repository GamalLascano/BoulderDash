package game.model;

import game.model.actor.Rockford;
import game.model.cell.Exit;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.model.map.bdlevel.BDLevelReader;
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
			MapInstance.setSelectedLevel(STARTLEVEL);
			MapInstance.readLevel();
			MapInstance.buildMap();
			MapVisual.getInstance().start(MapInstance.getLevelReader());

			boolean quit = false;

			MapVisual.drawMap();
			MapVisual.imprimirMapa();
			MapInstance.refresh();
			System.out.println(
					"Usar las teclas (w)(a)(s)(d) para mover a Rockford, (e) para esperar, apretar (q) para quitar el nivel");

			Rockford player = Rockford.getInstance();
			Exit door = Exit.getInstance();
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

				if (door.getSolid() == SolidTo.ALL)
				{
					Exit.open();
				}

				MapInstance.refresh();
				MapVisual.drawMap();
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