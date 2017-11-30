package game.util;

/**
 * Clase singleton
 *
 */
public class Singleton
{

	private static Singleton single;

	/**
	 * Constructor singleton
	 */
	private Singleton()
	{

	}

	/**
	 * Singleton
	 * @return
	 */
	public static Singleton getInstance()
	{
		if (single == null)
		{
			single = new Singleton();
		}
		return single;
	}
}
