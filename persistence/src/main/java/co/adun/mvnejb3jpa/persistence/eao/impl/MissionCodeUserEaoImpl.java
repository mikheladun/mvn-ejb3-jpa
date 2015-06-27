package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.MissionCodeUserEao;
import co.adun.mvnejb3jpa.persistence.entity.MissionCodeUser;

/**
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity. Strongly-typed interface created since it could be used by @Autowired
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@Transactional
public class MissionCodeUserEaoImpl extends BaseEaoImpl<MissionCodeUser> implements MissionCodeUserEao {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(MissionCodeEaoImpl.class.getName());
    
    private static final String HQL_ALL_USERS = "FROM MissionCodeUser m JOIN FETCH m.ltUserByLtUserId u JOIN FETCH u.userRoleCode ORDER BY m.missionCode.description";

    @Override
    public Class<MissionCodeUser> getEntityClass() {
	return MissionCodeUser.class;
    }

    @Override
    public List<MissionCodeUser> findAllUsers() {
	// set the column that needs to be sorted from the DB
	List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
	sortFields.add(new CustomSortField("missionCode", CustomSortField.ASCENDING));

	Query query = this.createHqlQuery(HQL_ALL_USERS);
	List<MissionCodeUser> missionCodeUsers = (List<MissionCodeUser>) this.executeQuery(query);

	return missionCodeUsers;
    }
}