package co.adun.mvnejb3jpa.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityManagerHelper
{
	private static final Logger logger = Logger.getLogger(EntityManagerHelper.class.getName());

	public static void log(String message, Level level)
	{
		if (logger.isLoggable(level))
		{
			logger.log(level, message);
		}
	}

	public static void log(String message, Level level, Throwable t)
	{
		if (logger.isLoggable(level))
		{
			logger.log(level, message, t);
		}
	}
}
