package game.model.cell;

import game.model.Position;
import game.model.SolidTo;
import game.model.SpriteChar;
import game.model.map.MapCell;

/**
 * Esta clase contiene todos los objetos del mapa que no se mueven
 *
 */
public abstract class Cell
{
	private SpriteChar spritechar;
	protected Position pos;
	private SolidTo solid;

	/**
	 * 
	 * @param po
	 * @param soli
	 */
	Cell(Position po, SolidTo soli)
	{
		pos = po;
		solid = soli;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDirt()
	{
		if (this instanceof Dirt)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isWall()
	{
		if (this instanceof Wall)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExit()
	{
		if (this instanceof Exit)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public SpriteChar getSpritechar()
	{
		return spritechar;
	}

	/**
	 * 
	 * @return
	 */
	public Position getPosition()
	{
		return pos;
	}

	/**
	 * 
	 * @return
	 */
	public SolidTo getSolid()
	{
		return solid;
	}

	/**
	 * 
	 * @param po
	 */
	public void setPosition(Position po)
	{
		pos = po;
	}

	/**
	 * 
	 * @param soli
	 */
	public void setSolid(SolidTo soli)
	{
		solid = soli;
	}
	
	/**
	 * 
	 */
	public void clear()
	{
		MapCell.removeCell(this.pos);
	}

}
