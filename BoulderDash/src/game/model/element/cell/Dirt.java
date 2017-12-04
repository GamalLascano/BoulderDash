package game.model.element.cell;

import game.model.element.Position;
import game.model.element.SpriteChar;

/**
 * Clase de la celda tierra y vacia (si dirty es false).
 */
public class Dirt extends Cell
{
	private boolean dirty;

	/**
	 * Constructor tierra.
	 * 
	 * @param pos
	 */
	public Dirt(Position pos)
	{
		super(pos);
		this.dirty = true;
		this.setSpritechar(SpriteChar.D);
	}

	/**
	 * Constructor tierra para generar una celda vacia con un argumento dirty
	 * para sacar la tierra de la celda.
	 * 
	 * @param pos
	 * @param dirty
	 */
	public Dirt(Position pos, boolean dirty)
	{
		super(pos);
		this.dirty = dirty;
		if (!dirty)
		{
			this.setSpritechar(SpriteChar._);
		}
		else
		{
			this.setSpritechar(SpriteChar.D);
		}
	}

	/**
	 * 
	 * @return si hay tierra en la celda
	 */
	public boolean isDirty()
	{
		return dirty;
	}

	/**
	 * Remueve la tierra de la celda.
	 */
	public void removeDirt()
	{
		this.dirty = false;
		this.setSpritechar(SpriteChar._);
	}

	@Override
	public void clear()
	{
		this.removeDirt();
	}
}
