package co.adun.mvnejb3jpa.business.service;

import co.adun.mvnejb3jpa.business.exception.BusinessException;

/**
 * An interface to define business services
 * 
 * @author Mikhel Adun
 */
public interface TimerService extends BusinessService
{
    java.util.Date getSystemTime() throws BusinessException;
}
