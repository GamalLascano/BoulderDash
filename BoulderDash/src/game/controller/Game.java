package game.controller;

import game.exception.LevelNotValidException;
import game.model.map.MapInstance;
import game.view.FrameMap;

import java.util.Timer;

/**
 * Clase de lanzamiento del juego. Ejecuta el GameThread.
 */
public class Game
{

	/**
	 * Crea un timer y utiliza el nivel almacenado en MapInstance, inicializa a
	 * Mapinstance, luego si el nivel es valido lo construye. Inicializa el
	 * Frame del mapa y lo pone en visible. Refresca todo y invoca al hilo del
	 * juego con el timer.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		final int TASKSPEED = 110;
		final int TASKDELAY = 1000;
		// final int LIVES = 3; Rockford.getInstance().setLives(3);
		// final int STARTLEVEL = 1;

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
			try
			{
				MapInstance.buildSelectedLevel(1);
			}
			catch (LevelNotValidException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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