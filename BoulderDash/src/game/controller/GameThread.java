package game.controller;

import java.util.Timer;
import java.util.TimerTask;

import game.controller.access.ElementAccess;
import game.controller.access.MapAccess;
import game.controller.access.PlayerAccess;
import game.exception.LevelNotValidException;
import game.model.map.MapInstance;
import game.view.FrameEnd;
import game.view.FrameMap;
import game.view.sound.Sound;

/**
 * Thread del juego.
 */
public class GameThread extends TimerTask
{
	private int turn = 0;
	private Timer timer;
	private boolean stop = false;

	boolean lost = false;
	boolean won = false;
	// Rockford player = PlayerAccess.getPlayer();

	/**
	 * Constructor, timer del thread.
	 * 
	 * @param timer
	 */
	public GameThread(Timer timer)
	{
		this.timer = timer;
	}

	/**
	 * Ejecucion en loop.<p>
	 * 1. Incrementa un turno y remueve los elementos del
	 * panel.<p>
	 * 2. Refresca el juego cada turno.<p>
	 * 3. Pierde si no tiene mas vidas,
	 * gana si llega a la salida.<p>
	 * 4. Cada turno verifica si el jugador gano o
	 * perdio, hace algo en cada caso.<p>
	 * 5. Cuando pierde, se parra el juego,
	 * ejecuta el frame de entrada de score y termina el timer.<p>
	 * 
	 */
	public void run()
	{
		turn++;
		FrameMap.remove();
		if (!stop)
		{
			if (!lost && !won)
			{
				MapAccess.refresh();
				FrameMap.refresh();

				if (PlayerAccess.getPlayer() != null)
				{
					won = PlayerAccess.getPlayer().isInExit();
				}
				if (!ElementAccess.entityIsAlive(PlayerAccess.getPlayer()))
				{
					try
					{
						MapInstance.buildSelectedLevel(MapAccess.getLevel());
					}
					catch (LevelNotValidException e)
					{
						try
						{
							MapInstance.buildSelectedLevel(1);
						}
						catch (LevelNotValidException e1)
						{
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
				if (PlayerAccess.getPlayer().getLives() == 0)
				{
					lost = true;
				}
				if (MapAccess.getTimer() == 0)
				{
					PlayerAccess.getPlayer().die();
				}
				ElementAccess.openExit();
				System.out.println(turn);
			}
			else if (lost)
			{
				Sound.lost();
				MapAccess.refresh();
				FrameMap.refresh();
				if (PlayerAccess.getPlayer().getLives() == 0)
				{
					stop = true;
				}
				try
				{
					MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
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
				lost = false;
			}
			else if (won)
			{
				Sound.won();
				MapAccess.refresh();
				FrameMap.refresh();
				won = false;
				if (!MapInstance.levelHasRockford())
				{
					stop = true;
				}
				MapInstance.setSelectedLevel(MapInstance.getSelectedLevel() + 1);
				try
				{
					MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
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
			}
		}
		else
		{
			Integer time = turn;
			FrameMap.getInstance().setVisible(false);
			FrameEnd.getInstance();
			FrameEnd.setTime(time);
			FrameEnd.runFrameEnd(null);
			FrameMap.disposeFrame();
			timer.cancel();
		}
	}

}
