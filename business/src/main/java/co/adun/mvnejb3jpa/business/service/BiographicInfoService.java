package co.adun.mvnejb3jpa.business.service;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;

/**
 * @author Mikhel Adun
 */
public interface BiographicInfoService extends BusinessService {
	
	/**
	 * Save a subject
	 * 
	 * @param LtSubject
	 *            ltSubject
	 * @throws BusinessException
	 */
	public LtSubject saveBiographicInfo(final LtSubject ltSubject) throws BusinessException;
	
}
