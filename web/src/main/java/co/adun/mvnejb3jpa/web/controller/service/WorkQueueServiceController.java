package co.adun.mvnejb3jpa.web.controller.service;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.web.model.PageModel;

@Controller
@RequestMapping(value = "/service/workqueue")
public class WorkQueueServiceController extends BaseServiceController {
    private static final Logger logger = Logger.getLogger(WorkQueueServiceController.class.getName());

    @Autowired(required = false)
    @Qualifier("leadService")
    private LeadService service;

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody String doService(String... params) {
	// TODO Auto-generated method stub
	return null;
    }

	@Override
	@RequestMapping(method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return null;
	}
}
