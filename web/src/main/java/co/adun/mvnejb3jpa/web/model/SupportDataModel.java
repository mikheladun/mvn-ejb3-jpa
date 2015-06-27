package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

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
import co.adun.mvnejb3jpa.persistence.entity.VisaClassCode;
import co.adun.mvnejb3jpa.web.model.PageModel;

/**
 * @author Mikhel Adun
 */
@Component
public class SupportDataModel implements PageModel {
    List<LeadTypeCode> leadTypeCodes;
    List<GenderCode> genderCodes;
    List<ClassAdmissionCode> classAdmissionCodes;
    List<CountryCode> countryCodes;
    List<MissionCode> missionCodes;
    List<NumberTypeCode> numberTypeCodes;
    List<PortEntryCode> portEntryCodes;
    List<SourceCode> sourceCodes;
    List<SpecialProjectCode> specialProjectCodes;
    List<ContactTypeCode> contactTypeCodes;
    List<LeadGeneratedFromCode> leadGeneratedFromCodes;
    List<RelationshipCode> relationshipCodes;
    List<StatusCode> statusCodes;
    List<VisaClassCode> visaClassCodes;
    List<StateProvinceCode> stateProvince;
    List<EventTypeCode> eventTypes;
    List<ApplicationLocationCode> applocs;
    List<LicenseTypeCode> licenseCodes;
    List<StatusCode> tecsStatusCodes;
    List<DisposCloseSystemCode> disposCloseSystemCodes;
    List<DisposCloseReasonCode> disposCloseReasonCodes;

    
    
    public List<DisposCloseSystemCode> getDisposCloseSystemCodes() {
        return disposCloseSystemCodes;
    }

    public void setDisposCloseSystemCodes(List<DisposCloseSystemCode> disposCloseSystemCodes) {
        this.disposCloseSystemCodes = disposCloseSystemCodes;
    }

    public List<DisposCloseReasonCode> getDisposCloseReasonCodes() {
        return disposCloseReasonCodes;
    }

    public void setDisposCloseReasonCodes(List<DisposCloseReasonCode> disposCloseReasonCodes) {
        this.disposCloseReasonCodes = disposCloseReasonCodes;
    }

    public List<StatusCode> getTecsStatusCodes(){
    	return this.tecsStatusCodes;
    }
    
    public void setTecsStatusCodes(List<StatusCode> tecsStatusCodes){
    	this.tecsStatusCodes = tecsStatusCodes;
    }
    public List<LicenseTypeCode> getLicenseCodes(){
    	return this.licenseCodes;
    }
    
    public void setLicenseCodes(List<LicenseTypeCode> licenseCodes){
    	this.licenseCodes = licenseCodes;
    }
    
    public List<ApplicationLocationCode> getApplocs(){
    	return this.applocs;
    }
    
    public void setApplocs(List<ApplicationLocationCode> applocs){
    	this.applocs = applocs;
    }
    
    public List<EventTypeCode> getEventTypes(){
    	return this.eventTypes;
    }
    
    public void setEventTypes(List<EventTypeCode> eventTypes){
    	this.eventTypes = eventTypes;
    }
    
    public List<StateProvinceCode> getStateProvince(){
    	return this.stateProvince;
    }
    
    public void setStateProvince(List<StateProvinceCode> stateProvince){
    	this.stateProvince = stateProvince;
    }
    
    public List<VisaClassCode> getVisaClassCodes(){
    	return this.visaClassCodes;
    }
    
    public void setVisaClassCodes(List<VisaClassCode> visaClassCodes){
    	this.visaClassCodes = visaClassCodes;
    }
    public List<LeadTypeCode> getLeadTypeCodes() {
	return leadTypeCodes;
    }

    public void setLeadTypeCodes(List<LeadTypeCode> leadTypeCodes) {
	this.leadTypeCodes = leadTypeCodes;
    }

    public List<GenderCode> getGenderCodes() {
	return genderCodes;
    }

    public void setGenderCodes(List<GenderCode> genderCodes) {
	this.genderCodes = genderCodes;
    }

    public List<ClassAdmissionCode> getClassAdmissionCodes() {
	return classAdmissionCodes;
    }

    public void setClassAdmissionCodes(List<ClassAdmissionCode> classAdmCodes) {
	this.classAdmissionCodes = classAdmCodes;
    }

    public List<CountryCode> getCountryCodes() {
	return countryCodes;
    }

    public void setCountryCodes(List<CountryCode> countryCodes) {
	this.countryCodes = countryCodes;
    }

    public List<MissionCode> getMissionCodes() {
	return missionCodes;
    }

    public void setMissionCodes(List<MissionCode> missionCodes) {
	this.missionCodes = missionCodes;
    }

    public List<NumberTypeCode> getNumberTypeCodes() {
	return numberTypeCodes;
    }

    public void setNumberTypeCodes(List<NumberTypeCode> numberTypeCodes) {
	this.numberTypeCodes = numberTypeCodes;
    }

    public List<PortEntryCode> getPortEntryCodes() {
	return portEntryCodes;
    }

    public void setPortEntryCodes(List<PortEntryCode> portEntryCodes) {
	this.portEntryCodes = portEntryCodes;
    }

    public List<SourceCode> getSourceCodes() {
	return sourceCodes;
    }

    public void setSourceCodes(List<SourceCode> sourceCodes) {
	this.sourceCodes = sourceCodes;
    }

    public List<SpecialProjectCode> getSpecialProjectCodes() {
	return specialProjectCodes;
    }

    public void setSpecialProjectCodes(List<SpecialProjectCode> specProjCodes) {
	this.specialProjectCodes = specProjCodes;
    }

    public List<ContactTypeCode> getContactTypeCodes() {
	return contactTypeCodes;
    }

    public void setContactTypeCodes(List<ContactTypeCode> contactTypeCodes) {
	this.contactTypeCodes = contactTypeCodes;
    }

    public List<LeadGeneratedFromCode> getLeadGeneratedFromCodes() {
	return this.leadGeneratedFromCodes;
    }

    public void setLeadGeneratedFromCodes(List<LeadGeneratedFromCode> leadGeneratedFromCodes) {
	this.leadGeneratedFromCodes = leadGeneratedFromCodes;
    }

    public List<RelationshipCode> getRelationshipCodes() {
	return relationshipCodes;
    }

    public void setRelationshipCodes(List<RelationshipCode> relationshipCodes) {
	this.relationshipCodes = relationshipCodes;
    }

    public List<StatusCode> getStatusCodes() {
	return statusCodes;
    }

    public void setStatusCodes(List<StatusCode> statusCodes) {
	this.statusCodes = statusCodes;
    }
    
}
