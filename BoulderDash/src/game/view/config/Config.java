package game.view.config;

/**
 * Objeto config para guardar los valores de configuracion.
 *
 */
public class Config
{
	private static Config config;
	private String top;
	private String resolution;
	private String initialLevel;
	private String fullscreen;
	
	private Config()
	{
		this.top = null;
		this.resolution = null;
		this.initialLevel = null;
		this.fullscreen = null;
	}

	public static Config getInstance()
	{
		if (config == null)
		{
			config = new Config();
		}
		return config;
	}

	public String getTop()
	{
		return top;
	}

	public void setTop(String top)
	{
		this.top = top;
	}

	public String getResolution()
	{
		return resolution;
	}

	public void setResolution(String resolution)
	{
		this.resolution = resolution;
	}

	public String getInitialLevel()
	{
		return initialLevel;
	}

	public void setInitialLevel(String initialLevel)
	{
		this.initialLevel = initialLevel;
	}

	public String getFullscreen()
	{
		return fullscreen;
	}

	public void setFullscreen(String fullscreen)
	{
		this.fullscreen = fullscreen;
	}
	
	
}
