package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.BiographicInfoService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.CountryCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectCitizenshipCountryEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/BiographicInfoService", beanInterface = BiographicInfoService.class)
public class BiographicInfoServiceImpl implements BiographicInfoService {

	private static final Logger log = Logger.getLogger(BiographicInfoServiceImpl.class.getName());

	@Inject
	private LtSubjectEao eao;

	@Inject
	private CountryCodeEao countryCodeEao;

	@Inject
	private LtSubjectCitizenshipCountryEao subjectCitizenshipCountryEao;

	@Inject
	UserService userService;

	@Inject
	TimerService timerService;

	@Override
	public LtSubject saveBiographicInfo(LtSubject detached) throws BusinessException {
		LtUser modifiedUser = userService.getSystemUser();
		Date modifiedDate = timerService.getSystemTime();
		LtUser createUser = userService.getSystemUser();
		Date createDate = timerService.getSystemTime();

		// copy the biographic info from the presentation layer into the DB
		// object
		if (detached != null) {
			// find the subject by ID
			LtSubject entity = eao.findById(detached.getId());
			entity.setBirthDate(detached.getBirthDate());
			entity.setGenderCode(detached.getGenderCode());
			entity.setCountryCode(detached.getCountryCode());

			Set<LtSubjectCitizenshipCountry> entityCocSet = entity.getLtSubjectCitizenshipCountries();
			for(LtSubjectCitizenshipCountry coc : entityCocSet) {
				coc.setLtSubject(null);
			}
			entityCocSet.clear();

			for (LtSubjectCitizenshipCountry detachedCoc : detached.getLtSubjectCitizenshipCountries()) {
				CompositeId compositeId = detachedCoc.getId();
				if(compositeId == null) {
					compositeId = new CompositeId();
				}
				compositeId.setId(entity.getId());
				compositeId.setCompId(detachedCoc.getCountryCode().getId());

				LtSubjectCitizenshipCountry entityCoc = subjectCitizenshipCountryEao.findById(compositeId);
				if(entityCoc == null) {
					entityCoc = detachedCoc;
					entityCoc.setId(compositeId);
					entityCoc.setCreateDate(createDate);
					entityCoc.setLtUserByCreateBy(createUser);
					entityCoc.setCountryCode(countryCodeEao.findById(detachedCoc.getCountryCode().getId()));
					entityCoc.setLtSubject(entity);
				}
				entityCoc.setModifiedDate(modifiedDate);
				entityCoc.setLtUserByModifiedBy(modifiedUser);
				entityCocSet.add(entityCoc);
			}
			entity.setLtSubjectCitizenshipCountries(entityCocSet);
			entity.setModifiedDate(modifiedDate);
			entity.setLtUserByModifiedBy(modifiedUser);
				
			// save the updated subject
			eao.saveOrUpdate(entity);
		}

		return detached;
	}

}
