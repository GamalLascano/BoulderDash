package game.map;

import game.item.Item;
import java.util.ArrayList;
import java.util.List;

public class ListItem
{
	private List<Item> it = new ArrayList<Item>();

	public ListItem(List<Item> it)
	{
		super();
		this.it = it;
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
