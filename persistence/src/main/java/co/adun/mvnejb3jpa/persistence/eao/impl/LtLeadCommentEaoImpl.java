package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtLeadCommentEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Ram Mahajan
 * 
 */
@Component
@Stateless
@Transactional
public class LtLeadCommentEaoImpl extends BaseEaoImpl<LtLeadComment> implements LtLeadCommentEao {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LtLeadCommentEaoImpl.class.getName());

	@Override
	public Class<LtLeadComment> getEntityClass() {
		return LtLeadComment.class;
	}

	@Override
	public List<LtLeadComment> getComments(LtLead ltLead) {
		List<LtLeadComment> commentList = this.findByProperty("ltLead.id", ltLead.getId());
		for(LtLeadComment comment : commentList) {
			Hibernate.initialize(comment.getLtUserByCreateBy());
			Hibernate.initialize(comment.getLtUserByModifiedBy());
		}
		return commentList;
	}
}
