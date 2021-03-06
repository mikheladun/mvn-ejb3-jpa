package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.EntryService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.ClassAdmissionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.PortEntryCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.PortEntryCode;

@Component
@Stateless
@EJB(name = "java:global/EntryService", beanInterface = EntryService.class)
public class EntryServiceImpl implements EntryService {

	private static final Logger logger = Logger.getLogger(LeadServiceImpl.class.getName());

	@Inject
	private LtLeadEao leadEao;

	@Inject
	private LtSubjectEao subjectEao;

	@Inject
	private UserService userService;

	@Inject
	private ClassAdmissionCodeEao classAdmCodes;

	@Inject
	private PortEntryCodeEao portOfEntryCodes;

	@Inject
	private TimerService timerService;

	@Override
	public LtSubject getEntryInformation(LtLead ltLead) throws BusinessException {
		Long leadid = ltLead.getId();

		LtLead ltlead = leadEao.findById(leadid);
		LtSubject ltSubject = ltlead.getLtSubject();

		return ltSubject;
	}

	@Override
	public LtSubject getEntryInformation(LtSubject ltSubject) throws BusinessException {
		Long subjectid = ltSubject.getId();
		LtSubject ltsubject = subjectEao.findById(subjectid);
		return ltsubject;
	}

	@Override
	public void save(LtSubject detached) throws BusinessException {
		LtSubject entity = subjectEao.findById(detached.getId());

		LtUser modifiedBy = userService.getSystemUser();
		Date modifiedDate = timerService.getSystemTime();
		if (entity.getLtUserByCreateBy() == null) {
			entity.setLtUserByCreateBy(userService.getSystemUser());
		}
		if (entity.getCreateDate() == null) {
			entity.setCreateDate(timerService.getSystemTime());
		}
		entity.setModifiedDate(modifiedDate);
		entity.setLtUserByModifiedBy(modifiedBy);

		PortEntryCode poe = detached.getPortEntryCode();
		if(poe != null){
			poe = portOfEntryCodes.findById(poe.getId());
		}
		entity.setPortEntryCode(poe);

		ClassAdmissionCode coa = detached.getClassAdmissionCode();
		if(coa != null) {
			coa = classAdmCodes.findById(coa.getId());
		}
		entity.setClassAdmissionCode(coa);
		
		entity.setEntryDate(detached.getEntryDate());
		entity.setAdmitUntilDate(detached.getAdmitUntilDate());
		entity.setAdjustedAdmitUntilDate(detached.getAdjustedAdmitUntilDate());
		entity.setDepartureDate(detached.getDepartureDate());
		entity.setDurationStatus(detached.getDurationStatus());

		subjectEao.saveOrUpdate(entity);
		detached.setId(entity.getId());
	}

}
