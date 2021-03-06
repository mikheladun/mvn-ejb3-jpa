package co.adun.mvnejb3jpa.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.OrganizationService;
import co.adun.mvnejb3jpa.business.service.SupportDataService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.entity.ApplicationLocationCode;
import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.ContactTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.EventTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.GenderCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadGeneratedFromCode;
import co.adun.mvnejb3jpa.persistence.entity.LeadTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.LicenseTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedLead;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSpecialProject;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectContact;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;
import co.adun.mvnejb3jpa.persistence.entity.NumberTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.PortEntryCode;
import co.adun.mvnejb3jpa.persistence.entity.RelationshipCode;
import co.adun.mvnejb3jpa.persistence.entity.SourceCode;
import co.adun.mvnejb3jpa.persistence.entity.SpecialProjectCode;
import co.adun.mvnejb3jpa.persistence.entity.StateProvinceCode;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;
import co.adun.mvnejb3jpa.persistence.entity.VisaClassCode;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;
import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.AssociatedLeadModel;
import co.adun.mvnejb3jpa.web.model.DateValueModel;
import co.adun.mvnejb3jpa.web.model.IdentifyingNumberModel;
import co.adun.mvnejb3jpa.web.model.LeadModel;
import co.adun.mvnejb3jpa.web.model.LtLeadModel;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.model.SupportDataModel;
import co.adun.mvnejb3jpa.web.model.ValueModel;
import co.adun.mvnejb3jpa.web.utils.PageModelUtils;
import co.adun.mvnejb3jpa.web.validation.LeadModelValidator;

/**
 * @author Mikhel Adun
 */
@Controller
@RequestMapping(value = "/initiateLead")
public class InitiateLeadController extends BaseController {
	private static final Logger logger = Logger.getLogger(InitiateLeadController.class.getName());

	@Autowired(required = false)
	@Qualifier("leadService")
	LeadService leadService;

	@Autowired(required = false)
	@Qualifier("userService")
	UserService userService;

	@Autowired(required = false)
	@Qualifier("supportDataService")
	SupportDataService supportDataService;

	@Autowired(required = false)
	@Qualifier("organizationService")
	private OrganizationService organizationService;

	@Autowired
	LeadModelValidator validator;

	@Autowired
	LeadModel model;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request) {

		LeadModel model = new LeadModel();
		setup(model);

		ModelAndView mav = new ModelAndView("initiateLead", "model", model);

		return mav;
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return new ModelAndView("forward:/initiateLead/ajaxPost");
	}

	@RequestMapping(value = "/ajaxPost", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doAjaxPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		logger.info("InitiateLeadController doPost()" + model);

		LeadModel leadModel = (LeadModel) model;

		StringBuffer response = new StringBuffer();
		try {
			if (!result.hasErrors()) {
				List<LtLead> ltLeads = process(leadModel);

				ltLeads = leadService.save(ltLeads);

				response.append("<response status='success'>");
				response.append("<message code=''>");

				for (LtLead ltLead : ltLeads) {
					// TODO avoid DB fetch inside loop
					// TODO use entity transformer
					ltLead = leadService.getLead(ltLead.getId());
					LtSubject ltSubject = ltLead.getLtSubject();
					String name = ltSubject.getLastname() + ", " + ltSubject.getFirstname();
					String lsid = ltSubject.getLsid();
					response.append("<subject name='").append(name).append("' id='").append(lsid).append("' />");
				}

				response.append("</message>");

			}
			else {
				response.append("<response status='error'>");

				for (FieldError error : result.getFieldErrors()) {
					response.append("<error code='").append(error.getField()).append("' message='").append(error.getDefaultMessage()).append("' />");
				}
			}
		}
		catch (BusinessException e) {

			response.append("<response status='error'>");
			response.append("<error code='").append("").append("' message='").append(e.getMessage()).append("' />");
			logger.log(Level.SEVERE, e.getMessage(), e);

		}
		finally {
		}

		return response.append("</response>").toString();
	}

	private List<LtLead> process(LeadModel model) throws BusinessException {

		List<LtLeadModel> ltLeadsModel = model.getLtLeadsModel();
		List<LtLead> ltLeads = new ArrayList<LtLead>();
		LtUser user = userService.getCurrentUser(WebSecurityContext.getUsername());

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

		for (LtLeadModel ltLeadModel : ltLeadsModel) {
			LtLead ltLead = ltLeadModel.getLtLead();

			if (ltLead != null && !StringUtils.isEmpty(ltLeadModel.getFormId())) {

				DateValueModel birthDateModel = ltLeadModel.getBirthDateModel();
				if (!StringUtils.isEmpty(birthDateModel.getValue())) {
					Date date = null;
					try {
						date = dateFormat.parse(birthDateModel.getValue());
					}
					catch (ParseException e) {
						logger.info(e.getMessage());
					}
					ltLead.getLtSubject().setBirthDate(date);
				}

				DateValueModel entryDateModel = ltLeadModel.getEntryDateModel();
				if (!StringUtils.isEmpty(entryDateModel.getValue())) {
					Date date = null;
					try {
						date = dateFormat.parse(entryDateModel.getValue());
					}
					catch (ParseException e) {
						logger.info(e.getMessage());
					}
					ltLead.getLtSubject().setEntryDate(date);
				}

				MissionCode missionCode = ltLead.getMissionCode();

				// if Mission Box is selected by not Supervisor and Analyst,
				// assign
				// Lead to mission box
				boolean assignToSup = false;
				boolean assignToAna = false;
				logger.info("Calling leadService.save");

				/*
				 * If the Analyst is selected, the lead is assigned to the
				 * analyst. If analyst is not selected and Supervisor is
				 * selected, then the lead is assigned to the Supervisor
				 */

				// assign to supervisor, if it is selected

				ValueModel analystModel = ltLeadModel.getAnalystModel();
				if (!StringUtils.isEmpty(analystModel.getValue())) {
					LtUser ltUserByLtAssignToUserId = new LtUser();
					ltUserByLtAssignToUserId.setId(new Long(analystModel.getValue()));
					ltLead.setLtUserByLtAssignToUserId(ltUserByLtAssignToUserId);
					assignToAna = true;
				}

				ValueModel supervisorModel = ltLeadModel.getSupervisorModel();
				logger.info("Supervisor model value=" + supervisorModel.getValue());
				if (!assignToAna) {
					if (!StringUtils.isEmpty(supervisorModel.getValue())) {
						LtUser ltUserByLtAssignToUserId = new LtUser();
						ltUserByLtAssignToUserId.setId(new Long(supervisorModel.getValue()));
						ltLead.setLtUserByLtAssignToUserId(ltUserByLtAssignToUserId);
						assignToSup = true;
					}
				}

				StatusCode statusCode = new StatusCode();
				if (!assignToSup && !assignToAna) {
					// TODO refactor to Constants
					if (missionCode != null) {
						setMissionCodeToLead(missionCode, ltLead);
					}
					else {
						ltLead.setMissionCode(null);
					}
					// set Lead to unassigned status, when it is not assigned to
					// a
					// person
					statusCode.setId(100L); // Unassigned cod
				}

				// Lead is in assigned status, when is is assigned to a person
				if (assignToSup == true || assignToAna == true) {
					statusCode.setId(101L); // assigned code
					ltLead.setMissionCode(null);
				}

				ltLead.setStatusCode(statusCode);

				LeadGeneratedFromCode leadGeneratedFromCode = ltLead.getLeadGeneratedFromCode();
				String abbreviation = leadGeneratedFromCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						leadGeneratedFromCode.setId(new Long(strings[0]));
						leadGeneratedFromCode.setAbbreviation(strings[1]);
					}
				}

				LeadTypeCode leadTypeCode = ltLead.getLeadTypeCode();
				abbreviation = leadTypeCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						leadTypeCode.setId(new Long(strings[0]));
						leadTypeCode.setAbbreviation(strings[1]);
					}
				}

				Set<LtLeadSource> ltLeadSources = new HashSet<LtLeadSource>();
				LtLeadSource ltLeadSource = ltLeadModel.getLtLeadSource();
				ContactTypeCode contactTypeCode = ltLeadSource.getContactTypeCode();
				abbreviation = contactTypeCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						contactTypeCode.setId(new Long(strings[0]));
						contactTypeCode.setAbbreviation(strings[1]);
						ltLeadSource.setLtLead(ltLead);
						ltLeadSources.add(ltLeadSource);
					}
				}
				ltLead.setLtLeadSources(ltLeadSources);

				Set<LtLeadComment> ltLeadComments = new HashSet<LtLeadComment>();
				LtLeadComment ltLeadComment = ltLeadModel.getLtLeadComment();
				if (ltLeadComment != null && !StringUtils.isEmpty(ltLeadComment.getLeadComment())) {
					ltLeadComments.add(ltLeadComment);
				}
				ltLead.setLtLeadComments(ltLeadComments);

				ClassAdmissionCode classAdmissionCode = ltLead.getLtSubject().getClassAdmissionCode();
				abbreviation = classAdmissionCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						classAdmissionCode.setId(new Long(strings[0]));
						classAdmissionCode.setAbbreviation(strings[1]);
					}
					else {
						ltLead.getLtSubject().setClassAdmissionCode(null);
					}
				}
				else {
					ltLead.getLtSubject().setClassAdmissionCode(null);
				}

				CountryCode countryCode = ltLead.getLtSubject().getCountryCode();
				abbreviation = countryCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						countryCode.setId(new Long(strings[0]));
						countryCode.setAbbreviation(strings[1]);
					}
					else {
						ltLead.getLtSubject().setCountryCode(null);
					}
				}
				else {
					ltLead.getLtSubject().setCountryCode(null);
				}

				GenderCode genderCode = ltLead.getLtSubject().getGenderCode();
				abbreviation = genderCode.getAbbreviation();
				if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
					String[] strings = StringUtils.split(abbreviation, ":");
					if (strings != null) {
						genderCode.setId(new Long(strings[0]));
						genderCode.setAbbreviation(strings[1]);
					}
					else {
						ltLead.getLtSubject().setGenderCode(null);
					}
				}
				else {
					ltLead.getLtSubject().setGenderCode(null);
				}

				Set<LtLeadSpecialProject> ltLeadSpecialProjects = new HashSet<LtLeadSpecialProject>();
				for (LtLeadSpecialProject ltLeadSpecialProject : ltLeadModel.getLtLeadSpecialProjects()) {
					SpecialProjectCode specialProjectCode = ltLeadSpecialProject.getSpecialProjectCode();
					abbreviation = specialProjectCode.getAbbreviation();
					if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
						String[] strings = StringUtils.split(abbreviation, ":");
						if (strings != null) {
							specialProjectCode.setId(new Long(strings[0]));
							specialProjectCode.setAbbreviation(strings[1]);
							ltLeadSpecialProject.setLtLead(ltLead);
							ltLeadSpecialProjects.add(ltLeadSpecialProject);
						}
					}
				}
				ltLead.setLtLeadSpecialProjects(ltLeadSpecialProjects);

				Set<LtSubjectCitizenshipCountry> ltSubjectCitizenshipCountries = new HashSet<LtSubjectCitizenshipCountry>();
				for (LtSubjectCitizenshipCountry ltSubjectCitizenshipCountry : ltLeadModel.getLtSubjectCitizenshipCountries()) {
					CountryCode cocCountryCode = ltSubjectCitizenshipCountry.getCountryCode();
					abbreviation = cocCountryCode.getAbbreviation();
					if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
						String[] strings = StringUtils.split(abbreviation, ":");
						if (strings != null) {
							cocCountryCode.setId(new Long(strings[0]));
							cocCountryCode.setAbbreviation(strings[1]);
							ltSubjectCitizenshipCountry.setLtSubject(ltLead.getLtSubject());
							ltSubjectCitizenshipCountries.add(ltSubjectCitizenshipCountry);
						}
					}
				}
				ltLead.getLtSubject().setLtSubjectCitizenshipCountries(ltSubjectCitizenshipCountries);

				Set<LtAssociatedLead> ltAssociatedLeads = new HashSet<LtAssociatedLead>();
				Set<LtAssociatedSubject> ltAssociatedSubjects = new HashSet<LtAssociatedSubject>();
				List<AssociatedLeadModel> associates = ltLeadModel.getAssociateModel();
				for (AssociatedLeadModel associateModel : associates) {
					String id = associateModel.getValue();
					if (!StringUtils.isEmpty(id)) {

						RelationshipCode code = associateModel.getRelationshipCode();
						code.setId(PageModelUtils.getCode(code.getAbbreviation()));

						LtSubject ltAssociate = ltLeadsModel.get(Integer.parseInt(id)).getLtLead().getLtSubject();
						LtAssociatedSubject ltAssociatedSubject = new LtAssociatedSubject();
						ltAssociatedSubject.setLtSubject(ltLead.getLtSubject());
						ltAssociatedSubject.setLtSubjectAssociate(ltAssociate);
						ltAssociatedSubject.setRelationshipCode(code);
						ltAssociatedSubjects.add(ltAssociatedSubject);

						LtLead ltAssociateLead = ltLeadsModel.get(Integer.parseInt(id)).getLtLead();
						LtAssociatedLead ltAssociatedLead = new LtAssociatedLead();
						ltAssociatedLead.setLtLeadByLtLeadId(ltLead);
						ltAssociatedLead.setLtLeadByLtAssociatedLeadId(ltAssociateLead);
						ltAssociatedLeads.add(ltAssociatedLead);
					}
				}
				ltLead.setLtAssociatedLeadsForLtLeadId(ltAssociatedLeads);
				ltLead.getLtSubject().setLtAssociatedSubjects(ltAssociatedSubjects);

				Set<LtLeadSubject> ltLeadSubjects = new HashSet<LtLeadSubject>();
				LtLeadSubject ltLeadSubject = ltLeadModel.getLtLeadSubject();
				ltLeadSubject.setLtLead(ltLead);
				ltLeadSubject.setLtSubject(ltLead.getLtSubject());
				ltLeadSubjects.add(ltLeadSubject);
				ltLead.setLtLeadSubjects(ltLeadSubjects);

				ltLead.setLtUserByCreateBy(user);
				ltLead.setLtUserByModifiedBy(user);

				ltLeads.add(ltLead);
			}
		}

		return ltLeads;
	}

	private void setup(LeadModel model) {

		try {
			setupFormData(model);
			setupSupportData(model);
			setupOrganizationData(model);
			LtUser user = userService.getCurrentUser(WebSecurityContext.getUsername());
			model.setCurrentUser(user);

			setPageModel(model);

		}
		catch (BusinessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void setupOrganizationData(LeadModel model) throws BusinessException {

		// get all users
		List<LtUser> allAnalysts = organizationService.getAllAnalysts();
		model.setAnalystsList(allAnalysts);

		// get supervisor list
		List<LtUser> allSupervisors = organizationService.getAllSupervisors();
		model.setSupervisorsList(allSupervisors);
	}

	private void setupSupportData(LeadModel model) throws BusinessException {

		SupportDataModel supportDataModel = new SupportDataModel();
		model.setSupportDataModel(supportDataModel);

		List<LeadTypeCode> leadCodes = supportDataService.getLeadTypeCodes();
		supportDataModel.setLeadTypeCodes(leadCodes);

		List<GenderCode> genderCodes = supportDataService.getGenderCodes();
		supportDataModel.setGenderCodes(genderCodes);

		List<ClassAdmissionCode> classAdmissionCodes = supportDataService.getClassAdmCodes();
		supportDataModel.setClassAdmissionCodes(classAdmissionCodes);

		List<CountryCode> countryCodes = supportDataService.getCountryCodes();
		supportDataModel.setCountryCodes(countryCodes);

		List<MissionCode> missionCodes = supportDataService.getMissionCodes();
		supportDataModel.setMissionCodes(missionCodes);

		List<NumberTypeCode> nmbrTypeCodes = supportDataService.getNmbrTypeCodes();
		supportDataModel.setNumberTypeCodes(nmbrTypeCodes);

		List<PortEntryCode> portEntryCodes = supportDataService.getPortOfEntryCodes();
		supportDataModel.setPortEntryCodes(portEntryCodes);

		List<SourceCode> sourceCodes = supportDataService.getSourceCodes();
		supportDataModel.setSourceCodes(sourceCodes);

		List<SpecialProjectCode> specProjCodes = supportDataService.getSpecProjCodes();
		supportDataModel.setSpecialProjectCodes(specProjCodes);

		List<LeadGeneratedFromCode> leadGenFrmCodes = supportDataService.getLeadGenFrmCodes();
		supportDataModel.setLeadGeneratedFromCodes(leadGenFrmCodes);

		List<ContactTypeCode> contactTypeCodes = supportDataService.getContactTypeCodes();
		supportDataModel.setContactTypeCodes(contactTypeCodes);

		List<RelationshipCode> relationshipCodes = supportDataService.getRelationshipCodes();
		supportDataModel.setRelationshipCodes(relationshipCodes);

		List<StatusCode> statusCodes = supportDataService.getStatusCodes();
		supportDataModel.setStatusCodes(statusCodes);

		List<EventTypeCode> eventTypes = supportDataService.getEventTypeCodes();
		supportDataModel.setEventTypes(eventTypes);

		List<VisaClassCode> visaClassCodes = supportDataService.getVisaClassCodes();
		supportDataModel.setVisaClassCodes(visaClassCodes);

		List<StateProvinceCode> stateProvince = supportDataService.getStateProvinceCodes();
		supportDataModel.setStateProvince(stateProvince);

		List<ApplicationLocationCode> applocs = supportDataService.getApplicationLocationCodes();
		supportDataModel.setApplocs(applocs);

		List<LicenseTypeCode> licenseCodes = supportDataService.getLicenseTypeCodes();
		supportDataModel.setLicenseCodes(licenseCodes);

		List<StatusCode> tecsStatusCodes = supportDataService.getStatusCodesByType("Tecs");
		supportDataModel.setTecsStatusCodes(tecsStatusCodes);

		model.setSupportDataModel(supportDataModel);
	}

	private void setupFormData(LeadModel model) {
		LtLeadModel ltLeadModel = new LtLeadModel();

		LtLead ltLead = new LtLead();
		ltLeadModel.setLtLead(ltLead);

		MissionCode missionCode = new MissionCode();
		ltLead.setMissionCode(missionCode);

		LeadTypeCode leadTypeCode = new LeadTypeCode();
		ltLead.setLeadTypeCode(leadTypeCode);

		LeadGeneratedFromCode leadGeneratedFromCode = new LeadGeneratedFromCode();
		ltLead.setLeadGeneratedFromCode(leadGeneratedFromCode);

		StatusCode statusCode = new StatusCode();
		statusCode.setId(101L);
		ltLead.setStatusCode(statusCode);

		LtLeadSpecialProject ltLeadSpecialProject = new LtLeadSpecialProject();
		SpecialProjectCode specialProjectCode = new SpecialProjectCode();
		ltLeadSpecialProject.setSpecialProjectCode(specialProjectCode);
		List<LtLeadSpecialProject> ltLeadSpecialProjects = new ArrayList<LtLeadSpecialProject>();
		ltLeadSpecialProjects.add(ltLeadSpecialProject);
		ltLeadModel.setLtLeadSpecialProjects(ltLeadSpecialProjects);

		LtSubject ltSubject = new LtSubject();
		GenderCode genderCode = new GenderCode();
		ltSubject.setGenderCode(genderCode);
		ClassAdmissionCode classAdmissionCode = new ClassAdmissionCode();
		ltSubject.setClassAdmissionCode(classAdmissionCode);
		CountryCode countryCode = new CountryCode();
		ltSubject.setCountryCode(countryCode);

		LtSubjectContact ltSubjectContact = new LtSubjectContact();
		ltSubjectContact.setLtSubject(ltSubject);

		LtSubjectCitizenshipCountry coc1 = new LtSubjectCitizenshipCountry();
		CountryCode countryCode1 = new CountryCode();
		coc1.setCountryCode(countryCode1);
		coc1.setLtSubject(ltSubject);
		LtSubjectCitizenshipCountry coc2 = new LtSubjectCitizenshipCountry();
		CountryCode countryCode2 = new CountryCode();
		coc2.setCountryCode(countryCode2);
		coc2.setLtSubject(ltSubject);
		List<LtSubjectCitizenshipCountry> cocList = new ArrayList<LtSubjectCitizenshipCountry>();
		cocList.add(coc1);
		cocList.add(coc2);
		ltLeadModel.setLtSubjectCitizenshipCountries(cocList);

		ltLead.setLtSubject(ltSubject);

		LtLeadSource ltLeadSource = new LtLeadSource();
		ContactTypeCode contactTypeCode = new ContactTypeCode();
		ltLeadSource.setContactTypeCode(contactTypeCode);
		ltLeadSource.setLtLead(ltLead);
		ltLeadModel.setLtLeadSource(ltLeadSource);

		LtLeadComment ltLeadComment = new LtLeadComment();
		ltLeadComment.setLtLead(ltLead);
		ltLeadModel.setLtLeadComment(ltLeadComment);

		LtLeadSubject ltLeadSubject = new LtLeadSubject();
		ltLeadSubject.setLtLead(ltLead);
		ltLeadSubject.setLtSubject(ltSubject);
		ltLeadModel.setLtLeadSubject(ltLeadSubject);

		DateValueModel birthDateModel = new DateValueModel();
		birthDateModel.setRef(ltLead);
		ltLeadModel.setEntryDateModel(birthDateModel);

		DateValueModel entryDateModel = new DateValueModel();
		entryDateModel.setRef(ltLead);
		ltLeadModel.setBirthDateModel(entryDateModel);

		ValueModel supervisorModel = new ValueModel();
		supervisorModel.setRef(ltLead);
		ltLeadModel.setSupervisorModel(supervisorModel);

		ValueModel analystModel = new ValueModel();
		analystModel.setRef(ltLead);
		ltLeadModel.setAnalystModel(analystModel);

		// Associated Subjects
		List<AssociatedLeadModel> associates = new ArrayList<AssociatedLeadModel>();
		AssociatedLeadModel associateModel = new AssociatedLeadModel();
		associateModel.setRelationshipCode(new RelationshipCode());
		associateModel.setRef(ltLead);
		associates.add(associateModel);
		ltLeadModel.setAssociateModel(associates);

		// Identifying Numbers
		List<IdentifyingNumberModel> identifyingNumbers = new ArrayList<IdentifyingNumberModel>();
		IdentifyingNumberModel numberModel = new IdentifyingNumberModel();
		identifyingNumbers.add(numberModel);
		ltLeadModel.setIdentifyingNumberModel(identifyingNumbers);

		List<LtLeadModel> ltLeadsModel = new ArrayList<LtLeadModel>();
		ltLeadsModel.add(ltLeadModel);

		model.setLtLeadModel(ltLeadsModel);
	}

	/**
	 * Set Mission code to Lead for commits.
	 * 
	 * @param missionCode
	 * @param ltLead
	 */

	private void setMissionCodeToLead(MissionCode missionCode, LtLead ltLead) {

		String abbreviation = missionCode.getAbbreviation();
		if (!StringUtils.isEmpty(abbreviation)) {
			String[] strings = StringUtils.split(abbreviation, ":");
			if (strings != null && !"None".equalsIgnoreCase(strings[1])) {
				missionCode.setId(new Long(strings[0]));
				missionCode.setAbbreviation(strings[1]);
			}
			else {
				ltLead.setMissionCode(null);
			}
		}

	}
}