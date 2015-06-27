package co.adun.mvnejb3jpa.web.controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.web.controller.BaseController;
import co.adun.mvnejb3jpa.web.model.PageModel;

public abstract class BaseServiceController extends BaseController {

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request) {
		return null;
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/xml")
	public abstract @ResponseBody
	String doService(String... params);
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/xml")
	public abstract @ResponseBody
	String doService(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request);
}