package game.actor;

import game.ListOfEntities;
import game.Position;
import game.item.Diamond;
import game.map.MapActor;
import game.map.MapCell;
import game.map.MapInstance;
import game.map.MapItem;

public abstract class Enemy extends Actor
{

	// CONSTRUCTOR

	public Enemy(Position pos)
	{
		super(pos, StatusActorEnum.IDLE);
		// TODO Auto-generated constructor stub
	}

	// ROTATE

	protected abstract void rotate();

	// EXPLODE WITH DIAMONDS

	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public void explode()
	{
		MapInstance.kill(this.getPosition().getX(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().getX(), this.getPosition().checkUp());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().checkUp());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().checkRight(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().getX(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().checkDown());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().getY());
		MapInstance.kill(this.getPosition().checkLeft(), this.getPosition().checkUp());

		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkUp())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkUp())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().getX(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkDown())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkRight(), this.getPosition().getY())));
		MapItem.setItem(new Diamond(new Position(this.getPosition().checkLeft(), this.getPosition().checkUp())));
	}

	// DIE
	
	public void die()
	{
		if (this.state != StatusActorEnum.DEAD)
		{
			this.state = StatusActorEnum.DEAD;
			this.explode();
		}
		ListOfEntities.getList().remove(this);
		MapActor.removeActor(this.getPosition());
	}
	
	// MAKE MOVE

	/**
	 * Se occupa de hacer mover a los enemigos.
	 * 
	 */
	public void makeMove()
	{
		switch (this.state)
		{
			case MOVINGUP:
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkUp()).getSolid() < 1
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkUp()).getSolid() < 1)
				{
					this.getPosition().goUp();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGDOWN:
				if (MapCell.getCell(this.getPosition().getX(), this.getPosition().checkDown()).getSolid() < 1
						&& MapItem.getItem(this.getPosition().getX(), this.getPosition().checkDown()).getSolid() < 1)
				{
					this.getPosition().goDown();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGRIGHT:
				if (MapCell.getCell(this.getPosition().checkRight(), this.getPosition().getY()).getSolid() < 1
						&& MapItem.getItem(this.getPosition().checkRight(), this.getPosition().getY()).getSolid() < 1)
				{
					this.getPosition().goRight();
				}
				else
				{
					this.rotate();
				}
				break;
			case MOVINGLEFT:
				if (MapCell.getCell(this.getPosition().checkLeft(), this.getPosition().getY()).getSolid() < 1
						&& MapItem.getItem(this.getPosition().checkLeft(), this.getPosition().getY()).getSolid() < 1)
				{
					this.getPosition().goLeft();
				}
				else
				{
					this.rotate();
				}
				break;
			default:
				break;
		}
	}

}
