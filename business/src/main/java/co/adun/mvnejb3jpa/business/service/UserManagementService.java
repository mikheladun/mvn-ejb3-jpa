package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

public interface UserManagementService extends BusinessService{
	   public List<LtUser> findSupervisorsByAnalyst(LtUser analyst) throws BusinessException;
	   public  List<LtUser> findAnalystsBySupervisor(LtUser supervisor) throws BusinessException;

}
