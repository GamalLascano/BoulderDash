package game.view.scoreboard;

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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scorename other = (Scorename) obj;
		if (Name == null)
		{
			if (other.Name != null)
				return false;
		}
		else if (!Name.equals(other.Name))
			return false;
		return true;
	}
	
	

	
}
