package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;

/**
 * @author Mikhel Adun
 */
public interface LeadSubjectService extends BusinessService {
    public LtLeadSubject getLtLeadSubject(Long leadId, Long subjectId) throws BusinessException;

    public LtLeadSubject save(LtLeadSubject ltLeadSubject) throws BusinessException;
    public List<LtLeadSubject> save(List<LtLeadSubject> ltLeadSubjects) throws BusinessException;
}
