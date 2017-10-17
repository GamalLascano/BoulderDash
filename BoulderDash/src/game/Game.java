package game;

import game.character.Firefly;
import game.character.Rockford;
import game.map.*;

public class Game
{
	//HAY QUE CAMBIAR TODO ESTO DSP Y HACERLO BIEN
	public static void main(String[] args) {
		BDLevelReader levelReader = new BDLevelReader();
		CurrentDirection dir;
	
		int nivelElegido = 1;
		int levels = levelReader.readLevels("levels.xml");
		levelReader.setCurrentLevel(nivelElegido);
		MapInstance map = MapInstance.getInstance();
		
		Rockford player = new Rockford();
		
		MapInstance.buildTiles(levelReader);
		player.move(CurrentDirection.DOWN); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.UP); //cavar
		player.move(CurrentDirection.UP); //collectar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.DOWN);
		player.move(CurrentDirection.DOWN);
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT);
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.DOWN); //cavar
		player.move(CurrentDirection.DOWN); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		player.move(CurrentDirection.RIGHT); //cavar
		
		nivelElegido = 2;
		MapInstance.buildTiles(levelReader);
		player.move(CurrentDirection.RIGHT); //collectar
		player.move(CurrentDirection.UP); //cavar
		player.move(CurrentDirection.UP); //cavar
		player.move(CurrentDirection.UP); //collectar
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT);
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.LEFT); //empujar
		player.move(CurrentDirection.UP); //cavar
		player.move(CurrentDirection.UP); //explotar
		
		
	}
	
}
