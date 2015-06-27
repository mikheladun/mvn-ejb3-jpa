package co.adun.mvnejb3jpa.business.service;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;


/**
 * An interface to define business services
 * 
 * @author Mikhel Adun
 */
public interface UserService extends BusinessService
{
	public LtUser getSystemUser() throws BusinessException;
	public LtUser getCurrentUser(String name) throws BusinessException;
}
