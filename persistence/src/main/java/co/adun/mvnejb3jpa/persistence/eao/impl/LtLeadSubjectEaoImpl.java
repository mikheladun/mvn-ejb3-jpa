package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtLeadSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.UserRoleCode;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@Transactional
public class LtLeadSubjectEaoImpl extends BaseEaoImpl<LtLeadSubject> implements LtLeadSubjectEao {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LtLeadSubjectEaoImpl.class.getName());

	private static final String HQL_WORKQUEUE_AN = "SELECT distinct ls FROM LtLeadSubject ls LEFT JOIN FETCH ls.ltLead l LEFT JOIN FETCH l.statusCode lsc LEFT JOIN FETCH l.leadTypeCode la LEFT JOIN FETCH ls.ltSubject s LEFT JOIN FETCH s.countryCode LEFT JOIN FETCH s.classAdmissionCode LEFT JOIN FETCH s.ltSubjectCitizenshipCountries WHERE lsc.abbreviation IN ('HO','AS','OP','RT') AND l.ltUserByLtAssignToUserId.id = :userId ORDER BY l.createDate ASC";
	private static final String HQL_WORKQUEUE_SA_SU = "SELECT distinct ls FROM LtLeadSubject ls LEFT JOIN FETCH ls.ltLead l LEFT JOIN FETCH l.statusCode lsc LEFT JOIN FETCH l.leadTypeCode la LEFT JOIN FETCH ls.ltSubject s LEFT JOIN FETCH s.countryCode LEFT JOIN FETCH s.classAdmissionCode LEFT JOIN FETCH s.ltSubjectCitizenshipCountries WHERE lsc.abbreviation IN ('HO','AS','OP','RE','') AND l.ltUserByLtAssignToUserId.id = :userId ORDER BY l.createDate ASC";

	@Override
	public Class<LtLeadSubject> getEntityClass() {
		return LtLeadSubject.class;
	}

	@Override
	public List<LtLeadSubject> getWorkQueueForUser(LtUser user) {
		Query query = null;
		UserRoleCode roleCode = user.getUserRoleCode();
		if ("AN".equalsIgnoreCase(roleCode.getAbbreviation())) {
			query = this.createHqlQuery(HQL_WORKQUEUE_AN);
		}
		else if ("SA".equalsIgnoreCase(roleCode.getAbbreviation()) || "SU".equalsIgnoreCase(roleCode.getAbbreviation())) {
			query = this.createHqlQuery(HQL_WORKQUEUE_SA_SU);
		}

		query.setParameter("userId", user.getId());
		List<LtLeadSubject> items = (List<LtLeadSubject>) this.executeQuery(query);
		for (LtLeadSubject item : items) {
			Hibernate.initialize(item.getLtSubject());
			Hibernate.initialize(item.getLtLead());
			Hibernate.initialize(item.getLtLead().getDisposCloseReasonCode());
			Hibernate.initialize(item.getLtLead().getDisposCloseSystemCode());
			Hibernate.initialize(item.getLtSubject().getGenderCode());
			Hibernate.initialize(item.getLtSubject().getLtSubjectCitizenshipCountries());
			Set<LtAssociatedLead> ltAssociatedLeads = item.getLtLead().getLtAssociatedLeadsForLtLeadId();
			Hibernate.initialize(ltAssociatedLeads);
			for(LtAssociatedLead lead : ltAssociatedLeads) {
				Hibernate.initialize(lead);
				Hibernate.initialize(lead.getLtLeadByLtLeadId());
				Hibernate.initialize(lead.getLtLeadByLtAssociatedLeadId());
			}
		}

		return items;
	}
}
