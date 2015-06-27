package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

/**
 * @author Mikhel Adun
 */
public interface WorkQueueService extends BusinessService {

    List<LtLeadSubject> getWorkQueueItems(LtUser ltUser) throws BusinessException;

}
