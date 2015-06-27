package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.BaseEao;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

/** 
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity.  
 * Strongly-typed interface created since it could be used by @Autowired
 * 
 * @author Mikhel Adun
 *
 */
@Transactional
@Component
public interface ExampleEao extends BaseEao<ExampleEntity>
{
}
