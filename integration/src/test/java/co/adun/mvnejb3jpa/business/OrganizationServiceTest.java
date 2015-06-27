package co.adun.mvnejb3jpa.business;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.OrganizationService;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrganizationServiceTest {
	@Inject
	private OrganizationService service;

	@Test
	public void getAllAnalysts() {

		try {
			List<LtUser> analysts = service.getAllAnalysts();
			Assert.assertNotNull(analysts);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	@Test
	public void getAllSupervisors() {

		try {
			service.getAllSupervisors();
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	@Test
	public void getAllSupervisorForAnalysts() {

		try {
			List<LtUser> analysts = service.getAllSupervisorForAnAnalystsId(113L);
			System.out.println("Supervisor for analyst 113 count=" + analysts.size());
			Assert.assertNotNull(analysts);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void getAllAnalystsforSupervisor() {

		try {
			List<LtUser> analysts = service.getAllAnalystsforSupervisorId(101L);
			System.out.println(" analysts for Supervisor 101 count=" + analysts.size());
			Assert.assertNotNull(analysts);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	@Test
	public void getLtUserManagementAssociationList() {

		try {
			service.getLtUserManagementAssociationList();
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	@Test
	public void getMissionCodeUsersList() {

		try {
			service.getMissionCodeUsersList();
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}
}
