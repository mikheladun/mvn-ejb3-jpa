package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.web.model.PageModel;

@Component
public class CommentPageModel implements PageModel {

	List<CommentValueModel> comments;
	List<LtLeadComment> ltLeadComments;

	public List<LtLeadComment> getLtLeadComments() {
		return ltLeadComments;
	}
	public void setLtLeadComments(List<LtLeadComment> ltLeadComments) {
		this.ltLeadComments = ltLeadComments;
	}

	public List<CommentValueModel> getComments() {
		return comments;
	}
	public void setComments(List<CommentValueModel> comments) {
		this.comments = comments;
	}

}
