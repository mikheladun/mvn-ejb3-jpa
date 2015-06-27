package co.adun.mvnejb3jpa.web.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumber;
import co.adun.mvnejb3jpa.web.model.DateValueModel;
import co.adun.mvnejb3jpa.web.model.IdentifyingNumberModel;

@Component
public class IdentifyingNumberValidator extends BaseValidator<IdentifyingNumberModel> {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IdentifyingNumberValidator.class.getName());

    @Autowired
    DateModelValidator dateModelValidator;

    @Override
    public boolean supports(Class<?> clazz) {
	return IdentifyingNumberModel.class.isAssignableFrom(clazz) || ArrayList.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

	List<IdentifyingNumberModel> inList = (List<IdentifyingNumberModel>) target;

	/*
	 * Field names:
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].numberType.abbreviation
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].number
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].otherType
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].statusCode.abbreviation
	 * ltLeadsModel
	 * [0].identifyingNumberModel["+i+"].countryCode.abbreviation
	 * ltLeadsModel
	 * [0].identifyingNumberModel["+i+"].sourceCodes[0].abbreviation
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].comments
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].issueDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].expirationDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].eventDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].creationDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].updateDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].incidentDate
	 * ltLeadsModel[0].identifyingNumberModel["+i+"].naturalizationDate
	 */

	// retrieve DOB from lead model
	// Date dob = model.getBirthDateModel().getAsDate();

	String errorCode = "";
	int i = 0;

	for (IdentifyingNumberModel in : inList) {
	    LtIdentifyingNumber ltIdentifyingNumber = in.getLtIdentifierNumber();
	    if(ltIdentifyingNumber == null) continue;

	    DateValueModel dateValueModel = in.getBirthDateModel();
	    Date dob = dateValueModel.getAsDate();

	    String numberType = in.getLtIdentifierNumber().getNumberTypeCode().getAbbreviation();

	    // **************Global checks***************
	    // - Number Type should not be default
	    // - Check comment length (4000)
	    // - Check source for not empty
	    // Note: All dates are NOT required, but
	    // an earlier-than-DOB check (if DOB is not null) must be done.

	    if (numberType == "Select...") {
		errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].numberType.abbreviation", errorCode,
			"Please select a Number Type.");
	    }
	    if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumberComment()) && ltIdentifyingNumber.getIdentifyingNumberComment().length() > 4000) {
		errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].comments", errorCode,
			"Comments must be 4000 characters or less.");
	    }
	    if (in.getSourceCodes().isEmpty()) {
		errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].sourceCodes[0].abbreviation", errorCode,
			"Each IN must have at least one source.");
	    }

	    // *************Number-specific checks*************

	    String pattern = "";

	    // Alien Registration #
	    if (numberType == "AR") {
		// number [9 numerics, not null]
		pattern = "^(\\d{9})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [Alien Reg. #: 9 numerics]");
		}
	    }
	    // FIN
	    else if (numberType == "FI") {
		// number [10 numerics, not null]
		pattern = "^(\\d{10})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Incorrect format [FIN: 10 numerics]");
		}
	    }
	    // SSN
	    else if (numberType == "SS") {
		// number [9 numerics input type ###-##-####, not null]
		pattern = "^(\\d{3}-\\d{2}-\\d{4})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Incorrect format [SSN: ###-##-####]");
		}
	    }
	    // ADIS PID
	    else if (numberType == "AD") {
		// number [9 numerics, not null]
		pattern = "^(\\d{9})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [ADIS PID: 9 numerics]");
		}
	    }
	    // Credit Card
	    else if (numberType == "CC") {
		// number [100 chars, not null]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
	    }
	    // ENFORCE Event
	    else if (numberType == "EE") {
		// number [13 alphanumerics, not null]
		pattern = "^(\\w{9})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [ENFORCE Event: 9 alphanumerics]");
		}
	    }
	    // LSID
	    else if (numberType == "LS") {
		// number [100 chars, not null]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
	    }
	    // NUIN
	    else if (numberType == "NU") {
		// number [100 chars, not null]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
	    }
	    // Other
	    else if (numberType == "OT") {
		// other type [100 chars, not null]
		// number [100 chars, not null]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
	    }
	    // TSC
	    else if (numberType == "TS") {
		// number [100 chars, not null]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
	    }
	    // TECS Case
	    else if (numberType == "TC") {
		// number [14 alphanumerics, not null]
		// status [DD]
		pattern = "^(\\w{14})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [TECS Case: 14 alphanumerics]");
		}
		if (ltIdentifyingNumber.getStatusCodeByTecsCaseStatusCodeId().getAbbreviation() != null && ltIdentifyingNumber.getStatusCodeByTecsCaseStatusCodeId().getAbbreviation() == "Select...") {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].statusCode.abbreviation", errorCode, "Status is required.");
		}
	    }
	    // Naturalization
	    else if (numberType == "NA") {
		// number [100 chars, not null]
		// naturalization date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel naturalizationDateModel = in.getNaturalizationDate();
		ValidationUtils.invokeValidator(dateModelValidator, naturalizationDateModel, errors);
		Date naturalizationDate = naturalizationDateModel.getAsDate();
		if (dob != null && naturalizationDate != null && naturalizationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].naturalizationDate", errorCode,
			    "Naturalization Date should be later than lead's DOB.");
		}
	    }
	    // TECS ILOG
	    else if (numberType == "TI") {
		// number [100 chars, not null]
		// incident date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel incidentDateModel = in.getIncidentDate();
		ValidationUtils.invokeValidator(dateModelValidator, incidentDateModel, errors);
		Date incidentDate = incidentDateModel.getAsDate();
		if (dob != null && incidentDate != null && incidentDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].incidentDate", errorCode,
			    "Incident Date should be later than lead's DOB.");
		}
	    }
	    // TECS Subject Record
	    else if (numberType == "TR") {
		// number [14 alphanumerics, not null]
		// status [DD]
		// creation date [date]
		// update date [date]
		pattern = "^(\\w{14})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [TECS Subject Record: 14 alphanumerics]");
		}
		DateValueModel creationDateModel = in.getCreationDate();
		ValidationUtils.invokeValidator(dateModelValidator, creationDateModel, errors);
		Date creationDate = creationDateModel.getAsDate();
		if (dob != null && creationDate != null && creationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].creationDate", errorCode,
			    "Creation Date should be later than lead's DOB.");
		}
		DateValueModel updateDateModel = in.getUpdateDate();
		ValidationUtils.invokeValidator(dateModelValidator, updateDateModel, errors);
		Date updateDate = updateDateModel.getAsDate();
		if (dob != null && updateDate != null && updateDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].updateDate", errorCode,
			    "Update Date should be later than lead's DOB.");
		}
	    }
	    // Passport
	    else if (numberType == "PA") {
		// number [100 chars, not null]
		// country [DD]
		// issue date [date]
		// expiration date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel issueDateModel = in.getIssueDate();
		ValidationUtils.invokeValidator(dateModelValidator, issueDateModel, errors);
		Date issueDate = issueDateModel.getAsDate();
		if (dob != null && issueDate != null && issueDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].issueDate", errorCode,
			    "Issue Date should be later than lead's DOB.");
		}
		DateValueModel expirationDateModel = in.getExpirationDate();
		ValidationUtils.invokeValidator(dateModelValidator, expirationDateModel, errors);
		Date expirationDate = expirationDateModel.getAsDate();
		if (dob != null && expirationDate != null && expirationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].expirationDate", errorCode,
			    "Expiration Date should be later than lead's DOB.");
		}
	    }
	    // Visa
	    else if (numberType == "VI") {
		// number [8 alphanumerics, not null]
		// issue date [date]
		// expiration date [date]
		pattern = "^(\\w{8})$";
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && !ltIdentifyingNumber.getIdentifyingNumber().matches(pattern)) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Incorrect format [Visa: 8 alphanumerics]");
		}
		DateValueModel issueDateModel = in.getIssueDate();
		ValidationUtils.invokeValidator(dateModelValidator, issueDateModel, errors);
		Date issueDate = issueDateModel.getAsDate();
		if (dob != null && issueDate != null && issueDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].issueDate", errorCode,
			    "Issue Date should be later than lead's DOB.");
		}
		DateValueModel expirationDateModel = in.getExpirationDate();
		ValidationUtils.invokeValidator(dateModelValidator, expirationDateModel, errors);
		Date expirationDate = expirationDateModel.getAsDate();
		if (dob != null && expirationDate != null && expirationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].expirationDate", errorCode,
			    "Expiration Date should be later than lead's DOB.");
		}
	    }
	    // Driver's License
	    else if (numberType == "DL") {
		// number [100 chars, not null]
		// country [DD]
		// issue date [date]
		// expiration date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel issueDateModel = in.getIssueDate();
		ValidationUtils.invokeValidator(dateModelValidator, issueDateModel, errors);
		Date issueDate = issueDateModel.getAsDate();
		if (dob != null && issueDate != null && issueDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].issueDate", errorCode,
			    "Issue Date should be later than lead's DOB.");
		}
		DateValueModel expirationDateModel = in.getExpirationDate();
		ValidationUtils.invokeValidator(dateModelValidator, expirationDateModel, errors);
		Date expirationDate = expirationDateModel.getAsDate();
		if (dob != null && expirationDate != null && expirationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].expirationDate", errorCode,
			    "Expiration Date should be later than lead's DOB.");
		}
	    }
	    // State ID Card
	    else if (numberType == "SI") {
		// number [100 chars, not null]
		// country [DD]
		// issue date [date]
		// expiration date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel issueDateModel = in.getIssueDate();
		ValidationUtils.invokeValidator(dateModelValidator, issueDateModel, errors);
		Date issueDate = issueDateModel.getAsDate();
		if (dob != null && issueDate != null && issueDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].issueDate", errorCode,
			    "Issue Date should be later than lead's DOB.");
		}
		DateValueModel expirationDateModel = in.getExpirationDate();
		ValidationUtils.invokeValidator(dateModelValidator, expirationDateModel, errors);
		Date expirationDate = expirationDateModel.getAsDate();
		if (dob != null && expirationDate != null && expirationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].expirationDate", errorCode,
			    "Expiration Date should be later than lead's DOB.");
		}
	    }
	    // FAA License
	    else if (numberType == "FA") {
		// number [100 chars, not null]
		// issue date [date]
		// expiration date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel issueDateModel = in.getIssueDate();
		ValidationUtils.invokeValidator(dateModelValidator, issueDateModel, errors);
		Date issueDate = issueDateModel.getAsDate();
		if (dob != null && issueDate != null && issueDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].issueDate", errorCode,
			    "Issue Date should be later than lead's DOB.");
		}
		DateValueModel expirationDateModel = in.getExpirationDate();
		ValidationUtils.invokeValidator(dateModelValidator, expirationDateModel, errors);
		Date expirationDate = expirationDateModel.getAsDate();
		if (dob != null && expirationDate != null && expirationDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].expirationDate", errorCode,
			    "Expiration Date should be later than lead's DOB.");
		}
	    }
	    // Visa Control
	    else if (numberType == "VC") {
		// number [100 chars, not null]
		// event date [date]
		if (ltIdentifyingNumber.getIdentifyingNumber() == null) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode, "Number field cannot be null.");
		}
		if (!StringUtils.isEmpty(ltIdentifyingNumber.getIdentifyingNumber()) && ltIdentifyingNumber.getIdentifyingNumber().length() > 100) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].number", errorCode,
			    "Number must be 100 characters or less.");
		}
		DateValueModel eventDateModel = in.getEventDate();
		ValidationUtils.invokeValidator(dateModelValidator, eventDateModel, errors);
		Date eventDate = eventDateModel.getAsDate();
		if (dob != null && eventDate != null && eventDate.compareTo(dob) <= 0) {
		    errors.rejectValue("ltLeadsModel[0].identifyingNumberModel[" + i + "].eventDate", errorCode,
			    "Event Date should be later than lead's DOB.");
		}
	    }
	    i++;
	}
    }
}