package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@Transactional
public class LtUserEaoImpl extends BaseEaoImpl<LtUser> implements LtUserEao {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LtUserEaoImpl.class.getName());

    @Override
    public Class<LtUser> getEntityClass() {
	return LtUser.class;
    }

    @Override
    public List<LtUser> findAllAnalysts() {
	List<CustomFilterField> filterFields = new ArrayList<CustomFilterField>();
	List<Long> roles = new ArrayList<Long>();
	roles.add(101L);
	roles.add(102L);
	filterFields.add(new CustomFilterField("userRoleCode.id", roles));
	List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
	sortFields.add(new CustomSortField("lastname", CustomSortField.ASCENDING));
	sortFields.add(new CustomSortField("firstname", CustomSortField.ASCENDING));
	List<LtUser> analystList = this.findAllInFilterAndSort(filterFields, sortFields);

	return analystList;
    }
}
