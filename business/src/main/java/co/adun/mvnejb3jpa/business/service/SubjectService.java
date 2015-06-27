package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;

/**
 * @author Mikhel Adun
 */
public interface SubjectService extends BusinessService {

    /**
     * Save a subject
     * 
     * @param LtSubject
     *            ltSubject
     * @throws BusinessException
     */
    public LtSubject save(final LtSubject ltSubject) throws BusinessException;

    /**
     * Delete a subject
     * 
     * @param LtSubject
     *            ltSubject
     * @throws BusinessException
     */
    public void delete(final LtSubject ltSubject) throws BusinessException; 
    /**
     * Save a subject
     * 
     * @param LtSubject
     *            ltSubject
     * @throws BusinessException
     */
  //  public LtSubject update(final LtSubject ltSubject) throws BusinessException;

    /**
     * Retrieve Subject by id
     * 
     * @param id
     * @return
     */
    public LtSubject getLtSubject(final Long id, final String lsid) throws BusinessException;

    /**
     * Find Subject by last name and first name
     * 
     * @param lastName
     * @param firstName
     * @throws BusinessException
     */
    public List<LtSubject> findSubjectByName(String lastName, String firstName, String lsid) throws BusinessException;

    /**
     * Find Lead Subject by last name and first name and lead Id
     * 
     * @param lastName
     * @param firstName
     * @throws BusinessException
     */

    public List<LtSubject> findSubjectByCriteria(final LtSubject ltSubject) throws BusinessException;

    public List<LtSubject> findSubjectByLsidAndName( final LtSubject ltSubject) throws BusinessException;
    public List<LtSubject> findSubjectByLeadId(final LtSubject ltSubject) throws BusinessException;
    

}
