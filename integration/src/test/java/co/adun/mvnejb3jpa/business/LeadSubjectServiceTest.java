package co.adun.mvnejb3jpa.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.LeadSubjectService;
import co.adun.mvnejb3jpa.business.service.SupportDataService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.ContactTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseReasonCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseSystemCode;
import co.adun.mvnejb3jpa.persistence.entity.GenderCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadGeneratedFromCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSpecialProject;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectContact;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;
import co.adun.mvnejb3jpa.persistence.entity.SpecialProjectCode;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LeadSubjectServiceTest {
    @Inject
    private LeadSubjectService service;
    @Inject
    private LeadService leadService;
    @Inject
    private UserService userService;
    @Inject
    private SupportDataService supportDataService;

    @Test
    public void save() {

	try {

	    LtLead ltLead = new LtLead();

	    MissionCode missionCode = new MissionCode();
	    missionCode = supportDataService.getMissionCodes().iterator().next();
	    ltLead.setMissionCode(missionCode);

	    DisposCloseSystemCode disposCloseSystemCode = new DisposCloseSystemCode();
	    disposCloseSystemCode = supportDataService.getDisposCloseSystemCodes().iterator().next();
	    ltLead.setDisposCloseSystemCode(disposCloseSystemCode);

	    LtUser ltUserByLtAssignToUserId = new LtUser();
	    ltUserByLtAssignToUserId = userService.getSystemUser();
	     ltLead.setLtUserByLtAssignToUserId(ltUserByLtAssignToUserId);

	    LeadTypeCode leadTypeCode = new LeadTypeCode();
	    leadTypeCode = supportDataService.getLeadTypeCodes().iterator().next();
	    ltLead.setLeadTypeCode(leadTypeCode);

	    LeadGeneratedFromCode leadGeneratedFromCode = new LeadGeneratedFromCode();
	    leadGeneratedFromCode = supportDataService.getLeadGenFrmCodes().iterator().next();
	    ltLead.setLeadGeneratedFromCode(leadGeneratedFromCode);

	    StatusCode statusCode = new StatusCode();
	    statusCode = supportDataService.getStatusCodes().iterator().next();
	    ltLead.setStatusCode(statusCode);

	    DisposCloseReasonCode disposCloseReasonCode = new DisposCloseReasonCode();
	    disposCloseReasonCode = supportDataService.getDisposCloseReasonCodes().iterator().next();
	    ltLead.setDisposCloseReasonCode(disposCloseReasonCode);

	    Set<LtLeadSpecialProject> ltLeadSpecialProjects = new HashSet<LtLeadSpecialProject>();
	    LtLeadSpecialProject ltLeadSpecialProject = new LtLeadSpecialProject();
	    ltLeadSpecialProject.setLtLead(ltLead);
	    SpecialProjectCode specialProjectCode = new SpecialProjectCode();
	    specialProjectCode = supportDataService.getSpecProjCodes().iterator().next();
	    ltLeadSpecialProject.setSpecialProjectCode(specialProjectCode);
	    ltLeadSpecialProjects.add(ltLeadSpecialProject);
	    ltLead.setLtLeadSpecialProjects(ltLeadSpecialProjects);

	    LtSubject ltSubject = new LtSubject();
	    ltSubject.setLastname("Doe");
	    ltSubject.setFirstname("John");
	    GenderCode genderCode = new GenderCode();
	    genderCode = supportDataService.getGenderCodes().iterator().next();
	    ltSubject.setGenderCode(genderCode);
	    ClassAdmissionCode classAdmissionCode = new ClassAdmissionCode();
	    classAdmissionCode = supportDataService.getClassAdmCodes().iterator().next();
	    ltSubject.setClassAdmissionCode(classAdmissionCode);
	    CountryCode countryCode = new CountryCode();
	    ltSubject.setCountryCode(countryCode);

	    LtSubjectContact ltSubjectContact = new LtSubjectContact();
	    ltSubjectContact.setLtSubject(ltSubject);

	    LtSubjectCitizenshipCountry coc1 = new LtSubjectCitizenshipCountry();
	    CountryCode countryCode1 = new CountryCode();
	    countryCode1 = supportDataService.getCountryCodes().get(0);
	    coc1.setCountryCode(countryCode1);
	    coc1.setLtSubject(ltSubject);
	    LtSubjectCitizenshipCountry coc2 = new LtSubjectCitizenshipCountry();
	    CountryCode countryCode2 = new CountryCode();
	    countryCode2 = supportDataService.getCountryCodes().get(1);
	    coc2.setCountryCode(countryCode2);
	    coc2.setLtSubject(ltSubject);
	    LtSubjectCitizenshipCountry[] cocArray = new LtSubjectCitizenshipCountry[] { coc1, coc2 };
	    Set<LtSubjectCitizenshipCountry> cocSet = new HashSet<LtSubjectCitizenshipCountry>();
	    cocSet.add(coc1);
	    cocSet.add(coc2);
	    ltSubject.setLtSubjectCitizenshipCountries(cocSet);
	    
	    CountryCode cob = new CountryCode();
	    cob = supportDataService.getCountryCodes().get(2);
	    ltSubject.setCountryCode(cob);

	    ltLead.setLtSubject(ltSubject);

	    LtLeadSource ltLeadSource = new LtLeadSource();
	    ltLeadSource.setName("Paul, Pierce");
	    ltLeadSource.setTitle("Agent");
	    ltLeadSource.setLtLead(ltLead);
	    ltLeadSource.setContact("@agent");
	    ContactTypeCode contactTypeCode = supportDataService.getContactTypeCodes().iterator().next();
	    ltLeadSource.setContactTypeCode(contactTypeCode);

	    Set<LtLeadSource> ltLeadSources = new HashSet<LtLeadSource>();
	    ltLeadSources.add(ltLeadSource);
	    ltLead.setLtLeadSources(ltLeadSources);

	    LtLeadComment ltLeadComment = new LtLeadComment();
	    ltLeadComment.setLeadComment("Lead comment lorem ipsum dolore");
	    ltLeadComment.setLtLead(ltLead);
	    LtLeadComment[] ltLeadCommentArray = new LtLeadComment[1];
	    ltLeadCommentArray[0] = ltLeadComment;
	    Set<LtLeadComment> ltLeadComments = new HashSet<LtLeadComment>();
	    ltLeadComments.add(ltLeadComment);
	    ltLead.setLtLeadComments(ltLeadComments);

	    List<LtLeadSubject> ltLeadSubjects = new ArrayList<LtLeadSubject>();
	    LtLeadSubject ltLeadSubject = new LtLeadSubject();
	    ltLeadSubject.setLtLead(ltLead);
	    ltLeadSubject.setLtSubject(ltLead.getLtSubject());
//	    ltLeadSubject.setLsid(System.currentTimeMillis());

	    ltLeadSubjects.add(ltLeadSubject);
	    ltLeadSubjects = service.save(ltLeadSubjects);

	    Serializable id = ltLeadSubjects.iterator().next().getLtLead().getId();
	    Assert.assertNotNull(id);

	} catch (BusinessException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
	return;
    }
}
