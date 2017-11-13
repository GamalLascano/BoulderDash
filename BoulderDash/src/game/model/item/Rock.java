package game.model.item;

import game.model.Position;
import game.model.SpriteChar;
import game.model.actor.Rockford;
import game.model.map.MapActor;

/**Esta clase representa una roca, junto con su caracter de representacion y un booleano
 * para ver si esta siendo empujado o no, junto con otras propiedades de otros items
 */
public class Rock extends Fallable
{
	private SpriteChar spritechar = SpriteChar.O;
	
	// CONSTRUCTOR
	
	public Rock(Position pos)
	{
		super(pos, false, true, true, false, true, 2, StatusFallableEnum.IDLE);
	}
	
	public Rock(Position pos, StatusFallableEnum state)
	{
		super(pos, false, true, true, false, true, 2, state);
	}

	// GETTERS
	
	/** Permite obtener al caracter de una roca
	 * 
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// MOVIMIENTO
	

	/** Permite modificar el estado de la roca si esta siendo pusheada por rockford
	 * @param player: El objeto que representa a rockford
	 */
	public void pushed(Rockford player)
	{
		if (player.isPushing() && this.isMoveable())
		{
			switch ( player.getState() )
			{
				case MOVINGLEFT:
					this.state = StatusFallableEnum.SLIDINGLEFT;
					break;
				case MOVINGRIGHT:
					this.state = StatusFallableEnum.SLIDINGRIGHT;
					break;
				default:
					break;
			}		
		}
	}
	
	// REFRESH POSITION
	
	public void makeMove()
	{
		switch (this.state)
		{
			case FALLINGOFF:
					this.getPosition().goDown();
				break;
			case FALLING:
				this.getPosition().goDown();
				if(MapActor.getActor(this.getPosition()) != null)
				{
					MapActor.getActor(this.getPosition()).die();
				}
				this.state = StatusFallableEnum.IDLE;
			break;
			case SLIDINGRIGHT:
					this.getPosition().goRight();
					this.state = StatusFallableEnum.IDLE;
				break;
			case SLIDINGLEFT:
					this.getPosition().goLeft();
					this.state = StatusFallableEnum.IDLE;
				break;
			default:
				break;
		}
	}
	

}
