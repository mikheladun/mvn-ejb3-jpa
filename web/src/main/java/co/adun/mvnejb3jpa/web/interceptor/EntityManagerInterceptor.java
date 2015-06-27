package co.adun.mvnejb3jpa.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EntityManagerInterceptor implements Filter
{

	@Override
	public void destroy()
	{
	}

	@Override
	public void init(FilterConfig fc) throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{

		try
		{
			// EntityManagerHelper.beginTransaction();
			chain.doFilter(req, res);
			// EntityManagerHelper.commit();
		}
		catch (RuntimeException e)
		{

			// if (EntityManagerHelper.getEntityManager() != null && EntityManagerHelper.getEntityManager().isOpen())
			// EntityManagerHelper.rollback();
			throw e;

		}
		finally
		{
			// EntityManagerHelper.close();
		}
	}
}
