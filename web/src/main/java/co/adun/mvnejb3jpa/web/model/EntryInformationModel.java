package co.adun.mvnejb3jpa.web.model;

import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.PortEntryCode;
import co.adun.mvnejb3jpa.web.model.PageModel;

public class EntryInformationModel implements PageModel {

	PortEntryCode portEntryCode;
	ClassAdmissionCode classAdmissionCode;
	DateValueModel entryDate;
	DateValueModel admitUntilDate;
	DateValueModel adjustedAdmitUntilDate;
	DateValueModel departureDate;
	ValueModel durationStatus;

	public PortEntryCode getPortEntryCode() {
		return portEntryCode;
	}
	public void setPortEntryCode(PortEntryCode portEntryCode) {
		this.portEntryCode = portEntryCode;
	}
	public ClassAdmissionCode getClassAdmissionCode() {
		return classAdmissionCode;
	}
	public void setClassAdmissionCode(ClassAdmissionCode classAdmissionCode) {
		this.classAdmissionCode = classAdmissionCode;
	}
	public DateValueModel getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(DateValueModel entryDate) {
		this.entryDate = entryDate;
	}
	public DateValueModel getAdmitUntilDate() {
		return admitUntilDate;
	}
	public void setAdmitUntilDate(DateValueModel admitUntilDate) {
		this.admitUntilDate = admitUntilDate;
	}
	public DateValueModel getAdjustedAdmitUntilDate() {
		return adjustedAdmitUntilDate;
	}
	public void setAdjustedAdmitUntilDate(DateValueModel adjustedAdmitUntilDate) {
		this.adjustedAdmitUntilDate = adjustedAdmitUntilDate;
	}
	public DateValueModel getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(DateValueModel departureDate) {
		this.departureDate = departureDate;
	}
	public ValueModel getDurationStatus() {
		return durationStatus;
	}
	public void setDurationStatus(ValueModel durationStatus) {
		this.durationStatus = durationStatus;
	}
	
	
}
