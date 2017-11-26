package game.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameEnd extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameEnd frameend;
	private static JPanel panelend;

	// panelend
	private JTextField field;
	private JButton button;

	private FrameEnd()
	{
		setLayout(new FlowLayout());
		buildPanelEnd();
		add(panelend);
		pack();
		setVisible(true);
		
	}

	public static FrameEnd getInstance()
	{
		if (frameend == null)
		{
			frameend = new FrameEnd();
		}
		return frameend;
	}
	
	public void buildPanelEnd()
	{
		GridBagConstraints c = new GridBagConstraints();
		panelend = new JPanel();
		panelend.setLayout(new GridBagLayout());
		
		field = new JTextField();
		field.setEditable(true);
		field.setSize(100, 50);
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		panelend.add(field,c);
		
		button = new JButton("Save score");
		button.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				FrameMenu.main(null);
			}
		});
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 2;
		panelend.add(button,c);		
	}
	
	public static void main(String[] args)
	{
		FrameEnd runFrameEnd = FrameEnd.getInstance();
		runFrameEnd.isActive();
	}

}
