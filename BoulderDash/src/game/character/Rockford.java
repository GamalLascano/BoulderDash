package game.character;

import game.Position;
import game.Status;

public class Rockford extends Character {
	int score;
	int diamonds;
	
	public Rockford(Status state, Position pos) {
		super(state, pos, true);
		// TODO Auto-generated constructor stub
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}

	public boolean save() {
		return true;
	}
	
	//singleton aca??
}
