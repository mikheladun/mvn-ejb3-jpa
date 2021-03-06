/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.business;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.SubjectAliasService;
import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.SourceCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAlias;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAliasSource;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.SourceCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class SubjectAliasTest {
	
	@Inject
	private LtSubjectEao subjectEao;
	
	@Inject
	private SubjectAliasService aliasService;
	
	@Inject
	SourceCodeEao sourceCodeEao;
	
	@Inject
	private UserService userService;
	
	long test_subject_id = 160L;
	
	@Test
	public void saveSubjectAlias(){
		System.out.println("Begin Test...");
		
		LtSubject ltSubject = subjectEao.findById(test_subject_id);
		
		LtSubjectAlias ltSubjectAlias = new LtSubjectAlias();
		
		ltSubjectAlias.setLastname("Doe");
		
		ltSubjectAlias.setFirstname("John");
		
		ltSubjectAlias.setLtSubject(ltSubject);
		
		LtUser user = null;
		
		try {
			user = userService.getSystemUser();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ltSubjectAlias.setCreateBy(user);
		ltSubjectAlias.setModifiedBy(user);
		
		ltSubjectAlias.setCreateDate(new Date(System.currentTimeMillis()));
		ltSubjectAlias.setModifiedDate(new Date(System.currentTimeMillis()));
			
		Long subjectAliasId = ltSubjectAlias.getId();
			
		LtSubjectAliasSource ltSubjectAliasSource = new LtSubjectAliasSource();
		
		CompositeId cmpid = new CompositeId();
		cmpid.setId(118L);
		cmpid.setCompId(subjectAliasId);
		ltSubjectAliasSource.setId(cmpid);
		
		SourceCode sourceCode = sourceCodeEao.findById(118L);
		ltSubjectAliasSource.setSourceCode(sourceCode);
		
		ltSubjectAliasSource.setLtSubjectAlias(ltSubjectAlias);
		
		ltSubjectAliasSource.setCreateBy(user);
		ltSubjectAliasSource.setModifiedBy(user);
		ltSubjectAliasSource.setCreateDate(new Date(System.currentTimeMillis()));
		ltSubjectAliasSource.setModifiedDate(new Date(System.currentTimeMillis()));
		
		Set<LtSubjectAliasSource> sourceset = ltSubjectAlias.getLtSubjectAliasSources();
		
		if ( null == sourceset ) sourceset = new HashSet<LtSubjectAliasSource>();
			
		sourceset.add(ltSubjectAliasSource);
		
		aliasService.save(ltSubject, ltSubjectAlias);
		
		System.out.println("Subject Firstname: " + ltSubject.getFirstname() + ", Lastname: " + ltSubject.getLastname());
		
		System.out.println("Aliases:");
		
		List<LtSubjectAlias> aliases = aliasService.getAliases(ltSubject);
		
		Iterator<LtSubjectAlias> iterator = aliases.iterator();
		
		while (iterator.hasNext()){
			LtSubjectAlias alias = iterator.next();
			System.out.println("alias firstname: " + alias.getFirstname() + ",");
			System.out.println(" lastname:" + alias.getLastname());
			
			Set<LtSubjectAliasSource> sources = alias.getLtSubjectAliasSources();
			Iterator<LtSubjectAliasSource> source_iterator = sources.iterator();
			
			while (source_iterator.hasNext()){
				LtSubjectAliasSource subjaliassrc = source_iterator.next();
				SourceCode sourcecode = subjaliassrc.getSourceCode();
				
				System.out.println("Source:" + sourcecode.getDescription());
			}
			
		}
		
		System.out.println("End Test.");
	}

}
