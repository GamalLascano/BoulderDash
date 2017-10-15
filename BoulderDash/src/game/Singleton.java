package game;

public class Singleton {

	private static Singleton single;
	
	private Singleton() {
		
	}
	
	public static synchronized Singleton getSingleton() {
		if (single == null) {
			single = new Singleton();
		}
		return single;
	}
}
