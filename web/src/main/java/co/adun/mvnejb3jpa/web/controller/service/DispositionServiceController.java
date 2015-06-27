package co.adun.mvnejb3jpa.web.controller.service;

import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
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
import co.adun.mvnejb3jpa.business.service.DispositionService;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.StatusCode;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;
import co.adun.mvnejb3jpa.web.model.DispositionModel;
import co.adun.mvnejb3jpa.web.model.LeadSubjectModel;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.utils.PageModelUtils;
import co.adun.mvnejb3jpa.web.validation.LeadModelValidator;

@Controller
@RequestMapping(value = "/service/disposition")
public class DispositionServiceController extends BaseServiceController {
	private static final Logger logger = Logger.getLogger(DispositionServiceController.class.getName());

	@Autowired(required = false)
	@Qualifier("dispositionService")
	DispositionService service;

	@Autowired
	LeadModelValidator validator;

	@Autowired
	LeadSubjectModel model;
	
	@Inject
	LeadService leadService;

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return new ModelAndView("forward:/service/disposition/post");
	}

	@Override
	@RequestMapping(value="/post", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@ModelAttribute("model") PageModel pageModel, BindingResult result, HttpServletRequest request) {
		LeadSubjectModel model = (LeadSubjectModel) pageModel;

		StringBuffer response = new StringBuffer();
		
		try {
			if (!result.hasErrors()) {
				LtLead ltLead = model.getLtLead();
				DispositionModel dispositionModel = model.getDispositionModel();

				StatusCode statusCode = dispositionModel.getStatusCode();
				statusCode.setId(PageModelUtils.getCode(statusCode.getAbbreviation()));
				String abbrv = PageModelUtils.getAbbreviation(dispositionModel.getStatusCode().getAbbreviation());
				if("CL".equalsIgnoreCase(abbrv)) { //Close Lead
					Long reasonCode = PageModelUtils.getCode(dispositionModel.getReasonCode().getAbbreviation()); //closure reason
					dispositionModel.getReasonCode().setId(reasonCode);
					Long systemCode = PageModelUtils.getCode(dispositionModel.getSystemCode().getAbbreviation()); //system reason
					dispositionModel.getSystemCode().setId(systemCode);

					Set<String> roles = WebSecurityContext.getUserRoles();
					if(roles.contains(WebSecurityContext.UserRole.ANALYST_ROLE.getRoleName())) {
						dispositionModel.getStatusCode().setId(104L);
						LtUser supervisor = dispositionModel.getSupervisor();						
						
						ltLead.setLtUserByLtAssignToUserId(supervisor);
					}
					
					
					
				} else if("PE".equalsIgnoreCase(abbrv)) { //Place Lead in Pending
					Long reasonCode = PageModelUtils.getCode(dispositionModel.getReasonCode().getAbbreviation()); //closure reason
					dispositionModel.getReasonCode().setId(reasonCode);
					Date callupDate = PageModelUtils.getDate(dispositionModel.getCallupDateModel()); //call up date
					ltLead.setCallUpDate(callupDate);
					
					//fix - kalyan
					
					Set<String> roles = WebSecurityContext.getUserRoles();
					if(roles.contains(WebSecurityContext.UserRole.ANALYST_ROLE.getRoleName())) {
						dispositionModel.getStatusCode().setId(104L);
						LtUser supervisor = dispositionModel.getSupervisor();						
						
						ltLead.setLtUserByLtAssignToUserId(supervisor);
					}
					
					//end
					
					
					
				} else if("HO".equalsIgnoreCase(abbrv)) { // Place Lead on Hold
					Long reasonCode = PageModelUtils.getCode(dispositionModel.getReasonCode().getAbbreviation()); //closure reason
					dispositionModel.getReasonCode().setId(reasonCode);
					String details = dispositionModel.getDetails(); // detail
					
				} else if("RT".equalsIgnoreCase(abbrv)) { // Return Lead
					Long reasonCode = PageModelUtils.getCode(dispositionModel.getReasonCode().getAbbreviation()); //closure reason
					dispositionModel.getReasonCode().setId(reasonCode);
					String details = dispositionModel.getDetails(); // detail
					LtUser analyst = dispositionModel.getAnalyst();
					ltLead.setLtUserByLtAssignToUserId(analyst);
				}

				ltLead.setStatusCode(dispositionModel.getStatusCode());
				ltLead.setDisposCloseReasonCode(dispositionModel.getReasonCode());
				ltLead.setDisposCloseSystemCode(dispositionModel.getSystemCode());
				ltLead.setId(model.getLtLead().getId());
					
				service.saveDisposition(ltLead);

				response.append("<response status='success'>");
				response.append("<message code=''>");

				response.append("</message>");

			} else {
				response.append("<response status='error'>");

				for (FieldError error : result.getFieldErrors()) {
					response.append("<error code='").append(error.getField()).append("' message='").append(error.getDefaultMessage()).append("' />");
				}
			}
		} catch (BusinessException e) {
			response.append("<response status='error'>");
			response.append("<error code='").append("").append("' message='").append(e.getMessage()).append("' />");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
		}

		return response.append("</response>").toString();
	}

	@ModelAttribute("model")
	public PageModel getPageModel() {
		return this.model;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	String doService(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
}