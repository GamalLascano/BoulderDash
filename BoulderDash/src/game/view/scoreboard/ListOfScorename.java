package game.view.scoreboard;

import java.io.IOException;
import java.net.URISyntaxException;
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

	/**
	 * Agrega un scorename en la lista y pone los datos de la lista en un
	 * archivo scoreboard.
	 * 
	 * @param scorename
	 */
	public void addNameTable(Scorename scorename)
	{
		ListOfScorename.getList().add(scorename);
		try
		{
			ScoreBoard.getInstance().writeScorenames();
		}
		catch (ClassNotFoundException | IOException | URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListOfScorename.sortScorename();
	}
	
	/**
	 * Remplaza un scorename en la lista y pone los datos de la lista en un
	 * archivo scoreboard.
	 * 
	 * @param scorename
	 */
	public void replaceNameTable(Scorename scorename)
	{
		int i = 0;
		while(i < ListOfScorename.getList().size() && !ListOfScorename.getList().get(i).equals(scorename))
		{
			i++;
		}
		ListOfScorename.getList().remove(i);
		ListOfScorename.getList().add(scorename);
		try
		{
			ScoreBoard.getInstance().writeScorenames();
		}
		catch (ClassNotFoundException | IOException | URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListOfScorename.sortScorename();
	}
	@Override
	public boolean equals(Object obj) {
		for (int i=0;i<scorenamelist.size();i++) {
			if (scorenamelist.get(i).equals(obj)) {
				return true;
			}
		}
		return false;
	}
}
