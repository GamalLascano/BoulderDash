package game.character;

public class Rockford extends Character {
	int score;
	int diamonds;
	
	public Rockford() {
		super();
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
