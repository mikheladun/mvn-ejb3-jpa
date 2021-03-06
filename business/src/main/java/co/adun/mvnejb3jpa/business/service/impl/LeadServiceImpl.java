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
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.ActionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.ContactTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.CountryCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberSourceEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadCommentEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadHistoryEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectCitizenshipCountryEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.eao.NumberTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.RelationshipCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.SourceCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.SpecialProjectCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.StatusCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.ActionCode;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.ContactTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedLead;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadHistory;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSpecialProject;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/LeadService", beanInterface = LeadService.class)
public class LeadServiceImpl implements LeadService {

	private static final Logger logger = Logger.getLogger(LeadServiceImpl.class.getName());

	@Inject
	private LtLeadEao eao;

	@Inject
	private LtUserEao userEao;

	@Inject
	private LtLeadCommentEao commentEao;

	@Inject
	private LtSubjectEao subjectEao;

	@Inject
	private SpecialProjectCodeEao specialProjectCodeEao;

	@Inject
	private ActionCodeEao actionCodeEao;

	@Inject
	private CountryCodeEao countryCodeEao;

	@Inject
	private ContactTypeCodeEao contactTypeCodeEao;

	@Inject
	private LtSubjectCitizenshipCountryEao subjectCitizenshipCountryEao;

	@Inject
	private TimerService timerService;

	@Inject
	private UserService userService;

	@Inject
	StatusCodeEao statusCodeEao;

	@Inject
	NumberTypeCodeEao numberTypeCodeEao;

	@Inject
	SourceCodeEao sourceCodeEao;

	@Inject
	LtUserEao ltUserEAO;

	@Inject
	LtIdentifyingNumberEao numberEao;

	@Inject
	LtIdentifyingNumberSourceEao sourceEao;

	@Inject
	private LtLeadIdentifyingNumberEao leadIdentEao;
	
	@Inject
	private LtLeadHistoryEao leadHistoryEao;

	@Inject
	private RelationshipCodeEao relationshipCodeEao;
	
	@Override
	public LtLead getLead(Long id) throws BusinessException {
		return eao.findByIdDeep(id);
//		return eao.findById(id);
	}

	@Override
	public LtLead save(LtLead ltLead) throws BusinessException {
		ltLead = preSave(ltLead);
		System.out.println("@@@ size of comments = " + ltLead.getLtLeadComments().size());
		eao.saveOrUpdate(ltLead);

		return ltLead;
	}

	@Override
	public List<LtLead> save(List<LtLead> ltLeads) throws BusinessException {
		for (LtLead ltLead : ltLeads) {
			preSave(ltLead);
		}

		eao.saveOrUpdate(ltLeads);

		return ltLeads;
	}

	public LtLead preSave(LtLead ltLead) throws BusinessException {
		Date createDate = ltLead.getCreateDate();
		if (createDate == null) {
			createDate = timerService.getSystemTime();
		}
		Date modifiedDate = createDate;
		LtUser createUser = ltLead.getLtUserByCreateBy();
		if (createUser == null) {
			createUser = userService.getSystemUser();
		}
		LtUser modifiedUser = ltLead.getLtUserByModifiedBy();
		if (modifiedUser == null) {
			modifiedUser = userService.getSystemUser();
		}

		MissionCode missionCode = ltLead.getMissionCode();
		if (missionCode != null) {
			String code = missionCode.getAbbreviation();
			if ("NONE".equalsIgnoreCase(code)) {
				ltLead.setMissionCode(null);
			}
		}

		ltLead.setCreateDate(createDate);
		ltLead.setModifiedDate(modifiedDate);
		ltLead.setLtUserByCreateBy(createUser);
		ltLead.setLtUserByModifiedBy(modifiedUser);

		LtSubject detachedSubject = ltLead.getLtSubject();
		Long ltSubjectId = detachedSubject.getId();
		LtSubject entitySubject = null;
		Set<LtSubjectCitizenshipCountry> entityCocSet = new HashSet<LtSubjectCitizenshipCountry>();
		Set<LtAssociatedSubject> entityAssociateSet = new HashSet<LtAssociatedSubject>();
		if (detachedSubject.getId() != null) {
			entitySubject = subjectEao.findById(ltSubjectId);
			entityCocSet = entitySubject.getLtSubjectCitizenshipCountries();
			for(LtSubjectCitizenshipCountry coc : entityCocSet) {
				coc.setLtSubject(null);
			}
			entityCocSet.clear();

			entityAssociateSet = entitySubject.getLtAssociatedSubjects();
//			for(LtAssociatedSubject associate : entityAssociateSet) {
//				associate.setLtSubject(null);
//			}
//			entityAssociateSet.clear();
		}
		else {
			entitySubject = ltLead.getLtSubject();
			entitySubject.setCreateDate(createDate);
			entitySubject.setLtUserByCreateBy(createUser);
		}
		entitySubject.setModifiedDate(modifiedDate);
		entitySubject.setLtUserByModifiedBy(modifiedUser);

		CountryCode countryCode = detachedSubject.getCountryCode();
		if (countryCode != null && countryCode.getId() != null) {
			countryCode = countryCodeEao.findById(countryCode.getId());
		}
		entitySubject.setCountryCode(countryCode);
		entitySubject.setClassAdmissionCode(detachedSubject.getClassAdmissionCode());
		entitySubject.setBirthDate(detachedSubject.getBirthDate());
		entitySubject.setEntryDate(detachedSubject.getEntryDate());

		for (LtSubjectCitizenshipCountry detachedCoc : detachedSubject.getLtSubjectCitizenshipCountries()) {
			CompositeId compositeId = detachedCoc.getId();
			if(compositeId == null) {
				compositeId = new CompositeId();
			}
			compositeId.setId(entitySubject.getId());
			compositeId.setCompId(detachedCoc.getCountryCode().getId());

			LtSubjectCitizenshipCountry entityCoc = subjectCitizenshipCountryEao.findById(compositeId);
			if(entityCoc == null) {
				entityCoc = detachedCoc;
				entityCoc.setId(compositeId);
				entityCoc.setCreateDate(createDate);
				entityCoc.setLtUserByCreateBy(createUser);
				entityCoc.setCountryCode(countryCodeEao.findById(detachedCoc.getCountryCode().getId()));
				entityCoc.setLtSubject(entitySubject);
			}
			entityCoc.setModifiedDate(modifiedDate);
			entityCoc.setLtUserByModifiedBy(modifiedUser);
			entityCocSet.add(entityCoc);
		}
		entitySubject.setLtSubjectCitizenshipCountries(entityCocSet);
		ltLead.setLtSubject(entitySubject);

		for (LtLeadSource ltLeadSource : ltLead.getLtLeadSources()) {
			ltLeadSource.setContactTypeCode(contactTypeCodeEao.findById(ltLeadSource.getContactTypeCode().getId()));
			ltLeadSource.setCreateDate(createDate);
			ltLeadSource.setModifiedDate(modifiedDate);
			ltLeadSource.setLtUserByCreateBy(createUser);
			ltLeadSource.setLtUserByModifiedBy(modifiedUser);

			// remove formatting from Source Contact Phone
			ContactTypeCode contactTypeCode = ltLeadSource.getContactTypeCode();
			if (contactTypeCode.getId() == 100) {
				String contact = ltLeadSource.getContact();
				ltLeadSource.setContact(contact.replaceAll("[^0-9]", ""));
			}
		}

		for (LtLeadSpecialProject ltLeadSpecialProject : ltLead.getLtLeadSpecialProjects()) {
			ltLeadSpecialProject.setLtLead(ltLead);
			ltLeadSpecialProject.setSpecialProjectCode(specialProjectCodeEao.findById(ltLeadSpecialProject.getSpecialProjectCode().getId()));
			CompositeId id = ltLeadSpecialProject.getId();
			if (id == null) {
				ltLeadSpecialProject.setId(new CompositeId());
			}
			String comments = ltLeadSpecialProject.getLeadSpecialProjectComment();
			if (comments == null || "".equals(comments.trim())) {
				ltLeadSpecialProject.setLeadSpecialProjectComment(" ");
			}
			ltLeadSpecialProject.setCreateDate(createDate);
			ltLeadSpecialProject.setModifiedDate(modifiedDate);
			ltLeadSpecialProject.setLtUserByCreateBy(createUser);
			ltLeadSpecialProject.setLtUserByModifiedBy(modifiedUser);
		}

		for (LtLeadComment ltLeadComment : ltLead.getLtLeadComments()) {
			ltLeadComment.setLtLead(ltLead);
			ltLeadComment.setCreateDate(createDate);
			ltLeadComment.setModifiedDate(modifiedDate);
			ltLeadComment.setLtUserByCreateBy(createUser);
			ltLeadComment.setLtUserByModifiedBy(modifiedUser);
		}

		for (LtLeadSubject ltLeadSubject : ltLead.getLtLeadSubjects()) {
			ltLeadSubject.setLtLead(ltLead);
			ltLeadSubject.setLtSubject(entitySubject);
			CompositeId id = ltLeadSubject.getId();
			if (id == null) {
				ltLeadSubject.setId(new CompositeId());
			}
			ltLeadSubject.setCreateDate(createDate);
			ltLeadSubject.setModifiedDate(modifiedDate);
			ltLeadSubject.setLtUserByCreateBy(createUser);
			ltLeadSubject.setLtUserByModifiedBy(modifiedUser);
		}

		// Associated Leads
		for (LtAssociatedLead detachedAssociatedLead : ltLead.getLtAssociatedLeadsForLtLeadId()) {
			CompositeId compositeId = detachedAssociatedLead.getId();
			if(compositeId == null) {
				compositeId = new CompositeId();
			}
			compositeId.setId(detachedAssociatedLead.getLtLeadByLtAssociatedLeadId().getId());
			compositeId.setCompId(ltLead.getId());
			detachedAssociatedLead.setId(compositeId);

			detachedAssociatedLead.setLtLeadByLtLeadId(ltLead);
			LtLead associateLead = detachedAssociatedLead.getLtLeadByLtAssociatedLeadId();
			LtSubject ltSubject = associateLead.getLtSubject();
			if(ltSubject.getId() != null) {
				ltSubject = subjectEao.findById(ltSubject.getId());
				associateLead.setLtSubject(ltSubject);
			}
			associateLead.setLeadGeneratedFromCode(ltLead.getLeadGeneratedFromCode());
			associateLead.setLeadTypeCode(ltLead.getLeadTypeCode());
			associateLead.setStatusCode(ltLead.getStatusCode());
			associateLead.setCreateDate(createDate);
			associateLead.setModifiedDate(modifiedDate);
			associateLead.setLtUserByCreateBy(createUser);
			associateLead.setLtUserByModifiedBy(modifiedUser);

			detachedAssociatedLead.setCreateDate(createDate);
			detachedAssociatedLead.setModifiedDate(modifiedDate);
			detachedAssociatedLead.setLtUserByCreateBy(createUser);
			detachedAssociatedLead.setLtUserByModifiedBy(modifiedUser);
		}

		//Associated Subjects
		for (LtAssociatedSubject detachedAssociatedSubject : detachedSubject.getLtAssociatedSubjects()) {
			CompositeId compositeId = detachedAssociatedSubject.getId();
			if (compositeId == null) {
				compositeId = new CompositeId();
			}
			compositeId.setId(detachedAssociatedSubject.getLtSubjectAssociate().getId());
			compositeId.setCompId(entitySubject.getId());
			detachedAssociatedSubject.setId(compositeId);

			detachedAssociatedSubject.setLtSubject(entitySubject);
			detachedAssociatedSubject.setRelationshipCode(relationshipCodeEao.findById(detachedAssociatedSubject.getRelationshipCode().getId()));
			LtSubject associateSubject = detachedAssociatedSubject.getLtSubjectAssociate();
			if(associateSubject.getId() != null) {
				associateSubject = subjectEao.findById(associateSubject.getId());
				detachedAssociatedSubject.setLtSubjectAssociate(associateSubject);
			}
			associateSubject.setCreateDate(createDate);
			associateSubject.setModifiedDate(modifiedDate);
			associateSubject.setLtUserByCreateBy(createUser);
			associateSubject.setLtUserByModifiedBy(modifiedUser);

			detachedAssociatedSubject.setCreateDate(createDate);
			detachedAssociatedSubject.setModifiedDate(modifiedDate);
			detachedAssociatedSubject.setCreateBy(createUser);
			detachedAssociatedSubject.setModifiedBy(modifiedUser);

			entityAssociateSet.add(detachedAssociatedSubject);
		}
		entitySubject.setLtAssociatedSubjects(entityAssociateSet);

		// assigned To user
		if (ltLead.getLtUserByLtAssignToUserId() != null) {
			ltLead.setLtUserByLtAssignToUserId(userEao.findById(ltLead.getLtUserByLtAssignToUserId().getId()));
		}

		Long ltLeadId = (Long) ltLead.getId();
		if (ltLeadId == null) {
			LtLeadHistory ltLeadHistory = new LtLeadHistory();
			ltLeadHistory.setLtLead(ltLead);
			// TODO use constants
			ActionCode actionCode = null;
			actionCode = actionCodeEao.findById(101L); // Initiated
			if (ltLead.getLtUserByLtAssignToUserId() != null) {
				actionCode = actionCodeEao.findById(102L); // Assigned
			}
			ltLeadHistory.setActionCode(actionCode);
			ltLeadHistory.setTakenDate(createDate);
			String name = modifiedUser.getFirstname() + " " + modifiedUser.getLastname();
			ltLeadHistory.setTakenBy(modifiedUser);
			ltLeadHistory.setDetail(actionCode.getDescription() + " by " + name);
			ltLeadHistory.setCreateDate(createDate);
			ltLeadHistory.setModifiedDate(modifiedDate);
			ltLeadHistory.setLtUserByCreateBy(createUser);
			ltLeadHistory.setLtUserByModifiedBy(modifiedUser);

			Set<LtLeadHistory> ltLeadHistories = new HashSet<LtLeadHistory>();
			ltLeadHistories.add(ltLeadHistory);
			ltLead.setLtLeadHistories(ltLeadHistories);
		}

		return ltLead;
	}

	@Override
	public List<LtLeadComment> getComments(LtLead ltLead) throws BusinessException {
		logger.info("Inside getComments() Lead id = " + ltLead.getId());
		return commentEao.getComments(ltLead);
	}

	/**
	 * @author - kalyan
	 */
	@Override
	public void writeAuditTrail(LtLead ltlead, Long statcode) throws BusinessException {
		LtLeadHistory ltLeadHistory = new LtLeadHistory();
		LtLead ltLead = eao.findById(ltlead.getId());
		ltLeadHistory.setLtLead(ltLead);
		
		ActionCode actionCode = null;
		
		if (statcode.equals(new Long(102L)))
			actionCode = actionCodeEao.findById(103L); // Opened
		
		if (statcode.equals(new Long(103L)))
			actionCode = actionCodeEao.findById(104L); // Held
		
		if (statcode.equals(new Long(104L)))
			actionCode = actionCodeEao.findById(105L); // Sent for Review
		
		if (statcode.equals(new Long(106L)))
			actionCode = actionCodeEao.findById(106L); // Set Pending
		
		if (statcode.equals(new Long(108L)))
			actionCode = actionCodeEao.findById(108L); // Closed
		
		ltLeadHistory.setActionCode(actionCode);
		LtUser createBy = null, modifiedBy = null;
		createBy = ltLead.getLtUserByModifiedBy();
		modifiedBy = ltLead.getLtUserByModifiedBy();
		
		if (null == createBy) createBy = userService.getSystemUser();
		if (null == modifiedBy) modifiedBy = userService.getSystemUser();

		Date now = new Date(System.currentTimeMillis());
		ltLeadHistory.setLtUserByCreateBy(createBy);
		ltLeadHistory.setLtUserByModifiedBy(modifiedBy);
		ltLeadHistory.setTakenBy(modifiedBy);
		ltLeadHistory.setTakenDate(now);
		ltLeadHistory.setCreateDate(now);
		ltLeadHistory.setModifiedDate(now);
		String actionedby = modifiedBy.getFirstname() + " " + modifiedBy.getLastname();
		String detail = actionCode.getDescription() + " by " + actionedby;
		ltLeadHistory.setDetail(detail);

		//leadHistoryEao.save(ltLeadHistory);
		
		Set<LtLeadHistory> leadHistories = ltLead.getLtLeadHistories();
		if (null == leadHistories)
			leadHistories = new HashSet<LtLeadHistory>(0);

		leadHistories.add(ltLeadHistory);
		
	}

	@Override
	public void setLeadStatusById(Long leadid, Long statcode) throws BusinessException {
		StatusCode statusCode = statusCodeEao.findById(statcode);
		LtLead ltLead = eao.findById(leadid);
		ltLead.setStatusCode(statusCode);
		writeAuditTrail(ltLead, statcode);
		eao.saveOrUpdate(ltLead);
	}

	/**
	 * @author - kalyan
	 */

	@Override
	public void setLeadStatusByAbbrev(Long leadid, String abbrev) throws BusinessException {
		List<StatusCode> statusCodes = statusCodeEao.findByProperty("abbreviation", abbrev);
		LtLead ltLead = eao.findById(leadid);
		ltLead.setStatusCode(statusCodes.get(0));
		writeAuditTrail(ltLead, statusCodes.get(0).getId());
		eao.saveOrUpdate(ltLead);
	}

	/**
	 * @author - kalyan
	 */

	@Override
	public void setLeadStatusByDescription(Long leadid, String description) throws BusinessException {
		List<StatusCode> statusCodes = statusCodeEao.findByProperty("description", description);
		LtLead ltLead = eao.findById(leadid);
		ltLead.setStatusCode(statusCodes.get(0));
		writeAuditTrail(ltLead, statusCodes.get(0).getId());
		eao.saveOrUpdate(ltLead);
	}
}