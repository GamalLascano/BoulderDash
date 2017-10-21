package game.map;

import game.item.Item;
import java.util.ArrayList;
import java.util.List;

public class ActiveItems
{
	private List<Item> list = new ArrayList<Item>();

	public ActiveItems()
	{
		super();
	}

	public List<Item> getList()
	{
		return list;
	}

	public void setList(List<Item> list)
	{
		this.list = list;
	}

}
