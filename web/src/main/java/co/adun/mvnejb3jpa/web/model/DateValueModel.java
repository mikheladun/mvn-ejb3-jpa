package co.adun.mvnejb3jpa.web.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DateValueModel extends ValueModel {
    private static final Logger logger = Logger.getLogger(DateValueModel.class.getName());

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    public Date date;

    public Date getAsDate() {
	Date date = null;
	try {
	    if (!StringUtils.isEmpty(this.getValue())) {
		date = dateFormat.parse(this.getValue());
	    }
	} catch (ParseException e) {
	    logger.info(e.getMessage());
	}

	return date;
    }

}
