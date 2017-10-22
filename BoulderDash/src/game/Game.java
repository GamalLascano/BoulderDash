package game;

import game.actor.Rockford;
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

		int nivelElegido = 1;
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
		
		Rockford player = MapInstance.getEntitiesActive().findRockford();
		Exit exit = MapInstance.getMapCell().findExit();
		
		MapVisual.drawMap(MapInstance.getMapCell(), MapInstance.getMapItem(), MapInstance.getMapActor());
		MapVisual.imprimirMapa();
		MapInstance.refresh();
		
		int i = 0;
		while( i < 10 )
		{
			i++;
			char dir = in.next(".").charAt(0);
			switch (dir)
			{
				case 'w':
					player.move(CurrentDirection.UP);
					break;
				case 's':
					player.move(CurrentDirection.DOWN);
					break;
				case 'd':
					player.move(CurrentDirection.RIGHT);
					break;
				case 'a':
					player.move(CurrentDirection.LEFT);
					break;
				default:
					break;
			}
			
			MapInstance.refresh();
			MapVisual.drawMap(MapInstance.getMapCell(), MapInstance.getMapItem(), MapInstance.getMapActor());
			MapVisual.imprimirMapa();
			
		}

		/**
		nivelElegido = 2;
		//levelFrame.setCurrentLevel(nivelElegido);
		MapInstance.buildMap(levelFrame);
		*/
		in.close();
	}

}
