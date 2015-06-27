package co.adun.mvnejb3jpa.business.service;

import java.util.List;
import java.util.Set;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;

/**
 * @author Mikhel Adun
 */
public interface LeadService extends BusinessService {

    /**
     * Save collection of leads
     */
    List<LtLead> save(final List<LtLead> ltLeads) throws BusinessException;
    LtLead save(final LtLead ltLead) throws BusinessException;

    /**
     * Retrieve Lead by id
     * 
     * @param id
     * @return
     */
    LtLead getLead(final Long id) throws BusinessException;

    /**
     * Get comments for given Lead id
     * @param ltLead
     * @return list of comments
     * @throws BusinessException
     */
    List<LtLeadComment> getComments(LtLead ltLead) throws BusinessException;
    
    public void setLeadStatusById(Long leadid, Long statid) throws BusinessException;
	
	public void setLeadStatusByAbbrev(Long leadid, String abbrev) throws BusinessException;
	
	public void setLeadStatusByDescription(Long leadid, String description) throws BusinessException;
	
	public void writeAuditTrail(LtLead ltLead, Long statcode) throws BusinessException;
}
