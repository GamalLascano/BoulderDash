package game.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

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
	private Integer time;

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

		scoreinfo = new JLabel("Tu puntuacion es: " + Rockford.getInstance().getScore().toString(),
				SwingConstants.CENTER);
		scoreinfo.setSize(100, 50);
		scoreinfo.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridy = 0;
		panelend.add(scoreinfo);

		field = new JTextField();
		field.setEditable(true);
		field.setSize(100, 50);
		c.gridy = 1;
		panelend.add(field, c);

		button = new JButton("Save score");
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = field.getText();
				if (nameIsValid(name))
				{
					if (name.length() > 20)
						name = name.substring(0, 20);
					setVisible(false);
					FrameMenu.getInstance().addNameTable(name, Rockford.getRockford().getScore(), frameend.time);
					try
					{
						FrameMenu.main(null);
					}
					catch (ClassNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (URISyntaxException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					field.setText("INVALIDO!");
				}
			}
		});
		c.gridy = 2;
		panelend.add(button, c);
	}

	public boolean nameIsValid(String name)
	{
		if (name.length() > 2 && !name.contains(" ") && !name.equals("INVALIDO!"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void setTime(Integer time)
	{
		frameend.time = time;
	}

	public static void main(String[] args)
	{
		FrameEnd runFrameEnd = FrameEnd.getInstance();
		runFrameEnd.setVisible(true);
	}

}
