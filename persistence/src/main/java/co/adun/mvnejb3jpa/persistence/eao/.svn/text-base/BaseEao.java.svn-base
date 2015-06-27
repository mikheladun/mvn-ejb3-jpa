package co.adun.mvnejb3jpa.persistence.eao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;


/**
 * An interface to define persistent storage service for JPA persistable objects
 * 
 * @author Mikhel Adun
 */
public interface BaseEao<T> extends PersistenceEao<T> {

    public static final String ENTITY_MANAGER_JNDI = "java:jboss/persistenceUnit";
    public static final String ENTITY_MANAGER_FACTORY_JNDI = "java:jboss/EntityManagerFactory";

    /**
     * @return
     */
    public Class<T> getEntityClass();

    /**
     * Stores / updates entity in database
     * 
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * Stores / updates entity in database
     * 
     * @param entity
     */
    void saveOrUpdate(List<T> entities);

    /**
     * Indicates whether given entity can be removed or still is referenced by other related entities.
     * 
     * @param entity
     * @return boolean
     */
    public boolean canBeRemoved(T t);

    public Object executeQuery(final Query query);
    public Query createHqlQuery(final String hql);
    public SQLQuery createSqlQuery(final String sql);
    public List getJpaQueryResults(String querystr, Object[] params);
  
}
