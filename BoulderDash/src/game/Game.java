package game;

import game.actor.*;
import game.item.*;
import game.map.*;
import game.map.bdlevel.BDLevelReader;

public class Game
{
	// HAY QUE CAMBIAR TODO ESTO DSP Y HACERLO BIEN
	public static void main(String[] args)
	{
		BDLevelReader levelFrame = new BDLevelReader();
		CurrentDirection dir;

		int nivelElegido = 1;
		int levels = levelFrame.readLevels("levels.xml");
		levelFrame.setCurrentLevel(nivelElegido);
		MapInstance map = MapInstance.getInstance();

		// se ponen los objectos en la matriz
		MapInstance.buildMap(levelFrame);
		Position playerPos;
		Position posmap;
		Actor player;

		player = MapInstance.getActorsActive().findRockford();
		
		player.move(CurrentDirection.DOWN); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.UP); // cavar
		player.move(CurrentDirection.UP); // collectar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.DOWN);
		player.move(CurrentDirection.DOWN);
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT);
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.DOWN); // cavar
		player.move(CurrentDirection.DOWN); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar
		player.move(CurrentDirection.RIGHT); // cavar

		nivelElegido = 2;
		levelFrame.setCurrentLevel(nivelElegido);
		MapInstance.buildMap(levelFrame);
		player.move(CurrentDirection.RIGHT); // collectar
		player.move(CurrentDirection.UP); // cavar
		player.move(CurrentDirection.UP); // cavar
		player.move(CurrentDirection.UP); // collectar
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.LEFT); // empujar
		player.move(CurrentDirection.UP); // cavar
		player.move(CurrentDirection.UP); // explotar

	}

}
