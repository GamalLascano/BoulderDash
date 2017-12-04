package game.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

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
			MapInstance.buildSelectedLevel(level);
			Rockford.getRockford().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}
		
		if( label.getText().equals(">") )
		{
			Integer level = MapInstance.getSelectedLevel() + 1;
			MapInstance.buildSelectedLevel(level);
			Rockford.getRockford().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}
		
	}
	
}
