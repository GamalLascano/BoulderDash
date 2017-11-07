package game.actor;

import game.ListOfEntities;
import game.Position;
import game.map.MapInstance;
import game.SpriteChar;

public class Firefly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.F;
	
	public Firefly(StatusActor state, Position pos)
	{
		super(state, pos, 1);
		this.getState().setStateEnum(StatusActorEnum.MOVINGUP);
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// ROTACION
	
	public void rotate()
	{
		switch ( this.getState().getStateEnum() )
		{
			case MOVINGUP:
				this.getState().setStateEnum(StatusActorEnum.MOVINGRIGHT);
				break;
			case MOVINGRIGHT:
				this.getState().setStateEnum(StatusActorEnum.MOVINGDOWN);
				break;
			case MOVINGDOWN:
				this.getState().setStateEnum(StatusActorEnum.MOVINGLEFT);
				break;
			case MOVINGLEFT:
				this.getState().setStateEnum(StatusActorEnum.MOVINGUP);
				break;
			default:
				break;
		}
	}

	// EXPLOSION
	
	public void explode()
	{
		if (this.state.isAlive()==false) {
			ListOfEntities list = MapInstance.getEntitiesActive();
			int i;
			for (i = 0 ; i < list.getList().size(); ++i) {
				if (this.isInRange(list.getList().get(i).getPosition())==true) {
					//cambiar objeto en el mapa y setear el estado del objeto como dead, o exploding
					//si es una luciernaga o una mariposa
				}
			}
		}
	}

}
