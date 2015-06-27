/**
 * @author - kalyan
 */

package co.adun.mvnejb3jpa.business;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LeadStatusUpdateTest {

	@Inject
	private LeadService leadService;

	// Test harness
	long leadid = 100000L;

	long statcode = 102L;
	String stat_abbrev = "PE";
	String stat_descr = "Closed";

	// Test cases

	@Test
	public void updateLeadStatusByCode() {
		// Java auto-boxing in effect !
		try {
			leadService.setLeadStatusById(leadid, statcode);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateLeadStatusByAbbrev() {
		try {
			leadService.setLeadStatusByAbbrev(leadid, stat_abbrev);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateStatusByDescr() {
		try {
			leadService.setLeadStatusByDescription(leadid, stat_descr);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
