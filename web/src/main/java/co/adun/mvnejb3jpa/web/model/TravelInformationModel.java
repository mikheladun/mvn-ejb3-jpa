package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import co.adun.mvnejb3jpa.persistence.entity.LtSubjectTravel;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectTravelSource;
import co.adun.mvnejb3jpa.web.model.PageModel;

public class TravelInformationModel implements PageModel {

	LtSubjectTravel ltSubjectTravel;
    DateValueModel travelDate;
    List<LtSubjectTravelSource> sources;

	public LtSubjectTravel getLtSubjectTravel() {
		return ltSubjectTravel;
	}

	public void setLtSubjectTravel(LtSubjectTravel ltSubjectTravel) {
		this.ltSubjectTravel = ltSubjectTravel;
	}

	public List<LtSubjectTravelSource> getSources() {
		return sources;
	}

	public void setSources(List<LtSubjectTravelSource> sources) {
		this.sources = sources;
	}

	public DateValueModel getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(DateValueModel travelDate) {
		this.travelDate = travelDate;
	}

}
