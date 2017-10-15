package game.character;

public class Rockford extends Character {
	int score;
	int cantDiamonds;
	
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

	public int getCantDiamonds() {
		return cantDiamonds;
	}

	public void setCantDiamonds(int cantDiamonds) {
		this.cantDiamonds = cantDiamonds;
	}

	public boolean save() {
		return true;
	}
	
	//singleton aca??
}
