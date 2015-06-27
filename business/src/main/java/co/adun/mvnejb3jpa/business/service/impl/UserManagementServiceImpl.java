package co.adun.mvnejb3jpa.business.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.UserManagementService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtUserManagementAssociationEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

@Component
@Stateless
@EJB(name = "java:global/UserManagementService", beanInterface = UserManagementService.class)
public class UserManagementServiceImpl implements UserManagementService {
	
	@Inject
	private LtUserManagementAssociationEao usermgmtEao;
	
	@Override
	public List<LtUser> findSupervisorsByAnalyst(LtUser analyst) throws BusinessException{
		return usermgmtEao.findSupervisorsByAnalyst(analyst);
	}
	
	@Override
	public List<LtUser> findAnalystsBySupervisor(LtUser supervisor) throws BusinessException{
		return usermgmtEao.findAnalystsBySupervisor(supervisor);
	}

}
