package game.exception;

public class LevelNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LevelNotFoundException()
	{
		super();
	}

	public LevelNotFoundException(String message)
	{
		super(message);
	}

	public LevelNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LevelNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
