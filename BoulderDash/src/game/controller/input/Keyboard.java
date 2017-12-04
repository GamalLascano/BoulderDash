package game.controller.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.exception.LevelNotValidException;
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
			Rockford.getRockford().die();
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_UP)
		{
			MapInstance.setSelectedLevel(MapInstance.getSelectedLevel() + 1);
			try
			{
				MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
			}
			catch (LevelNotValidException e1)
			{
				try
				{
					MapInstance.buildSelectedLevel(1);
				}
				catch (LevelNotValidException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			Rockford.getRockford().reset();
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)
		{
			MapInstance.setSelectedLevel(MapInstance.getSelectedLevel() - 1);
			try
			{
				MapInstance.buildSelectedLevel(MapInstance.getSelectedLevel());
			}
			catch (LevelNotValidException e1)
			{
				try
				{
					MapInstance.buildSelectedLevel(1);
				}
				catch (LevelNotValidException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			Rockford.getRockford().reset();
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

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

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
