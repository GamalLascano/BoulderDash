package game.model;

import java.util.Timer;
import java.util.TimerTask;

import game.model.actor.Rockford;
import game.model.cell.Exit;
import game.model.map.MapInstance;
import game.view.FrameEnd;
import game.view.FrameMap;
import game.view.sound.SoundPlay;

/**
 * Thread del juego.
 */
public class GameThread extends TimerTask
{
	private int turn = 0;
	private Timer timer;
	private boolean stop = false;

	int currentlevel = MapInstance.getSelectedLevel();
	boolean lost = false;
	boolean won = false;
	Rockford player = Rockford.getRockford();

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
				MapInstance.refresh();
				FrameMap.refresh();

				if (player != null)
				{
					won = player.isInExit();
				}
				if (!ListOfEntities.getList().contains(player))
				{
					MapInstance.buildSelectedLevel(currentlevel);
				}
				if (player.getLives() == 0)
				{
					lost = true;
				}
				if (MapInstance.getTimer() == 0)
				{
					Rockford.getRockford().die();
				}
				Exit.getInstance().open();
				System.out.println(turn);
			}
			else if (lost)
			{
				SoundPlay.lost();
				MapInstance.refresh();
				FrameMap.refresh();
				if (Rockford.getRockford().getLives() == 0)
				{
					stop = true;
				}
				MapInstance.buildSelectedLevel(currentlevel);
				lost = false;
			}
			else if (won)
			{
				SoundPlay.won();
				MapInstance.refresh();
				FrameMap.refresh();
				won = false;
				if (!MapInstance.levelHasRockford())
				{
					stop = true;
				}
				MapInstance.buildSelectedLevel(++currentlevel);
			}
		}
		else
		{
			Integer time = turn;
			FrameMap.getInstance().setVisible(false);
			FrameEnd.getInstance();
			FrameEnd.setTime(time);
			FrameEnd.main(null);
			FrameMap.disposeFrame();
			timer.cancel();
		}
	}

}
