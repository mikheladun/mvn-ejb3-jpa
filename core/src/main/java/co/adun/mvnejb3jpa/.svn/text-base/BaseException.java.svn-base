package co.adun.mvnejb3jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/** An exception that can hold multiple messages.
 *
 * @author Mikhel Adun
 * */
public class BaseException extends Exception
{
	private static final long serialVersionUID = 1L;

	private List<ExceptionMessage> messages = new ArrayList<ExceptionMessage>();
	private String message;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public BaseException(String message) {
		this(new ExceptionMessage(message));
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(Set<ExceptionMessage> messagess) {
		super();
		setmessagess(messagess);
	}


	public BaseException(ExceptionMessage messages) {
		super();
		add(messages);
	}

	public BaseException(List<String> messagess) {
		super();
		addStringMessages(messagess);
	}

	public void setmessagess(Collection<ExceptionMessage> messagess) {
		this.messages = new ArrayList<ExceptionMessage>(messagess);
	}


	public List<ExceptionMessage> getmessagess() {
		return messages;
	}


	public void add(Collection<ExceptionMessage> messagess) {
		if (messagess == null) return;

		for(ExceptionMessage ive : messagess) {
			add(ive);
		}
	}

	public void add(ExceptionMessage msg){
		messages.add(msg);
	}

	public void add(int index, ExceptionMessage msg){
		messages.add(index, msg);
	}

	public void add(String messages){
		add(new ExceptionMessage(messages));
	}

	public void add(int index, String messages){
		add(index, new ExceptionMessage(messages));
	}
	
	public void addStringMessages(Collection<String> messagess) {
		for(String s : messagess){
			add(new ExceptionMessage(s));
		}
	}
	
	public boolean hasMessage(){
		return message != null;
	}

	@Override
	public String getMessage() {
		if(message != null){
			return message;
		}
		StringBuilder sb = new StringBuilder("Input validation messages: ");
		if(messages.size() == 0){
			sb.append("No messages found.");
		}else{
//			sb.append(MessageTextResolver.getMessage(messagess.iterator().next().getMessage()));
			if(messages.size() > 1){
				sb.append(". ").append(messages.size() - 1).append(" more.");
			}
		}
		return sb.toString();
	}

	public void setMessage(String message){
		this.message = message;
	}

	@Override
	public String toString() {
		return toString(true) ;
	}

	public String toString(boolean includeHeader) {
		StringBuilder sb = new StringBuilder( includeHeader ? "Leadtrac exception messages: " : "");
		if(messages.size() == 0){
			sb.append("No messages found.");
		}else{
			for (ExceptionMessage ivm : messages) {
				sb.append("\n").append(ivm.getMessage());
			}
		}
		return sb.toString();
	}
}
