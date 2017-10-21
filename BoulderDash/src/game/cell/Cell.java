package game.cell;

import game.Position;
import game.SpriteChar;

public abstract class Cell
{
	SpriteChar spritechar;
	Position pos;
	boolean solid; // Si los personajes pueden caminar sobre la celda..

	Cell(Position pos, boolean solid)
	{
		this.pos = pos;
		this.solid = solid;
	}
	
	// GETTERS

	public SpriteChar getSpritechar()
	{
		return spritechar;
	}
	
	public Position getPos()
	{
		return pos;
	}
	
	public boolean isSolid()
	{
		return solid;
	}

	// SETTERS

	public void setPos(Position pos)
	{
		this.pos = pos;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
}
