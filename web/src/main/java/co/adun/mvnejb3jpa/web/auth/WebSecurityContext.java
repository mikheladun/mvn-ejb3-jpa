package co.adun.mvnejb3jpa.web.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class WebSecurityContext {

    private static final String ROLE_NAME_ANALYST = "AN";
    private static final String ROLE_NAME_SENIOR_ANALYST = "SA";
    private static final String ROLE_NAME_SUPERVISOR = "SU";
    private static final String ROLE_NAME_ADMIN = "AD";

    public static enum UserRole {
	ANALYST_ROLE(101L, ROLE_NAME_ANALYST), 
	SENIOR_ANALYST_ROLE(102L, ROLE_NAME_SENIOR_ANALYST), 
	SUPERVISOR_ROLE(100L, ROLE_NAME_SUPERVISOR), 
	ADMIN_ROLE(103L, ROLE_NAME_ADMIN);

	private Long id;
	private String role;

	private UserRole(Long id, String role) {
	    this.id = id;
	    this.role = role;
	}

	public String getRoleName() {
	    return this.role;
	}

	public Long getRoleId() {
	    return this.id;
	}
    }

    @Autowired
    SecurityContextHolder springSecurityContext;

    public static Set<String> getUserRoles() {
	GrantedAuthority[] authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
		.toArray(new GrantedAuthority[SecurityContextHolder.getContext().getAuthentication().getAuthorities().size()]);

	Set<String> roles = new HashSet<String>();
	for (GrantedAuthority authority : authorities) {
	    roles.add(authority.getAuthority());
	}
	return roles;
    }

    public static String getUsername() {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();

	return user;
    }

}
