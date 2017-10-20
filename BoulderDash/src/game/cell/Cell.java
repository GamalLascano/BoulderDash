package game.cell;

import game.Position;

public abstract class Cell
{
	Position pos;
	boolean solid; // Si los personajes pueden caminar sobre la celda..

	Cell(Position pos, boolean solid)
	{
		this.pos = pos;
		this.solid = solid;
	}

	public Position getPos()
	{
		return pos;
	}

	public void setPos(Position pos)
	{
		this.pos = pos;
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
}
