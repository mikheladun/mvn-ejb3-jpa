package co.adun.mvnejb3jpa.persistence.eao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.LtUserManagementAssociation;

/**
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity. Strongly-typed interface created since it could be used by @Autowired
 * 
 * @author Mikhel Adun
 */
@Transactional
@Component
public interface LtUserManagementAssociationEao extends BaseEao<LtUserManagementAssociation> {

    List<LtUserManagementAssociation> findAllUsers();
    public List<LtUser> findSupervisorsByAnalyst(LtUser analyst);
    public List<LtUser> findAnalystsBySupervisor(LtUser supervisor);
}