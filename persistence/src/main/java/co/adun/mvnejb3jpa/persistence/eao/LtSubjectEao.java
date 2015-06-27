package co.adun.mvnejb3jpa.persistence.eao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtSubject;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Transactional
@Component
public interface LtSubjectEao extends BaseEao<LtSubject> {
	public List<LtSubject> findSubjectByName(String lastName, String firstName, String lsid);

    public List<LtSubject> findSubjectByCriteria(LtSubject ltSubject);

    public List<LtSubject> findSubjectByLsidAndName(LtSubject ltSubject);
    public List<LtSubject> findSubjectByLeadId(LtSubject ltSubject);
}
