package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSpecialProject;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.web.model.PageModel;

@Component
public class LtLeadModel implements PageModel {
	
	LtLead ltLead;
	LtLeadSource ltLeadSource;
	LtLeadComment ltLeadComment;
	LtLeadSubject ltLeadSubject;
	ValueModel supervisorModel;
	ValueModel analystModel;
	
	DateValueModel birthDateModel;
	DateValueModel entryDateModel;
	List<AssociatedLeadModel> associateModel;
	
	List<LtLeadSpecialProject> ltLeadSpecialProjects;
	List<LtSubjectCitizenshipCountry> ltSubjectCitizenshipCountries;
	List<IdentifyingNumberModel> identifyingNumberModel;
	
	String formId;
	
	public String getFormId() {
		return formId;
	}
	
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	public LtLeadSubject getLtLeadSubject() {
		return ltLeadSubject;
	}
	
	public void setLtLeadSubject(LtLeadSubject ltLeadSubject) {
		this.ltLeadSubject = ltLeadSubject;
	}
	
	public LtLead getLtLead() {
		return ltLead;
	}
	
	public void setLtLead(LtLead ltLead) {
		this.ltLead = ltLead;
	}
	
	public DateValueModel getBirthDateModel() {
		return birthDateModel;
	}
	
	public void setBirthDateModel(DateValueModel birthDateModel) {
		this.birthDateModel = birthDateModel;
	}
	
	public DateValueModel getEntryDateModel() {
		return entryDateModel;
	}
	
	public void setEntryDateModel(DateValueModel entryDateModel) {
		this.entryDateModel = entryDateModel;
	}
	
	public ValueModel getSupervisorModel() {
		return supervisorModel;
	}
	
	public void setSupervisorModel(ValueModel supervisorModel) {
		this.supervisorModel = supervisorModel;
	}
	
	public ValueModel getAnalystModel() {
		return analystModel;
	}
	
	public void setAnalystModel(ValueModel analystModel) {
		this.analystModel = analystModel;
	}
	
	public List<AssociatedLeadModel> getAssociateModel() {
		return associateModel;
	}
	
	public void setAssociateModel(List<AssociatedLeadModel> associateModel) {
		this.associateModel = associateModel;
	}
	
	public List<IdentifyingNumberModel> getIdentifyingNumberModel() {
		return identifyingNumberModel;
	}
	
	public void setIdentifyingNumberModel(List<IdentifyingNumberModel> identifyingNumberModel) {
		this.identifyingNumberModel = identifyingNumberModel;
	}
	
	public List<LtLeadSpecialProject> getLtLeadSpecialProjects() {
		return ltLeadSpecialProjects;
	}
	
	public void setLtLeadSpecialProjects(List<LtLeadSpecialProject> ltLeadSpecialProjects) {
		this.ltLeadSpecialProjects = ltLeadSpecialProjects;
	}
	
	public List<LtSubjectCitizenshipCountry> getLtSubjectCitizenshipCountries() {
		return ltSubjectCitizenshipCountries;
	}
	
	public void setLtSubjectCitizenshipCountries(List<LtSubjectCitizenshipCountry> ltSubjectCitizenshipCountries) {
		this.ltSubjectCitizenshipCountries = ltSubjectCitizenshipCountries;
	}
	
	public LtLeadSource getLtLeadSource() {
		return ltLeadSource;
	}
	
	public void setLtLeadSource(LtLeadSource ltLeadSource) {
		this.ltLeadSource = ltLeadSource;
	}
	
	public LtLeadComment getLtLeadComment() {
		return ltLeadComment;
	}
	
	public void setLtLeadComment(LtLeadComment ltLeadComment) {
		this.ltLeadComment = ltLeadComment;
	}
	
}
