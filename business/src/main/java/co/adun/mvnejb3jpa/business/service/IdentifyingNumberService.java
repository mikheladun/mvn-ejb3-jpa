/**
 * @Author: Kalyan
 */

package co.adun.mvnejb3jpa.business.service;

import java.util.Set;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadIdentifyingNumber;

public  interface IdentifyingNumberService extends BusinessService{

	public void save(LtLeadIdentifyingNumber ltLeadIdentifyingNumber) throws BusinessException;
	
	public void save(Set<LtLeadIdentifyingNumber> ltLeadIdentifyingNumbers ) throws BusinessException;
		
}
