package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.service.BusinessService;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

/**
 * An interface to define business services
 * 
 * @author Mikhel Adun
 */
public interface ExampleService extends BusinessService {

    public void add(ExampleEntity entity);

    public List<ExampleEntity> getMembers();

    public ExampleEntity getMember(long id);

    public ExampleEntity getMember(String email);

}
