package game.map;

import game.item.Item;
import java.util.ArrayList;
import java.util.List;

public class ActiveItem
{
	private List<Item> list = new ArrayList<Item>();

	public ActiveItem()
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
