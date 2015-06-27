package co.adun.mvnejb3jpa.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.service.ExampleService;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.ExampleEao;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ExampleEaoTest {
    @Inject
    private ExampleEao eao;
    @Inject
    private ExampleService service;    

    private static final String name = "Jane Doe";
    private static final String email = "jane.doe" + System.currentTimeMillis() + "@mailinator.com";
    private static final String phone = "2125552121";

    @Test
    public void testDelete() {
	List<ExampleEntity> entities = service.getMembers();
	for (ExampleEntity entity : entities) {
	    eao.delete(entity);
	}

	entities = service.getMembers();
	Assert.assertTrue(entities.isEmpty());
	return;
    }

    @Test
    public void testRegister() {
	ExampleEntity exampleEntity = new ExampleEntity();
	exampleEntity.setEmail(email);
	exampleEntity.setName(name);
	exampleEntity.setPhoneNumber(phone);

	service.add(exampleEntity);
	Long id = exampleEntity.getId();
	Assert.assertNotNull(id);
	Assert.assertEquals(name, exampleEntity.getName());
	Assert.assertEquals(email, exampleEntity.getEmail());
	Assert.assertEquals(phone, exampleEntity.getPhoneNumber());
	return;
    }

    @Test
    public void testFindByEmail() {
	ExampleEntity exampleEntity = service.getMember(email);
	Assert.assertEquals(name, exampleEntity.getName());
	Assert.assertEquals(email, exampleEntity.getEmail());
	Assert.assertEquals(phone, exampleEntity.getPhoneNumber());
	return;
    }

    @Test
    public void testFindById() {
	ExampleEntity exampleEntity = service.getMember(email);
	exampleEntity = service.getMember(exampleEntity.getId());

	Assert.assertEquals(name, exampleEntity.getName());
	Assert.assertEquals(email, exampleEntity.getEmail());
	Assert.assertEquals(phone, exampleEntity.getPhoneNumber());
	return;
    }

    @Test
    public void testFindAllOrderedByName() {
	ExampleEntity exampleEntity = new ExampleEntity();
	exampleEntity.setEmail(email);
	exampleEntity.setName(name);
	exampleEntity.setPhoneNumber(phone);
	eao.save(exampleEntity);

	List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
	sortFields.add(new CustomSortField("name", CustomSortField.ASCENDING));
	List<ExampleEntity> exampleEntitys = eao.findAllWithSort(sortFields);
	exampleEntity = exampleEntitys.iterator().next();

	Assert.assertEquals(name, exampleEntity.getName());
	Assert.assertEquals(email, exampleEntity.getEmail());
	Assert.assertEquals(phone, exampleEntity.getPhoneNumber());
	return;
    }
}
