package game.model;

import game.model.actor.Rockford;
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
		final int TASKSPEED = 200;
		final int TASKDELAY = 1000;

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

		// Timer timer = new Timer("Imprimir..");
		// Mitarea tarea = new MiTarea(timer);
		// timer.schedule(tarea, 0, 2000);
		// final CountDownLatch latch = new CountDownLatch(1);

		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.scheduleAtFixedRate(new Runnable()
		{
			int seconds = 0;
			boolean quit = false;

			@Override
			public void run()
			{
				Rockford player = Rockford.getInstance();
				FrameMap.remove();
				if (!quit)
				{
					MapVisual.drawMap();
					MapInstance.refresh();
					FrameMap.draw();
					if (player != null)
					{
						quit = player.isInExit();
					}
					if (Rockford.getInstance() != null)
					{
						quit = false;
					}
					else
					{
						quit = true;
					}
					seconds++;
					System.out.println(seconds);

				}
				else
				{
					MapVisual.drawMap();
					MapInstance.refresh();
					FrameMap.draw();
					// latch.countDown();
				}
			}
		}, TASKDELAY, TASKSPEED, TimeUnit.MILLISECONDS);

		// try
		// {
		// latch.await();
		// }
		// catch (InterruptedException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// executorService.shutdownNow();
		//
		// MapVisual.drawMap();
		// MapInstance.refresh();
		// FrameMap.draw();
		// System.out.println("FIN DEL PROGRAMA");
		// FrameMap.disposeFrame();
		//
		// FrameMenu.main(new String[0]);
	}

}