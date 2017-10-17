package game;

public class Status {
	boolean status;
	boolean exploding;
	boolean moving;
	boolean falling;
	boolean alive;

	public Status(){
		
	}
	
	public Status(boolean status, boolean exploding, boolean moving, boolean falling, boolean alive) {
		super();
		this.status = status;
		this.exploding = exploding;
		this.moving = moving;
		this.falling = falling;
		this.alive = alive;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void changeStatus() {
		if (status) 
			status=false;
		else status=true;
	}

	@Override
	public String toString() {
		return "Status [status=" + status + ", exploding=" + exploding + ", moving=" + moving + ", falling=" + falling
				+ ", alive=" + alive + "]";
	}
	
}
