package co.adun.mvnejb3jpa.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import co.adun.mvnejb3jpa.business.service.ExampleService;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

@RequestScoped
public class MemberListProducer {
    @EJB
    private ExampleService service;
    private List<ExampleEntity> members;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<ExampleEntity> getMembers() {
	return members;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final ExampleEntity member) {
	retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
	try {
	    members = service.getMembers();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
