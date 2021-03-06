package co.adun.mvnejb3jpa.web.controller.service;

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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.LeadCommentsService;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.persistence.Persistable;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadComment;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.web.model.CommentPageModel;
import co.adun.mvnejb3jpa.web.model.CommentValueModel;
import co.adun.mvnejb3jpa.web.model.DateValueModel;
import co.adun.mvnejb3jpa.web.model.LeadSubjectModel;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.transform.EntityTransformer;
import co.adun.mvnejb3jpa.web.utils.PageModelUtils;
import co.adun.mvnejb3jpa.web.validation.LeadModelValidator;

@Controller
@RequestMapping(value = "/service/comments")
public class LeadCommentsServiceController extends BaseServiceController {
	private static final Logger logger = Logger.getLogger(LeadCommentsServiceController.class.getName());

	@Autowired(required = false)
	@Qualifier("leadCommentsService")
	LeadCommentsService leadCommentsService;

	@Autowired(required = false)
	@Qualifier("leadService")
	LeadService leadService;

	@Autowired
	LeadModelValidator validator;

	@Autowired
	LeadSubjectModel model;

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doPost(@Valid @ModelAttribute("model") PageModel model, BindingResult result, HttpServletRequest request) {
		return new ModelAndView("forward:/service/comments/post");
	}

	@Override
	@RequestMapping(value = "/post", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@ModelAttribute("model") PageModel pageModel, BindingResult result, HttpServletRequest request) {
		LeadSubjectModel model = (LeadSubjectModel) pageModel;

		StringBuffer response = new StringBuffer();
		try {
			if (!result.hasErrors()) {

				CommentPageModel commentModel = model.getCommentModel();
				List<CommentValueModel> cmvs = commentModel.getComments();
				List<LtLeadComment> ltLeadComments = new ArrayList<LtLeadComment>();
				for(CommentValueModel cmv : cmvs){
					if(!StringUtils.isEmpty(cmv.getUser())) {
						LtLeadComment ltLeadComment = new LtLeadComment();
						ltLeadComment.setId(cmv.getId());
						ltLeadComment.setLeadComment(cmv.getText());
						ltLeadComment.setCreateDate(PageModelUtils.getDate(cmv.getDate()));
						ltLeadComment.setModifiedDate(PageModelUtils.getDate(cmv.getDate()));
						LtUser ltUser = new LtUser();
						ltUser.setId(new Long(cmv.getUser().split(":")[0]));
						ltLeadComment.setLtUserByCreateBy(ltUser);
						ltLeadComment.setLtUserByModifiedBy(ltUser);
						ltLeadComment.setLtLead(model.getLtLead());
						ltLeadComments.add(ltLeadComment);
					}
				}
				if(ltLeadComments.isEmpty()) {
					leadCommentsService.deleteComments(model.getLtLead());
				} else {
					leadCommentsService.saveComments(ltLeadComments);
				}

				commentModel.setComments(new ArrayList<CommentValueModel>());
				commentModel.setLtLeadComments(leadService.getComments(model.getLtLead()));
				StringBuffer sb = new StringBuffer();
				EntityTransformer transformer = new EntityTransformer();
				for (Persistable entity : commentModel.getLtLeadComments()) {
					String xml = transformer.transform(entity, true);
					sb.append(xml);
				}

				response.append("<response status='success'>");
				response.append("<message code=''>");
				response.append(sb.toString());
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
        catch (TransformerException e) {
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