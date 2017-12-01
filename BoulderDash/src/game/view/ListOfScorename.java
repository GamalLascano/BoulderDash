package game.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lista que contiene las entidades.
 */
public class ListOfScorename
{
	private static ListOfScorename singleton;
	private static List<Scorename> scorenamelist;

	/**
	 * Constructor que setea la lista en null.
	 */
	private ListOfScorename()
	{
		scorenamelist = null;
	}

	/**
	 * Singleton de la lista de entidades.
	 * 
	 * @return singleton
	 */
	public static ListOfScorename getInstance()
	{
		if (singleton == null)
		{
			singleton = new ListOfScorename();
		}
		return singleton;
	}

	/**
	 * start inicia la lista con una lista vacia, y lo setea como inicializado.
	 */
	public void start()
	{
		scorenamelist = new ArrayList<Scorename>();
	}

	/**
	 * Devuelve la lista.
	 * 
	 * @return Retorna la lista de entidades
	 */
	public static List<Scorename> getList()
	{
		return scorenamelist;
	}
	
	/**
	 * Ordena la lista de participantes, el mayor puntaje y menor tiempo estan
	 * primeros en la lista.
	 */
	public static void sortScorename()
	{
		if (scorenamelist != null)
		{
			Collections.sort(scorenamelist, new Comparator<Scorename>()
			{

				@Override
				public int compare(Scorename o1, Scorename o2)
				{
					if (o1.getPoints() < o2.getPoints() || o1.getPoints() == o2.getPoints() && o1.getTime() > o2.getTime())
					{
						return 1;
					}
					if (o1.getPoints() > o2.getPoints() || o1.getPoints() == o2.getPoints() && o1.getTime() < o2.getTime())
					{
						return -1;
					}
					return 0;
				}

			});
			int i;
			for (i = 0; i < scorenamelist.size(); i++)
			{
				scorenamelist.get(i).setRank(i + 1);
			}
		}
	}

}
