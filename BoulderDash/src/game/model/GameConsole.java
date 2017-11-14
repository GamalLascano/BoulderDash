package game.model;

import game.model.actor.Rockford;
import game.model.cell.Exit;
import game.model.map.MapCell;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.model.map.bdlevel.BDLevelReader;
import game.view.FrameMap;

import java.util.Scanner;

/**
 * Esta es la clase principal del juego De aqui se maneja la logica del juego,
 * explicacion acerca de funcionamiento adentro del metodo
 */
public class GameConsole
{
	public static void main(String[] args)
	{
		// Primero armo el scanner para el movimiento, y inicializo el level
		// reader
		Scanner in = new Scanner(System.in);
		BDLevelReader levelFrame = new BDLevelReader();
		int nivelElegido;
		// empiezo del nivel 1, y voy iterando por los niveles
		for (nivelElegido = 1; nivelElegido <= 10; nivelElegido++)
		{
			// hago que lea los niveles, y cargue el nivel elegido
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// se empieza la instancia del mapa
			MapInstance.getInstance();
			// el mapa se empieza con el frame del nivel actual
			MapInstance.start(levelFrame);
			// y luego se arma
			MapInstance.buildMap();
			// luego, armo las visuales con el levelframe
			MapVisual.getInstance().start(levelFrame);
			;
			// busco a rockford en la lista de entidades
			Rockford player = ListOfEntities.findRockford();
			Exit salida=MapCell.findExit();
			boolean quit = false;
			
			// interpolo el mapa actual con todos los mapas de todos los objetos
			MapVisual.drawMap();
			FrameMap.start();
			FrameMap.draw();
			// imprimo el mapa en pantalla
			MapVisual.imprimirMapa();
			// hago que se muevan todos los actores
			MapInstance.refresh();
			System.out.println(
					"Usar las teclas (w)(a)(s)(d) para mover a Rockford, (e) para esperar, apretar (q) para quitar el nivel");

			while (!quit)
			{
				// Este case va a obtener los movimientos que va a hacer el
				// personaje .
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
				
				if (salida.getSolid() == SolidTo.ALL) {
					salida.open(levelFrame, player);
				}
				// y esto refresca el mapa con el movimiento elegido
				FrameMap.remove();
				MapInstance.refresh();
				MapVisual.drawMap();
				FrameMap.draw();
				MapVisual.imprimirMapa();
				if (!quit) {
					quit=player.isInExit();
				}
			}

			System.out.println("FIN DEL NIVEL: " + nivelElegido);

		}
		System.out.println("FIN DEL PROGRAMA");
		in.close();
	}

}