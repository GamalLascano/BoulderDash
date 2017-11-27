package game.model;

import game.model.actor.Rockford;
import game.model.actor.StatusActorEnum;
import game.model.cell.Exit;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.view.FrameEnd;
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
		//final int STARTLEVEL = 1;
		final int LIVES = 3;
		MapInstance.start();
		MapVisual.start();
		
		MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
		Rockford.getRockford().setLives(LIVES);
		FrameMap.start();
		FrameMap.refresh();
		MapInstance.refresh();
		FrameMap.getInstance().setVisible(true);

		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new Runnable()
		{
			int turn = 0;
			int currentlevel = MapInstance.getSelectedLevel();
			boolean lost = false;
			boolean won = false;
			Rockford player = Rockford.getRockford();

			@Override
			public void run()
			{

				FrameMap.remove();
				if (!lost && !won)
				{
					MapInstance.refresh();
					FrameMap.refresh();

					if (player != null)
					{
						won = player.isInExit();
					}
					if (Rockford.getRockford().getState() == StatusActorEnum.DEAD)
					{
						lost = true;
					}

					if (MapInstance.getTimer() == 0)
					{
						Rockford.getRockford().die();
					}
					Exit.open();
					turn++;
					System.out.println(turn);

				}
				else if (lost)
				{
					MapInstance.refresh();
					FrameMap.refresh();
					lost = false;
					MapInstance.buildSelectedLevel(currentlevel);
					if (Rockford.getRockford().getLives() == 0)
					{
						Integer time = turn;
						FrameMap.getInstance().setVisible(false);
						FrameEnd.setTime(time);
						FrameEnd.main(null);
						executorService.shutdownNow();
						FrameMap.disposeFrame();
					}
				}
				else if (won)
				{
					MapInstance.refresh();
					FrameMap.refresh();
					won = false;
					MapInstance.buildSelectedLevel(++currentlevel);
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