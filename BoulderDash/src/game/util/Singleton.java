package game.util;

public class Singleton
{

	private static Singleton single;

	private Singleton()
	{

	}

	public static Singleton getSingleton()
	{
		if (single == null)
		{
			single = new Singleton();
		}
		return single;
	}
}
