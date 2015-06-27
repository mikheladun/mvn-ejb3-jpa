package co.adun.mvnejb3jpa.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.util.StringUtils;

import co.adun.mvnejb3jpa.web.model.DateValueModel;

public class PageModelUtils {
	private static final Logger logger = Logger.getLogger(PageModelUtils.class.getName());

	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
	private static final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss", Locale.US);

	public static Date getDate(DateValueModel dateValueModel) {
		Date date = null;

		if (dateValueModel != null && !StringUtils.isEmpty(dateValueModel.getValue())) {
			try {
//				date = dateFormat.parse(dateValueModel.getValue());
				String dateStr = dateValueModel.getValue();
		        date = dateStr.matches("\\d{4}\\-\\d{2}\\-\\d{2}.*") ? timeFormat.parse(dateStr) : dateFormat.parse(dateStr);
			} catch (ParseException e) {
				logger.info(e.getMessage());
			}
		}
		
		return date;
	}

	public static Long getCode(String abbreviation) {
		Long code = null;
		if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
			String[] strings = StringUtils.split(abbreviation, ":");
			if (strings != null) {
				code = new Long(strings[0]);
			}
		}

		return code;
	}

	public static String getAbbreviation(String abbreviation) {
		String abbrv = null;
		if (!StringUtils.isEmpty(abbreviation) && !"Select...".equals(abbreviation)) {
			String[] strings = StringUtils.split(abbreviation, ":");
			if (strings != null) {
				abbrv = strings[1];
			}
		}

		return abbrv;
	}
}
