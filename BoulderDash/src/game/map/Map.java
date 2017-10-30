package game.map;
import game.map.bdlevel.*;
import game.Position;
public abstract class Map
{
	private static Map singleton;
	private static BDLevelReader level;
	private static Object[][] matrix;
	
	public Map() {
		matrix=null;
	}
	
	abstract Map getInstance();
	
	abstract void start(BDLevelReader level);
	
	abstract Object getObject(Position pos);
	
	abstract Object getObject(Integer x, Integer y);
	
	abstract boolean setObject(Position pos, Object obj);
	
	abstract boolean removeObject(Position pos);
	
	public void fill() {
		for (int x = 0; x < level.getWIDTH(); x++)
			for (int y = 0; y < level.getHEIGHT(); y++)
			{
				matrix[x][y] = null;
			}
	}
}
