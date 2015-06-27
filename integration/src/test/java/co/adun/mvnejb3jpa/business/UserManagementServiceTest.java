package co.adun.mvnejb3jpa.business;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.UserManagementService;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)



public class UserManagementServiceTest {

	@Inject  private UserManagementService userManagementService;
	@Inject private LtUserEao userEao;
	Long analystID = 128L;
	Long supervisorID = 108L;
	
	@Test
	public void findSupervisorsByAnalyst(){
		LtUser analyst = userEao.findById(analystID);
		try {
			List<LtUser> resultlist = userManagementService.findSupervisorsByAnalyst(analyst);
			for (LtUser userSupervisor: resultlist){
				System.out.println("Supervisor name: " + userSupervisor.getFirstname() + " " + userSupervisor.getLastname());
			}
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void findAnalystsBySupervisor(){
		LtUser supervisor = userEao.findById(supervisorID);
		try {
			List<LtUser> resultlist = userManagementService.findAnalystsBySupervisor(supervisor);
			for (LtUser userAnalyst: resultlist){
				System.out.println("Analyst's name: " + userAnalyst.getFirstname() + " " + userAnalyst.getLastname());
			}
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	
}
