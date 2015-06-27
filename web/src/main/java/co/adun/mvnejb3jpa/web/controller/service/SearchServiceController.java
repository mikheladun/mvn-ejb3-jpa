package co.adun.mvnejb3jpa.web.controller.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.web.model.PageModel;
import co.adun.mvnejb3jpa.web.transform.SubjectTransformer;

@Controller
@RequestMapping(value = "/service/search")
public class SearchServiceController extends BaseServiceController {
	private static final Logger logger = Logger
			.getLogger(SearchServiceController.class.getName());

	@Autowired(required = false)
	@Qualifier("subjectService")
	private SubjectService service;

	@Autowired
	private SubjectTransformer transformer;

	@RequestMapping(value = "/subject", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	String findSubjectByLsidAndName(String lastName, String firstName,
			String lsid) {
		String subjectXML = null;
		try {
			logger.info("Enter Search Subject service search param: lastname "
					+ lastName + ", firstName " + firstName + " , lsid " + lsid);
			// create a LtSubject model object and use that to perform search
			LtSubject subject = new LtSubject();
			// set Last Name for search
			if (!StringUtils.isEmpty(lastName)) {
				subject.setLastname(lastName.trim());
			}
			// set First Name for search
			if (!StringUtils.isEmpty(firstName)) {
				subject.setFirstname(firstName.trim());
			}
			if(!StringUtils.isEmpty(lsid)){
				subject.setLsid(lsid);
			}
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			if (lsid != null && lsid.trim().length() > 0) {
				// leadSubject.setLsid(new Long(lsid.trim()));
				Set<LtLeadSubject> leadSubjSet = new HashSet<LtLeadSubject>();
				leadSubjSet.add(leadSubject);
				subject.setLtLeadSubjects(leadSubjSet);
			}
			int count = 0;
			List<LtSubject> subjectList = null;
			// search subjects by lastname, firstname and LSID
			if (firstName != null || lastName != null || lsid != null) {
				subjectList = service.findSubjectByLsidAndName(subject);
				count = subjectList != null ? subjectList.size() : 0;
				logger.info("Search Result count =" + count);
			}
			if (count > 0) {
				subjectXML = transformer.transform(subjectList);
			} else {
				logger.info("No record Found for search criteria: lastname "
						+ lastName + "," + " firstName " + firstName
						+ " , lsid " + lsid);
			}
		} catch (TransformerException te) {
			te.printStackTrace();
			logger.severe(te.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
		}

		return subjectXML;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	String doService(String... params) {
		return findSubjectByLsidAndName(params[0], params[1], params[2]);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody
	String doService(@Valid @ModelAttribute("model") PageModel model,
			BindingResult result, HttpServletRequest request) {
		return doService();
	}
}
