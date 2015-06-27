package co.adun.mvnejb3jpa.business.service;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;

/**
 * @author Mikhel Adun
 */
public interface DispositionService extends BusinessService {

	public void saveDisposition(LtLead ltLead) throws BusinessException;
}
