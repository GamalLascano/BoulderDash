package game.view.scoreboard;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

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
	public void readScorenames() throws IOException, ClassNotFoundException
	{
		boolean endfile = false;
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

			while (!endfile)
			{
				try
				{
					participant = (Scorename) input.readObject();
					ListOfScorename.getList().add(participant);
				}
				catch(EOFException e)
				{
					endfile = true;
					//e.printStackTrace();
				}
			}
			input.close();

		}
	}

	/**
	 * Escribe el archivo. Saca los datos de la lista de scorenames.
	 * 
	 * @param scorenamelist
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void writeScorenames() throws IOException, ClassNotFoundException
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
		for (i = 0; i < ListOfScorename.getList().size(); i++)
		{
			output.writeObject(ListOfScorename.getList().get(i));
		}
		output.close();

	}

}
