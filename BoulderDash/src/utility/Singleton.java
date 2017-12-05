package utility;

/**
 * Clase singleton por defecto.
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
	 * 
	 * @return single
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
