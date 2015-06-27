package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.DispositionService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.ActionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.DisposCloseReasonCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.DisposCloseSystemCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.eao.StatusCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.ActionCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseReasonCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseSystemCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadHistory;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/DispositionService", beanInterface = DispositionService.class)
public class DispositionServiceImpl implements DispositionService {

	private static final Logger logger = Logger.getLogger(DispositionServiceImpl.class.getName());

	@Inject
	private LtLeadEao leadEao;

	@Inject
	private LtUserEao userEao;

	@Inject
	private StatusCodeEao statusCodeEao;
	
	@Inject
	private ActionCodeEao actionCodeEao;

	@Inject
	private DisposCloseReasonCodeEao disposCloseReasonCodeEao;

	@Inject
	private DisposCloseSystemCodeEao disposCloseSystemCodeEao;

	@Inject
	private TimerService timerService;

	@Inject
	private UserService userService;


	@Override
    public void saveDisposition(LtLead detached) throws BusinessException {
		LtLead entity = leadEao.findById(detached.getId());
		Date modifiedDate = timerService.getSystemTime();
		LtUser modifiedUser = userService.getSystemUser();

		StatusCode statusCode = statusCodeEao.findById(detached.getStatusCode().getId());
		entity.setStatusCode(statusCode);

		if(detached.getDisposCloseReasonCode() != null && detached.getDisposCloseReasonCode().getId() != null) {
			DisposCloseReasonCode disposCloseReasonCode = disposCloseReasonCodeEao.findById(detached.getDisposCloseReasonCode().getId());
			entity.setDisposCloseReasonCode(disposCloseReasonCode);
		}

		if(detached.getDisposCloseSystemCode() != null && detached.getDisposCloseSystemCode().getId() != null) {
			DisposCloseSystemCode disposCloseSystemCode = disposCloseSystemCodeEao.findById(detached.getDisposCloseSystemCode().getId());
			entity.setDisposCloseSystemCode(disposCloseSystemCode);
		}

		//TODO save Call-up Date, Details, Analyst, Supervisor

		LtUser user = detached.getLtUserByLtAssignToUserId();
		if(entity.getLtUserByLtAssignToUserId() == null || 
				(detached.getLtUserByLtAssignToUserId() != null && 
				(entity.getLtUserByLtAssignToUserId().getId() != detached.getLtUserByLtAssignToUserId().getId()))) {

			user = userEao.findById(user.getId());
			entity.setLtUserByLtAssignToUserId(user);
		}

		entity.setCallUpDate(detached.getCallUpDate());
		entity.setModifiedDate(modifiedDate);
		entity.setLtUserByModifiedBy(modifiedUser);
		
	//Log action to LeadHistory	- kalyan
		
	
		LtLeadHistory ltLeadHistory = new LtLeadHistory();
		ltLeadHistory.setLtLead(entity);
		Long status_code = entity.getStatusCode().getId();
		ActionCode actionCode = null;
		actionCode = actionCodeEao.findById(status_code); // Sent for Review

		if (status_code.equals(new Long(102L)))
			actionCode = actionCodeEao.findById(103L); // Opened
		
		if (status_code.equals(new Long(103L)))
			actionCode = actionCodeEao.findById(104L); // Held
		
		if (status_code.equals(new Long(104L)))
			actionCode = actionCodeEao.findById(105L); // Sent for Review
		
		if (status_code.equals(new Long(106L)))
			actionCode = actionCodeEao.findById(106L); // Set Pending
		
		if (status_code.equals(new Long(108L)))
			actionCode = actionCodeEao.findById(108L); // Closed
		
		ltLeadHistory.setActionCode(actionCode);
		
		ltLeadHistory.setLtUserByCreateBy(modifiedUser);
		ltLeadHistory.setLtUserByModifiedBy(modifiedUser);
		ltLeadHistory.setTakenBy(modifiedUser);
		ltLeadHistory.setTakenDate(modifiedDate);
		ltLeadHistory.setCreateDate(modifiedDate);
		ltLeadHistory.setModifiedDate(modifiedDate);
		String actionedby = modifiedUser.getFirstname() + " " + modifiedUser.getLastname();
		String detail = actionCode.getDescription() + " by " + actionedby;
		ltLeadHistory.setDetail(detail);

		
		Set<LtLeadHistory> leadHistories = entity.getLtLeadHistories();
		if (null == leadHistories)
			leadHistories = new HashSet<LtLeadHistory>(0);

		leadHistories.add(ltLeadHistory);
		
		
		
	//end 
		
				
		leadEao.saveOrUpdate(entity);
    }

}