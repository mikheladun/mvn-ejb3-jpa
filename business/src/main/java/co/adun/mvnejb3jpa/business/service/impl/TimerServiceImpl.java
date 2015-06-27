package co.adun.mvnejb3jpa.business.service.impl;


import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.TimerService;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/TimerService", beanInterface = TimerService.class)
public class TimerServiceImpl implements TimerService {
    private static final Logger log = Logger.getLogger(TimerServiceImpl.class.getName());

    @Override
    public java.util.Date getSystemTime() throws BusinessException {
	Timestamp systemTime = new Timestamp(System.currentTimeMillis());
	log.info(systemTime.toString());
	return systemTime;
    }
}