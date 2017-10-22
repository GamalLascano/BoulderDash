package game.actor;

import game.Position;
import game.item.Item;
import game.SpriteChar;

public class Rockford extends Actor
{
	SpriteChar spritechar = SpriteChar.R;
	int score;
	int diamonds;
	boolean pushing;
	

	public Rockford(StatusActor state, Position pos)
	{
		super(state, pos);
		this.score = 0;
		this.diamonds = 0;
		this.pushing = false;
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public int getScore()
	{
		return score;
	}

	public int getDiamonds()
	{
		return diamonds;
	}

	public boolean isPushing()
	{
		return pushing;
	}
	
	// SETTERS
	
	public void setScore(int score)
	{
		this.score = score;
	}

	public void setDiamonds(int diamonds)
	{
		this.diamonds = diamonds;
	}

	public void setPushing(boolean pushing)
	{
		this.pushing = pushing;
	}

	// SAVE
	
	public boolean save()
	{
		return true;
	}

	// hacer una clase proximidad? sino hay que hacer empujar en cada direction
	public void push(Item item)
	{
		while (this.pos.getX() == item.getPosition().getX() + 1)
		{
			this.pushing = true;
		}
	}

	// singleton aca??
	// banco hacerlo singleton
}
