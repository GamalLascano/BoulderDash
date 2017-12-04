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

import game.controller.MapAccess;
import game.view.scoreboard.ListOfScorename;
import game.view.scoreboard.Scorename;

/**
 * Panel que aparece cuando termina el juego. Se encarga de guardar el score del
 * jugador en la tabla.
 */
public class FrameEnd extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861437422306275617L;
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

	/**
	 * Construye el panel.
	 */
	public void buildPanelEnd()
	{

		panelend = new JPanel();
		panelend.setLayout(new GridBagLayout());

		scoreinfo = new JLabel("text", SwingConstants.CENTER);
		scoreinfo.setSize(100, 50);
		scoreinfo.setHorizontalAlignment(SwingConstants.CENTER);
		panelend.add(scoreinfo);

		field = new JTextField();
		field.setEditable(true);
		field.setSize(100, 50);
		Constraint.setup(0, 1, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		panelend.add(field, Constraint.get());

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
					Scorename scorename = new Scorename(0, name, MapAccess.getTotalScore(), frameend.time);
					
					if(ListOfScorename.getInstance().equals(scorename))
					{
						setVisible(false);
						FrameNameExists.runFrameNameExists(scorename);
					}
					else
					{
						ListOfScorename.getInstance().addNameTable(scorename);
						setVisible(false);
						FrameMenu.runFrameMenu();
					}

				}
				else
				{
					field.setText("INVALIDO!");
				}
			}
		});
		Constraint.setup(0, 2, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		panelend.add(button, Constraint.get());
	}

	/**
	 * Verifica si el nombre del jugador es valido.
	 * 
	 * @param name
	 * @return
	 */
	private boolean nameIsValid(String name)
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

	/**
	 * Setea el tiempo del jugador.
	 * 
	 * @param time
	 */
	public static void setTime(Integer time)
	{
		frameend.time = time;
	}

	/**
	 * Refresca el panelEnd.
	 */
	private void refreshPanelEnd()
	{
		if(MapAccess.getTotalScore() != null && frameend.time != null)
		{
			scoreinfo.setText("Puntuacion: " + MapAccess.getTotalScore().toString() + "\n " + "Tiempo: " + frameend.time.toString());
		}
	}
	
	public static void runFrameEnd(String[] args)
	{
		FrameEnd runFrameEnd = FrameEnd.getInstance();
		runFrameEnd.refreshPanelEnd();
		runFrameEnd.setVisible(true);
	}

}
