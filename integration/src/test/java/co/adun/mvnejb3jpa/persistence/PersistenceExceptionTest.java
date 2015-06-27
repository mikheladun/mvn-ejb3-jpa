package co.adun.mvnejb3jpa.persistence;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.PersistenceException;
import co.adun.mvnejb3jpa.persistence.exception.DataAccessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PersistenceExceptionTest {

    @Test
    public void testThrowPersistenceException() {
    	try{
    		throwException1();
    	}catch(PersistenceException pe){
    		Assert.assertNotNull(pe);
    	}    	
    }

    @Test
    public void testThrowDataAccessException(){
    	try{
    		throwException2();
    	}catch(PersistenceException dae){
    		Assert.assertNotNull(dae);
    	}    	
    }
    
    public void throwException1() throws PersistenceException{
    	throw new PersistenceException("Could not open JDBC connection !");
    }

    public void throwException2() throws PersistenceException{
    	throw new DataAccessException("Subject associated with given Lead not found !");
    }

}
