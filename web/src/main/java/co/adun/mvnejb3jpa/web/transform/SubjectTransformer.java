package co.adun.mvnejb3jpa.web.transform;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import co.adun.mvnejb3jpa.persistence.Persistable;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;

@Component
public class SubjectTransformer extends BaseTransformer {

	private static final Logger logger = Logger
			.getLogger(SubjectTransformer.class.getName());

	/**
	 * Create an xml map for list of subjects and related info
	 */
	public String transform(List<LtSubject> entitiesList)
			throws TransformerException {
		String xmlString = null;

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			// root elements
			Element root = doc.createElement("subjectsearchresult");
			doc.appendChild(root);

			Long lastSubjectId = 0L;
			Element element = null;

			for (LtSubject entity : entitiesList) {
				LtSubject subject = (LtSubject) entity;
				// for each subject, populate subject and related lead info.
				if (subject.getId() != null) {
					if (lastSubjectId != subject.getId()) {
						element = doc.createElement("subject");
						root.appendChild(element);
						// set attribute to start element
						element.setAttribute("id", subject.getId().toString());
						// Add Subject first name elements to xml
						addSubjectInfoToXML(subject, doc, element);
						// Add Lead related data to XML
						addLeadSubjectInfoToXML(subject, doc, element);
						// save last subject added to xml
						lastSubjectId = (Long) subject.getId();
					}

				}
			}
			xmlString = this.transform(doc);

			logger.info("Subject List Xml" + xmlString);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			logger.severe(pce.getMessage());
			throw new TransformerException(pce);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlString;
	}

	// Add Subject related data to XML
	private void addSubjectInfoToXML(LtSubject subject, Document doc,
			Element element) throws ParseException {
		DateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",
				Locale.ENGLISH);
		DateFormat dateFormatNeeded = new SimpleDateFormat("MM/dd/yyyy");

		Element firstNameElem = doc.createElement("firstname");
		firstNameElem.appendChild(doc.createTextNode(subject.getFirstname()
				.toString()));
		element.appendChild(firstNameElem);
		// Add Subject last name elements to xml
		Element lastNameElem = doc.createElement("lastname");
		lastNameElem.appendChild(doc.createTextNode(subject.getLastname()
				.toString()));
		element.appendChild(lastNameElem);
		// Add Subject date of birth elements to xml
		if (subject.getBirthDate() != null) {
			Element dateOfBirthElem = doc.createElement("birthdate");
			// covert birthdate into mm/dd/yyyy format
			Date date = dbDateFormat.parse(subject.getBirthDate().toString());
			String dateOfBirth = dateFormatNeeded.format(date);
			dateOfBirthElem.appendChild(doc.createTextNode(dateOfBirth));
			element.appendChild(dateOfBirthElem);
		}
		// Add Subject place of birth elements to xml
		if (subject.getCountryCode() != null) {
			Element placeOfBirthElem = doc.createElement("countryofbirth");
			System.out.println(" Country Code =" + subject.getCountryCode());
			placeOfBirthElem.appendChild(doc.createTextNode(subject
					.getCountryCode().getDescription().toString()));
			element.appendChild(placeOfBirthElem);
		}
		// Add subject gender code elements to xml
		if (subject.getGenderCode() != null) {
			Element genderCode = doc.createElement("gender");
			System.out.println(" genderCode Code =" + subject.getGenderCode());
			genderCode.appendChild(doc.createTextNode(subject.getGenderCode()
					.getDescription().toString()));
			element.appendChild(genderCode);
		}
		// Add Subject country of citizenship elements to xml
		addCitizenshipCountry(subject, doc, element);

	}

	// Add Lead related Data to XML
	private void addLeadSubjectInfoToXML(LtSubject subject, Document doc,
			Element element) {
		// lead counts
		int openLeadCount = 0;
		int closedLeadCount = 0;
		String lsid = subject.getLsid();
		logger.info(" Lead Count = " + subject.getLtLeads().size());
		for (LtLead lead : subject.getLtLeads()) {
			// Count lead in open status for the
			logger.info(" Lead status = " + lead.getStatusCode().getId());
			if (lead.getStatusCode().getId() == 102L) {
				openLeadCount++;
			}
			// Count lead in open status for the
			if (lead.getStatusCode().getId() == 108L) {
				closedLeadCount++;
			}
		}
//		for (LtLeadSubject leadSubj : subject.getLtLeadSubjects()) {
//			// take 1st lSID --not sure --need explanaton
//			lsid = leadSubj.getLsid();
//			break;
//		}
		// add lead open/close count to xml
		Element openLeadElem = doc.createElement("numberofopenleads");
		openLeadElem.appendChild(doc.createTextNode(new Integer(openLeadCount)
				.toString()));
		element.appendChild(openLeadElem);
		Element closeLeadElem = doc.createElement("numberofclosedleads");
		closeLeadElem.appendChild(doc.createTextNode(new Integer(
				closedLeadCount).toString()));
		element.appendChild(closeLeadElem);
		Element lsidElem = doc.createElement("lsid");
		if (lsid != null) {
			lsidElem.appendChild(doc.createTextNode(lsid.toString()));
		}
		element.appendChild(lsidElem);
	}

	// Add Subject country of citizenship element(s) to xml
	private void addCitizenshipCountry(LtSubject subject, Document doc,
			Element subjElem) {

		// List<LtSubjectCitizenshipCountry> countryOfCitzList =
		// (List<LtSubjectCitizenshipCountry>) subject
		// .getLtSubjectCitizenshipCountries();
		if (subject != null
				&& subject.getLtSubjectCitizenshipCountries().size() > 0) {
			Element countryOfCitzElem = doc
					.createElement("countriesofcitizenship");
			// add separate elements for Country of Citizenship
			subjElem.appendChild(countryOfCitzElem);
			for (LtSubjectCitizenshipCountry countryofCitz : subject
					.getLtSubjectCitizenshipCountries()) {
				if (countryofCitz.getCountryCode().getDescription() != null) {
					Element countryname = doc.createElement("countryname");
					countryname.appendChild(doc.createTextNode(countryofCitz
							.getCountryCode().getDescription()));
					countryOfCitzElem.appendChild(countryname);
				}
			}
		}
	}

	@Override
	String transform(Persistable entity) throws TransformerException {
		LtUserManagementAssociation userMgmtAssoc = (LtUserManagementAssociation) entity;
		String xml = null;

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element supervisorId = doc.createElement("supervisorId");
			// set attribute to start element
			supervisorId.setAttribute("id", userMgmtAssoc
					.getLtUserBySupervisorId().toString());
			doc.appendChild(supervisorId);

			// User id elements
			Element userId = doc.createElement("id");
			userId.appendChild(doc.createTextNode(userMgmtAssoc
					.getLtUserByUserId().toString()));
			supervisorId.appendChild(userId);

			xml = transform(doc);

		} catch (ParserConfigurationException pce) {
			logger.severe(pce.getMessage());
			throw new TransformerException(pce);
		}

		return xml;
	}
}
