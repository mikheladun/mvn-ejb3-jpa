package co.adun.mvnejb3jpa.web.model;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.web.model.PageModel;

@Component
public class CommentValueModel implements PageModel {
	private Long id;
	private String text;
	private DateValueModel date;
	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateValueModel getDate() {
		return date;
	}

	public void setDate(DateValueModel date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}