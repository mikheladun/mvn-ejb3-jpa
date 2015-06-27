package co.adun.mvnejb3jpa.business;

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
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserServiceTest {
    @Inject
    private UserService service;

    @Test
    public void getSystemUser() {

	try {
	    LtUser user = service.getSystemUser();
	} catch (BusinessException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
	return;
    }

    @Test
    public void getCurrentUser() {

	try {
	    LtUser user = service.getCurrentUser("SYSTEM");
	} catch (BusinessException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
	return;
    }

}
