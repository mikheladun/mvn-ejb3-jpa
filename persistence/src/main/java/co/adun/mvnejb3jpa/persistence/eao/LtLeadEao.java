package co.adun.mvnejb3jpa.persistence.eao;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtLead;

/**
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity. Strongly-typed interface created since
 * it could be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Transactional
@Component
public interface LtLeadEao extends BaseEao<LtLead>{

	public LtLead findByIdDeep(Long id);
}
