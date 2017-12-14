package game.model.element.entity.item;

import game.model.element.Position;
import game.model.element.entity.ListOfEntities;
import game.model.map.MapElement;

/**
 * Clase para los diamantes y rocas que se pueden caer.
 */
public abstract class Fallable extends Item
{
	protected StatusFallableEnum state;

	/**
	 * Constructor fallable.
	 * 
	 * @param pos
	 * @param collectable
	 * @param moveable
	 * @param rounded
	 * @param solid
	 * @param state
	 */
	public Fallable(Position pos, boolean collectable, boolean moveable, boolean rounded, StatusFallableEnum state)
	{
		super(pos, collectable, moveable, rounded);
		this.state = state;
	}

	/**
	 * Retorna el estado del fallable.
	 * 
	 * @return el estado del fallable
	 */
	public StatusFallableEnum getState()
	{
		return state;
	}

	/**
	 * Setea el estado del fallable.
	 * 
	 * @param state
	 */
	public void setState(StatusFallableEnum state)
	{
		this.state = state;
	}

	@Override
	public void die()
	{
		this.state = StatusFallableEnum.DEAD;
		ListOfEntities.getList().remove(this);
		MapElement.removeElement(this.getPosition());
	}

	@Override
	public void changePosition()
	{
		MapElement.removeElement(this.getPosition());
		this.fall();
		this.makeMove();
		MapElement.setItem(this);
	}

	/**
	 * Cambia el estado dependiendo de las condiciones
	 * del fallable.
	 */
	public abstract void fall();
	
	// METODOS SIMPLES
	
	/**
	 * Devuelve si el fallable esta quieto.
	 * @return boolean
	 */
	public boolean isIdle()
	{
		return this.state == StatusFallableEnum.IDLE;
	}
	
	/**
	 * Devuelve si el fallable esta quieto.
	 * @return boolean
	 */
	public boolean isFalling()
	{
		return this.state == StatusFallableEnum.FALLINGOFF || this.state == StatusFallableEnum.FALLING;
	}
	
//	/**
//	 * Devuelve si el fallable esta deslizandose.
//	 * @return boolean
//	 */
//	public boolean isSliding()
//	{
//		return this.state == StatusFallableEnum.SLIDINGLEFT || this.state == StatusFallableEnum.SLIDINGRIGHT;
//	}
//	
	
	

}
