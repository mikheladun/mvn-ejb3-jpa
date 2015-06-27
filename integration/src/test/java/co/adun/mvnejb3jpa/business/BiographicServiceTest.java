package co.adun.mvnejb3jpa.business;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.BiographicInfoService;
import co.adun.mvnejb3jpa.business.service.LeadService;
import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BiographicServiceTest {

	@Inject
	private TimerService timerService;

	@Inject
	private SubjectService subjectService;

	@Inject
	private LeadService leadService;

	@Inject
	private BiographicInfoService biographicInfoService;

	@Test
	public void saveBiographicInfo() {
		LtLead lead;
		try {
			lead = leadService.getLead(100000L);
			LtSubject subject = lead.getLtSubject();

			LtSubject ltSubject = new LtSubject();
			ltSubject.setId(subject.getId());
			ltSubject.setBirthDate(subject.getBirthDate());
			ltSubject.setCountryCode(subject.getCountryCode());
			ltSubject.setGenderCode(subject.getGenderCode());

			Set<LtSubjectCitizenshipCountry> cocs = new HashSet<LtSubjectCitizenshipCountry>();
			LtSubjectCitizenshipCountry coc = new LtSubjectCitizenshipCountry();
			CountryCode countryCode1 = new CountryCode();
			countryCode1.setId(103L);
			coc.setCountryCode(countryCode1);
			cocs.add(coc);
			LtSubjectCitizenshipCountry coc2 = new LtSubjectCitizenshipCountry();
			CountryCode countryCode2 = new CountryCode();
			countryCode2.setId(101L);
			coc2.setCountryCode(countryCode2);
			cocs.add(coc2);
			ltSubject.setLtSubjectCitizenshipCountries(cocs);

			ltSubject = biographicInfoService.saveBiographicInfo(ltSubject);

			cocs = ltSubject.getLtSubjectCitizenshipCountries();
			Assert.assertTrue(!cocs.isEmpty());
			Assert.assertEquals(cocs.size(), 1);
		}
		catch (BusinessException e) {
			Assert.fail(e.getMessage());
		}

	}
}
