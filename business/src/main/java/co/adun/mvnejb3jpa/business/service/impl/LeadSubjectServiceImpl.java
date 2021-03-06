package co.adun.mvnejb3jpa.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadSubjectService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/LeadSubjectService", beanInterface = LeadSubjectService.class)
public class LeadSubjectServiceImpl implements LeadSubjectService {
	
	private static final Logger log = Logger.getLogger(LeadSubjectServiceImpl.class.getName());
	
	@Inject
	private LtLeadSubjectEao eao;
	
	@Inject
	UserService userService;
	
	@Inject
	TimerService timerService;
	
	@Override
	public LtLeadSubject save(LtLeadSubject ltLeadSubject) throws BusinessException {
		Date createDate = timerService.getSystemTime();
		Date modifiedDate = timerService.getSystemTime();
		LtUser createUser = userService.getSystemUser();
		LtUser modifiedUser = userService.getSystemUser();
		
		UserRoleCode userRoleCode = new UserRoleCode();
		userRoleCode.setId(100L);
		createUser.setUserRoleCode(userRoleCode);
		modifiedUser.setUserRoleCode(userRoleCode);
		
		ltLeadSubject.setCreateDate(createDate);
		ltLeadSubject.setModifiedDate(modifiedDate);
		ltLeadSubject.setLtUserByCreateBy(createUser);
		ltLeadSubject.setLtUserByModifiedBy(modifiedUser);
		
		if (ltLeadSubject.getId() == null) {
			ltLeadSubject = eao.save(ltLeadSubject);
		} else {
			ltLeadSubject = eao.update(ltLeadSubject);
		}
		
		return ltLeadSubject;
	}
	
	@Override
	public LtLeadSubject getLtLeadSubject(Long leadId, Long subjectId) throws BusinessException {
		// get the list of the users by supervisor role
		List<CustomFilterField> filters = new ArrayList<CustomFilterField>();
		CustomFilterField ltLeadFilter = new CustomFilterField("ltLeadId", leadId);
		CustomFilterField ltSubjectFilter = new CustomFilterField("ltSubjectId", subjectId);
		filters.add(ltLeadFilter);
		filters.add(ltSubjectFilter);
		List<LtLeadSubject> ltLeadSubjects = eao.findAllByRangeWithFilter(0, 0, filters);
		LtLeadSubject ltLeadSubject = (LtLeadSubject) ltLeadSubjects.iterator().next();
		
		return ltLeadSubject;
	}
	
	@Override
	public List<LtLeadSubject> save(List<LtLeadSubject> ltLeadSubjects) throws BusinessException {
		for (LtLeadSubject ltLeadSubject : ltLeadSubjects) {
			save(ltLeadSubject);
		}
		return ltLeadSubjects;
	}
	
}
