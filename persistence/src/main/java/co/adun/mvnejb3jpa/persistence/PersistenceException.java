package co.adun.mvnejb3jpa.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.ApplicationException;

import co.adun.mvnejb3jpa.BaseException;
import co.adun.mvnejb3jpa.ExceptionMessage;

/**
 * Application exception thrown when there is a Resource level error like JDBC connection  
 * 
 *  @author Mikhel Adun, Ram Mahajan
 */

@ApplicationException()
public class PersistenceException extends BaseException
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private List<ExceptionMessage> messages = new ArrayList<ExceptionMessage>();
	@SuppressWarnings("unused")
	private String message;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public PersistenceException(String message) {
		super(new ExceptionMessage(message));
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

	public PersistenceException(Set<ExceptionMessage> messagess) {
		super();
		setmessagess(messagess);
	}

	public PersistenceException(ExceptionMessage messages) {
		super();
		add(messages);
	}

	public PersistenceException(List<String> messagess) {
		super();
		addStringMessages(messagess);
	}
	
}
