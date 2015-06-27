package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/SubjectService", beanInterface = SubjectService.class)
public class SubjectServiceImpl implements SubjectService {
	
	private static final Logger log = Logger.getLogger(SubjectServiceImpl.class.getName());
	
	@Inject
	private LtSubjectEao eao;
	
	@Inject
	private LtLeadSubjectEao leadSubjectEao;
	
	@Inject
	UserService userService;
	
	@Inject
	TimerService timerService;
	
	@Override
	public LtSubject save(LtSubject ltSubject) throws BusinessException {
		Date createDate = timerService.getSystemTime();
		Date modifiedDate = timerService.getSystemTime();
		LtUser createUser = userService.getSystemUser();
		LtUser modifiedUser = userService.getSystemUser();
		
		UserRoleCode userRoleCode = new UserRoleCode();
		userRoleCode.setId(100L);
		createUser.setUserRoleCode(userRoleCode);
		modifiedUser.setUserRoleCode(userRoleCode);
		
		ltSubject.setCreateDate(createDate);
		ltSubject.setModifiedDate(modifiedDate);
		ltSubject.setLtUserByCreateBy(createUser);
		ltSubject.setLtUserByModifiedBy(modifiedUser);

		if (ltSubject.getId() == null) {
			ltSubject = eao.save(ltSubject);
		} else {
			eao.saveOrUpdate(ltSubject);
		}
		
		return ltSubject;
	}
	
	@Override
	public LtSubject getLtSubject(Long id, String lsid) throws BusinessException {
		return eao.findById(id);
	}
	
	@Override
	public List<LtSubject> findSubjectByName(String lastName, String firstName, String lsid) throws BusinessException {
		return eao.findSubjectByName(lastName, firstName, lsid);
	}
	
	@Override
	public List<LtSubject> findSubjectByLsidAndName(final LtSubject ltSubject) throws BusinessException {
		return eao.findSubjectByLsidAndName(ltSubject);
	}
	
	@Override
	public List<LtSubject> findSubjectByCriteria(final LtSubject ltSubject) throws BusinessException {
		return eao.findSubjectByCriteria(ltSubject);
	}
	
	@Override
	public List<LtSubject> findSubjectByLeadId(final LtSubject ltSubject) throws BusinessException {
		return eao.findSubjectByLeadId(ltSubject);
	}
	
	@Override
	public void delete(LtSubject ltSubject) throws BusinessException {
		eao.delete(ltSubject);
	}
	
}
