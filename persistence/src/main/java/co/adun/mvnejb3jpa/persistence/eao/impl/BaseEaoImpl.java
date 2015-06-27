package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.EntityManagerHelper;
import co.adun.mvnejb3jpa.persistence.eao.BaseEao;

/**
 * An entity access object (EAO) class to provide persistent storage functions
 * and search support for JPA persistable objects. The EntityManager API is used
 * to create, read, update, and delete persistent objects. Entities that are
 * managed by the EntityManager instance must implement the Persistable
 * interface, and must be colocated in their mapping to the same database.
 * Transaction control of the save(), update() and delete() operations must be
 * handled externally by senders of these methods or must by manually added to
 * each of these methods for data to be persisted to the JPA datastore.
 * 
 * @author Mikhel Adun
 */
public abstract class BaseEaoImpl<T> implements BaseEao<T> {
	private static final Logger logger = Logger.getLogger(BaseEaoImpl.class.getName());

	private static final String CODE = "code";
	private static final String NAME = "name";
	private static final String DESCR = "description";
	private static final String ABBRV = "abbreviaton";

	@PersistenceContext
	protected EntityManager entityManager;

	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	// @Inject
	// private Event<T> event;

	@Override
	public abstract Class<T> getEntityClass();

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(T entity) {
		EntityManagerHelper.log("delete - going to delete entity: " + entity, Level.INFO);
		Transaction transaction = null;
		try {
			transaction = ((Session) entityManager.getDelegate()).getTransaction();
			if (!transaction.isActive()) {
				transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			}
			entityManager.remove(entity);
			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			transaction.rollback();
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Delete a persistent Persistable entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersistableEAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Persistable entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(String idValue) {
		EntityManagerHelper.log("deleteById():: going to delete entity/entities with ID: " + idValue, Level.INFO);
		Transaction transaction = null;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
			Root<T> entity = criteria.from(getEntityClass());
			criteria.select(entity).where(builder.equal(entity.get("id"), idValue));
			List<T> objsToDelete = entityManager.createQuery(criteria).getResultList();
			transaction = ((Session) entityManager.getDelegate()).getTransaction();
			if (!transaction.isActive()) {
				transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			}
			for (T obj : objsToDelete) {
				EntityManagerHelper.log("deleteById()::deleteById():: going to delete entity: " + obj, Level.INFO);
				entityManager.remove(obj);
				// event.fire(obj);
			}
			transaction.commit();
		}
		catch (RuntimeException re) {
			transaction.rollback();
			EntityManagerHelper.log("deleteById failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
		EntityManagerHelper.log("deleteById():: done", Level.INFO);
	}

	@Override
	public T findByStringId(String id) {
		EntityManagerHelper.log("getByStringId - getting entity by id (String): " + id, Level.INFO);
		try {
			return entityManager.find(getEntityClass(), Long.valueOf(id));
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	@Override
	public T findById(Serializable id) {
		EntityManagerHelper.log("getById - getting entity by id (long): " + id, Level.INFO);
		try {
			return entityManager.find(getEntityClass(), id);
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	public T detach(T entity) {
		org.hibernate.Session session = (Session) entityManager.getDelegate();
		session.evict(entity);
		return entity;
	}

	@Override
	public T findByIdLocked(Serializable id) {
		EntityManagerHelper.log("getByIdLocked - getting locked entity by id: " + id, Level.INFO);
		try {
			return entityManager.find(getEntityClass(), id, LockModeType.OPTIMISTIC);
			// TODO see LockOptions.UPGRADE usage
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Perform an initial save of a previously unsaved Persistable entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersistableEAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persistable entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T save(T entity) {
		EntityManagerHelper.log("save - going to save entity: " + entity, Level.INFO);
		Transaction transaction = null;
		try {
			transaction = ((Session) entityManager.getDelegate()).getTransaction();
			if (!transaction.isActive()) {
				transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			}
			entityManager.persist(entity);
			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
		// persist new Persistable when JTA transaction completes (when
		// method
		// ends).
		// internally:
		// 1. Look for existing "entityPU" persistence context in active JTA
		// transaction and use if found.
		// 2. Else create new "entityPU" persistence context (e.g. instance
		// of
		// org.hibernate.ejb.HibernatePersistence) and put in current active
		// JTA
		// transaction.
		// return Persistable entity (will be detached from the persistence
		// context when caller gets control)
		// Transaction.commit will be called, Persistable entity will be
		// persisted to the database and "entityPU" persistence context

		return entity;
	}

	/**
	 * Persist a previously saved Persistable entity and return it or a copy of
	 * it to the sender. A copy of the Persistable entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersistableEAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persistable entity to update
	 * @return Persistable the persisted Persistable entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T update(T entity) {
		EntityManagerHelper.log("save - going to update entity: " + entity, Level.INFO);
		Transaction transaction = null;
		try {
			transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			entity = entityManager.merge(entity);
			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			transaction.rollback();
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

		return entity;
	}

	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveOrUpdate(T t) {
		EntityManagerHelper.log("save - going to save entity: " + t, Level.INFO);
		Transaction transaction = null;
		try {
			transaction = ((Session) entityManager.getDelegate()).getTransaction();
			if (!transaction.isActive()) {
				transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			}

			((Session) getEntityManager().getDelegate()).saveOrUpdate(t);

			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveOrUpdate(List<T> ts) {
		Transaction transaction = null;
		try {
			transaction = ((Session) getEntityManager().getDelegate()).getTransaction();
			if (!transaction.isActive()) {
				transaction = ((Session) getEntityManager().getDelegate()).beginTransaction();
			}
			for (T t : ts) {
				EntityManagerHelper.log("save - going to save entity: " + t, Level.INFO);

				((Session) getEntityManager().getDelegate()).saveOrUpdate(t);
			}

			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			if (transaction != null) {
				transaction.rollback();
			}
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T merge(T entity) {
		EntityManagerHelper.log("merge: ", Level.INFO);
		Transaction transaction = null;
		try {
			transaction = ((Session) entityManager.getDelegate()).beginTransaction();
			entity = entityManager.merge(entity);
			transaction.commit();
			// event.fire(entity);
		}
		catch (RuntimeException re) {
			transaction.rollback();
			EntityManagerHelper.log("merge failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

		return entity;
	}

	@Override
	public Long count() {
		return countWithFilter(null);
	}

	@Override
	public Long countWithFilter(List<CustomFilterField> filterFields) {
		EntityManagerHelper.log("countWithFilter - going to count the entities", Level.INFO);
		try {
			Criteria crit = ((Session) entityManager.getDelegate()).createCriteria(getEntityClass());
			addFilterCriterions(crit, filterFields);

			return (Long) crit.setProjection(Projections.rowCount()).uniqueResult();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Retrieves all entities of designated type
	 * 
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		EntityManagerHelper.log("findAll():: Looking up for all entities entityManager:" + entityManager, Level.INFO);
		List<T> objs;
		try {
			objs = ((Session) entityManager.getDelegate()).createCriteria(getEntityClass()).list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

		return objs;
	}

	/**
	 * Retrieves all entities of designated type
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllWithSort(List<CustomSortField> sortFields) {
		EntityManagerHelper.log("findAll():: Looking up for all entities entityManager:" + entityManager, Level.INFO);
		List<T> objs = this.findAllWithFilterAndSort(null, sortFields);

		return objs;
	}

	/**
	 * Retrieves all entities of designated type
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllWithFilter(List<CustomFilterField> filterFields) {
		EntityManagerHelper.log("findAll():: Looking up for all entities entityManager:" + entityManager, Level.INFO);
		List<T> objs = this.findAllWithFilterAndSort(filterFields, null);

		return objs;
	}

	/**
	 * Retrieves all entities of designated type
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllInFilterAndSort(List<CustomFilterField> filterFields, List<CustomSortField> sortFields) {
		EntityManagerHelper.log("findAll():: Looking up for all entities entityManager:" + entityManager, Level.INFO);
		List<T> objs;
		try {
			Criteria crit = ((Session) entityManager.getDelegate()).createCriteria(getEntityClass());
			if (filterFields != null) {
				for (CustomFilterField field : filterFields) {
					if (field != null && field.getPropertyName() != null && field.getFilterValue() != null)
						crit.add(Restrictions.in(field.getPropertyName(), (Collection) field.getFilterValue()));
				}
			}
			addSortCriterions(crit, sortFields);

			objs = crit.list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

		return objs;
	}

	/**
	 * Retrieves all entities of designated type
	 * 
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAllWithFilterAndSort(List<CustomFilterField> filterFields, List<CustomSortField> sortFields) {
		EntityManagerHelper.log("findAll():: Looking up for all entities entityManager:" + entityManager, Level.INFO);
		List<T> objs;
		try {
			Criteria crit = ((Session) entityManager.getDelegate()).createCriteria(getEntityClass());
			addFilterCriterions(crit, filterFields);
			addSortCriterions(crit, sortFields);

			objs = crit.list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("findAllWithFilterAndSort failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

		return objs;
	}

	/**
	 * Retrieves all entities of designated type limited between firstRow and
	 * endRow
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllByRange(int firstRow, int endRow) {
		EntityManagerHelper.log("findAllByRange():: Looking up for all entities between " + firstRow + " and " + endRow, Level.INFO);
		try {
			return findAllByRangeWithFilterAndSort(firstRow, endRow, null, null);
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Retrieves all entities of designated type limited ((between firstRow and
	 * endRow) AND (filterFields (if any)))
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllByRangeWithFilter(int firstRow, int endRow, List<CustomFilterField> filterFields) {
		EntityManagerHelper.log("findAllByRangeWithFilter():: Looking up for all entities between " + firstRow + " and " + endRow, Level.INFO);
		try {
			return findAllByRangeWithFilterAndSort(firstRow, endRow, filterFields, null);
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	private void addFilterCriterions(Criteria crit, List<CustomFilterField> filterFields) {
		if (filterFields != null) {
			for (CustomFilterField field : filterFields) {
				if (field != null && field.getPropertyName() != null && field.getFilterValue() != null)
					crit.add(Restrictions.eq(field.getPropertyName(), field.getFilterValue()));
			}
		}
	}

	private void addSortCriterions(Criteria crit, List<CustomSortField> sortFields) {
		if (sortFields != null) {
			for (CustomSortField field : sortFields) {
				if (field != null && field.getPropertyName() != null) {
					if (CustomSortField.ASCENDING.equals(field.getOrdering()))
						crit.addOrder(Order.asc(field.getPropertyName()));
					if (CustomSortField.DESCENDING.equals(field.getOrdering()))
						crit.addOrder(Order.desc(field.getPropertyName()));
				}
			}
		}
	}

	/*
	 * Retrieves all entities of designated type limited ((between firstRow and
	 * endRow) AND (filterFields (if any))). Ordered according to sortFields.
	 * 
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAllByRangeWithFilterAndSort(int firstRow, int endRow, List<CustomFilterField> filterFields, List<CustomSortField> sortFields) {
		EntityManagerHelper.log("findAllByRangeWithFilterAndSort():: Looking up for all entities between " + firstRow + " and " + endRow, Level.INFO);
		try {
			// TODO remove Session reference
			Criteria crit = ((Session) entityManager.getDelegate()).createCriteria(getEntityClass());

			crit.setFirstResult(firstRow);
			crit.setMaxResults(endRow - firstRow + 1);

			addFilterCriterions(crit, filterFields);
			addSortCriterions(crit, sortFields);

			return crit.list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Retrieves all entities of designated type filtered by condition
	 * attribName column == value
	 * 
	 * @param attribName
	 * @param value
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(final String attribName, final Object value) {
		EntityManagerHelper.log("findByAttribute - looking up for all entities where: " + attribName + " = " + value, Level.INFO);
		try {
			return ((Session) entityManager.getDelegate()).createCriteria(getEntityClass()).add(Restrictions.eq(attribName, value)).list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Retrieves one entity of designated type filtered by condition attribName
	 * column == value
	 * 
	 * @param attribName
	 * @param value
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findByPropertyUniqueResult(final String attribName, final Object value) {
		EntityManagerHelper.log("findByAttribute - looking up for all entities where: " + attribName + " = " + value, Level.INFO);
		try {
			return (T) ((Session) entityManager.getDelegate()).createCriteria(getEntityClass()).add(Restrictions.eq(attribName, value)).uniqueResult();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Retrieves all entities of designated type filtered by condition
	 * attribName column in values
	 * 
	 * @param attribName
	 * @param value
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByPropertyIn(final String attribName, final Collection<? extends Object> value) {
		EntityManagerHelper.log("findByAttributeIn - looking up for all entities where: " + attribName + " in " + value, Level.INFO);
		try {
			return ((Session) entityManager.getDelegate()).createCriteria(getEntityClass()).add(Restrictions.in(attribName, value)).list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * Create HQL query for usage by Daos (mainly mapping arguments etc...)
	 * 
	 * @param hql
	 * @return Query object suitable for HQL queries
	 */
	public Query createHqlQuery(final String hql) {
		EntityManagerHelper.log("createHqlQuery - going to create hql query: " + hql, Level.INFO);
		try {
			return ((Session) entityManager.getDelegate()).createQuery(hql);
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

	}

	/**
	 * Create SQL query for usage by Daos (mainly mapping arguments etc...)
	 * 
	 * @param sql
	 * @return Query object suitable for SQL queries
	 */
	public SQLQuery createSqlQuery(final String sql) {
		EntityManagerHelper.log("createSqlQuery - going to create sql query: " + sql, Level.INFO);
		try {
			return ((Session) entityManager.getDelegate()).createSQLQuery(sql);
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}

	}

	/**
	 * Executes HQL or SQL query
	 * 
	 * @param query
	 * @return List of entities, or arrays, as defined by HQL
	 */
	public Object executeQuery(final Query query) {
		EntityManagerHelper.log("executeHql - executing HQL: " + query.getQueryString(), Level.INFO);
		try {
			return query.list();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	@Override
	@Transactional(timeout = 30, propagation = Propagation.REQUIRED, readOnly = true)
	public boolean canBeRemoved(T entity) {
		throw new RuntimeException("method not implemented");
		// EntityManagerHelper.log("canBeRemoved", Level.INFO);
		// try {
		// if (entity == null)
		// return true;
		//
		// entity = entityManager.find(getEntityClass(), entity.getId());
		// if (entity == null)
		// return true;
		//
		// // 5.try - disable lazy-loading completely (not recommended due to
		// // performance and memory allocation reasons!)
		// return entity.canBeRemoved();
		// } catch (RuntimeException re) {
		// EntityManagerHelper.log("find all failed", Level.SEVERE, re);
		// throw re;
		// } finally {
		// entityManager.close();
		// }

	}

	/**
	 * Find all Persistable entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Persistable property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Persistable instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Persistable model where model." + propertyName + "= :propertyValue";
			javax.persistence.Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	@Override
	public List<T> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	@Override
	public List<T> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	@Override
	public List<T> findByDescription(Object description, int... rowStartIdxAndCount) {
		return findByProperty(DESCR, description, rowStartIdxAndCount);
	}

	@Override
	public List<T> findByAbbreviation(Object abbreviation, int... rowStartIdxAndCount) {
		return findByProperty(ABBRV, abbreviation, rowStartIdxAndCount);
	}

	/**
	 * Find all Persistable entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<T> all Persistable entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Persistable instances", Level.INFO, null);
		try {
			final String queryString = "select model from Persistable model";
			javax.persistence.Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		}
		catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		finally {
			// entityManager.close();
		}
	}

	/**
	 * @author - kalyan
	 * 
	**/
	@Override
	public List getJpaQueryResults(String querystr, Object[] params){
		try{
		javax.persistence.Query query = (javax.persistence.Query)entityManager.createQuery(querystr);
		if (null != params){
			for ( int i=1; i< params.length + 1; i++){
				query = query.setParameter(i,params[i - 1]);
			}
		}
		return query.getResultList();
		}
		catch(Exception e) {
			//System.out.println("Hibernate JPA Exception: "  + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
