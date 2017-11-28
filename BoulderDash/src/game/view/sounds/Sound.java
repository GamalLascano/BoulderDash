package game.view.sounds;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{

	private AudioInputStream audioStream;
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
			url = this.getClass().getClassLoader().getResource("button.wav");
			audioStream = AudioSystem.getAudioInputStream(url);
			this.button = AudioSystem.getClip();

			url = this.getClass().getClassLoader().getResource("diamond.wav");
			audioStream = AudioSystem.getAudioInputStream(url);
			this.diamond = AudioSystem.getClip();

			url = this.getClass().getClassLoader().getResource("newgame.wav");
			audioStream = AudioSystem.getAudioInputStream(url);
			this.newgame = AudioSystem.getClip();

			url = this.getClass().getClassLoader().getResource("lost.wav");
			audioStream = AudioSystem.getAudioInputStream(url);
			this.lost = AudioSystem.getClip();

			url = this.getClass().getClassLoader().getResource("won.wav");
			audioStream = AudioSystem.getAudioInputStream(url);
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
			button.open(audioStream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.start();
	}

	public void diamond()
	{
		try
		{
			diamond.open(audioStream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		diamond.start();
	}

	public void lost()
	{
		try
		{
			lost.open(audioStream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lost.start();
	}

	public void newgame()
	{

		try
		{
			newgame.open(audioStream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newgame.start();
	}

	public void won()
	{
		try
		{
			won.open(audioStream);
		}
		catch (LineUnavailableException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		won.start();
	}
}