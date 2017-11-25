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
		final int TASKSPEED = 110;
		final int TASKDELAY = 1000;
		final int STARTLEVEL = 1;

		MapInstance.start();
		FrameMap.start();
		
		MapInstance.setSelectedLevel(STARTLEVEL);
		MapInstance.readLevel();
		MapVisual.getInstance().start(MapInstance.getLevelReader());

		MapInstance.buildMap();
		MapVisual.drawMap();
		FrameMap.getPanelmap().repaint();
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
					FrameMap.refreshPaneltop();
					FrameMap.getPanelmap().repaint();
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
					FrameMap.getPanelmap().repaint();
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