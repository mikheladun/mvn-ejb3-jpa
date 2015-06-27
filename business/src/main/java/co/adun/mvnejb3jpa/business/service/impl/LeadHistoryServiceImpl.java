package co.adun.mvnejb3jpa.business.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.service.LeadHistoryService;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@EJB(name = "java:global/LeadHistoryService", beanInterface = LeadHistoryService.class)
public class LeadHistoryServiceImpl implements LeadHistoryService
{
}
