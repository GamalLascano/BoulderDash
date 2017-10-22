package game.actor;

import game.ActiveEntities;
import game.Position;
import game.map.MapInstance;
import game.SpriteChar;

public class Firefly extends Enemy
{
	SpriteChar spritechar = SpriteChar.F;
	
	public Firefly(StatusActor state, Position pos)
	{
		super(state, pos);
		// TODO Auto-generated constructor stub
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	// S
	
	public boolean rotate()
	{
		return true;
	}

	// S
	
	public void explode()
	{
		if (this.state.isAlive()==false) {
			ActiveEntities list = MapInstance.getActorsActive();
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
