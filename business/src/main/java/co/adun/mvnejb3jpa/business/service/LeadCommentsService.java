package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;

/**
 * @author Mikhel Adun
 */
public interface LeadCommentsService extends BusinessService {

	public void deleteComments(LtLead ltLead) throws BusinessException;
	public void saveComments(List<LtLeadComment> ltLeadComments) throws BusinessException;
	public void updateComments(LtLeadComment detached) throws BusinessException ;
}
