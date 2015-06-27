package co.adun.mvnejb3jpa.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.OrganizationService;
import co.adun.mvnejb3jpa.business.service.SubjectTravelService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.Persistable;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectTravel;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;
import co.adun.mvnejb3jpa.web.cache.SupportDataCache;
import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.BiographicInformationModel;
import co.adun.mvnejb3jpa.web.model.CommentPageModel;
import co.adun.mvnejb3jpa.web.model.DispositionModel;
import co.adun.mvnejb3jpa.web.model.EntryInformationModel;
import co.adun.mvnejb3jpa.web.model.LeadSubjectModel;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.model.TravelInformationModel;
import co.adun.mvnejb3jpa.web.model.ValueModel;
import co.adun.mvnejb3jpa.web.transform.EntityTransformer;

/**
 * @author Mikhel Adun
 */
@Controller
@RequestMapping(value = "/viewLead")
public class ViewLeadController extends BaseController {
	private static final Logger logger = Logger.getLogger(ViewLeadController.class.getName());

	@Autowired(required = false)
	@Qualifier("leadService")
	LeadService leadService;

	@Autowired(required = false)
	@Qualifier("subjectTravelService")
	SubjectTravelService subjectTravelService;

	@Autowired(required = false)
	@Qualifier("userService")
	UserService userService;

	@Autowired(required = false)
	@Qualifier("supportDataCache")
	SupportDataCache supportDataCache;

	@Autowired(required = false)
	@Qualifier("organizationService")
	private OrganizationService organizationService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(validator);
	}

	@Override
	public ModelAndView doGet(HttpServletRequest request) {
		String leadId = request.getParameter("leadId");
		return new ModelAndView("forward:/viewLead/" + leadId);
	}

	@RequestMapping(value = "/{leadId}", method = RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request, @PathVariable("leadId") String leadId) {
		try {
			this.model = new LeadSubjectModel();
			Long id = new Long(leadId);

			// set Lead Status to 'OPEN' if previous status was 'Assigned'
			if(leadService.getLead(id).getStatusCode().getId() == 101L){
				leadService.setLeadStatusById(id, 102L);
			}
			LtLead ltLead = leadService.getLead(id);
			((LeadSubjectModel)model).setLtLead(ltLead);

			BiographicInformationModel biographicModel = new BiographicInformationModel();
			biographicModel.setCocs(new ArrayList<LtSubjectCitizenshipCountry>());
			((LeadSubjectModel)model).setBiographicModel(biographicModel);

			EntryInformationModel entryModel = new EntryInformationModel();
			ValueModel durationStatus = new ValueModel();
			durationStatus.setValue(Character.toString(ltLead.getLtSubject().getDurationStatus() == null ? 'N' : ltLead.getLtSubject().getDurationStatus()));
			entryModel.setDurationStatus(durationStatus);
			((LeadSubjectModel)model).setEntryModel(entryModel);

			CommentPageModel commentModel = new CommentPageModel();
			List<LtLeadComment> ltLeadComments = leadService.getComments(ltLead);
			commentModel.setLtLeadComments(ltLeadComments);
			((LeadSubjectModel)model).setCommentModel(commentModel);

			TravelInformationModel travelModel = new TravelInformationModel();
			LtSubjectTravel ltSubjectTravel = subjectTravelService.getSubjectTravel(ltLead.getLtSubject());
			travelModel.setLtSubjectTravel(ltSubjectTravel == null ? new LtSubjectTravel() : ltSubjectTravel);
			((LeadSubjectModel)model).setTravelModel(travelModel);

			DispositionModel dispositionModel = new DispositionModel();
			LtUser curUser = userService.getCurrentUser(WebSecurityContext.getUsername());
			
			Long roleCode = curUser.getUserRoleCode().getId();
			
			if (roleCode.equals(new Long(101L)) || roleCode.equals(new Long(102L))){ // Pull in all Supervisors for the analyst
				List<LtUser> supervisors = organizationService.getAllSupervisorForAnAnalystsId(curUser.getId());
				dispositionModel.setSupervisors(supervisors);
			} else
				if (roleCode.equals(new Long(100L))){ // Pull in all Analysts under the supervisor
					List<LtUser> analysts = organizationService.getAllAnalystsforSupervisorId(curUser.getId());
					dispositionModel.setAnalysts(analysts);
				}	
			((LeadSubjectModel)model).setDispositionModel(dispositionModel);
			

			setPageModel(model);

			//TODO move to CommentPageModel or CommentServiceController
			EntityTransformer transformer = new EntityTransformer();
			StringBuffer sb = new StringBuffer();
			for (Persistable entity : commentModel.getLtLeadComments()) {
				String xml = transformer.transform(entity, true);
				sb.append(xml);
			}
			request.setAttribute("commentsXml", sb.toString().trim());
			//TODO move to application scope
			request.setAttribute("supportDataCache", supportDataCache.getMap());
			LtUser user = userService.getCurrentUser(WebSecurityContext.getUsername());
			request.setAttribute("currentUser", user);

		} catch(TransformerException  e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (BusinessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
		}
		
		ModelAndView mav = new ModelAndView("viewLead", "model", model);
		
		return mav;
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return doGet(request);
	}	
}