package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.ExampleEao;
import co.adun.mvnejb3jpa.persistence.eao.impl.BaseEaoImpl;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

/**
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity. Strongly-typed interface created since
 * it could be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@Transactional
@Repository
@EJB(name = "java:global/ExampleEao", beanInterface = ExampleEao.class)
public class ExampleEaoImpl extends BaseEaoImpl<ExampleEntity> implements ExampleEao
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ExampleEaoImpl.class.getName());

	@Override
	public Class<ExampleEntity> getEntityClass()
	{
		return ExampleEntity.class;
	}
}
