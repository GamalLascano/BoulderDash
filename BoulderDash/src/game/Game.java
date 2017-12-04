package game;

import game.exception.LevelNotValidException;
import game.model.map.MapInstance;
import game.view.FrameMap;

import java.util.Timer;

/**
 * Clase de lanzamiento del juego. Ejecuta el GameThread.
 */
public class Game
{

	public static void main(String[] args)
	{
		final int TASKSPEED = 110;
		final int TASKDELAY = 1000;
		// final int LIVES = 3; Rockford.getInstance().setLives(3);
		//final int STARTLEVEL = 1;

		Timer timer = new Timer("test");
		int selectedlevel; 
		selectedlevel = MapInstance.getSelectedLevel();
		MapInstance.start();
		try
		{
			MapInstance.buildSelectedLevel(selectedlevel);
		}
		catch (LevelNotValidException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FrameMap.start();
		FrameMap.refresh();
		MapInstance.refresh();
		FrameMap.getInstance().setVisible(true);

		GameThread task = new GameThread(timer);
		timer.schedule(task, TASKDELAY, TASKSPEED);

	}

}