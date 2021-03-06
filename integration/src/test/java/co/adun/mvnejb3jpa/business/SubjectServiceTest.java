package co.adun.mvnejb3jpa.business;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.BiographicInfoService;
import co.adun.mvnejb3jpa.business.service.SubjectService;
import co.adun.mvnejb3jpa.business.service.SupportDataService;
import co.adun.mvnejb3jpa.persistence.entity.CountryCode;
import co.adun.mvnejb3jpa.persistence.entity.GenderCode;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectCitizenshipCountry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SubjectServiceTest {
	@Inject
	private SubjectService service;
	@Inject
	private BiographicInfoService bioService;
	@Inject
	private SupportDataService supportDataService;

	@Test
	public void findSubjectByLsid() {

		try {
			// create a LtSubject model object and use that to perform search
			LtSubject ltSubject = new LtSubject();
			ltSubject.setFirstname("");
			ltSubject.setLastname("");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			String lsid = "LS136872109";
//			leadSubject.setLsid(lsid);
			ltSubject.setLsid(lsid);
			List<LtSubject> subjectList = service
					.findSubjectByLsidAndName(ltSubject);
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// xmlOutut = transform( subject );
				// System.out.println(xmlOutut);
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void findSubjectByLeadid() {

		try {
			// create a LtSubject model object and use that to perform search
			LtSubject ltSubject = new LtSubject();
			ltSubject.setFirstname("");
			ltSubject.setLastname("");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLead ltLead = new LtLead();
			ltLead.setId(100000L);
			leadSubject.setLtLead(ltLead);
			Set<LtLeadSubject> ltLeadsubjects = new HashSet<LtLeadSubject>();
			ltLeadsubjects.add(leadSubject);
			ltSubject.setLtLeadSubjects(ltLeadsubjects);

			List<LtSubject> subjectList = service
					.findSubjectByLeadId(ltSubject);

			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// xmlOutut = transform( subject );
				// System.out.println(xmlOutut);
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	@Test
	public void saveSubject() {

		try {
			// create a LtSubject model object and use that to perform search
			LtSubject ltSubject = new LtSubject();

			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLead ltLead = new LtLead();
			ltLead.setId(100000L);
			leadSubject.setLtLead(ltLead);
			Set<LtLeadSubject> ltLeadsubjects = new HashSet<LtLeadSubject>();
			ltLeadsubjects.add(leadSubject);
			ltSubject.setLtLeadSubjects(ltLeadsubjects);

			List<LtSubject> subjectList = service
					.findSubjectByLeadId(ltSubject);

			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// update the subject
				subject.setFirstname("Michael");
				subject.setLastname("Jordan");
				GenderCode genderCode = new GenderCode();
				genderCode.setId(100L); // MALE
				subject.setGenderCode(genderCode);
			    
				// set country code
				CountryCode countryOfBirth = new CountryCode();
				countryOfBirth.setId(370L); //YEMEN
				subject.setCountryCode(countryOfBirth);
				
				//set date Of Birth to 01/01/1979
//				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
//				Date  dob = null;
//				dob = dateFormat.parse("01/01/1979");
//				subject.setBirthDate( dob);
				
				// set country of citizenship
				LtSubjectCitizenshipCountry coc1 = new LtSubjectCitizenshipCountry();
				coc1.setCountryCode(supportDataService.getCountryCodes().get(0));
				coc1.setLtSubject(subject);
				LtSubjectCitizenshipCountry coc2 = new LtSubjectCitizenshipCountry();
				coc2.setCountryCode(supportDataService.getCountryCodes().get(4));
				coc2.setLtSubject(subject);
				Set<LtSubjectCitizenshipCountry> cocSet = new HashSet<LtSubjectCitizenshipCountry>();
				cocSet.add(coc1);
				cocSet.add(coc2);
				subject.setLtSubjectCitizenshipCountries(cocSet);
				System.out.println(" Subject country of citizenship new count = "
						+ subject.getLtSubjectCitizenshipCountries().size());
				// Update the current subject
				bioService.saveBiographicInfo(subject);
		
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}
	@Test
	public void saveSubjectWithJustOneCountryCode() {

		try {
			// create a LtSubject model object and use that to perform search
			LtSubject ltSubject = new LtSubject();

			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLead ltLead = new LtLead();
			ltLead.setId(100000L);
			leadSubject.setLtLead(ltLead);
			Set<LtLeadSubject> ltLeadsubjects = new HashSet<LtLeadSubject>();
			ltLeadsubjects.add(leadSubject);
			ltSubject.setLtLeadSubjects(ltLeadsubjects);

			List<LtSubject> subjectList = service
					.findSubjectByLeadId(ltSubject);

			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// update the subject
				subject.setFirstname("Michael");
				subject.setLastname("Jordan");
				GenderCode genderCode = new GenderCode();
				genderCode.setId(100L); // MALE
				subject.setGenderCode(genderCode);
			    
				// set country code
				CountryCode countryOfBirth = new CountryCode();
				countryOfBirth.setId(370L); //YEMEN
				subject.setCountryCode(countryOfBirth);
				
				//set date Of Birth to 01/01/1979
//				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
//				Date  dob = null;
//				dob = dateFormat.parse("01/01/1979");
//				subject.setBirthDate( dob);
				
				// set country of citizenship
//				LtSubjectCitizenshipCountry coc1 = new LtSubjectCitizenshipCountry();
//				coc1.setCountryCode(supportDataService.getCountryCodes().get(0));
//				coc1.setLtSubject(subject);
				LtSubjectCitizenshipCountry coc2 = new LtSubjectCitizenshipCountry();
				coc2.setCountryCode(supportDataService.getCountryCodes().get(4));
				coc2.setLtSubject(subject);
				Set<LtSubjectCitizenshipCountry> cocSet = new HashSet<LtSubjectCitizenshipCountry>();
				//cocSet.add(coc1);
				cocSet.add(coc2);
				subject.setLtSubjectCitizenshipCountries(cocSet);
				System.out.println(" Subject country of citizenship new count = "
						+ subject.getLtSubjectCitizenshipCountries().size());
				// Update the current subject
				bioService.saveBiographicInfo(subject);
		
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}
	@Test
	public void deleteSubject() {

		try {
			// create a LtSubject model object and use that to perform search
			LtSubject ltSubject = new LtSubject();

			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLead ltLead = new LtLead();
			ltLead.setId(100000L);
			leadSubject.setLtLead(ltLead);
			Set<LtLeadSubject> ltLeadsubjects = new HashSet<LtLeadSubject>();
			ltLeadsubjects.add(leadSubject);
			ltSubject.setLtLeadSubjects(ltLeadsubjects);

			List<LtSubject> subjectList = service
					.findSubjectByLeadId(ltSubject);

			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}

				
				// delete the current subject
				// @TODO  commented out for compilation error
				service.delete(subject);
		
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

		
	@Test
	public void findSubjectByFirstName() {

		try {
			LtSubject ltSubject = new LtSubject();
			ltSubject.setFirstname("John");
			ltSubject.setLastname("");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLeadSubject[] ltLeadsubjArray = null;
			// Long lsid = new Long(1368721090355L);
			// leadSubject.setLsid(lsid);
			// ltLeadsubjArray = new LtLeadSubject[] { leadSubject };
			List<LtSubject> subjectList = service
					.findSubjectByLsidAndName(ltSubject);
			String xmlOutut;
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// xmlOutut = transform( subject );
				// System.out.println(xmlOutut);
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void findSubjectByLastName() {

		try {
			LtSubject ltSubject = new LtSubject();

			ltSubject.setFirstname("");
			ltSubject.setLastname("Doe");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLeadSubject[] ltLeadsubjArray = null;
			// Long lsid = new Long(1368721090355L);
			// leadSubject.setLsid(lsid);
			// ltLeadsubjArray = new LtLeadSubject[] { leadSubject };
			List<LtSubject> subjectList = service
					.findSubjectByLsidAndName(ltSubject);
			String xmlOutut;
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// xmlOutut = transform( subject );
				// System.out.println(xmlOutut);
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void findSubjectByName() {

		try {
			List<LtSubject> subjectList = service.findSubjectByName("Doe",
					"John", null);
			String xmlOutut;
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname());
				xmlOutut = transform(subject);
				System.out.println(xmlOutut);
			}
			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void findSubjectByFirstLastNameAndLsid() {

		try {
			LtSubject ltSubject = new LtSubject();

			ltSubject.setFirstname("John");
			ltSubject.setLastname("Doe");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLeadSubject[] ltLeadsubjArray = null;
			String lsid = "LS136872109";
//			leadSubject.setLsid(lsid);
			ltSubject.setLsid(lsid);
			ltLeadsubjArray = new LtLeadSubject[] { leadSubject };
			List<LtSubject> subjectList = service
					.findSubjectByLsidAndName(ltSubject);
			String xmlOutut;
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname()
						+ " " + subject.getLastname());
				System.out.println(" Lead Count = "
						+ subject.getLtLeads().size());
				System.out.println(" Lead SubjectvCount = "
						+ subject.getLtLeadSubjects().size());
				System.out.println(" Subject country of birth = "
						+ subject.getCountryCode().getDescription());
				System.out.println(" Subject country of citizenship count = "
						+ subject.getLtSubjectCitizenshipCountries().size());

				for (LtSubjectCitizenshipCountry citz : subject
						.getLtSubjectCitizenshipCountries()) {
					System.out.println(" Subject country of citizenship: "
							+ citz.getCountryCode().getDescription());
				}
				// xmlOutut = transform( subject );
				// System.out.println(xmlOutut);
			}

			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void findSubjectByCriteria() {

		try {
			LtSubject ltSubject = new LtSubject();

			ltSubject.setFirstname("John");
			ltSubject.setLastname("Doe");
			// set Lsid for search
			LtLeadSubject leadSubject = new LtLeadSubject();
			LtLeadSubject[] ltLeadsubjArray = null;
			String lsid = "LS136872109";
			ltSubject.setLsid(lsid);
//			leadSubject.setLsid(lsid);
			ltLeadsubjArray = new LtLeadSubject[] { leadSubject };
			List<LtSubject> subjectList = service
					.findSubjectByCriteria(ltSubject);
			System.out.println("Subject count=" + subjectList.size());
			for (LtSubject subject : subjectList) {
				System.out.println(" Subject Name = " + subject.getFirstname());
				// xmlOutut = transform( lSubj );
				// System.out.println(xmlOutut);
			}
			Assert.assertNotNull(subjectList);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return;
	}

	/* *//**
	 * 
	 * To test if the conversion the subject list to XML is successful
	 */
	/*
	 * @Test public void convertLeadSubjectByNameToXML() {
	 * 
	 * try { List<LtLeadSubject> leadSubjectList =
	 * service.findLeadSubjectByName("", "", "1368721090355"); String xmlOutut =
	 * null;
	 * 
	 * if (leadSubjectList != null) { System.out.println(
	 * leadSubjectList.size()); for ( int i= 0; i < leadSubjectList.size(); i++
	 * ) { LtLeadSubject lSubj = (LtLeadSubject) leadSubjectList.get(i);
	 * System.out.println(" Lead LSID = " + lSubj.getLsid() ); xmlOutut =
	 * transform( lSubj ); System.out.println(xmlOutut); } } else {
	 * Assert.fail(); } } catch (BusinessException e) { e.printStackTrace();
	 * Assert.fail(); } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); Assert.fail(); } return; }
	 */

	private String transform(LtSubject entity) throws Exception {
		String xml = null;
		// create JAXB context and initializing Marshaller
		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(entity.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// for getting nice formatted output
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			Writer sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			jaxbMarshaller.marshal(entity, result);

			xml = sw.toString();
			return xml;

		} catch (JAXBException e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
