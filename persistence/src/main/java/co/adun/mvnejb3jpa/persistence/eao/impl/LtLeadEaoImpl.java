package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.EntityManagerHelper;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSpecialProject;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectContact;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectPhoto;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Stateless
@Transactional
@Component
public class LtLeadEaoImpl extends BaseEaoImpl<LtLead> implements LtLeadEao {

    @Inject
    private LtSubjectEao subjectEao;

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LtLeadEaoImpl.class.getName());

    @Override
    public Class<LtLead> getEntityClass() {
	return LtLead.class;
    }
     
    @Override
    public LtLead findByIdDeep(Long id) {
		EntityManagerHelper.log("findByIdDeep() - getting deep entity by id (long): " + id, Level.INFO);
		try {
		    LtLead lead = entityManager.find(getEntityClass(), id);

		    Hibernate.initialize(lead.getDisposCloseReasonCode());
		    Hibernate.initialize(lead.getDisposCloseSystemCode());
		    Hibernate.initialize(lead.getLeadGeneratedFromCode());
		    Hibernate.initialize(lead.getLeadTypeCode());
		    Hibernate.initialize(lead.getLtUserByCreateBy());
		    Hibernate.initialize(lead.getLtUserByModifiedBy());
		    Hibernate.initialize(lead.getLtUserByLtAssignToUserId());
		    Hibernate.initialize(lead.getStatusCode());
		    Hibernate.initialize(lead.getMissionCode());
		    
		    Hibernate.initialize(lead.getLtSubject());
		    if(lead.getLtSubject() != null) {
			    Hibernate.initialize(lead.getLtSubject().getLtSubjectPhotos());
			    Hibernate.initialize(lead.getLtSubject().getLtSubjectCitizenshipCountries());
			    Hibernate.initialize(lead.getLtSubject().getLtSubjectContacts());
		    }
		    
		    Hibernate.initialize(lead.getLtLeadSources());
		    Hibernate.initialize(lead.getLtLeadSpecialProjects());
		    Hibernate.initialize(lead.getLtLeadSubjects());

		    return lead;

		} catch (RuntimeException re) {
		    EntityManagerHelper.log("find all failed", Level.SEVERE, re);
		    throw re;
		} finally {
		    // entityManager.close();
		}
    }

}