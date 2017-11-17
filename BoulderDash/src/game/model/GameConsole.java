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
		BDLevelReader levelFrame = new BDLevelReader();
		int nivelElegido;

		for (nivelElegido = 1; nivelElegido <= 10; nivelElegido++)
		{
			try
			{
				levelFrame.readLevels("levels.xml");
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			try
			{
				levelFrame.setCurrentLevel(nivelElegido);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			MapInstance.getInstance();
			MapInstance.start(levelFrame);
			MapInstance.buildMap();
			MapVisual.getInstance().start(levelFrame);

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

			System.out.println("FIN DEL NIVEL: " + nivelElegido);

		}
		System.out.println("FIN DEL PROGRAMA");
		in.close();
	}

}