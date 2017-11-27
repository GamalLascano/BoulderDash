package game.model;

import game.model.actor.Rockford;
import game.model.map.MapInstance;
import game.model.map.MapVisual;
import game.view.FrameMap;

import java.util.Timer;

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
		final int LIVES = 3;
		
		Timer timer = new Timer("test");
		MapInstance.start();
		MapVisual.start();
		
		MapInstance.buildSelectedLevel(STARTLEVEL);
		//MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
		
		Rockford.getRockford().setLives(LIVES);
		FrameMap.start();
		FrameMap.refresh();
		MapInstance.refresh();
		FrameMap.getInstance().setVisible(true);

		GameThread task = new GameThread(timer);
		timer.schedule(task, TASKDELAY, TASKSPEED);
		
	}

}