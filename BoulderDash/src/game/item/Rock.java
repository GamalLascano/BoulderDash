package game.item;

import game.Position;
import game.SpriteChar;
import game.actor.Rockford;

/**Esta clase representa una roca, junto con su caracter de representacion y un booleano
 * para ver si esta siendo empujado o no, junto con otras propiedades de otros items
 */
public class Rock extends Item
{
	private SpriteChar spritechar = SpriteChar.O;
	private boolean pushed;

	/** Permite inicializar a la roca con un estado de movimiento y una posicion predeterminada
	 * @param state: estado de movimiento
	 * @param pos: posicion
	 */
	public Rock(StatusItem state, Position pos)
	{
		super(state, pos, false, true, true, false, true, 2);
		this.pushed = false;
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
		if (player.isPushing() && this.moveable)
		{
			switch ( player.getState().getStateEnum() )
			{
				case MOVINGLEFT:
					this.state.setStateEnum(StatusItemEnum.SLIDINGLEFT);
					this.pushed = true;
					break;
				case MOVINGRIGHT:
					this.state.setStateEnum(StatusItemEnum.SLIDINGRIGHT);
					this.pushed = true;
					break;
				default:
					break;
			}
		this.pushed = false;		
		}
	}

}
