package co.adun.mvnejb3jpa.business.service.impl;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;

/**
 * A stateless session bean to provide business service implementation
 * 
 * @author Mikhel Adun
 */
@Component
@Stateless
@EJB(name = "java:global/UserService", beanInterface = UserService.class)
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Inject
    private LtUserEao userEAO;

    @Override
    public LtUser getSystemUser() throws BusinessException {
	LtUser ltUser = userEAO.findById(100L);
	return ltUser;
    }

    @Override
    public LtUser getCurrentUser(String name) throws BusinessException {
	LtUser ltUser = userEAO.findByPropertyUniqueResult("username", name);
	return ltUser;
    }
}