package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.web.model.PageModel;

/**
 * @author Mikhel Adun
 */
@Component
public class LeadModel implements PageModel {

    List<LtLeadModel> ltLeadsModel;
    SupportDataModel supportDataModel;

    LtUser assignToSupervisor;
    LtUser assignToAnalyst;
    LtUser currentUser;

    List<LtUser> analystsList;
    List<LtUser> supervisorsList;

    public void setAnalystsList(List<LtUser> analystsList) {
	this.analystsList = analystsList;
    }

    public void setSupervisorsList(List<LtUser> supervisorsList) {
	this.supervisorsList = supervisorsList;
    }

    public List<LtUser> getAnalystsList() {
	return this.analystsList;
    }

    public List<LtUser> getSupervisorsList() {
	return this.supervisorsList;
    }

    public SupportDataModel getSupportDataModel() {
	return supportDataModel;
    }

    public void setSupportDataModel(SupportDataModel supportDataModel) {
	this.supportDataModel = supportDataModel;
    }

    public List<LtLeadModel> getLtLeadsModel() {
	return ltLeadsModel;
    }

    public void setLtLeadModel(List<LtLeadModel> ltLeadsModel) {
	this.ltLeadsModel = ltLeadsModel;
    }

    public LtUser getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(LtUser currentUser) {
	this.currentUser = currentUser;
    }

    public LtUser getAssignToSupervisor() {
	return assignToSupervisor;
    }

    public void setAssignToSupervisor(LtUser assignToSupervisor) {
	this.assignToSupervisor = assignToSupervisor;
    }

    public LtUser getAssignToAnalyst() {
	return assignToAnalyst;
    }

    public void setAssignToAnalyst(LtUser assignToAnalyst) {
	this.assignToAnalyst = assignToAnalyst;
    }
}
