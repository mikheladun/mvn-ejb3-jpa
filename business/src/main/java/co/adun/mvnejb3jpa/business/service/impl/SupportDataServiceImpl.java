package co.adun.mvnejb3jpa.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.SupportDataService;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.ApplicationLocationCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.ClassAdmissionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.ContactTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.CountryCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.DisposCloseReasonCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.DisposCloseSystemCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.EventTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.GenderCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LeadGeneratedFromCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LeadTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.LicenseTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.MissionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.NumberTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.PortEntryCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.RelationshipCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.SourceCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.SpecialProjectCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.StateProvinceCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.StatusCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.TravelDirectionCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.VisaClassCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.ApplicationLocationCode;
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
 * A stateless session bean to load support data.
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/SupportDataService", beanInterface = SupportDataService.class)
public class SupportDataServiceImpl implements SupportDataService {

	@Inject
	private ClassAdmissionCodeEao classAdmCodes;

	@Inject
	private CountryCodeEao countryCodes;

	@Inject
	private GenderCodeEao genderCodes;

	@Inject
	private LeadTypeCodeEao leadTypeCodes;

	@Inject
	private MissionCodeEao missionCodes;

	@Inject
	private PortEntryCodeEao portOfEntryCodes;

	@Inject
	private SpecialProjectCodeEao specProjCodes;

	@Inject
	private NumberTypeCodeEao nmbrTypeCodes;

	@Inject
	private SourceCodeEao sourceCodes;

	@Inject
	private LeadGeneratedFromCodeEao leadGenFrmCodes;

	@Inject
	private ContactTypeCodeEao contactTypeCodes;

	@Inject
	private RelationshipCodeEao relationshipCodes;

	@Inject
	private StatusCodeEao statusCodes;

	@Inject
	private DisposCloseSystemCodeEao disposCloseSystemCodes;

	@Inject
	private DisposCloseReasonCodeEao disposCloseReasonCodes;

	@Inject
	private VisaClassCodeEao visaClassCodes;

	@Inject
	private StateProvinceCodeEao stateProvinceCodes;

	@Inject
	private ApplicationLocationCodeEao appLocations;

	@Inject
	private EventTypeCodeEao eventTypeCodes;

	@Inject
	private LicenseTypeCodeEao licenseTypeCodes;

	@Inject
	private TravelDirectionCodeEao travelDirectionEao;

	@Inject
	private StatusCodeEao statusCodeEao;

	//
	// @Inject
	// private TravelDirectionEao travelDirectionEao;

	@Override
	public List<ClassAdmissionCode> getClassAdmCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<ClassAdmissionCode> codes = classAdmCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<CountryCode> getCountryCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<CountryCode> codes = countryCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<GenderCode> getGenderCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<GenderCode> codes = genderCodes.findAllWithSort(sortFields);
		return codes;

	}

	@Override
	public List<LeadTypeCode> getLeadTypeCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<LeadTypeCode> codes = leadTypeCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<MissionCode> getMissionCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<MissionCode> codes = missionCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<PortEntryCode> getPortOfEntryCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<PortEntryCode> codes = portOfEntryCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<SpecialProjectCode> getSpecProjCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<SpecialProjectCode> codes = specProjCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<NumberTypeCode> getNmbrTypeCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<NumberTypeCode> codes = nmbrTypeCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<SourceCode> getSourceCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<SourceCode> codes = sourceCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<LeadGeneratedFromCode> getLeadGenFrmCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<LeadGeneratedFromCode> codes = leadGenFrmCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<ContactTypeCode> getContactTypeCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<ContactTypeCode> codes = contactTypeCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<RelationshipCode> getRelationshipCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<RelationshipCode> codes = relationshipCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<StatusCode> getStatusCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<StatusCode> codes = statusCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<DisposCloseSystemCode> getDisposCloseSystemCodes() {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<DisposCloseSystemCode> codes = disposCloseSystemCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<DisposCloseReasonCode> getDisposCloseReasonCodes() {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<DisposCloseReasonCode> codes = disposCloseReasonCodes.findAllWithSort(sortFields);
		return codes;
	}

	@Override
	public List<VisaClassCode> getVisaClassCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<VisaClassCode> visaCodes = visaClassCodes.findAllWithSort(sortFields);
		return visaCodes;
	}

	@Override
	public List<StateProvinceCode> getStateProvinceCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<StateProvinceCode> stpCodes = stateProvinceCodes.findAllWithSort(sortFields);
		return stpCodes;
	}

	@Override
	public List<ApplicationLocationCode> getApplicationLocationCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<ApplicationLocationCode> appLocs = appLocations.findAllWithSort(sortFields);
		return appLocs;
	}

	@Override
	public List<EventTypeCode> getEventTypeCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<EventTypeCode> eventTypes = eventTypeCodes.findAllWithSort(sortFields);
		return eventTypes;
	}

	@Override
	public List<LicenseTypeCode> getLicenseTypeCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<LicenseTypeCode> licenseCodes = licenseTypeCodes.findAllWithSort(sortFields);
		return licenseCodes;
	}

	@Override
	public List<StatusCode> getStatusCodesByType(String type) throws BusinessException {
		List<StatusCode> statusCodes = statusCodeEao.findByProperty("type", type);
		return statusCodes;
	}

	@Override
	public List<TravelDirectionCode> getTravelDirectionCodes() throws BusinessException {
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("description", CustomSortField.ASCENDING));
		List<TravelDirectionCode> codes = travelDirectionEao.findAllWithSort(sortFields);
		return codes;
	}

}