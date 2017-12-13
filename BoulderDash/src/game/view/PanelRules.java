package game.view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.view.sound.Sound;

/**
 * Panel de reglas del juego.
 */
public class PanelRules extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PanelRules rulespanel;
	private BufferedImage instructionsImg;
	private JLabel instructions;
	private JButton button;
	private Background panel;

	private PanelRules()
	{
		panel = FrameMenu.getInstance().getPanel();
		button = FrameMenu.getInstance().getButtons()[0];
	}

	public static PanelRules getInstance()
	{
		if (rulespanel == null)
		{
			rulespanel = new PanelRules();
		}
		return rulespanel;
	}

	/**
	 * Muestra el panel de reglas.
	 * 
	 * @param panel
	 */
	public void showRules(FrameMenu framemenu)
	{

		framemenu.refreshPanel(framemenu.getPanel());

		try
		{
			instructionsImg = ImageIO.read(rulespanel.getClass().getClassLoader().getResource("./res/Menu/instructions.png"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		instructions = new JLabel(new ImageIcon(instructionsImg));

		button.setText("Back");
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Sound.button();
				FrameMenu.showMenu();
			}
		});

		Constraint.setup(0, 0, 1, 1, 1, 1, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(instructions, Constraint.get());

		Constraint.setup(0, 1, 1, 1, 1, 0.8, GridBagConstraints.BELOW_BASELINE, GridBagConstraints.CENTER);
		panel.add(button, Constraint.get());
	}

}
