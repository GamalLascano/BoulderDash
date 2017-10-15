package game;

public class Status {
	boolean status;
	boolean exploding;
	boolean expanding;
	boolean moving;
	boolean falling;

	public Status(){
		
	}
	
	public Status(boolean status, boolean exploding, boolean expanding, boolean moving, boolean falling) {
		super();
		this.status = status;
		this.exploding = exploding;
		this.expanding = expanding;
		this.moving = moving;
		this.falling = falling;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public boolean isExpanding() {
		return expanding;
	}

	public void setExpanding(boolean expanding) {
		this.expanding = expanding;
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
		return "Status [status=" + status + ", exploding=" + exploding + ", expanding=" + expanding + ", moving="
				+ moving + ", falling=" + falling + "]";
	}
	
}
