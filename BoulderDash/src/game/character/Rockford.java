package game.character;

public class Rockford extends Character {
	int score;
	int cantDiamonds;
	int health;
	
	public boolean save() {
		return true;
	}
	
	@Override
	public boolean move() {
		return true;
	}
	
	@Override
	public boolean explode() {
		return true;
	}
}
