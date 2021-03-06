package co.adun.mvnejb3jpa.web.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.PageModel;

@RequestMapping(value = "/")
public abstract class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class.getName());

    PageModel model;

    @RequestMapping(method = RequestMethod.GET)
    public abstract ModelAndView doGet(HttpServletRequest request);
    @RequestMapping(method = RequestMethod.POST)
    public abstract ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request);

    @ModelAttribute("model")
    public PageModel getPageModel() {
	return model;
    }
    public void setPageModel(PageModel model) {
	this.model = model;
    }
}
