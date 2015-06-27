package co.adun.mvnejb3jpa.business.service;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;

public interface EntryService extends BusinessService {

	public LtSubject getEntryInformation(LtLead ltLead) throws BusinessException;

	public LtSubject getEntryInformation(LtSubject ltSubject) throws BusinessException;

	public void save(LtSubject ltSubject) throws BusinessException;	
}
