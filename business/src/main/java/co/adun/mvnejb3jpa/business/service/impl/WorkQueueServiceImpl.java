package co.adun.mvnejb3jpa.business.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.WorkQueueService;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@EJB(name = "java:global/WorkQueueService", beanInterface = WorkQueueService.class)
public class WorkQueueServiceImpl implements WorkQueueService {

    @Inject
    private LtUserEao userEao;

    @Inject
    private LtLeadEao leadEao;

    @Inject
    private LtLeadSubjectEao leadSubjectEao;

    @Override
    public List<LtLeadSubject> getWorkQueueItems(LtUser user) throws BusinessException {
	UserRoleCode roleCode = user.getUserRoleCode();
	if (roleCode == null) {
	    user = userEao.findById(user.getId());
	}

	List<LtLeadSubject> workQueue = leadSubjectEao.getWorkQueueForUser(user);

	return workQueue;
    }
}