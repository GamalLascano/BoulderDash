package game;

import java.util.Timer;
import java.util.TimerTask;

import game.controller.ElementAccess;
import game.controller.MapAccess;
import game.controller.PlayerAccess;
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
	//Rockford player = PlayerAccess.getPlayer();

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
	 * Ejecucion en loop. Cuando rockford no tiene mas vidas o no hay mas
	 * niveles, la variable stop determina el final del loop.
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
						// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
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
