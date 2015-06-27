package co.adun.mvnejb3jpa.web.cache;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimerFilter implements Filter
{
	private static final Logger logger = Logger.getLogger(TimerFilter.class.getName());

	@Override
	public void destroy()
	{
		// Do nothing

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		long start = System.currentTimeMillis();

		chain.doFilter(request, response);

		logger.log(Level.FINEST, (System.currentTimeMillis() - start) + " ms to process " + ((HttpServletRequest) request).getRequestURL());
		System.out.println();
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		// Do nothing
	}

}
