package co.adun.mvnejb3jpa.web.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.SupportDataService;
import co.adun.mvnejb3jpa.persistence.entity.ApplicationLocationCode;
import co.adun.mvnejb3jpa.persistence.entity.BaseEntity;
import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.ContactTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseReasonCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseSystemCode;
import co.adun.mvnejb3jpa.persistence.entity.EventTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.GenderCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadGeneratedFromCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.LicenseTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;
import co.adun.mvnejb3jpa.persistence.entity.NumberTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.PortEntryCode;
import co.adun.mvnejb3jpa.persistence.entity.RelationshipCode;
import co.adun.mvnejb3jpa.persistence.entity.SourceCode;
import co.adun.mvnejb3jpa.persistence.entity.SpecialProjectCode;
import co.adun.mvnejb3jpa.persistence.entity.StateProvinceCode;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;
import co.adun.mvnejb3jpa.persistence.entity.TravelDirectionCode;
import co.adun.mvnejb3jpa.persistence.entity.VisaClassCode;

/**
 * This class represents the support table cache. This is where all data will be
 * stored, once per application. Everything needs to be synchronized otherwise,
 * it might happen that one user is clearing the cache and another one will get
 * it empty.
 * 
 * @author Mikhel Adun
 */
public class SupportDataCache {
	private static final Logger logger = Logger.getLogger(SupportDataCache.class.getName());
	
	/**
	 * This is the service that the cache will use to get to the data.
	 */
	@Autowired
	@Qualifier("supportDataService")
	private SupportDataService service;
	
	/**
	 * This is the cache data.
	 */
	private Map<String, List<? extends BaseEntity>> cache = null;
	
	/**
	 * Make sure that the data is loaded in the cache and then return it from
	 * it.
	 * 
	 * @param name
	 *            Name of the property.
	 * @return Value of the property.
	 * @throws IOException
	 */
	public synchronized List<? extends BaseEntity> getProperty(String name) throws BusinessException {
		logger.log(Level.INFO, "Cache: Reading property " + name + "...");
		loadData();
		return cache.get(name);
	}

	/**
	 * Make sure that the data is loaded in the cache and then return it from
	 * it.
	 * 
	 * @param name
	 *            Name of the property.
	 * @return Value of the property.
	 * @throws IOException
	 */
	public synchronized Map<String, List<? extends BaseEntity>> getMap() throws BusinessException {
		loadData();
		return cache;
	}

	/**
	 * Check if the cache is loaded, if not, load all data and store it. If it
	 * is already filled out, then just return.
	 * 
	 * @throws BusinessException
	 *             If any problem happens while loading data.
	 */
	public synchronized void loadData() throws BusinessException {
		logger.log(Level.INFO, "Cache: Checking if data is loaded...");
		if (cache == null) {
			logger.log(Level.INFO, "Cache: Loading data...");

			// cache = service.getAll();
			cache = new HashMap<String, List<? extends BaseEntity>>();
			cache.put(ClassAdmissionCode.class.getSimpleName(), service.getClassAdmCodes());
			cache.put(CountryCode.class.getSimpleName(), service.getCountryCodes());
			cache.put(GenderCode.class.getSimpleName(), service.getGenderCodes());
			cache.put(LeadTypeCode.class.getSimpleName(), service.getLeadTypeCodes());
			cache.put(MissionCode.class.getSimpleName(), service.getMissionCodes());
			cache.put(NumberTypeCode.class.getSimpleName(), service.getNmbrTypeCodes());
			cache.put(PortEntryCode.class.getSimpleName(), service.getPortOfEntryCodes());
			cache.put(SourceCode.class.getSimpleName(), service.getSourceCodes());
			cache.put(SpecialProjectCode.class.getSimpleName(), service.getSpecProjCodes());
			cache.put(LeadGeneratedFromCode.class.getSimpleName(), service.getLeadGenFrmCodes());
			cache.put(ContactTypeCode.class.getSimpleName(), service.getContactTypeCodes());
			cache.put(RelationshipCode.class.getSimpleName(), service.getRelationshipCodes());
			cache.put(StatusCode.class.getSimpleName(), service.getStatusCodes());
			cache.put(EventTypeCode.class.getSimpleName(), service.getEventTypeCodes());
			cache.put(VisaClassCode.class.getSimpleName(), service.getVisaClassCodes());
			cache.put(StateProvinceCode.class.getSimpleName(), service.getStateProvinceCodes());
			cache.put(ApplicationLocationCode.class.getSimpleName(), service.getApplicationLocationCodes());
			cache.put(LicenseTypeCode.class.getSimpleName(), service.getLicenseTypeCodes());
			cache.put(StatusCode.class.getSimpleName(), service.getStatusCodes());
			cache.put(DisposCloseReasonCode.class.getSimpleName(), service.getDisposCloseReasonCodes());
			cache.put(DisposCloseSystemCode.class.getSimpleName(), service.getDisposCloseSystemCodes());
			cache.put(TravelDirectionCode.class.getSimpleName(), service.getTravelDirectionCodes());

			logger.log(Level.INFO, "Cache: Data loaded.");
		} else {
			logger.log(Level.INFO, "Cache: Don't need to reload data.");
		}
	}

	/**
	 * Clear cached data. This data needs to be called everytime a new entry is
	 * saved in the database. This way, the cache will always have the latest
	 * version of the data.
	 */
	public synchronized void clearCache() {
		logger.log(Level.INFO, "Cache: Clearing cache data...");
		cache = null;
		logger.log(Level.INFO, "Cache: Cache data cleared.");
	}
}
