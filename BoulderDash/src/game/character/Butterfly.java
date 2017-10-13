package game.character;

public class Butterfly extends Enemy {

	@Override
	public boolean explode() {
		return true;
	}

	@Override
	public boolean rotate() {
		return true;
	}
	
}
