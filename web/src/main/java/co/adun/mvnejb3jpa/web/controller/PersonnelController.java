package co.adun.mvnejb3jpa.web.controller;

import java.security.Principal;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;
import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.PageModel;

@Controller
@RequestMapping(value = "/personnel")
public class PersonnelController extends BaseController {
    private static final Logger logger = Logger.getLogger(PersonnelController.class.getName());

    @Autowired(required = false)
    @Qualifier("userService")
    public UserService service;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request) {
	Principal principal = request.getUserPrincipal();
	String name = SecurityContextHolder.getContext().getAuthentication().getName();
	// var userId = ${pageContext.request.userPrincipal.principal.id}

	Set<String> roles = WebSecurityContext.getUserRoles();
	String username = WebSecurityContext.getUsername();
//	LtUser user = service.getUser(username);
//	Long id = user.getId();
	String view = roles.contains(WebSecurityContext.UserRole.ANALYST_ROLE) ? "workQueue" : "redirect:/initiateLead";

	ModelAndView mav = new ModelAndView(view);
	setPageModel(model);

	return mav;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(@Valid @ModelAttribute("member") PageModel model, BindingResult result, HttpServletRequest request) {
	Set<String> roles = WebSecurityContext.getUserRoles();
	String view = roles.contains(WebSecurityContext.UserRole.ANALYST_ROLE) ? "workQueue" : "redirect:/initiateLead";

	ModelAndView mav = new ModelAndView(view);
	mav.addObject("mode", model);
	setPageModel(model);

	return mav;
    }
}