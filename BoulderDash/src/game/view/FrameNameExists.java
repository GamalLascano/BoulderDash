package game.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import game.view.scoreboard.ListOfScorename;
import game.view.scoreboard.Scorename;

/**
 * Si el nombre ya existe en la lista de scorenames, este frame aparece
 *
 */
public class FrameNameExists extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FrameNameExists framenameexists;
	private static JPanel panelend;

	private JButton button1;
	private JButton button2;
	private JLabel scoreinfo;
	private Scorename score;

	private FrameNameExists()
	{
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		buildPanelNE();
		add(panelend);
		pack();
		setSize(250, 150);
		setVisible(true);
	}

	public static FrameNameExists getInstance()
	{
		if (framenameexists == null)
		{
			framenameexists = new FrameNameExists();
		}
		return framenameexists;
	}

	/**
	 * Construye el panel.
	 */
	public void buildPanelNE()
	{
		panelend = new JPanel();
		panelend.setLayout(new GridBagLayout());
		Constraint.setup(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);

		scoreinfo = new JLabel("Quiere sobreescribir su resultado, o entrar otro nombre", SwingConstants.CENTER);
		scoreinfo.setSize(100, 50);
		scoreinfo.setHorizontalAlignment(SwingConstants.CENTER);
		panelend.add(scoreinfo, Constraint.get());

		button1 = new JButton("Sobreescribir");
		button1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (score != null)
				{
					ListOfScorename.getInstance().replaceNameTable(score);
					setVisible(false);
					FrameMenu.runFrameMenu();
				}
			}

		});
		button1.setSize(100, 50);
		Constraint.setup(0, 1, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		panelend.add(button1, Constraint.get());

		button2 = new JButton("Cambiar Nombre");
		button2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FrameNameExists.getInstance().setVisible(false);
				FrameEnd.getInstance().setVisible(true);
			}
		});
		Constraint.setup(0, 2, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		panelend.add(button2, Constraint.get());
	}

	/**
	 * Se muestra si el nombre ya existe en el top.
	 * 
	 * @param score
	 */
	public static void runFrameNameExists(Scorename score)
	{
		FrameNameExists runFrameNameExists = FrameNameExists.getInstance();
		runFrameNameExists.setVisible(true);
		runFrameNameExists.score = score;
	}
}
