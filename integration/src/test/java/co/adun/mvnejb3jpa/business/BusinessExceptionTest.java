package co.adun.mvnejb3jpa.business;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.exception.BusinessRuleException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BusinessExceptionTest {
	
/*	Usage: Presentation layer Java code will refer it as
	<spring:message code="Error.TestExceptionErrorMessage"/>
*/

    @Test
    public void testThrowBusinessRuleException() {
    	try{
    		throwException1();
    	}catch(BusinessException be){
    		Assert.assertNotNull(be);
    	}    	
    }

    @Test
    public void testThrowBusinessException() {
    	try{
    		throwException2();
    	}catch(BusinessException be){
    		Assert.assertNotNull(be);
    	}    	
    }
    
    public void throwException1() throws BusinessException{
    	throw new BusinessRuleException("Subject can not have null address", "Error.SubjectNullAddress");
    }

    public void throwException2() throws BusinessException{
    	throw new BusinessException("Subject associated with given Lead not found !");
    }

}
