package co.adun.mvnejb3jpa.business;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.BiographicInfoService;
import co.adun.mvnejb3jpa.business.service.LeadCommentsService;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LeadCommentsServiceTest {

	@Inject
	private TimerService timerService;

	@Inject
	private SubjectService subjectService;

	@Inject
	private LeadService leadService;

	@Inject
	private LeadCommentsService leadCommentsService;

	@Test
	public void addLeadComments() {
		LtLead lead;

		try {
			lead = leadService.getLead(100000L);
			List<LtLeadComment> comments = leadService.getComments(lead);
			LtLeadComment ltLeadComment = new LtLeadComment();
			ltLeadComment
					.setLeadComment("Newmcomments to an existing Lead comment lorem ipsum dolore");
			List<LtLeadComment> ltLeadComments = leadService.getComments(lead);
			ltLeadComment.setLtLead(lead);
			ltLeadComments.add(ltLeadComment);
			leadCommentsService.saveComments(ltLeadComments);

		} catch (BusinessException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void UpdateLeadComments() {
		LtLead lead;
	

		try {
			lead = leadService.getLead(100000L);
			List<LtLeadComment> comments = leadService.getComments(lead);
			for (LtLeadComment ltLeadComment : comments) {
				ltLeadComment
						.setLeadComment("Update18 lead comments to an existing Lead comment lorem ipsum dolore");
				ltLeadComment.setModifiedDate(new Date());
				// update only the first comment
				break;
			}
			
			leadCommentsService.saveComments(comments);

		} catch (BusinessException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void deleteLeadComments() {
		LtLead lead;
	

		try {
			lead = leadService.getLead(100008L);
			List<LtLeadComment> comments =  leadService.getComments(lead);
			    // remove 1st comment
				comments.remove(0);
				System.out.println("size of comments list = " + comments.size());
//				Set<LtLeadComment> comset = new HashSet<LtLeadComment>(comments);
//				lead.setLtLeadComments(comset);
//				leadService.save(lead);
				leadCommentsService.saveComments(comments);
			//leadCommentsService.saveComments(comments);

		} catch (BusinessException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void updatesSingleComment() {
		LtLead lead;

		try {
			lead = leadService.getLead(100000L);
			List<LtLeadComment> comments = leadService.getComments(lead);

			for (LtLeadComment ltLeadComment : comments) {
				// update 1st comment only
				ltLeadComment
						.setLeadComment("Update 1st lead comments to an existing Lead comment lorem ipsum dolore");
				leadCommentsService.updateComments(ltLeadComment);
				break;
			}

		} catch (BusinessException e) {
			Assert.fail(e.getMessage());
		}
	}
}
