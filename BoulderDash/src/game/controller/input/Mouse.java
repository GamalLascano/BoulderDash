package game.controller.input;

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
	public void mouseClicked(MouseEvent e)
	{
		JLabel label = (JLabel) e.getSource();

		if (label.getText().equals("<"))
		{
			MapInstance.getInstance().levelPrevious();
			Rockford.getInstance().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}

		if (label.getText().equals(">"))
		{
			MapInstance.getInstance().levelNext();
			Rockford.getInstance().reset();
			FrameMap.getInstance().refreshLevelLabel();
		}

	}

}
