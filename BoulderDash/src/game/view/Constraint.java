package game.view;

import java.awt.GridBagConstraints;

/**
 * Se utiliza para setear el constraint del gridbaglayout.
 *
 */
public class Constraint
{
	private static GridBagConstraints constraint = new GridBagConstraints();

	/**
	 * Setea la configuracion posicion/visual de un elemento antes de agregarlo
	 * en el panel.
	 * 
	 * @param constraint
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param weightx
	 * @param weighty
	 * @param anchor
	 * @param fill
	 */
	public static void setup(int x, int y, int width, int height, double weightx, double weighty, int anchor, int fill)
	{
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = width;
		constraint.gridheight = height;
		constraint.weightx = weightx;
		constraint.weighty = weighty;
		constraint.anchor = anchor;
		constraint.fill = fill;
	}

	/**
	 * Devuelve el constraint.
	 * 
	 * @return constraint
	 */
	public static GridBagConstraints get()
	{
		return constraint;
	}
}
