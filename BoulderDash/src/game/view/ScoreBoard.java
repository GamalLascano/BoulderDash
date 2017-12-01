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

/**
 * Sirve para cargar el top en una matriz.
 *
 */
public class ScoreBoard
{
	private File file;
	private static ScoreBoard scoreboard;

	private ScoreBoard() throws URISyntaxException, FileNotFoundException
	{
		URL filename = this.getClass().getResource("Scoreboard.dat");
		this.file = new File(filename.toURI());
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
	 * 
	 * @param matrix
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readScorenames(ArrayList<Scorename> scorenamelist) throws IOException, ClassNotFoundException
	{

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
		}
	}

	/**
	 * 
	 * 
	 * @param matrix
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void writeScorenames(ArrayList<Scorename> scorenamelist) throws IOException, ClassNotFoundException
	{
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
}
