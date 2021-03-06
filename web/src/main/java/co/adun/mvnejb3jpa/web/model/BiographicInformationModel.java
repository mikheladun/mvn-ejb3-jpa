package co.adun.mvnejb3jpa.web.model;

import java.util.List;

import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.GenderCode;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;
import co.adun.mvnejb3jpa.web.model.PageModel;

public class BiographicInformationModel implements PageModel {

	GenderCode genderCode;
	CountryCode countryCode;
	List<LtSubjectCitizenshipCountry> cocs;
    DateValueModel dob;

	public GenderCode getGenderCode() {
		if(genderCode == null)
			genderCode = new GenderCode();
		return genderCode;
	}

	public void setGenderCode(GenderCode genderCode) {
		this.genderCode = genderCode;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public List<LtSubjectCitizenshipCountry> getCocs() {
		return cocs;
	}

	public void setCocs(List<LtSubjectCitizenshipCountry> cocs) {
		this.cocs = cocs;
	}

	public DateValueModel getDob() {
		return dob;
	}

	public void setDob(DateValueModel dob) {
		this.dob = dob;
	}

}
