package game;

public class Timer {
	//Esta clase va a ser temporal, va a cambiar cuando sea tiempo real, ahora funciona a base de turnos
	private int time;
	private boolean running;
	public void start() {
		running=true;
		time=0;
	}
	public void run() {
		if (running==true) {
			time++;
		}
	}
	public void pause() {
		running=false;
	}
	public int getTime() {
		return time;
	}
}
