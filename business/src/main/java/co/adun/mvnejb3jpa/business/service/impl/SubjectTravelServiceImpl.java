package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.SubjectTravelService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectTravelEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectTravelSourceEao;
import co.adun.mvnejb3jpa.persistence.eao.SourceCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.TravelDirectionCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectTravel;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectTravelSource;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.TravelDirectionCode;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/SubjectTravelService", beanInterface = SubjectTravelService.class)
public class SubjectTravelServiceImpl implements SubjectTravelService {

	private static final Logger log = Logger.getLogger(SubjectTravelServiceImpl.class.getName());

	@Inject
	private LtSubjectEao subjectEao;

	@Inject
	private LtLeadSubjectEao leadSubjectEao;

	@Inject
	private LtSubjectTravelEao subjectTravelEao;

	@Inject
	private LtSubjectTravelSourceEao subjectTravelSourceEao;

	@Inject
	private TravelDirectionCodeEao travelDirectionCodeEao;

	@Inject
	private SourceCodeEao sourceCodeEao;

	@Inject
	UserService userService;

	@Inject
	TimerService timerService;

	@Override
	public void saveSubjectTravel(LtSubjectTravel detached) throws BusinessException {
		LtSubjectTravel entity = this.getSubjectTravel(detached.getLtSubject());
		if(entity == null) {
			entity = new LtSubjectTravel();
			entity.setLtSubject(detached.getLtSubject());
		}

		LtUser modifiedUser = userService.getSystemUser();
		Date modifiedDate = timerService.getSystemTime();
		if (entity.getLtUserByCreateBy() == null) {
			entity.setLtUserByCreateBy(userService.getSystemUser());
		}
		if (entity.getCreateDate() == null) {
			entity.setCreateDate(timerService.getSystemTime());
		}
		entity.setModifiedDate(modifiedDate);
		entity.setLtUserByModifiedBy(modifiedUser);

		entity.setCarrier(detached.getCarrier());
		entity.setFlightNumber(detached.getFlightNumber());
		entity.setTravelDate(detached.getTravelDate());
		TravelDirectionCode travelDirectionCode = travelDirectionCodeEao.findById(detached.getTravelDirectionCode().getId());
		entity.setTravelDirectionCode(travelDirectionCode);
		entity.setTravelComment(detached.getTravelComment());

		Set<LtSubjectTravelSource> entitySourceSet = entity.getLtSubjectTravelSources();
		for (LtSubjectTravelSource source : entitySourceSet) {
			source.setLtSubjectTravel(null);
		}
		entitySourceSet.clear();

		for (LtSubjectTravelSource detachedSource : detached.getLtSubjectTravelSources()) {
			CompositeId compositeId = detachedSource.getId();
			if (compositeId == null) {
				compositeId = new CompositeId();
			}
			compositeId.setId(detachedSource.getSourceCode().getId());
			compositeId.setCompId(entity.getId());

			LtSubjectTravelSource entitySource = subjectTravelSourceEao.findById(compositeId);
			if (entitySource == null) {
				entitySource = detachedSource;
				entitySource.setId(compositeId);
				entitySource.setCreateDate(timerService.getSystemTime());
				entitySource.setLtUserByCreateBy(userService.getSystemUser());
				entitySource.setSourceCode(sourceCodeEao.findById(detachedSource.getSourceCode().getId()));
				entitySource.setLtSubjectTravel(entity);
			}
			entitySource.setModifiedDate(modifiedDate);
			entitySource.setLtUserByModifiedBy(modifiedUser);
			entitySource.setLtSubjectTravel(entity);
			entitySourceSet.add(entitySource);
		}
		entity.setLtSubjectTravelSources(entitySourceSet);

		subjectTravelEao.saveOrUpdate(entity);
		detached.setId(entity.getId());
	}

	@Override
	public LtSubjectTravel getSubjectTravel(LtSubject ltSubject) throws BusinessException {
		LtSubjectTravel ltSubjectTravel = subjectTravelEao.findBySubjectId(ltSubject.getId());
		return ltSubjectTravel;
	}

}
