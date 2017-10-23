package game.item;

import game.Position;
import game.Entity;
import game.map.MapInstance;
import game.SpriteChar;

public abstract class Item extends Entity
{
	SpriteChar spritechar;
	StatusItem state;
	boolean solid;
	boolean collectable;
	boolean moveable;
	boolean fallable;
	boolean explodable;
	boolean rounded;	//Si un objeto sobre otro se cae por los lados

	public Item(StatusItem state, Position pos, boolean collectable, boolean moveable, boolean fallable,
			boolean explodable, boolean rounded, boolean solid)
	{
		super(pos);
		this.state = state;
		this.collectable = collectable;
		this.moveable = moveable;
		this.fallable = fallable;
		this.explodable = explodable;
		this.rounded = rounded;
		this.solid = solid;
	}

	// GETTERS
	
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public StatusItem getState()
	{
		return state;
	}

	public boolean isCollectable()
	{
		return collectable;
	}

	public boolean isMoveable()
	{
		return moveable;
	}

	public boolean isFallable()
	{
		return fallable;
	}
	
	public boolean isExplodable()
	{
		return explodable;
	}
	
	public boolean isRounded()
	{
		return rounded;
	}

	public boolean isSolid()
	{
		return solid;
	}

	// SETTERS
	
	public void setState(StatusItem state)
	{
		this.state = state;
	}

	public void setCollectable(boolean collectable)
	{
		this.collectable = collectable;
	}

	public void setMoveable(boolean moveable)
	{
		this.moveable = moveable;
	}

	public void setFallable(boolean fallable)
	{
		this.fallable = fallable;
	}

	public void setExplodable(boolean explodable)
	{
		this.explodable = explodable;
	}

	public void setRounded(boolean rounded)
	{
		this.rounded = rounded;
	}

	//COMPORTAMIENTO
	
	public void fall()
	{
		Position posDown = new Position(super.getPosition().getX(),super.getPosition().checkDown());
		
		if ( MapInstance.getMapCell().isEmpty(posDown))
		{
			state.setStateEnum(StatusItemEnum.FALLING);
		}else {
			if (MapInstance.getMapItem().getItem(posDown).isRounded()) {
				Position posLeft = new Position(super.getPosition().checkLeft(),super.getPosition().getY());
				Position posRight = new Position(super.getPosition().checkRight(), super.getPosition().getY());
				if (MapInstance.getMapCell().isEmpty(posLeft)) {
					state.setStateEnum(StatusItemEnum.SLIDINGLEFT);	
				}else {
					if (MapInstance.getMapCell().isEmpty(posRight)) {
						state.setStateEnum(StatusItemEnum.SLIDINGRIGHT);
						
					}
				}
			}
		}
	}

	
	/**
	 * poner collect en Rockford? public void collected() { if ()
	 * BDLevelReader.field[pos.posX][pos.posY] = BDTile.EMPTY;
	 * 
	 * }
	 */
}
