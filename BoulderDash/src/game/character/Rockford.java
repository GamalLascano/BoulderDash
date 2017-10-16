package game.character;

import game.Position;
import game.Status;
import game.item.Item;

public class Rockford extends Character {
	int score;
	int diamonds;
	boolean pushing;

	public Rockford(Status state, Position pos)
	{
		super(state, pos);
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
	
	public boolean isPushing()
	{
		return pushing;
	}

	public void setPushing(boolean pushing)
	{
		this.pushing = pushing;
	}

	public boolean save() {
		return true;
	}
	
	//hacer una clase proximidad? sino hay que hacer empujar en cada direction
	public void push(Item item) {
		while ( this.pos.getPosX() == item.getPos().getPosX() + 1) {
			this.pushing = true;
		}
	}
	
	//singleton aca??
	//banco hacerlo singleton
}
