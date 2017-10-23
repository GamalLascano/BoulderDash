package game.actor;

import game.Position;

public abstract class Enemy extends Actor
{

	public Enemy(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}
	
	// S

	protected abstract void rotate();

	// S
	
	/**
	 * Explosion, pone celda vacias alrededor del personaje. Explosion cuadrada
	 * 3x3.
	 */
	public abstract void explode();

	// Recibe una posicion, y ve si esta en un radio de 3x3
	public boolean isInRange(Position pos) {
		if( ( pos.getX() == super.getPosition().checkLeft() ) || ( pos.getX() == super.getPosition().getX() ) || ( pos.getX() == super.getPosition().checkRight() ) ) 
		{
			if ( ( pos.getY() == super.getPosition().checkDown() ) || ( pos.getY() == super.getPosition().getY() ) || ( pos.getY() == super.getPosition().checkUp() ) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
}
