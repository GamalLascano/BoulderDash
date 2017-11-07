package game.cell;

import game.Position;
import game.SpriteChar;

/**
 * Esta clase contiene todos los objetos del mapa que no se mueven
 *
 */
public abstract class Cell
{
	private SpriteChar spritechar;
	private Position pos;
	private int solid; // Si los personajes pueden caminar sobre la celda..

	Cell(Position pos, int solid)
	{
		this.pos = pos;
		this.solid = solid;
	}
	
	// GETTERS

	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public Position getPosition()
	{
		return pos;
	}
	
	public int isSolid()
	{
		return solid;
	}

	// SETTERS

	public void setPosition(Position pos)
	{
		this.pos = pos;
	}

	public void setSolid(int solid)
	{
		this.solid = solid;
	}
}
