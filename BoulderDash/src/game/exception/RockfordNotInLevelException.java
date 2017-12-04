package game.exception;

/**
 * Se tira cuando Rockford no esta en el mapa.
 *
 */
public class RockfordNotInLevelException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RockfordNotInLevelException()
	{
		super();
	}

	public RockfordNotInLevelException(String message)
	{
		super(message);
	}

	public RockfordNotInLevelException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RockfordNotInLevelException(Throwable cause)
	{
		super(cause);
	}
}
