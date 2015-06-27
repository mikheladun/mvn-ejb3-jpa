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
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;
import co.adun.mvnejb3jpa.persistence.entity.MissionCodeUser;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;
import co.adun.mvnejb3jpa.web.auth.WebSecurityContext;

@Component
public class MissionCodeUserTransformer extends BaseTransformer {

    private static final Logger logger = Logger.getLogger(MissionCodeUserTransformer.class.getName());

    /**
     * Create an xml map for Mission and User relationship
     */
    public String transform(List<MissionCodeUser> entitiesList) throws TransformerException {
	String xmlString = null;

	try {
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc = docBuilder.newDocument();
	    // root elements
	    Element root = doc.createElement("missionCodeUserMap");
	    doc.appendChild(root);

	    Long lastMissionCodeId = 0L;
	    Element element = null;
	    for (Persistable entity : entitiesList) {
		MissionCodeUser missionCodeUser = (MissionCodeUser) entity;
		MissionCode missionCode = missionCodeUser.getMissionCode();
		if (lastMissionCodeId != missionCode.getId()) {
		    element = doc.createElement("missionCode");
		    // set attribute to start element
		    element.setAttribute("id", missionCode.getId().toString());
		    root.appendChild(element);
		    lastMissionCodeId = (Long) missionCode.getId();
		}

		LtUser user = missionCodeUser.getLtUserByLtUserId();
		UserRoleCode analystRoleCode = user.getUserRoleCode();
		if (analystRoleCode.getId() == WebSecurityContext.UserRole.ANALYST_ROLE.getRoleId()
			|| analystRoleCode.getId() == WebSecurityContext.UserRole.SENIOR_ANALYST_ROLE.getRoleId() 
			|| analystRoleCode.getId() == WebSecurityContext.UserRole.SUPERVISOR_ROLE.getRoleId()) {
		    // User id elements
		    Element userId = doc.createElement("id");
		    userId.appendChild(doc.createTextNode(user.getId().toString()));
		    UserRoleCode userRoleCode = user.getUserRoleCode();
		    if (userRoleCode.getId() == WebSecurityContext.UserRole.SUPERVISOR_ROLE.getRoleId()) {
			userId.setAttribute("role", "su");
		    } else if (userRoleCode.getId() == WebSecurityContext.UserRole.ANALYST_ROLE.getRoleId()) {
			userId.setAttribute("role", "an");
		    } else if (userRoleCode.getId() == WebSecurityContext.UserRole.SENIOR_ANALYST_ROLE.getRoleId()) {
			userId.setAttribute("role", "sa");
		    }
		    userId.setAttribute("enabled", user.getEnabled().toString());
		    element.appendChild(userId);
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
	MissionCodeUser missionCodeUser = (MissionCodeUser) entity;
	String xml = null;

	try {
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    // root elements
	    Document doc = docBuilder.newDocument();
	    Element missionCode = doc.createElement("missionCode");
	    // set attribute to start element
	    missionCode.setAttribute("id", missionCodeUser.getMissionCode().getId().toString());
	    doc.appendChild(missionCode);

	    // User id elements
	    Element userId = doc.createElement("id");
	    userId.appendChild(doc.createTextNode(missionCodeUser.getLtUserByLtUserId().getId().toString()));
	    missionCode.appendChild(userId);

	    xml = transform(doc);

	} catch (ParserConfigurationException pce) {
	    logger.severe(pce.getMessage());
	    throw new TransformerException(pce);
	}

	return xml;
    }
}
