package co.adun.mvnejb3jpa.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.OrganizationService;
import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserManagementAssociationEao;
import co.adun.mvnejb3jpa.persistence.eao.MissionCodeUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;
import co.adun.mvnejb3jpa.persistence.entity.MissionCodeUser;

/**
 * A stateless session bean to load support data.
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/OrganizationService", beanInterface = OrganizationService.class)
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger logger = Logger.getLogger(OrganizationServiceImpl.class.getName());

	@Inject
	private LtUserManagementAssociationEao userMgmtAssocEao;

	@Inject
	private MissionCodeUserEao missionCodeUserEao;

	@Inject
	private LtUserEao userEao;

	/**
	 * Get the list of all the users by analyst role
	 */
	@Override
	public List<LtUser> getAllAnalysts() throws BusinessException {
		logger.info("Entered userServiceImpl.findAll()..");
		List<LtUser> analystList = userEao.findAllAnalysts();

		return analystList;
	}

	/**
	 * Get the list of users/analyst for a given supervisor id
	 */
	@Override
	public List<LtUser> getAllAnalystsforSupervisorId(Long supervisorId) throws BusinessException {
		logger.info("Entered userServiceImpl.getAllAnalystsforSupervisorId()..");
		List<LtUser> analystforSupervisor = new ArrayList<LtUser>();
		List<LtUserManagementAssociation> assocList = (List<LtUserManagementAssociation>) userMgmtAssocEao.findAllUsers();
		for (LtUserManagementAssociation mgmtAssoc : assocList) {
			if (mgmtAssoc.getLtUserBySupervisorId().getId().equals(supervisorId)) {
				analystforSupervisor.add(mgmtAssoc.getLtUserByUserId());
			}
		}
		return analystforSupervisor;
	}

	/**
	 * Get the list of users/analyst for a given user id
	 */
	@Override
	public List<LtUser> getAllSupervisorForAnAnalystsId(Long analystId) throws BusinessException {
		logger.info("Entered userServiceImpl.getAllSupervisorForAnAnalyst()..");
		List<LtUser> supervisorListForAnAnalyst = new ArrayList<LtUser>();
		List<LtUserManagementAssociation> assocList = (List<LtUserManagementAssociation>) userMgmtAssocEao.findAllUsers();
		for (LtUserManagementAssociation mgmtAssoc : assocList) {
			logger.info("GetAllSupervisorforAnalsyst: " + "User id=" + mgmtAssoc.getLtUserByUserId().getId() + "Analyst id=" + analystId);
			if (mgmtAssoc.getLtUserByUserId().getId().equals(analystId)) {
				supervisorListForAnAnalyst.add(mgmtAssoc.getLtUserBySupervisorId());
			}
		}
		return supervisorListForAnAnalyst;
	}

	/**
	 * Get the list of the users by supervisor role
	 */
	@Override
	public List<LtUser> getAllSupervisors() throws BusinessException {
		List<CustomFilterField> filterFields = new ArrayList<CustomFilterField>();
		List<Long> roles = new ArrayList<Long>();
		roles.add(100L);
		filterFields.add(new CustomFilterField("userRoleCode.id", roles));
		List<CustomSortField> sortFields = new ArrayList<CustomSortField>();
		sortFields.add(new CustomSortField("lastname", CustomSortField.ASCENDING));
		sortFields.add(new CustomSortField("firstname", CustomSortField.ASCENDING));
		List<LtUser> superVisorsList = userEao.findAllInFilterAndSort(filterFields, sortFields);

		return superVisorsList;
	}

	@Override
	public List<MissionCodeUser> getMissionCodeUsersList() throws BusinessException {
		List<MissionCodeUser> missionCodeUsers = (List<MissionCodeUser>) missionCodeUserEao.findAllUsers();

		return missionCodeUsers;
	}

	@Override
	public List<LtUserManagementAssociation> getLtUserManagementAssociationList() throws BusinessException {
		List<LtUserManagementAssociation> codes = (List<LtUserManagementAssociation>) userMgmtAssocEao.findAllUsers();
		return codes;
	}
}