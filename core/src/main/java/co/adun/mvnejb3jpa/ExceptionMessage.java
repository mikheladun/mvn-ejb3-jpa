package co.adun.mvnejb3jpa;

public class ExceptionMessage {

	private String message;

	public ExceptionMessage() {

	}
	public ExceptionMessage(String message){
		super();
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
