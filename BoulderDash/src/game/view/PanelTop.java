package game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.view.scoreboard.ListOfScorename;
import game.view.scoreboard.ScoreBoard;
import game.view.scoreboard.Scorename;
import game.view.sound.SoundPlay;

/**
 * Panel de reglas del juego.
 */
public class PanelTop extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PanelTop toppanel;
	private JButton button;
	private Background panel;
	private static String[] tablecolumn =
	{ "Puesto", "Nombre", "Puntos", "Tiempo", };
	private static Object[][] tableshow;
	private static JTable table = new JTable();
	private static DefaultTableModel tablemodel;
	private static JScrollPane scrollPane = new JScrollPane(table);
	private static JLabel toptitle;

	private PanelTop()
	{
		panel = FrameMenu.getInstance().getPanel();
		button = FrameMenu.getInstance().getButtons()[0];
	}

	public static PanelTop getInstance()
	{
		if (toppanel == null)
		{
			toppanel = new PanelTop();
		}
		return toppanel;
	}

	/**
	 * Panel top x.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void showTopX(FrameMenu framemenu) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		framemenu.refreshPanel(framemenu.getPanel());

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		Integer rowx = Integer.parseInt((String) PanelConfig.getInstance().getConfigTop());
		showXrow(rowx);

		button.setText("Back");
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SoundPlay.button();
				FrameMenu.showMenu();
			}
		});
		toptitle = new JLabel("TOP X");
		toptitle.setForeground(Color.WHITE);

		Constraint.setup(0, 0, 1, 1, 1, 0.5, GridBagConstraints.SOUTH, GridBagConstraints.CENTER);
		panel.add(toptitle, Constraint.get());

		Constraint.setup(0, 1, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		panel.add(scrollPane, Constraint.get());

		Constraint.setup(0, 2, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button, Constraint.get());
	}
	
	/**
	 * Carga los datos del archivo scoreboard y los pone en una tablemodel para
	 * monstrar solo x filas.
	 * 
	 * @param x
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void showXrow(Integer x) throws ClassNotFoundException, FileNotFoundException, IOException, URISyntaxException
	{
		makeTableshow(x);
		ScoreBoard.getInstance().readScorenames();
		tablemodel = null;
		tablemodel = new DefaultTableModel(tableshow, tablecolumn);
		table.setModel(tablemodel);
	}
	
	/**
	 * Tabla que se monstrara en el menu top.
	 */
	private void makeTableshow(Integer x)
	{
		Scorename participant;
		tableshow = new Object[x][4];
		if (x > ListOfScorename.getList().size())
		{
			for (int i = 0; i < ListOfScorename.getList().size(); i++)
			{
				participant = ListOfScorename.getList().get(i);
				tableshow[i][0] = participant.getRank();
				tableshow[i][1] = participant.getName();
				tableshow[i][2] = participant.getPoints();
				tableshow[i][3] = participant.getTime();
			}
		}
		else
		{
			for (int i = 0; i < x; i++)
			{
				participant = ListOfScorename.getList().get(i);
				tableshow[i][0] = participant.getRank();
				tableshow[i][1] = participant.getName();
				tableshow[i][2] = participant.getPoints();
				tableshow[i][3] = participant.getTime();
			}
		}
	}

}
