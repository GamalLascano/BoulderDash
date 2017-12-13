package game.view.scoreboard;

//import java.io.InputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
//import java.net.URL;

/**
 * Sirve para cargar y guardar la lista de scorenames en un archivo.
 *
 */
public class ScoreBoard
{
	private static ScoreBoard scoreboard;
	private String filename;
	private String foldername;
	private File folder;
	private File file;
	private String pathfolder;
	private String pathfile;
//	private URL fileurl;

	private ScoreBoard()
	{
//		this.fileurl = this.getClass().getResource("./res/Menu/Scoreboard.dat");
		this.filename = "scoreboard.dat";
		this.foldername = "Boulder Dash";
		this.pathfolder = System.getProperty("user.home") + File.separator + "Documents";
		pathfolder += File.separator + foldername;
		this.folder = new File(pathfolder);
		this.pathfile = this.pathfolder;
		this.file = new File(pathfile,filename);
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
	 * Trata de leer el archivo, si no existe lo crea, si existe lo lee.
	 * 
	 * @param scorenamelist
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void findFileAndRead() throws IOException, ClassNotFoundException
	{
		checkIfFolderExists();
		if (file.exists())
		{
			System.out.println(pathfile + "\\" + filename + " already exists");
			readScorenames();
		}
		else if (!file.exists())
		{
			System.out.println(pathfile + "\\" + filename + " doesn't exist");
		}
		else
		{
			System.out.println(pathfile + "\\" + filename + " was not created");
		}
	}

	/**
	 * Lee el archivo y pone los datos en una lista de scorenames. tambien
	 * ordenada la lista.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readScorenames() throws IOException, ClassNotFoundException
	{
		FileInputStream streamin = new FileInputStream(file);
		//InputStream streamin = getClass().getResourceAsStream("/res/Menu/Scoreboard.dat");
		if (streamin.available() != 0)
		{
			ObjectInputStream input = null;
			input = new ObjectInputStream(streamin);
			readingScorenames(input);
			input.close();
		}
	}

	/**
	 * Escribe el archivo.
	 * 
	 * @param scorenamelist
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void writeScorenames() throws IOException, ClassNotFoundException
	{
		FileOutputStream streamout = new FileOutputStream(file);
		ObjectOutputStream output = new ObjectOutputStream(streamout);
		writingScorenames(output);
		output.close();
	}
	
	//////////////////
	
	/**
	 * Saca los datos de la lista de scorenames.
	 * 
	 * @param output
	 * @throws IOException
	 */
	private void writingScorenames(ObjectOutputStream output) throws IOException
	{
		for (int i = 0; i < ListOfScorename.getList().size(); i++)
		{
			output.writeObject(ListOfScorename.getList().get(i));
		}
	}
	
	/**
	 * Lee el archivo y pone los datos en una lista de scorenames.
	 * 
	 * @param endfile
	 * @param input
	 * @return Si termino el archivo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readingScorenames(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		boolean endfile = false;
		Scorename participant;
		
		while (!endfile)
		{
			try
			{
				participant = (Scorename) input.readObject();
				ListOfScorename.getList().add(participant);
			}
			catch (EOFException e)
			{
				endfile = true;
				// e.printStackTrace();
			}
		}
	}
	
	/**
	 * Trata de encontrar el directorio, sino existe lo crea.
	 * 
	 */
	private void checkIfFolderExists()
	{
		if (folder.exists())
		{
			System.out.println(pathfolder + " already exists");
		}
		else if (folder.mkdirs())
		{
			System.out.println(pathfolder + " was created");
		}
		else
		{
			System.out.println(pathfolder + " was not created");
		}
	}

}
