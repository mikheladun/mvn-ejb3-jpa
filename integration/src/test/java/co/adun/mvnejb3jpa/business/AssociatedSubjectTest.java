package co.adun.mvnejb3jpa.business;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtAssociatedSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.RelationshipCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.RelationshipCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class AssociatedSubjectTest {

	@Inject
	private LtLeadEao leadEao;
	
	@Inject 
	private LtSubjectEao subjectEao;
	
	@Inject
	private LtAssociatedSubjectEao assocSubjectEao;
	
	@Inject
	private RelationshipCodeEao relationshipEao;
	
	@Inject
	private UserService userService;
	
	@Test
	
	public void saveAssociatedSubjects(){
		
		System.out.println("Test begins");
		
		LtLead ltLead = leadEao.findById(100000L);
		
		LtSubject ltSubject = ltLead.getLtSubject();
		
		//System.out.println("Lead ID: " + ltLead.getId() + " , Subject LSID: " + ltLead.getLtSubject().getLsid());
		
		LtAssociatedSubject ltAssociatedSubject = new LtAssociatedSubject();
		
	
		LtSubject mainSubject = subjectEao.findById(100L);
		
		LtSubject associatedSubject = subjectEao.findById(102L);
		
		CompositeId cmpid = new CompositeId();
		
		cmpid.setId(associatedSubject.getId());
		cmpid.setCompId(mainSubject.getId());
		
		ltAssociatedSubject.setId(cmpid);
		
		ltAssociatedSubject.setLtSubject(mainSubject);
		ltAssociatedSubject.setLtSubjectAssociate(associatedSubject);
		
		RelationshipCode relationshipCode = relationshipEao.findById(106L);
		ltAssociatedSubject.setRelationshipCode(relationshipCode);
		

		
		Date now = new Date(System.currentTimeMillis());
		LtUser createBy=null,modifiedBy=null;
		try {
			createBy = userService.getSystemUser();
			modifiedBy = userService.getSystemUser();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ltAssociatedSubject.setCreateBy(createBy);
		ltAssociatedSubject.setModifiedBy(modifiedBy);
		ltAssociatedSubject.setCreateDate(now);
		ltAssociatedSubject.setModifiedDate(now);
		
		Set<LtAssociatedSubject> associatedSubjects = ltSubject.getLtAssociatedSubjects();
		
		if (null == associatedSubjects) associatedSubjects = new HashSet<LtAssociatedSubject>(0);
		
		associatedSubjects.add(ltAssociatedSubject);
		
		ltSubject.setLtAssociatedSubjects(associatedSubjects);
		
		leadEao.save(ltLead);
		
		System.out.println("Test ends");
	}
	
	public void addAssocSubject(Long mainSubID, Long assocSubID, Long relcode){
		
		LtSubject associatedSubject = subjectEao.findById(assocSubID);
		
		LtAssociatedSubject ltAssociatedSubject = new LtAssociatedSubject();
		
		LtSubject mainSubject = subjectEao.findById(mainSubID);
				
		CompositeId cmpid = new CompositeId();
		
		cmpid.setId(associatedSubject.getId());
		cmpid.setCompId(mainSubject.getId());
		
		ltAssociatedSubject.setId(cmpid);
		
		ltAssociatedSubject.setLtSubject(mainSubject);
		ltAssociatedSubject.setLtSubjectAssociate(associatedSubject);
		
		RelationshipCode relationshipCode = relationshipEao.findById(relcode);
		ltAssociatedSubject.setRelationshipCode(relationshipCode);
	}
	
}
