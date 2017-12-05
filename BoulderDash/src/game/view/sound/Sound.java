package game.view.sound;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * Generador de sonidos. Levanta los archivos wav y contiene metodos para cada
 * sonido.
 */
public class Sound extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Sound playsound;

	private Sound()
	{

	}

	public static Sound getInstance()
	{
		if (playsound == null)
		{
			playsound = new Sound();
		}
		return playsound;
	}

	/**
	 * Sonido del boton.
	 */
	public static void button()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/button.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de colecion de diamante.
	 */
	public static void diamond()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/diamond.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido cuando se pierde el juego.
	 */
	public static void lost()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/lost.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de nuevo juego.
	 */
	public static void newgame()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/newgame.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de victoria.
	 */
	public static void won()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/won.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de cavar.
	 */
	public static void dig()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/dig.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de la explosion.
	 */
	public static void explosion()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/explosion.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido de empuje de rocas.
	 */
	public static void push()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/push.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	/**
	 * Sonido cuando camina rockford.
	 */
	public static void step()
	{
		Clip clip = null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(playsound.getClass().getResource("/res/Sound/step.wav"));
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);

		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}

	// SOUND TEST
	// public static void main(String[] args)
	// {
	// Sound playsound = new Sound();
	// playsound.button();
	// }
}