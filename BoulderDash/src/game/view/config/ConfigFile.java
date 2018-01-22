package game.view.config;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

/**
 * Sirve para cargar y guardar la lista de scorenames en un archivo.
 *
 */
public class ConfigFile
{
	private static ConfigFile config;
	private String filename;
	private String foldername;
	private File file;
	private String path;

	private ConfigFile()
	{
		this.filename = "config.txt";
		this.foldername = "Boulder Dash";
		this.path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + foldername;
		this.file = new File(path, filename);
	}

	public static ConfigFile getInstance() throws FileNotFoundException, URISyntaxException
	{
		if (config == null)
		{
			config = new ConfigFile();
		}
		return config;
	}

	/**
	 * Trata de leer el archivo, si no existe lo crea, si existe lo lee.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void findFileAndRead() throws IOException, ClassNotFoundException
	{
		if (file.exists())
		{
			System.out.println(path + "\\" + filename + " already exists");
			readConfig();
		}
		else if (!file.exists())
		{
			System.out.println(path + "\\" + filename + " doesn't exist");
			setDefaultConfig();
		}
		else
		{
			System.out.println(path + "\\" + filename + " was not created");
		}
	}

	/**
	 * Config por default
	 */
	private void setDefaultConfig()
	{
		Config.getInstance().setTop("5");
		Config.getInstance().setResolution("800x600");
		Config.getInstance().setInitialLevel("1");
		Config.getInstance().setFullscreen("false");
	}

	/**
	 * Lee el archivo y pone los datos de config.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readConfig() throws IOException, ClassNotFoundException
	{
		FileInputStream streamin = new FileInputStream(file);
		if (streamin.available() != 0)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(streamin));
			String top = reader.readLine();
			top = top.substring(top.lastIndexOf(" ") + 1);
			String res = reader.readLine();
			res = res.substring(res.lastIndexOf(" ") + 1);
			String ini = reader.readLine();
			ini = ini.substring(ini.lastIndexOf(" ") + 1);
			String ful = reader.readLine();
			ful = ful.substring(ful.lastIndexOf(" ") + 1);
			
			Config.getInstance().setTop(top);
			Config.getInstance().setResolution(res);
			Config.getInstance().setInitialLevel(ini);
			Config.getInstance().setFullscreen(ful);
			
			reader.close();
		}
	}

	/**
	 * Escribe el archivo.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException 
	 */
	public void writeConfig() throws IOException, ClassNotFoundException, URISyntaxException
	{
		FileOutputStream streamout = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(streamout));
	 
		bw.write("top: ");
		bw.write(Config.getInstance().getTop());
		bw.newLine();
		bw.write("resolution: ");
		bw.write(Config.getInstance().getResolution());
		bw.newLine();
		bw.write("initialLevel: ");
		bw.write(Config.getInstance().getInitialLevel());
		bw.newLine();
		bw.write("fullscreen: ");
		bw.write(Config.getInstance().getFullscreen());
		bw.newLine();

		bw.close();
	}

}
