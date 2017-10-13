package game;

public class Status {
	boolean status;

	public boolean isStatus() {
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
}
