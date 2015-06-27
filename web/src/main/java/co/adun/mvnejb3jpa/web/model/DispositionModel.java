package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import co.adun.mvnejb3jpa.persistence.entity.DisposCloseReasonCode;
import co.adun.mvnejb3jpa.persistence.entity.DisposCloseSystemCode;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;
import co.adun.mvnejb3jpa.web.model.PageModel;

public class DispositionModel implements PageModel {

	StatusCode statusCode;
	DisposCloseReasonCode reasonCode;
	DisposCloseSystemCode systemCode;
	LtUser supervisor;
	LtUser analyst;
    DateValueModel callupDateModel;
    List<LtUser> supervisors;
    List<LtUser> analysts;
    String details;

	public DisposCloseReasonCode getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(DisposCloseReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}

	public DisposCloseSystemCode getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(DisposCloseSystemCode systemCode) {
		this.systemCode = systemCode;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public LtUser getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(LtUser supervisor) {
		this.supervisor = supervisor;
	}

	public DateValueModel getCallupDateModel() {
		return callupDateModel;
	}

	public void setCallupDateModel(DateValueModel callupDateModel) {
		this.callupDateModel = callupDateModel;
	}

	public List<LtUser> getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(List<LtUser> supervisors) {
		this.supervisors = supervisors;
	}

	public List<LtUser> getAnalysts() {
		return analysts;
	}

	public void setAnalysts(List<LtUser> analysts) {
		this.analysts = analysts;
	}

	public LtUser getAnalyst() {
		return analyst;
	}

	public void setAnalyst(LtUser analyst) {
		this.analyst = analyst;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


}
