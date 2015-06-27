package co.adun.mvnejb3jpa.web.controller.service;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.adun.mvnejb3jpa.business.service.OrganizationService;
import co.adun.mvnejb3jpa.persistence.entity.MissionCodeUser;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.transform.MissionCodeUserTransformer;

@Controller
@RequestMapping(value = "/service/org")
public class OrganizationServiceController extends BaseServiceController {
	private static final Logger logger = Logger.getLogger(OrganizationServiceController.class.getName());
	
	@Autowired(required = false)
	@Qualifier("organizationService")
	private OrganizationService service;
	
	@Autowired
	private MissionCodeUserTransformer transformer;
	
	@Override
	@RequestMapping(value = "/missionCodeUsers", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	String doService(String... params) {
		String missionCodeUsers = null;
		try {
			// get all Supervisors and Analyst Association
			List<MissionCodeUser> missionCodeUsersList = service.getMissionCodeUsersList();
			
			missionCodeUsers = transformer.transform(missionCodeUsersList);
			
		} catch (TransformerException te) {
			logger.severe(te.getMessage());
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		
		return missionCodeUsers;
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return doService();
	}
}
