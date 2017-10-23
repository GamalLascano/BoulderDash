package game;

import game.Entity;
import game.actor.Rockford;
import java.util.ArrayList;
import java.util.List;


	//	Lista de entities (Items y Actores)
/**
 * Esta clase contiene todas las entidades que se pueden mover en el juego
 * Incluye rockford, enemigos, rocas y diamantes 
 * 
 *
 */
public class ActiveEntities
{
	private List<Entity> list = new ArrayList<Entity>();

	// CONSTRUCTORS
	
	public ActiveEntities()
	{
		super();
	}
	
	/**
	 * Esto permite inicializar esta lista de entidades movibles con otra lista
	 * @param list
	 */
	public ActiveEntities(List<Entity> list)
	{
		super();
		this.list = list;
	}

	// GETTERS
	
	public List<Entity> getList()
	{
		return list;
	}

	// SETTERS	
	
	public void setList(List<Entity> list)
	{
		this.list = list;
	}
	
	// SORT
	
	// FIND
	
	/**
	 * Este metodo permite sacar de la lista de entidades al personaje principal
	 * para ser usado en otros metodos del programa
	 * @return Rockford, el personaje principal
	 */
	public Rockford findRockford()
	{
		Rockford player;
		int i = 0;
		//Ahora va a rotar por toda la lista hasta encontrar a rockford
		while(!(list.get(i) instanceof Rockford))
		{
			i++;
		}
		player = (Rockford) list.get(i);
		return player;
	}

}
