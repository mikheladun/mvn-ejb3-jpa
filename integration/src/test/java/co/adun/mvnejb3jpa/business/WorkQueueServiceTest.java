package co.adun.mvnejb3jpa.business;

import java.util.Calendar;
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
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.business.service.WorkQueueService;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WorkQueueServiceTest {

    @Inject
    private WorkQueueService service;

    @Inject
    private UserService userService;

    @Test
    public void getWorkQueueItemsForAnalyst() {

	try {
	    LtUser analyst = userService.getCurrentUser("Analyst");

	    List<LtLeadSubject> result = service.getWorkQueueItems(analyst);
	    for (LtLeadSubject item : result) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(item.getLtSubject().getBirthDate());

		StringBuffer cocSB = new StringBuffer();
		Set<LtSubjectCitizenshipCountry> cocSet = item.getLtSubject().getLtSubjectCitizenshipCountries();
		for (LtSubjectCitizenshipCountry coc : cocSet) {
		    cocSB.append(coc.getCountryCode().getDescription() + ",");
		}

		StringBuffer assoLeadsSB = new StringBuffer();
		Set<LtAssociatedLead> assoLeadSet = item.getLtLead().getLtAssociatedLeadsForLtAssociatedLeadId();
		for (LtAssociatedLead assoLead : assoLeadSet) {
		    assoLeadsSB.append(assoLead.getLtLeadByLtAssociatedLeadId().getId() + ",");
		}

		System.out.println("SubjectName= " + item.getLtSubject().getFirstname() + " " + item.getLtSubject().getLastname() + " Gender="
			+ item.getLtSubject().getGenderCode().getAbbreviation().substring(0, 1) + " DOB=" + cal.get(Calendar.MONTH) + "/"
			+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR) + " COB="
			+ item.getLtSubject().getCountryCode().getDescription() + " COC="
			+ cocSB.toString().substring(0, cocSB.toString().length() - 1) + " COA="
			+ item.getLtSubject().getClassAdmissionCode().getAbbreviation() + " LeadType="
			+ item.getLtLead().getLeadTypeCode().getDescription() + " AssoLeads=" + assoLeadsSB.toString() + " Status="
			+ item.getLtLead().getStatusCode().getDescription());
	    }

	} catch (BusinessException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
	return;
    }

    @Test
    public void getWorkQueueItemsForSrAnalyst() {

	try {
	    LtUser sranalyst = userService.getCurrentUser("Supervisor");

	    List<LtLeadSubject> result = service.getWorkQueueItems(sranalyst);

	    System.out.println("size: " + result.size());

	    Assert.assertTrue(!result.isEmpty());

	} catch (BusinessException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
	return;
    }
    
    
}
