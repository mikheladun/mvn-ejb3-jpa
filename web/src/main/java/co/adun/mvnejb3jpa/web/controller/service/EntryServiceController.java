package co.adun.mvnejb3jpa.web.controller.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.EntryService;
import co.adun.mvnejb3jpa.persistence.entity.ClassAdmissionCode;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.PortEntryCode;
import co.adun.mvnejb3jpa.web.model.EntryInformationModel;
import co.adun.mvnejb3jpa.web.model.LeadSubjectModel;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.utils.PageModelUtils;
import co.adun.mvnejb3jpa.web.validation.LeadModelValidator;

@Controller
@RequestMapping(value = "/service/entry")
public class EntryServiceController extends BaseServiceController {
	private static final Logger logger = Logger.getLogger(EntryServiceController.class.getName());

	@Autowired(required = false)
	@Qualifier("entryService")
	EntryService entryService;

	@Autowired
	LeadModelValidator validator;

	@Autowired
	LeadSubjectModel model;

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return new ModelAndView("forward:/service/entry/post");
	}

	@Override
	@RequestMapping(value = "/post", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@ModelAttribute("model") PageModel pageModel, BindingResult result, HttpServletRequest request) {
		LeadSubjectModel model = (LeadSubjectModel) pageModel;

		StringBuffer response = new StringBuffer();
		try {
			if (!result.hasErrors()) {
				EntryInformationModel entryModel = model.getEntryModel();

				LtSubject ltSubject = new LtSubject();
				ltSubject.setId(model.getLtLead().getLtSubject().getId());

				ltSubject.setEntryDate(PageModelUtils.getDate(entryModel.getEntryDate()));
				ltSubject.setAdmitUntilDate(PageModelUtils.getDate(entryModel.getAdmitUntilDate()));
				ltSubject.setAdjustedAdmitUntilDate(PageModelUtils.getDate(entryModel.getAdjustedAdmitUntilDate()));
				ltSubject.setDepartureDate(PageModelUtils.getDate(entryModel.getDepartureDate()));
				ltSubject.setDurationStatus(entryModel.getDurationStatus() == null ? 'N' : entryModel.getDurationStatus().getValue().charAt(0));

				Long code = PageModelUtils.getCode(entryModel.getClassAdmissionCode().getAbbreviation());
				ClassAdmissionCode classAdmCode = null;
				if (code != null) {
					classAdmCode = new ClassAdmissionCode();
					classAdmCode.setId(code);
				}
				ltSubject.setClassAdmissionCode(classAdmCode);

				code = PageModelUtils.getCode(entryModel.getPortEntryCode().getAbbreviation());
				PortEntryCode portEntryCode = null;
				if (code != null) {
					portEntryCode = new PortEntryCode();
					portEntryCode.setId(code);
				}
				ltSubject.setPortEntryCode(portEntryCode);

				entryService.save(ltSubject);
				entryModel.setDurationStatus(null);

				response.append("<response status='success'>");
				response.append("<message code=''>");

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

	@ModelAttribute("model")
	public PageModel getPageModel() {
		return model;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	String doService(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
}