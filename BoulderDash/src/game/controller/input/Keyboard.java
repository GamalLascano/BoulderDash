package game.controller.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.model.element.entity.actor.Rockford;
import game.model.map.MapInstance;

/**
 * 
 * Acciones del teclado.
 *
 */
public class Keyboard extends KeyAdapter
{
	private static boolean up, down, right, left;

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Rockford.getInstance().die();
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_UP)
		{
			MapInstance.getInstance().levelNext();
			Rockford.getInstance().reset();
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)
		{
			MapInstance.getInstance().levelPrevious();
			Rockford.getInstance().reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
	}

	public static boolean isUp()
	{
		return up;
	}

	public static boolean isDown()
	{
		return down;
	}

	public static boolean isRight()
	{
		return right;
	}

	public static boolean isLeft()
	{
		return left;
	}

}
