package co.adun.mvnejb3jpa.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * 
 * @author Mikhel Adun
 * 
 */
public class LogoutRequestHandler extends SimpleUrlLogoutSuccessHandler
{
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException
	{
		Object principal = authentication.getPrincipal();
		response.sendRedirect(request.getServletContext().getContextPath() + "/jsp/login.jsp");
	}
}