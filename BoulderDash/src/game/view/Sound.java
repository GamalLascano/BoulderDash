package game.view;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * Generador de sonidos.
 *
 */
public class Sound extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AudioInputStream buttonstream;
	private AudioInputStream diamondstream;
	private AudioInputStream loststream;
	private AudioInputStream wonstream;
	private AudioInputStream newgamestream;
	private Clip button;
	private Clip diamond;
	private Clip lost;
	private Clip won;
	private Clip newgame;

	public Sound()
	{
		URL url;

		try
		{
			url = this.getClass().getResource("button.wav");
			buttonstream = AudioSystem.getAudioInputStream(url);
			this.button = AudioSystem.getClip();

			url = this.getClass().getResource("diamond.wav");
			diamondstream = AudioSystem.getAudioInputStream(url);
			this.diamond = AudioSystem.getClip();

			url = this.getClass().getResource("newgame.wav");
			newgamestream = AudioSystem.getAudioInputStream(url);
			this.newgame = AudioSystem.getClip();

			url = this.getClass().getResource("lost.wav");
			loststream = AudioSystem.getAudioInputStream(url);
			this.lost = AudioSystem.getClip();

			url = this.getClass().getResource("won.wav");
			wonstream = AudioSystem.getAudioInputStream(url);
			this.won = AudioSystem.getClip();
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void button()
	{
		try
		{
			button.open(buttonstream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.start();
		try
		{
			while(buttonstream.available() != 0)
			{
				button.stop();
				button.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void diamond()
	{
		try
		{
			diamond.open(diamondstream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		diamond.start();
		try
		{
			while(diamondstream.available() != 0)
			{
				diamond.stop();
				diamond.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lost()
	{
		try
		{
			lost.open(loststream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lost.start();
		try
		{
			while(loststream.available() != 0)
			{
				lost.stop();
				lost.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void newgame()
	{

		try
		{
			newgame.open(newgamestream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newgame.start();
		try
		{
			while(newgamestream.available() != 0)
			{
				newgame.stop();
				newgame.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void won()
	{
		try
		{
			won.open(wonstream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		won.start();
		try
		{
			while(wonstream.available() != 0)
			{
				won.stop();
				won.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Sound playsound = new Sound();
		playsound.button();
	}
}