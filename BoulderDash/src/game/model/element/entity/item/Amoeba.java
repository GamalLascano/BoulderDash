package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.ElementTypes;
import game.model.element.entity.ListOfEntities;
import game.model.element.entity.Moveable;
import game.model.element.entity.item.Diamond;
import game.model.element.entity.item.Rock;
import game.model.element.entity.item.StatusAmoebaEnum;
import game.model.map.MapElement;
import game.model.map.MapInstance;
/**
 * Clase que representa el Amoeba. Se mueve y se copia a si mismo.
 */
public class Amoeba extends Item implements Moveable
{
	private final int MAXAMOEBA = 40;
	private final int EXPANDSPEED = 20;
	private int energy = 4;
	private int energytime = 0;
	private boolean expanding;
	private int expandtime;
	private StatusAmoebaEnum state;

	/**
	 * Constructor del Amoeba.
	 * 
	 * @param pos
	 */
	public Amoeba(Position pos)
	{
		super(pos, false, false, false);
		this.setElementType(ElementTypes.Amoeba);
		this.expanding = true;
		this.expandtime = 0;
		this.state = StatusAmoebaEnum.EXPANDUP;
		this.putPassables();
	}
	
	/**
	 * Constructor del Amoeba.
	 * 
	 * @param pos
	 */
	private Amoeba(Position pos, int expandtime)
	{
		super(pos, false, false, false);
		this.setElementType(ElementTypes.Amoeba);
		this.expanding = true;
		this.expandtime = expandtime;
		this.state = StatusAmoebaEnum.EXPANDUP;
		this.putPassables();
	}

	/**
	 * Verifica si esta expandiandose.
	 * 
	 * @return boolean
	 */
	public boolean check()
	{
		if (expanding = true)
		{
			expanding = true;
			return true;
		}
		else
		{
			expanding = false;
			return false;
		}
	}

	@Override
	public void die()
	{
		this.state = StatusAmoebaEnum.DEAD;
		ListOfEntities.getList().remove(this);
		MapElement.removeElement(this.getPosition());
		if (this.expanding == false)
		{
			Diamond diamondcreated = new Diamond(this.getPosition());
			MapElement.setItem(diamondcreated);
			ListOfEntities.getList().add(diamondcreated);
		}
		else
		{
			Rock rockcreated = new Rock(this.getPosition());
			MapElement.setItem(rockcreated);
			ListOfEntities.getList().add(rockcreated);
		}
	}

	@Override
	public void rotate()
	{
		switch (this.state)
		{
			case EXPANDUP:
				this.state = StatusAmoebaEnum.EXPANDRIGHT;
				break;
			case EXPANDRIGHT:
				this.state = StatusAmoebaEnum.EXPANDDOWN;
				break;
			case EXPANDDOWN:
				this.state = StatusAmoebaEnum.EXPANDLEFT;
				break;
			case EXPANDLEFT:
				this.state = StatusAmoebaEnum.EXPANDUP;
				break;
			default:
				break;
		}
	}

	@Override
	public void changePosition()
	{
		this.expandtime++;
		this.energytime++;
		if(canExpand())
		{
			MapElement.removeElement(this.getPosition());
			this.makeMove();
			MapElement.setItem(this);
			this.energytime = 0;
			this.energy--;
		}
		else if(this.expandtime >= MAXAMOEBA * EXPANDSPEED)
		{
			this.die();
		}
	}

	@Override
	public void makeMove()
	{
		if (this.expanding)
		{
			switch (this.state)
			{
				case EXPANDUP:
					makeMoveUp();
					break;
				case EXPANDRIGHT:
					makeMoveRight();
					break;
				case EXPANDDOWN:
					makeMoveDown();
					break;
				case EXPANDLEFT:
					makeMoveLeft();
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void makeMoveUp()
	{
		if (this.canGoUp())
		{
			MapInstance.getInstance().kill(this.getPosition().getX(), this.getPosition().checkUp());
			Amoeba amoebacreated = new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkUp()), this.expandtime);
			MapElement.setItem(amoebacreated);
			ListOfEntities.getList().add(amoebacreated);
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveDown()
	{
		if (this.canGoDown())
		{
			MapInstance.getInstance().kill(this.getPosition().getX(), this.getPosition().checkDown());
			Amoeba amoebacreated = new Amoeba(new Position(this.getPosition().getX(), this.getPosition().checkDown()), this.expandtime);
			MapElement.setItem(amoebacreated);
			ListOfEntities.getList().add(amoebacreated);
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveRight()
	{
		if (this.canGoRight())
		{
			MapInstance.getInstance().kill(this.getPosition().checkRight(), this.getPosition().getY());
			Amoeba amoebacreated = new Amoeba(new Position(this.getPosition().checkRight(), this.getPosition().getY()), this.expandtime);
			MapElement.setItem(amoebacreated);
			ListOfEntities.getList().add(amoebacreated);
		}
		else
		{
			this.rotate();
		}
	}

	@Override
	public void makeMoveLeft()
	{
		if (this.canGoLeft())
		{
			MapInstance.getInstance().kill(this.getPosition().checkLeft(), this.getPosition().getY());
			Amoeba amoebacreated = new Amoeba(new Position(this.getPosition().checkLeft(), this.getPosition().getY()), this.expandtime);
			MapElement.setItem(amoebacreated);
			ListOfEntities.getList().add(amoebacreated);
		}
		else
		{
			this.rotate();
		}
	}
	
	// METODOS SIMPLE
	
	/**
	 * Pone los passables de Amoeba.
	 */
	private void putPassables()
	{
		this.getPassable().put(ElementTypes.Empty.hashCode(), ElementTypes.Empty);
		this.getPassable().put(ElementTypes.Dirt.hashCode(), ElementTypes.Dirt);
		this.getPassable().put(ElementTypes.Diamond.hashCode(), ElementTypes.Diamond);
		this.getPassable().put(ElementTypes.Firefly.hashCode(), ElementTypes.Firefly);
		this.getPassable().put(ElementTypes.Butterfly.hashCode(), ElementTypes.Butterfly);
	}
	
	/**
	 * Verifica si el amoeba se puede expander.
	 */
	private boolean canExpand()
	{
		return (this.energy > 0 && 
				this.energytime == EXPANDSPEED);
	}


}
