package game.model;

import game.model.actor.Rockford;
import game.model.cell.Exit;
import game.model.map.MapCell;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.model.map.bdlevel.BDLevelReader;
import game.view.FrameMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Esta es la clase principal del juego De aqui se maneja la logica del juego,
 * explicacion acerca de funcionamiento adentro del metodo
 */
public class Game
{

	public static void main(String[] args)
	{
		BDLevelReader levelReader = new BDLevelReader();
		int nivelElegido = 1;

		try
		{
			levelReader.readLevels("levels.xml");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			levelReader.setCurrentLevel(nivelElegido);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MapInstance.start(levelReader);
		FrameMap.start();
		MapVisual.getInstance().start(levelReader);

		MapInstance.buildMap();
		MapVisual.drawMap();
		FrameMap.draw();
		MapInstance.refresh();

		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.scheduleAtFixedRate(new Runnable()
		{
			int seconds = 0;
			@Override
			public void run()
			{
				Rockford player = ListOfEntities.findRockford();
				FrameMap.remove();
				if (player.leaveLevel() == false)
				{
					MapVisual.drawMap();
					MapInstance.refresh();
					FrameMap.draw();
					seconds++;
					System.out.println(seconds);
				}
			}
		}, 500, 500, TimeUnit.MILLISECONDS);
		System.out.println("FIN DEL PROGRAMA");
	}

}