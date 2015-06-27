package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;
import co.adun.mvnejb3jpa.persistence.entity.MissionCodeUser;

/**
 * @author Mikhel Adun
 */
public interface OrganizationService extends BusinessService {

    public List<LtUser> getAllAnalysts() throws BusinessException;

    public List<LtUser> getAllSupervisors() throws BusinessException;

    public List<LtUserManagementAssociation> getLtUserManagementAssociationList() throws BusinessException;

    public List<MissionCodeUser> getMissionCodeUsersList() throws BusinessException;
    
    public List<LtUser> getAllAnalystsforSupervisorId( Long supervisorId) throws BusinessException;
    public List<LtUser> getAllSupervisorForAnAnalystsId( Long supervisorId) throws BusinessException; 
    }

