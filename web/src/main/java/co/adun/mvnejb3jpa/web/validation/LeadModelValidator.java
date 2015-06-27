package co.adun.mvnejb3jpa.web.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.web.model.DateValueModel;
import co.adun.mvnejb3jpa.web.model.IdentifyingNumberModel;
import co.adun.mvnejb3jpa.web.model.LeadModel;
import co.adun.mvnejb3jpa.web.model.LtLeadModel;

@Component
public class LeadModelValidator extends BaseValidator<LeadModel> {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LeadModelValidator.class.getName());

    @Autowired
    DateModelValidator dateModelValidator;

    @Autowired
    IdentifyingNumberValidator identifyingNumberValidator;

    @Override
    public void validate(Object obj, Errors errors) {
	LeadModel model = (LeadModel) obj;

	List<LtLeadModel> ltLeadsModel = model.getLtLeadsModel();
	int i = 0; // count for leads
	for (LtLeadModel ltLeadModel : ltLeadsModel) {
	    LtLead ltLead = ltLeadModel.getLtLead();
	    if (ltLead != null && !StringUtils.isEmpty(ltLeadModel.getFormId())) {
		DateValueModel birthDateModel = ltLeadModel.getBirthDateModel();
		ValidationUtils.invokeValidator(dateModelValidator, birthDateModel, errors);

		DateValueModel entryDateModel = ltLeadModel.getEntryDateModel();
		ValidationUtils.invokeValidator(dateModelValidator, entryDateModel, errors);

//		List<IdentifyingNumberModel> identifyingNumberModels = ltLeadModel.getIdentifyingNumberModel();
//		if (identifyingNumberModels != null) {
//		    for (IdentifyingNumberModel identifyingNumber : identifyingNumberModels) {
//			identifyingNumber.setBirthDateModel(birthDateModel);
//		    }
//		    ValidationUtils.invokeValidator(identifyingNumberValidator, identifyingNumberModels, errors);
//		}

		Date birthDate = new Date();
		Date entryDate = new Date();

		String errorCode = null;

		/*--------------------------------------------------
		 Template error functions:
		 
		// Source of Information
		errors.rejectValue("ltLeadsModel["+i+"].ltLeadSource.name", errorCode, errorMessage);

		// Source Title
		errors.rejectValue("ltLeadsModel["+i+"].ltLeadSource.title", errorCode, errorMessage);

		// Source Contact Type
		errors.rejectValue("ltLeadsModel["+i+"].ltLeadSource.ltLeadSourceContact.contactTypeCode.abbreviation", errorCode, errorMessage);

		// Source Contact
		errors.rejectValue("ltLeadsModel["+i+"].ltLeadSource.ltLeadSourceContact.contact", errorCode, errorMessage);

		// Lead Generated From
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.leadGeneratedFromCode.abbreviation", errorCode, errorMessage);

		// Lead Type
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.leadTypeCode.abbreviation", errorCode, errorMessage);

		// Comments
		errors.rejectValue("ltLeadsModel["+i+"].leadComment", errorCode, errorMessage);

		// Surname
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.ltSubject.lastname", errorCode, errorMessage);

		// Given name
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.ltSubject.firstname", errorCode, errorMessage);

		// Gender
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.ltSubject.genderCode.abbreviation", errorCode, errorMessage);

		// DOB
		errors.rejectValue("ltLeadsModel["+i+"].birthDateModel.value", errorCode, errorMessage);

		// COB
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.ltSubject.countryCode.abbreviation", errorCode, errorMessage);

		// Country of Citizenship
		errors.rejectValue("ltLeadsModel["+i+"].ltSubjectCitizenshipCountries[0].countryCode.abbreviation", errorCode, errorMessage);

		//Entry Date
		errors.rejectValue("ltLeadsModel["+i+"].entryDateModel.value", errorCode, errorMessage);

		//COA
		errors.rejectValue("ltLeadsModel["+i+"].ltLead.ltSubject.classAdmissionCode.abbreviation", errorCode, errorMessage);

		//Special Projects
		errors.rejectValue("ltLeadsModel["+i+"].ltLeadSpecialProjects[0].specialProjectCode.abbreviation", errorCode, errorMessage);

		---------------------------------------------*/

		// Required fields check for null, else check length if
		// necessary:

		if (ltLeadModel.getLtLeadSource().getName() == null) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.name", errorCode, "Source of Information: NULL value");
		} else if (ltLeadModel.getLtLeadSource().getName().length() > 100) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.name", errorCode, "Source of Information: too many characters");
		}
		if (ltLeadModel.getLtLeadSource().getTitle() == null) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.title", errorCode, "Source Title: NULL value");
		} else if (ltLeadModel.getLtLeadSource().getTitle().length() > 100) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.title", errorCode, "Source Title: too many characters");
		}
		if (ltLeadModel.getLtLeadSource().getContact() == null) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.contact", errorCode, "Source Contact: NULL value");
		} else if (ltLeadModel.getLtLeadSource().getContact().length() > 100) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.contact", errorCode, "Source Contact: too many characters");
		}
		if (ltLeadModel.getLtLead().getLtSubject().getFirstname() == null) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.ltSubject.firstname", errorCode, "Given Name: NULL value");
		} else if (ltLeadModel.getLtLead().getLtSubject().getFirstname().length() > 100) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.ltSubject.firstname", errorCode, "Given Name: too many characters");
		}
		if (ltLeadModel.getLtLead().getLtSubject().getLastname() == null) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.ltSubject.lastname", errorCode, "Surname: NULL value");
		} else if (ltLeadModel.getLtLead().getLtSubject().getLastname().length() > 100) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.ltSubject.lastname", errorCode, "Surname: too many characters");
		}

		// Check required dropdowns:
		if (ltLeadModel.getLtLeadSource().getContactTypeCode().getAbbreviation() == "Select...") {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLeadSource.ltLeadSourceContact.contactTypeCode.abbreviation", errorCode,
			    "Contact Type: default option selected");
		}
		if (ltLeadModel.getLtLead().getLeadGeneratedFromCode().getAbbreviation() == "Select...") {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.leadGeneratedFromCode.abbreviation", errorCode,
			    "Lead Generated From: default option selected");
		}
		if (ltLeadModel.getLtLead().getLeadTypeCode().getAbbreviation() == "Select...") {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltLead.leadTypeCode.abbreviation", errorCode, "Lead Type: default option selected");
		}

		// Comment length check:
		LtLeadComment ltLeadComment = ltLeadModel.getLtLeadComment();
		if (ltLeadComment != null && !StringUtils.isEmpty(ltLeadComment.getLeadComment()) && ltLeadComment.getLeadComment().length() > 4000) {
		    errors.rejectValue("ltLeadsModel[" + i + "].leadComment", errorCode, "Lead Comment: too many characters");
		}

		// COC/SP limit checks:
		Set<LtSubjectCitizenshipCountry> coc = ltLeadModel.getLtLead().getLtSubject().getLtSubjectCitizenshipCountries();

		// Sets don't allow duplicate elements, so no need to check.
		// Special Projects are unlimited in size, so just check COCs:
		if (coc.size() > 2) {
		    errors.rejectValue("ltLeadsModel[" + i + "].ltSubjectCitizenshipCountries[0].countryCode.abbreviation", errorCode,
			    "COCs limited to 2 or less.");
		}

		// validate DOB/ED date comparisons:
		birthDate = birthDateModel.getAsDate();
		entryDate = entryDateModel.getAsDate();

		if (birthDate != null && entryDate != null) {
		    Date currentDate = new Date();
		    if (birthDate.compareTo(entryDate) > 0) {
			errors.rejectValue("ltLeadsModel[" + i + "].birthDateModel.value", errorCode, "DOB is greater than Entry Date.");
		    } else if (birthDate.compareTo(currentDate) > 0) {
			errors.rejectValue("ltLeadsModel[" + i + "].birthDateModel.value", errorCode, "DOB is greater than current date.");
		    } else if (entryDate.compareTo(currentDate) > 0) {
			errors.rejectValue("ltLeadsModel[" + i + "].entryDateModel.value", errorCode, "Entry Date is greater than current date.");
		    }
		}

		i++;
	    }
	}
    }

    @Override
    public boolean supports(Class<?> clazz) {
	return LeadModel.class.isAssignableFrom(clazz) || ArrayList.class.isAssignableFrom(clazz);
    }
}