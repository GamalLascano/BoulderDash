package game.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Sirve para cargar y guardar la lista de scorenames en un archivo.
 *
 */
public class ScoreBoard
{
	private static ScoreBoard scoreboard;
	URL filename;

	private ScoreBoard()
	{
		this.filename = this.getClass().getResource("Scoreboard.dat");
	}

	public static ScoreBoard getInstance() throws FileNotFoundException, URISyntaxException
	{
		if (scoreboard == null)
		{
			scoreboard = new ScoreBoard();
		}
		return scoreboard;
	}

	/**
	 * Lee el archivo y pone los datos en una lista de scorenames.
	 * tambien ordenada la lista.
	 * 
	 * @param scorenamelist
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readScorenames(ArrayList<Scorename> scorenamelist) throws IOException, ClassNotFoundException
	{
		File file = null;
		try
		{
			file = new File(this.filename.toURI());
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInputStream streamin = new FileInputStream(file);
		if (streamin.available() != 0)
		{
			ObjectInputStream input = null;
			Scorename participant;
			input = new ObjectInputStream(streamin);

			while (input.available() > 0)
			{
				participant = (Scorename) input.readObject();
				scorenamelist.add(participant);
			}
			input.close();
			sortScorename(scorenamelist);

		}
	}

	/**
	 * Escribe el archivo. Saca los datos de la lista de scorenames.
	 * 
	 * @param scorenamelist
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void writeScorenames(ArrayList<Scorename> scorenamelist) throws IOException, ClassNotFoundException
	{
		File file = null;
		try
		{
			file = new File(this.filename.toURI());
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputStream streamout = new FileOutputStream(file);
		ObjectOutputStream output = null;

		output = new ObjectOutputStream(streamout);
		int i;
		for (i = 0; i < scorenamelist.size(); i++)
		{
			output.writeObject(scorenamelist.get(i));
		}
		output.close();

	}

	/**
	 * Ordena la lista de participantes, el mayor puntaje y menor
	 * tiempo estan primeros en la lista.
	 */
	private void sortScorename(ArrayList<Scorename> scorenamelist)
	{
		Collections.sort(scorenamelist, new Comparator<Scorename>()
		{

			@Override
			public int compare(Scorename o1, Scorename o2)
			{
				if (o1.getPoints() > o2.getPoints())
					return 1;
				else if (o1.getPoints() == o2.getPoints() && o1.getTime() < o2.getTime())
					return 1;
				if (o1.getPoints() < o2.getPoints())
					return -1;
				return 0;
			}

		});
		int i;
		for(i = 0; i < scorenamelist.size(); i++)
		{
			scorenamelist.get(i).setRank(i+1);
		}
	}
}
