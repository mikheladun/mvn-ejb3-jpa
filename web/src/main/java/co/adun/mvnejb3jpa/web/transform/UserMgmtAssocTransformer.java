package co.adun.mvnejb3jpa.web.transform;

import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import co.adun.mvnejb3jpa.persistence.Persistable;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;

@Component
public class UserMgmtAssocTransformer extends BaseTransformer {

    private static final Logger logger = Logger.getLogger(UserMgmtAssocTransformer.class.getName());

    /**
     * Create an xml map for Mission and User relationship
     */
    public String transform(List<LtUserManagementAssociation> entitiesList) throws TransformerException {
	String xmlString = null;

	try {
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc = docBuilder.newDocument();
	    // root elements
	    Element root = doc.createElement("supervisorToAnalystMap");
	    doc.appendChild(root);

	    Long lastSupervisorId = 0L;
	    Element element = null;
	    for (Persistable entity : entitiesList) {
		LtUserManagementAssociation userMgmtAssoc = (LtUserManagementAssociation) entity;
		LtUser supervisor = userMgmtAssoc.getLtUserBySupervisorId();
		UserRoleCode supvRoleCode = supervisor.getUserRoleCode();
		if (supvRoleCode.getId() == WebSecurityContext.UserRole.SUPERVISOR_ROLE.getRoleId()) {
		    if (lastSupervisorId != supervisor.getId()) {
			element = doc.createElement("supervisor");
			// set attribute to start element
			element.setAttribute("id", supervisor.getId().toString());
			element.setAttribute("enabled", supervisor.getEnabled().toString());
			element.setAttribute("role", "su");
			root.appendChild(element);
			lastSupervisorId = (Long)supervisor.getId();
		    }

		    LtUser analyst = userMgmtAssoc.getLtUserByUserId();
		    UserRoleCode analystRoleCode = analyst.getUserRoleCode();
		    if (analystRoleCode.getId() == WebSecurityContext.UserRole.ANALYST_ROLE.getRoleId()
			    || analystRoleCode.getId() == WebSecurityContext.UserRole.SENIOR_ANALYST_ROLE.getRoleId()) {
			// User id elements
			Element userId = doc.createElement("id");
			userId.appendChild(doc.createTextNode(analyst.getId().toString()));
			if (analystRoleCode.getId() == WebSecurityContext.UserRole.ANALYST_ROLE.getRoleId()) {
			    userId.setAttribute("role", "an");
			} else if (analystRoleCode.getId() == WebSecurityContext.UserRole.SENIOR_ANALYST_ROLE.getRoleId()) {
			    userId.setAttribute("role", "sa");
			}

			userId.setAttribute("enabled", analyst.getEnabled().toString());
			element.appendChild(userId);
		    }
		}
	    }

	    xmlString = this.transform(doc);

	    logger.info("Mission to User Xml" + xmlString);

	} catch (ParserConfigurationException pce) {
	    logger.severe(pce.getMessage());
	    throw new TransformerException(pce);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return xmlString;
    }

    @Override
    String transform(Persistable entity) throws TransformerException {
	LtUserManagementAssociation userMgmtAssoc = (LtUserManagementAssociation) entity;
	String xml = null;

	try {
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    // root elements
	    Document doc = docBuilder.newDocument();
	    Element supervisorId = doc.createElement("supervisorId");
	    // set attribute to start element
	    supervisorId.setAttribute("id", userMgmtAssoc.getLtUserBySupervisorId().toString());
	    doc.appendChild(supervisorId);

	    // User id elements
	    Element userId = doc.createElement("id");
	    userId.appendChild(doc.createTextNode(userMgmtAssoc.getLtUserByUserId().toString()));
	    supervisorId.appendChild(userId);

	    xml = transform(doc);

	} catch (ParserConfigurationException pce) {
	    logger.severe(pce.getMessage());
	    throw new TransformerException(pce);
	}

	return xml;
    }
}
