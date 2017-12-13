package game.model.map;

import game.model.element.Position;
import game.model.element.ElementChar;
import game.model.element.entity.actor.Rockford;

/**
 * Esta clase es la encargada de agarrar los elementos y hacer una matriz de
 * carateres con estos.
 */
public class MapChar extends Map
{
	private static MapChar mapvisual;
	private static ElementChar[][] map;

	/**
	 * Constructor de MapVisual.
	 */
	private MapChar()
	{
		map = null;
	}

	/**
	 * Singleton MapVisual.
	 * 
	 * @return
	 */
	public static MapChar getInstance()
	{
		if (mapvisual == null)
		{
			mapvisual = new MapChar();
		}
		return mapvisual;
	}

	/**
	 * Construye el mapa de elementos con sus caracteres, los actores tienen mas
	 * prioridad que los objetos que tienen mas prioridad que las celdas.
	 */
	public static void drawMap()
	{
		Position pos = new Position(0, 0);
		int y;
		int x;
		for (y = 0; y < MapInstance.getInstance().getLevelReader().getHEIGHT(); y++)
		{
			for (x = 0; x < MapInstance.getInstance().getLevelReader().getWIDTH(); x++)
			{
				pos.setXY(x, y);
				if (MapActor.getActor(pos) != null)
				{
					map[x][y] = MapActor.getActor(pos).getSpritechar();
				}
				else if (!MapItem.getItem(pos).isEmpty())
				{
					map[x][y] = MapItem.getItem(pos).getSpritechar();
				}
				else
				{
					map[x][y] = MapCell.getCell(pos).getSpritechar();
				}
			}
		}
	}

	/**
	 * Imprime el mapa y algunas informacion. Se utiliza para la consola y
	 * debugging.
	 */
	public static void imprimirMapa()
	{
		System.out.println("..............................................................");

		for (int y = 0; y < MapInstance.getInstance().getLevelReader().getHEIGHT(); y++)
		{
			for (int x = 0; x < MapInstance.getInstance().getLevelReader().getWIDTH(); x++)
			{
				System.out.print(map[x][y]);
				System.out.print(" ");
			}
			System.out.println();

		}

		Rockford player = Rockford.getInstance();
		if (player != null)
		{
			System.out.println("Rockford Pos: " + player.getPosition().getX() + "," + player.getPosition().getY());
			System.out.println("Rockford Diamantes: " + player.getDiamonds());
		}
		else
		{
			System.out.println("Rockford muerto");
		}
		System.out.println("..............................................................");

	}

	/**
	 * Devuelve un caracter de la matriz, utilizando coordenadas X,Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static ElementChar getChar(int x, int y)
	{
		return map[x][y];
	}

	@Override
	public void start()
	{
		MapChar.getInstance();
		map = new ElementChar[MapInstance.getInstance().getLevelReader().getWIDTH()][MapInstance.getInstance().getLevelReader().getHEIGHT()];
		fill();
	}

	@Override
	public void fill()
	{
		for (int x = 0; x < MapInstance.getInstance().getLevelReader().getWIDTH(); x++)
			for (int y = 0; y < MapInstance.getInstance().getLevelReader().getHEIGHT(); y++)
			{
				map[x][y] = ElementChar.D;
			}
	}

}
