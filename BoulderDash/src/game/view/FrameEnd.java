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
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import game.model.actor.*;

public class FrameEnd extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static FrameEnd frameend;
	private static JPanel panelend;
	

	// panelend
	private JTextField field;
	private JButton button;
	private JLabel scoreinfo;
	
	private FrameEnd()
	{
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		buildPanelEnd();
		add(panelend);
		pack();
		setSize(250, 150);
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		c.gridx = 0;
		
		scoreinfo = new JLabel("Tu puntuación es: " + Rockford.getInstance().getScore().toString(),SwingConstants.CENTER);
		scoreinfo.setSize(100, 50);
		scoreinfo.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridy=0;
		panelend.add(scoreinfo);
		
		field = new JTextField();
		field.setEditable(true);
		field.setSize(100, 50);
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
		c.gridy = 2;
		panelend.add(button,c);		
	}
	
	public static void main(String[] args)
	{
		FrameEnd runFrameEnd = FrameEnd.getInstance();
		runFrameEnd.setVisible(true);
	}

}
