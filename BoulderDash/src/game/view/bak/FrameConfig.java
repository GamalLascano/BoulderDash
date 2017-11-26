package game.view.bak;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import game.model.map.MapInstance;
import game.view.FrameMenu;

public class FrameConfig extends JFrame
{
	/**
	 * 
	 */
	private static FrameConfig frameconfig;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> resoluciones;
	private JCheckBox fullScr;
	private JComboBox<String> top;
	private JComboBox<String> niveles;
	private JButton boton;
	private Dimension pastScreenSize = new Dimension(800, 600);

	private FrameConfig()
	{
		setTitle("Settings");
		setResizable(false);
		setSize(800, 600);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		String[] levels =
		{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		String[] resolutions =
		{ "800x600", "1024x768", "1366x768", "1920x1080" };
		String[] tops =
		{ "TOP 5", "TOP 10", "TOP 15", "TOP 20" };

		top = new JComboBox<>(tops);
		top.setSelectedIndex(0);
		resoluciones = new JComboBox<>(resolutions);
		resoluciones.setSelectedIndex(0);
		resoluciones.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				if (res.equals("800x600"))
				{
					FrameMenu.getInstance().setSize(800, 600);
					FrameMenu.getInstance().putBackground();
					frameconfig.setSize(800, 600);
					frameconfig.repaint();
				}
				else if (res.equals("1024x768"))
				{
					FrameMenu.getInstance().setSize(1024, 768);
					FrameMenu.getInstance().putBackground();
					frameconfig.setSize(1024, 768);
					frameconfig.repaint();
				}
				else if (res.equals("1366x768"))
				{
					FrameMenu.getInstance().setSize(1366, 768);
					FrameMenu.getInstance().putBackground();
					frameconfig.setSize(1366, 768);
					frameconfig.repaint();
				}
				else if (res.equals("1920x1080"))
				{
					FrameMenu.getInstance().setSize(1920, 1080);
					FrameMenu.getInstance().putBackground();
					frameconfig.setSize(1920, 1080);
					frameconfig.repaint();
				}
			}
		});
		fullScr = new JCheckBox("Pantalla Completa");
		fullScr.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					FrameConfig.getInstance().getContentPane().getComponent(1).setEnabled(false);
					FrameConfig.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
					FrameConfig.getInstance().dispose();
					FrameConfig.getInstance().setUndecorated(true);
					FrameConfig.getInstance().setVisible(true);
					pastScreenSize = FrameMenu.getInstance().getSize();
					FrameMenu.getInstance().setSize(screenSize);
					FrameMenu.getInstance().putBackground();
					FrameMenu.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
					FrameMenu.getInstance().dispose();
					FrameMenu.getInstance().setUndecorated(true);
				}
				else
				{
					FrameConfig.getInstance().getContentPane().getComponent(1).setEnabled(true);
					FrameConfig.getInstance().setExtendedState(JFrame.NORMAL);
					FrameConfig.getInstance().dispose();
					FrameConfig.getInstance().setUndecorated(false);
					FrameConfig.getInstance().setVisible(true);
					FrameMenu.getInstance().setSize(pastScreenSize);
					FrameMenu.getInstance().putBackground();
					FrameMenu.getInstance().setExtendedState(JFrame.NORMAL);
					FrameMenu.getInstance().dispose();
					FrameMenu.getInstance().setUndecorated(false);

				}
			}
		});
		fullScr.setSelected(false);
		niveles = new JComboBox<>(levels);
		niveles.setSelectedItem(0);
		niveles.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox) e.getSource();
				String res = (String) cb.getSelectedItem();
				MapInstance.setSelectedLevel(Integer.parseInt(res));
			}
		});
		boton = new JButton("Volver al menu");
		boton.setBounds(0, 0, 100, 25);
		boton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FrameMenu.getInstance().setVisible(true);
				FrameConfig.getInstance().setVisible(false);
			}
		});
		add(top);
		add(resoluciones);
		add(fullScr);
		add(niveles);
		add(boton);
		setVisible(true);
	}

	public static FrameConfig getInstance()
	{
		if (frameconfig == null)
		{
			frameconfig = new FrameConfig();
		}
		return frameconfig;
	}

	public static void main(String[] args)
	{
		FrameConfig.getInstance();
	}

}
