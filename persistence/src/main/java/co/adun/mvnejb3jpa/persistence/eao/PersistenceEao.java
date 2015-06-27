package co.adun.mvnejb3jpa.persistence.eao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;

/**
 * An interface to define persistent storage service for JPA persistable objects
 * 
 * @author Mikhel Adun
 */
@Component
@Configurable
public interface PersistenceEao<T> {
    /**
     * Stores entity in database
     * 
     * @param entity
     *            to store
     * @return generated id
     */
    T save(T entity);
    
    T detach(T entity);

    /**
     * Retrieve entity form database, identified by its id
     * 
     * @param id
     * @return
     */
    T findByStringId(String id);

    /**
     * Retrieve entity form database, identified by its id
     * 
     * @param id
     * @return
     */
    T findById(Serializable id);

    /**
     * Retrieve entity form database, identified by its id
     * 
     * @param id
     * @return
     */
    T findByIdLocked(Serializable id);

    /**
     * Updates already stored entity
     * 
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * Removes entity from database
     * 
     * @param entity
     * @return
     */
    void delete(T entity);

    /**
     * Removes entity from database by its unique ID
     * 
     * @param idValue
     * @return
     */
    void deleteById(String idValue);

    /**
     * Merges an entity
     * 
     * @param entity
     * @return newly merged entity
     */
    T merge(T entity);

    public Serializable count();

    public Serializable countWithFilter(List<CustomFilterField> filterFields);

    public List<T> findByPropertyIn(final String propertyName, final Collection<? extends Object> value);

    public List<T> findByProperty(final String propertyName, final Object value);

    public T findByPropertyUniqueResult(final String propertyName, final Object value);

    public List<T> findAll();

    public List<T> findAllWithFilterAndSort(List<CustomFilterField> filterField, List<CustomSortField> sortField);

    public List<T> findAllWithFilter(List<CustomFilterField> filterField);

    public List<T> findAllWithSort(List<CustomSortField> sortField);

    public List<T> findAllInFilterAndSort(List<CustomFilterField> filterField, List<CustomSortField> sortField);

    public List<T> findAllByRange(int firstRow, int endRow);

    public List<T> findAllByRangeWithFilter(int firstRow, int endRow, List<CustomFilterField> filterFields);

    public List<T> findAllByRangeWithFilterAndSort(int firstRow, int endRow, List<CustomFilterField> filterFields, List<CustomSortField> sortFields);

    public List<T> findAll(final int... rowStartIdxAndCount);

    public List<T> findByCode(Object code, int... rowStartIdxAndCount);

    public List<T> findByName(Object name, int... rowStartIdxAndCount);

    public List<T> findByDescription(Object descr, int... rowStartIdxAndCount);

    public List<T> findByAbbreviation(Object code, int... rowStartIdxAndCount);
}