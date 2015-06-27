package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
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
 * @author Mikhel Adun
 */
public interface SupportDataService extends BusinessService {
    public List<ClassAdmissionCode> getClassAdmCodes() throws BusinessException;

    public List<CountryCode> getCountryCodes() throws BusinessException;

    public List<GenderCode> getGenderCodes() throws BusinessException;

    public List<LeadTypeCode> getLeadTypeCodes() throws BusinessException;

    public List<MissionCode> getMissionCodes() throws BusinessException;

    public List<PortEntryCode> getPortOfEntryCodes() throws BusinessException;

    public List<SpecialProjectCode> getSpecProjCodes() throws BusinessException;

    public List<NumberTypeCode> getNmbrTypeCodes() throws BusinessException;

    public List<SourceCode> getSourceCodes() throws BusinessException;

    public List<LeadGeneratedFromCode> getLeadGenFrmCodes() throws BusinessException;

    public List<ContactTypeCode> getContactTypeCodes() throws BusinessException;

    public List<RelationshipCode> getRelationshipCodes() throws BusinessException;

    public List<StatusCode> getStatusCodes() throws BusinessException;

    public List<DisposCloseSystemCode> getDisposCloseSystemCodes();

    public List<DisposCloseReasonCode> getDisposCloseReasonCodes();
    
    public List<VisaClassCode> getVisaClassCodes() throws BusinessException;

    public List<StateProvinceCode> getStateProvinceCodes() throws BusinessException;

    public List<EventTypeCode> getEventTypeCodes() throws BusinessException;

    public List<ApplicationLocationCode> getApplicationLocationCodes() throws BusinessException;
    
    public List<LicenseTypeCode> getLicenseTypeCodes() throws BusinessException;
    
    public List<StatusCode> getStatusCodesByType(String type) throws BusinessException;

    public List<TravelDirectionCode> getTravelDirectionCodes() throws BusinessException;

}
