package game.exception;

/**
 * Tira una excepcion si el nivel no existe.
 *
 */
public class LevelNotValidException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LevelNotValidException()
	{
		super();
	}

	public LevelNotValidException(String message)
	{
		super(message);
	}

	public LevelNotValidException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LevelNotValidException(Throwable cause)
	{
		super(cause);
	}
}
