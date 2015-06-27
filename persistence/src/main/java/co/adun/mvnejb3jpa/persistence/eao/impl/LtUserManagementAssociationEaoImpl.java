package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.LtUserManagementAssociationEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@Transactional

public class LtUserManagementAssociationEaoImpl extends BaseEaoImpl<LtUserManagementAssociation>implements LtUserManagementAssociationEao {
    @SuppressWarnings("unused")
    
    private static final Logger logger = Logger.getLogger(LtUserManagementAssociationEaoImpl.class.getName());

    private static final String HQL_ALL_USERS = "FROM LtUserManagementAssociation m JOIN FETCH m.ltUserByUserId u JOIN FETCH m.ltUserBySupervisorId s JOIN FETCH u.userRoleCode JOIN FETCH s.userRoleCode ORDER BY s.id";

    public LtUserManagementAssociationEaoImpl(){
    	super();
    }
    @Override
    public Class<LtUserManagementAssociation> getEntityClass() {
	return LtUserManagementAssociation.class;
    }

    @Override
    public List<LtUserManagementAssociation> findAllUsers() {
	// set the column that needs to be sorted from the DB
	List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
	sortFields.add(new CustomSortField("ltUserBySupervisorId.id", CustomSortField.ASCENDING));
	List<CustomFilterField> filterFields = new ArrayList<CustomFilterField>();

	Query query = this.createHqlQuery(HQL_ALL_USERS);
	List<LtUserManagementAssociation> users = (List<LtUserManagementAssociation>) this.executeQuery(query);

	return users;
    }
   
    @Override
    public List<LtUser> findSupervisorsByAnalyst(LtUser analyst){
    	Long analystId = analyst.getId();
    	
    	String querystr = "select s from LtUserManagementAssociation um inner join um.ltUserBySupervisorId s "
    			+ " where um.ltUserByUserId.id = ?";
    	 
    	Object[] param_array = new Object[1];
    	
    	param_array[0] = analystId;
    	
    	List<LtUser> resultlist = (List<LtUser>)getJpaQueryResults(querystr, param_array);
    	
    	return resultlist;
    	
    }
    
    @Override
    public List<LtUser> findAnalystsBySupervisor(LtUser supervisor){
    	Long supervisorId = supervisor.getId();
   
    	String querystr = "select a from LtUserManagementAssociation um inner join um.ltUserByUserId "
    			+ "a where um.ltUserBySupervisorId.id = ?";
    	
    	Object[] param_array = new Object[1];
    	
    	param_array[0] = supervisorId;
    	
    	List<LtUser> resultlist = (List<LtUser>)getJpaQueryResults(querystr, param_array);
    	
    	return resultlist;
    
    }
}
