package game.model.map;

/**
 * Clase padre de los mapas.
 */
public abstract class Map
{

	/**
	 * Inicializa el mapa.
	 */
	abstract public void start();

	/**
	 * Llena el mapa, inicializa la matriz con tierra, vacio o null.
	 */
	abstract public void fill();

}
