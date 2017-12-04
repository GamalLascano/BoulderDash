package game.controller.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import game.exception.LevelNotValidException;
import game.model.element.entity.actor.Rockford;
import game.model.map.MapInstance;
import game.view.FrameMap;

/**
 * 
 * Acciones del mouse.
 *
 */
public class Mouse extends MouseAdapter
{
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		JLabel label = (JLabel) e.getSource();
		
		if( label.getText().equals("<") )
		{
			Integer level = MapInstance.getSelectedLevel() - 1;
			try
			{
				MapInstance.buildSelectedLevel(level);
			}
			catch (LevelNotValidException e1)
			{
				try
				{
					MapInstance.buildSelectedLevel(1);
				}
				catch (LevelNotValidException e2)
				{
					try
					{
						MapInstance.buildSelectedLevel(1);
					}
					catch (LevelNotValidException e3)
					{
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					e2.printStackTrace();
				}
				
				e1.printStackTrace();
			}
			Rockford.getRockford().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}
		
		if( label.getText().equals(">") )
		{
			Integer level = MapInstance.getSelectedLevel() + 1;
			try
			{
				MapInstance.buildSelectedLevel(level);
			}
			catch (LevelNotValidException e1)
			{
				try
				{
					MapInstance.buildSelectedLevel(1);
				}
				catch (LevelNotValidException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			Rockford.getRockford().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}
		
	}
	
}
