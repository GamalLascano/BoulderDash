package game;

import game.actor.Rockford;//
import game.map.MapInstance;
import game.map.MapVisual;
import game.cell.Exit;
import game.map.bdlevel.BDLevelReader;
import java.util.Scanner;

public class Game
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		BDLevelReader levelFrame = new BDLevelReader();
		int nivelElegido;
		
		for(nivelElegido = 1; nivelElegido <= 3; nivelElegido++)
		{
				try
				{
					int levels = levelFrame.readLevels("levels.xml");
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
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
		
				// se ponen los objectos en la matriz
				MapInstance.getInstance();
				MapInstance.start(levelFrame);
				MapInstance.buildMap(levelFrame);
				MapVisual.getInstance().start(levelFrame);;
				
				Rockford player = ActiveEntities.findRockford();
				Exit exit = MapInstance.getMapCell().findExit();
				boolean quit = false;
				

				MapVisual.drawMap(MapInstance.getMapCell(), MapInstance.getMapItem(), MapInstance.getMapActor());
				MapVisual.imprimirMapa();
				MapInstance.refresh();
				
				System.out.println("Usar las teclas (w)(a)(s)(d) para mover a Rockford, apretar (q) para quitar el nivel");
		
				while( quit == false )
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
						case "q":
							quit = true;
							break;
						default:
							break;
					}
					
					MapInstance.refresh();
					MapVisual.drawMap(MapInstance.getMapCell(), MapInstance.getMapItem(), MapInstance.getMapActor());
					MapVisual.imprimirMapa();
					
				}
				
				System.out.println("FIN DEL NIVEL: " + nivelElegido);
		

			}
		System.out.println("FIN DEL PROGRAMA");
		in.close();
	}

}
