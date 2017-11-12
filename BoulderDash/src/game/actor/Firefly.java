package game.actor;

import game.ListOfEntities;
import game.Position;
import game.SpriteChar;

public class Firefly extends Enemy
{
	private SpriteChar spritechar = SpriteChar.F;
	
	// CONSTRUCTOR
	
	public Firefly(Position pos)
	{
		super(pos);
		this.state = StatusActorEnum.MOVINGUP;
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
		switch ( this.state )
		{
			case MOVINGUP:
				this.state = StatusActorEnum.MOVINGRIGHT;
				break;
			case MOVINGRIGHT:
				this.state = StatusActorEnum.MOVINGDOWN;
				break;
			case MOVINGDOWN:
				this.state = StatusActorEnum.MOVINGLEFT;
				break;
			case MOVINGLEFT:
				this.state = StatusActorEnum.MOVINGUP;
				break;
			default:
				break;
		}
	}

	// EXPLOSION
	
	public void explode()
	{
		if (this.getState() != StatusActorEnum.DEAD) {
			int i;
			for (i = 0 ; i < ListOfEntities.getList().size(); ++i) {
				if (this.isInRange(ListOfEntities.getList().get(i).getPosition())) {
					//cambiar objeto en el mapa y setear el estado del objeto como dead, o exploding
					//si es una luciernaga o una mariposa
				}
			}
		}
	}

}
