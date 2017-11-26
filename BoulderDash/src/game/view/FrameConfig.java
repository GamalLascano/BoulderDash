package game.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class FrameConfig extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static FrameConfig single;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> resoluciones;
	private JCheckBox fullScr;
	private JComboBox<String> top;
	private JButton boton;

	private FrameConfig() {
		setTitle("Settings");
		setResizable(true);
		setSize(800, 600);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		String[] resolutions = { "800x600", "1024x768", "1366x768","1920x1080" };
		String[] tops = { "TOP 5", "TOP 10", "TOP 15", "TOP 20" };
		top = new JComboBox(tops);
		top.setSelectedIndex(0);
		resoluciones = new JComboBox(resolutions);
		resoluciones.setSelectedIndex(0);
		resoluciones.addActionListener(this);
		fullScr = new JCheckBox("Pantalla Completa");
		fullScr.addItemListener(new MiItemListener());
		fullScr.setSelected(false);
		boton = new JButton("Volver al menu");
		boton.setBounds(0, 0, 100, 25);
		boton.addMouseListener(new mouseListenerSalida());
		add(top);
		add(resoluciones);
		add(fullScr);
		add(boton);
		setVisible(true);
	}

	public static FrameConfig getInstance() {
		if (single == null) {
			single = new FrameConfig();
		}
		return single;
	}

	public class MiItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				FrameConfig.getInstance().getContentPane().getComponent(1).setEnabled(false);
				FrameConfig.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
				FrameConfig.getInstance().dispose();
				FrameConfig.getInstance().setUndecorated(true);
				FrameConfig.getInstance().setVisible(true);
				FrameMenu.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
				FrameMenu.getInstance().dispose();
				FrameMenu.getInstance().setUndecorated(true);
			} else {
				FrameConfig.getInstance().getContentPane().getComponent(1).setEnabled(true);
				FrameConfig.getInstance().setExtendedState(JFrame.NORMAL);
				FrameConfig.getInstance().dispose();
				FrameConfig.getInstance().setUndecorated(false);
				FrameConfig.getInstance().setVisible(true);
				FrameMenu.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
				FrameMenu.getInstance().dispose();
				FrameMenu.getInstance().setUndecorated(false);

			}
		}
	}
	public class mouseListenerSalida extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			FrameMenu.getInstance().setVisible(true);
			FrameConfig.getInstance().setVisible(false);
		}
	}
	public class miMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resoluciones) {
			JComboBox cb = (JComboBox) e.getSource();
			String res = (String) cb.getSelectedItem();
			if (res.equals("800x600")) {
				FrameMenu.getInstance().setSize(800, 600);
				this.setSize(800, 600);
				this.repaint();
			} else {
				if (res.equals("1024x768")) {
					FrameMenu.getInstance().setSize(1024, 768);
					this.setSize(1024, 768);
					this.repaint();
				} else {
					if (res.equals("1366x768")) {
						FrameMenu.getInstance().setSize(1366, 768);
						this.setSize(1366, 768);
						this.repaint();
					}else {
						if (res.equals("1920x1080")) {
							FrameMenu.getInstance().setSize(1920, 1080);
							this.setSize(1920,1080);
							this.repaint();
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		FrameConfig.getInstance();
	}

}
