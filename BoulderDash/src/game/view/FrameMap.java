package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//
//import javax.swing.AbstractAction;
//import javax.swing.Action;
//import javax.swing.InputMap;
//import javax.swing.JComponent;
//import javax.swing.KeyStroke;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.controller.Keyboard;
import game.controller.MapAccess;
import game.controller.Mouse;
import game.controller.PlayerAccess;
import game.view.graphics.PanelMap;
import game.view.sound.SoundPlay;

/**
 * Panel del juego, donde aparece el mapa.
 *
 */
public class FrameMap extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -678005524164695823L;
	private static int CELLSIZEX = 16;
	private static int CELLSIZEY = 16;
	private static PanelMap panelmap = new PanelMap();
	private static JPanel paneltop = new JPanel();
	private static FrameMap framemap;
	private static boolean fullscr;
	private static Dimension pastScreenSize = new Dimension(800, 600);
	// panel
	private static JLabel labeltop[][] = new JLabel[1][9];

	private FrameMap()
	{
		GridBagConstraints c = new GridBagConstraints();
		setTitle("Boulder Dash");
		setResizable(false);
		setSize(800, 600);
		getContentPane().setBackground(Color.BLACK);
		setLayout(new GridBagLayout());
		panelmap.setLayout(new GridLayout(MapAccess.getHeight(), MapAccess.getWidth()));
		buildPaneltop();
		add(paneltop, c);

		c.weighty = 10;
		c.weightx = 10;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		panelmap.setBackground(Color.BLACK);
		add(panelmap, c);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(new Keyboard());

//		KEYBIND		
//        Action numberAction = new AbstractAction()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                
//            }
//        };
//        
//        KeyStroke pressed = KeyStroke.getKeyStroke("W");
//        InputMap inputMap = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//        inputMap.put(pressed, text);
//        button.getActionMap().put(text, numberAction);

	}

	public static FrameMap getInstance()
	{
		if (framemap == null)
		{
			framemap = new FrameMap();
		}
		return framemap;
	}

	/**
	 * Refresca el mapa.
	 */
	public static void refresh()
	{
		PlayerAccess.updateMove();
		FrameMap.refreshPaneltop();
		FrameMap.panelmap.repaint();
	}

	/**
	 * Refresca el panel superior, el score, diamantes obtenidos y otras
	 * informacion del jugador.
	 */
	public static void refreshPaneltop()
	{
		labeltop[0][1].setText(MapAccess.getLevel().toString());
		labeltop[0][3].setText(PlayerAccess.getDiamonds().toString());
		labeltop[0][5].setText(MapAccess.getDiamondsneeded().toString());
		labeltop[0][6].setText(MapAccess.getTimer().toString());
		labeltop[0][7].setText(PlayerAccess.getLives().toString());
		labeltop[0][8].setText(PlayerAccess.getScore().toString());
	}

	/**
	 * Se usa en el gamethread. Remueve todo los elementos del panel.
	 */
	public static void remove()
	{
		panelmap.removeAll();
	}

	/**
	 * Se usa en el gamethread. Hace dispose del frame.
	 */
	public static void disposeFrame()
	{
		framemap.dispose();
	}

	/**
	 * Inicializa el framemap.
	 */
	public static void start()
	{
		FrameMap.getInstance();
		SoundPlay.getInstance();
		SoundPlay.newgame();
		PlayerAccess.resetPlayer();
	}

	public static int getCellsizex()
	{
		return CELLSIZEX;
	}

	public static int getCellsizey()
	{
		return CELLSIZEY;
	}

	public static void setCellsize(int x, int y)
	{
		CELLSIZEX = x;
		CELLSIZEY = y;
	}

	public static void setFullscr(boolean enabled)
	{
		fullscr = enabled;
	}

	/**
	 * 
	 */
	public static void fullScr()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pastScreenSize = FrameMenu.getInstance().getSize();
		if (fullscr)
		{
			pastScreenSize = getInstance().getSize();
			getInstance().setSize(screenSize);
			panelmap.cambiarsize();
			getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
			getInstance().dispose();
			getInstance().setUndecorated(true);
			getInstance().pack();
			getInstance().setVisible(true);
			getInstance().setAlwaysOnTop(true);
			getInstance().setLocationRelativeTo(null);

		}
		else
		{
			getInstance().setExtendedState(JFrame.NORMAL);
			getInstance().dispose();
			getInstance().setUndecorated(false);
			getInstance().pack();
			getInstance().setSize(pastScreenSize);
			panelmap.cambiarsize();
			getInstance().setVisible(true);
			getInstance().setAlwaysOnTop(false);
			getInstance().setLocationRelativeTo(null);

		}
	}

	public static void setPanelTopSize(int size)
	{
		paneltop.setPreferredSize(new Dimension(643, size));
	}
	
	public void refreshLevelLabel()
	{
		labeltop[0][1].setText(MapAccess.getLevel().toString());
	}

	/**
	 * Construye el panel superior.
	 */
	public static void buildPaneltop()
	{
		GridBagConstraints c = new GridBagConstraints();

		c.weighty = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		paneltop.setBackground(Color.BLACK);

		paneltop.setLayout(new FlowLayout());
		paneltop.setPreferredSize(new Dimension(643, 22));

		labeltop[0][0] = new JLabel("<");
		labeltop[0][0].setForeground(Color.WHITE);
		labeltop[0][0].addMouseListener(new Mouse());
		paneltop.add(labeltop[0][0]);

		labeltop[0][1] = new JLabel(MapAccess.getLevel().toString());
		labeltop[0][1].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][1]);

		labeltop[0][2] = new JLabel(">");
		labeltop[0][2].setForeground(Color.WHITE);
		labeltop[0][2].addMouseListener(new Mouse());
		paneltop.add(labeltop[0][2]);

		labeltop[0][3] = new JLabel(PlayerAccess.getDiamonds().toString());
		labeltop[0][3].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][3]);

		labeltop[0][4] = new JLabel(":");
		labeltop[0][4].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][4]);

		Integer diamondsneeded = MapAccess.getDiamondsneeded();
		labeltop[0][5] = new JLabel(diamondsneeded.toString());
		labeltop[0][5].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][5]);

		labeltop[0][6] = new JLabel(MapAccess.getTimer().toString());
		labeltop[0][6].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][6]);

		labeltop[0][7] = new JLabel(PlayerAccess.getLives().toString());
		labeltop[0][7].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][7]);

		labeltop[0][8] = new JLabel(PlayerAccess.getScore().toString());
		labeltop[0][8].setForeground(Color.WHITE);
		paneltop.add(labeltop[0][8]);

	}

}
