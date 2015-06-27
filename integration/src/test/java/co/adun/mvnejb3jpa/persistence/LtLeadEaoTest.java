package co.adun.mvnejb3jpa.persistence;

import java.io.Serializable;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LtLeadEaoTest {
    @Inject
    private LtLeadEao eao;

    @Test
    public void testSave() {
	LtLead ltLead = new LtLead();
	eao.save(ltLead);

	Serializable id = ltLead.getId();
	Assert.assertNotNull(id);
	return;
    }

}
