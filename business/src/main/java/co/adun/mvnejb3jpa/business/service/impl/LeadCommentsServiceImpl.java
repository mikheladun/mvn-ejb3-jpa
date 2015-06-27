package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadCommentsService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadCommentEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/LeadCommentsService", beanInterface = LeadCommentsService.class)
public class LeadCommentsServiceImpl implements LeadCommentsService {

	private static final Logger logger = Logger.getLogger(LeadCommentsServiceImpl.class.getName());

	@Inject
	private LtLeadEao leadEao;

	@Inject
	private LtLeadCommentEao leadCommentEao;

	@Inject
	private LtUserEao userEao;

	@Inject
	private TimerService timerService;

	@Inject
	private UserService userService;

	@Override
	public void deleteComments(LtLead detached) throws BusinessException {
		LtLead entity = leadEao.findById(detached.getId());
		Set<LtLeadComment> comments = entity.getLtLeadComments();
		for (LtLeadComment comment : comments) {
			comment.setLtLead(null);
		}
		comments.clear();
		entity.setLtLeadComments(comments);
		entity.setModifiedDate(timerService.getSystemTime());
		leadEao.saveOrUpdate(entity);
	}

	@Override
	public void saveComments(List<LtLeadComment> detachedComments) throws BusinessException {
		Date createDate = timerService.getSystemTime();

		LtLead ltLead = detachedComments.iterator().next().getLtLead();
		ltLead = leadEao.findById(ltLead.getId());
		Set<LtLeadComment> entities = ltLead.getLtLeadComments();
		for (LtLeadComment entity : entities) {
			entity.setLtLead(null);
		}
		entities.clear();

		for (LtLeadComment detached : detachedComments) {
			LtLeadComment entity = null;
			// existing comment
			if (detached.getId() != null) {
				entity = leadCommentEao.findById(detached.getId());
				// check to see if the comments were updated
				if (!detached.getLeadComment().equals(entity.getLeadComment())) {
					entity.setLeadComment(detached.getLeadComment());
					entity.setLtUserByModifiedBy(userEao.findById(detached.getLtUserByModifiedBy().getId()));
					entity.setModifiedDate(detached.getModifiedDate());
				}
			}
			else {
				entity = detached;
				// new comment
				entity.setLtUserByModifiedBy(userEao.findById(detached.getLtUserByModifiedBy().getId()));
				entity.setModifiedDate(detached.getModifiedDate());
				entity.setCreateDate(createDate);
				entity.setLeadComment(detached.getLeadComment());
			}

			entity.setLtLead(ltLead);
			entities.add(entity);
		}

		ltLead.setLtLeadComments(entities);
		leadEao.saveOrUpdate(ltLead);
	}

	@Override
	public void updateComments(LtLeadComment detached) throws BusinessException {

		Date modifiedDate = timerService.getSystemTime();
		LtUser modifiedUser = userService.getSystemUser();

		LtUser createUser = userService.getSystemUser();
		Date createDate = timerService.getSystemTime();
		LtLeadComment entity = detached;
		// existing comment
		if (detached.getId() != null) {
			entity = leadCommentEao.findById(detached.getId());
			entity.setLeadComment(detached.getLeadComment());
			entity.setLtUserByModifiedBy(modifiedUser);
			entity.setModifiedDate(modifiedDate);
		}
		leadCommentEao.saveOrUpdate(entity);
	}
}