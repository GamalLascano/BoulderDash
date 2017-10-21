package game.map;

import game.item.Item;
import java.util.ArrayList;
import java.util.List;

public class ListItem
{
	private List<Item> it = new ArrayList<Item>();

	public ListItem()
	{
		super();
	}

	public List<Item> getIt()
	{
		return it;
	}

	public void setIt(List<Item> it)
	{
		this.it = it;
	}

}
