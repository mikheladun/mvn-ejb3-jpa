package co.adun.mvnejb3jpa.web.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.adun.mvnejb3jpa.business.service.ExampleService;
import co.adun.mvnejb3jpa.persistence.entity.ExampleEntity;

@Controller
@RequestMapping(value = "/rest/example")
public class ExampleServiceController extends ExampleController {
    private static final Logger logger = Logger.getLogger(ExampleServiceController.class.getName());

    @Autowired(required = false)
    @Qualifier("exampleService")
    private ExampleService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public @ResponseBody
    List<ExampleEntity> listAllMembers() {
//	Set<String> roles = WebSecurityContext.getUserRoles();
//	System.out.println("Entered listAllMenbers() Users roles = " + roles);
//	if (roles.contains(WebSecurityContext.UserRole.ADMIN_ROLE)) {
	    return service.getMembers();
//	} else {
//	    return new ArrayList<ExampleEntity>();
//	}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ExampleEntity lookupMemberById(@PathVariable("id") Long id) {
	return service.getMember(id);
    }
}
