package game.view;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Dibuja la imagen de fondo en el menu.
 *
 */
public class Background extends JPanel
{
	private static final long serialVersionUID = -477222138070292249L;
	private Image img;

	/**
	 * Constructor background.
	 * @param gridBagLayout
	 */
	public Background(GridBagLayout gridBagLayout)
	{
		// TODO Auto-generated constructor
		this.setLayout(gridBagLayout);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(img, 0, 0, null);
	}

	/**
	 * Setea la imagen de fondo.
	 * @param img
	 */
	public void setImage(Image img)
	{
		this.img = img;
	}
}