package co.adun.mvnejb3jpa.web.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.PageModel;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController extends BaseController {
    private static final Logger logger = Logger.getLogger(ProjectController.class.getName());

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request) {
	return new ModelAndView("project");
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(PageModel model, BindingResult result, HttpServletRequest request) {
	return new ModelAndView("project");
    }
}
