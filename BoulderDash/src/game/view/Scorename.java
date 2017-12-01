package game.view;

import java.io.Serializable;

/**
 * Objeto para poner en la tabla de score.
 *
 */
public class Scorename implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5463699393880505320L;
	private Integer Rank;
	private String Name;
	private Integer Points;
	private Integer Time;

	public Scorename()
	{
		Rank = null;
		Name = null;
		Points = null;
		Time = null;
	}
	
	public Scorename(Integer rank, String name, Integer points, Integer time)
	{
		Rank = rank;
		Name = name;
		Points = points;
		Time = time;
	}

	public Integer getRank()
	{
		return Rank;
	}

	public String getName()
	{
		return Name;
	}

	public Integer getPoints()
	{
		return Points;
	}

	public Integer getTime()
	{
		return Time;
	}

	public void setRank(Integer rank)
	{
		Rank = rank;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public void setPoints(Integer points)
	{
		Points = points;
	}

	public void setTime(Integer time)
	{
		Time = time;
	}

	
}
