package game.controller;

import java.util.Timer;
import java.util.TimerTask;

import game.controller.access.ElementAccess;
import game.controller.access.MapAccess;
import game.controller.access.PlayerAccess;
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
	 * Ejecucion en loop.
	 * <p>
	 * 1. Incrementa un turno y remueve los elementos del panel.
	 * <p>
	 * 2. Refresca el juego cada turno.
	 * <p>
	 * 3. Pierde si no tiene mas vidas, gana si llega a la salida.
	 * <p>
	 * 4. Cada turno verifica si el jugador gano o perdio, hace algo en cada
	 * caso.
	 * <p>
	 * 5. Cuando pierde, se parra el juego, ejecuta el frame de entrada de score
	 * y termina el timer.
	 * <p>
	 * 
	 */
	public void run()
	{
		nextTurn();
		if (!stop)
		{
			doGameTurn();
		}
		else
		{
			doLastTurn();
		}
	}

	/**
	 * Hace un turno del juego.
	 */
	private void doGameTurn()
	{
		if (!lost && !won)
		{
			refreshMapAndFrame();
			
			checkPlayerCondition();
			ElementAccess.openExit();
			System.out.println(turn);
		}
		else if (lost)
		{
			Sound.lost();
			refreshMapAndFrame();

			if (playerHasLost())
			{
				stop = true;
			}

			MapInstance.getInstance().levelRestart();
			lost = false;
		}
		else if (won)
		{
			Sound.won();
			refreshMapAndFrame();

			won = false;
			if (playerNotInLevel())
			{
				stop = true;
			}

			nextLevel();
			MapInstance.getInstance().levelRestart();
		}
	}
	
	/**
	 * Hace el ultimo turno.
	 */
	private void doLastTurn()
	{
		Integer time = turn;

		startEnterScore(time);
		disposeFrameMap();

		timer.cancel();
	}

	/**
	 * Verifica la condicion del jugador. Si murio, no le queda tiempo,
	 * perdio sus vidas o esta en la salida hace algo.
	 */
	private void checkPlayerCondition()
	{
		if (playerExists() && PlayerAccess.getPlayer().isInExit())
		{
			won = true;
		}
		if (playerIsDead())
		{
			MapInstance.getInstance().levelRestart();
		}
		if (playerHasLost())
		{
			lost = true;
		}
		if (runOutOfTime())
		{
			PlayerAccess.getPlayer().die();
		}
	}

	/**
	 * Borra el framemap.
	 */
	private void disposeFrameMap()
	{
		FrameMap.getInstance().setVisible(false);
		FrameMap.disposeFrame();
	}

	/**
	 * Hace aparecer al frame para entrar el score.
	 * @param time
	 */
	private void startEnterScore(Integer time)
	{
		FrameEnd.getInstance();
		FrameEnd.setTime(time);
		FrameEnd.runFrameEnd(null);
	}

	/**
	 * setea el proximo nivel
	 */
	private void nextLevel()
	{
		MapInstance.getInstance().setSelectedLevel(MapInstance.getInstance().getSelectedLevel() + 1);
	}

	/**
	 * 
	 * @return si no esta en el nivel el player
	 */
	private boolean playerNotInLevel()
	{
		return !MapInstance.getInstance().levelHasRockford();
	}

	/**
	 * 
	 * @return si no queda mas tiempo
	 */
	private boolean runOutOfTime()
	{
		return MapAccess.getTimer() == 0;
	}

	/**
	 * 
	 * @return Si el jugador perdio
	 */
	private boolean playerHasLost()
	{
		return PlayerAccess.getPlayer().getLives() == 0;
	}

	/**
	 * 
	 * @return Si el player murio
	 */
	private boolean playerIsDead()
	{
		return !ElementAccess.entityIsAlive(PlayerAccess.getPlayer());
	}

	/**
	 * 
	 * @return Si el player existe
	 */
	private boolean playerExists()
	{
		return PlayerAccess.getPlayer() != null;
	}

	/**
	 * Refresca el mapa y framemap.
	 */
	private void refreshMapAndFrame()
	{
		MapAccess.refresh();
		FrameMap.refresh();
	}

	/**
	 * Proximo turno, se ejecuta siempre cada turno.
	 */
	private void nextTurn()
	{
		turn++;
		FrameMap.remove();
	}

}
