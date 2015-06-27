/**
 * @Author: kalyan
 */

package co.adun.mvnejb3jpa.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.IdentifyingNumberService;
import co.adun.mvnejb3jpa.business.service.TimerService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberSourceEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadEao;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.eao.LtUserEao;
import co.adun.mvnejb3jpa.persistence.eao.NumberTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.SourceCodeEao;
import co.adun.mvnejb3jpa.persistence.eao.StatusCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.CompositeId;
import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumber;
import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumberSource;
import co.adun.mvnejb3jpa.persistence.entity.LtLead;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadIdentifyingNumber;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
import co.adun.mvnejb3jpa.persistence.entity.NumberTypeCode;
import co.adun.mvnejb3jpa.persistence.entity.SourceCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/spring-context-integration.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)


public class IdentifyingNumberTest {

	@Inject
	private IdentifyingNumberService identifyingNumberService;
	@Inject
	LtLeadEao eao;
	
	@Inject
    StatusCodeEao statusCodeEao;
    
    @Inject
    NumberTypeCodeEao numberTypeCodeEao;
    
    @Inject
    SourceCodeEao sourceCodeEao;
    
    @Inject
    LtUserEao ltUserEAO;
    
    @Inject 
    TimerService timerService;
    
    @Inject
    UserService userService;
    
    @Inject
    LtIdentifyingNumberEao numberEao;
    
    @Inject
    LtIdentifyingNumberSourceEao sourceEao;
    
    @Inject
	private LtLeadIdentifyingNumberEao leadIdentEao;

/**

@Test
	public void addIdentifyingNumberToLead(){
	Long leadid = 100000L;
	Date createDt = new Date(System.currentTimeMillis()); 
	Date modifiedDt = new Date(System.currentTimeMillis());
	List<Long> sourceIds = new ArrayList<Long>(3);
	
	sourceIds.add(100L);
	sourceIds.add(109L);
	sourceIds.add(117L);
	
	addIdentifyingNumBerToLead(leadid, null, null, null, 
			100L, null, null, 100L, null, 100L, null, 
			null, "1234567890", null, "Created by Spring JUnit integration Test", 
			null, null, null, null, null, null, null, null, createDt, modifiedDt, sourceIds);
	
	}
**/
	
@Test
public void saveIdentifyingNumber() throws BusinessException{
	Long leadid = 100000L;

	Date createDt = new Date(System.currentTimeMillis()); 
	Date modifiedDt = new Date(System.currentTimeMillis());
	List<Long> sourceIds = new ArrayList<Long>(3);
	
	sourceIds.add(100L);
	sourceIds.add(109L);
	sourceIds.add(117L);
	System.out.println("Saving identifying number");
/**
LtLeadIdentifyingNumber ltLeadIdentifyingNumber = createObjectGraph(leadid, null, null, null, 
		100L, null, null, 100L, null, 100L, null, 
		null, "1234567890", null, "Created by Spring JUnit integration Test on 07/01/2013...", 
		null, null, null, null, null, null, null, null, createDt, modifiedDt, sourceIds);
	
	identifyingNumberService.save(ltLeadIdentifyingNumber);
	**/

	LtLead ltLead = createObjectGraph(leadid, null, null, null, 
			100L, null, null, 100L, null, 100L, null, 
			null, "1234567890", null, "Created by Spring JUnit integration Test on 07/01/2013...", 
			null, null, null, null, null, null, null, null, createDt, modifiedDt, sourceIds);
		
		eao.saveOrUpdate(ltLead);
		
System.out.println("Saved identifying number");

}
    
//public LtLeadIdentifyingNumber createObjectGraph(Long leadid, Long eventTypeCode,
public LtLead createObjectGraph(Long leadid, Long eventTypeCode,
		Long tecsCaseStatusCode, Long tecsSubjectRecStatusCode,
		Long modifiedById, Long appLocationCode, Long visaClassCode,
		Long numberTypeCode, Long stateProvinceCode, Long createdById,
		Long licenseTypeCode, Long countryCode, String number,
		String issueLocation, String comments, String othertype,
		Date tecsSubjectRecCreateDt, Date tecsSubjectRecUpdateDt,
		Date tecsILogIncDt, Date naturalizationDt, Date issueDt,
		Date expirationDt, Date eventDt, Date createDt, Date modifiedDt,
		List<Long> sourceIds) throws BusinessException{
		
	LtLead ltLead  = eao.findById(leadid);
	
	LtIdentifyingNumber ltIdentifyingNumber = new LtIdentifyingNumber();
	ltIdentifyingNumber.setIdentifyingNumber(number);
	
	NumberTypeCode number_type_code = null;

	if (null != numberTypeCode) number_type_code = numberTypeCodeEao.findById(numberTypeCode);
	ltIdentifyingNumber.setNumberTypeCode(number_type_code);
	
	LtUser ltUser = null;
	
	if (null != createdById){
		ltUser = ltUserEAO.findById(createdById);
		ltIdentifyingNumber.setLtUserByCreateBy(ltUser);
	}
	
	ltIdentifyingNumber.setCreateDate(new Date(System.currentTimeMillis()));
	
	ltUser = null;
	
	if (null != modifiedById){
		ltUser = ltUserEAO.findById(modifiedById);
		ltIdentifyingNumber.setLtUserByModifiedBy(ltUser);
	}
	
	ltIdentifyingNumber.setModifiedDate(new Date(System.currentTimeMillis()));
	
	//Save Identification number
	
	//ltIdentifyingNumber = numberEao.save(ltIdentifyingNumber);		
	
	//System.out.println("Saved Identifying number under ID: " + ltIdentifyingNumber.getId());
			
	// Save Identifying number sources
	
	Set<LtIdentifyingNumberSource> sourceSet = new HashSet<LtIdentifyingNumberSource>();
	
	for (int i=0; i<sourceIds.size(); i++){
		Long sourceid = sourceIds.get(i);
		if (null != sourceid){
			
			// Set Identifying number Source properties
			LtIdentifyingNumberSource idnumsrc = new LtIdentifyingNumberSource();
			
			// composite ID property relates source to the identifying number
			CompositeId sourcecomp = new CompositeId();
			sourcecomp.setId(sourceid);
			sourcecomp.setCompId(ltIdentifyingNumber.getId());
			idnumsrc.setId(sourcecomp);
			
			
			SourceCode sourcecode = sourceCodeEao.findById(sourceid);
			idnumsrc.setSourceCode(sourcecode);
			
			idnumsrc.setLtIdentifyingNumber(ltIdentifyingNumber);
			
			idnumsrc.setCreateDate(new Date(System.currentTimeMillis()));
			
			idnumsrc.setLtUserByCreateBy(ltUser);
			
			idnumsrc.setModifiedDate(new Date(System.currentTimeMillis()));
			
			idnumsrc.setLtUserByModifiedBy(ltUser);
						
			//sourceEao.save(idnumsrc);
			//sourceSet.add(idnumsrc);
		}
	}
	
	
	ltIdentifyingNumber.setLtIdentifyingNumberSources(sourceSet);
	
	if (null != comments) ltIdentifyingNumber.setIdentifyingNumberComment(comments);
	
	
	// Associating Lead with the Identification number
	
	// Create and initialize the Composite ID
	
	CompositeId compositeid = new CompositeId();
	
	compositeid.setId(ltLead.getId());
	compositeid.setCompId(ltIdentifyingNumber.getId());
	
	// Set properties of LtLeadIdentifyingNumber
	
	LtLeadIdentifyingNumber ltleadidentnum = new LtLeadIdentifyingNumber();
	ltleadidentnum.setId(compositeid);
	
	ltleadidentnum.setCreateDate(new Date(System.currentTimeMillis()));
	ltleadidentnum.setLtUserByCreateBy(ltUser);
	
	ltleadidentnum.setModifiedDate(new Date(System.currentTimeMillis()));
	ltleadidentnum.setLtUserByModifiedBy(ltUser);
	
	ltleadidentnum.setLtLead(ltLead);
	ltleadidentnum.setLtIdentifyingNumber(ltIdentifyingNumber);
	
	//return ltleadidentnum;
	
	Set<LtLeadIdentifyingNumber> ltLeadIdentifyingNumbers = ltLead.getLtLeadIdentifyingNumbers();
	
	if (null == ltLeadIdentifyingNumbers) ltLeadIdentifyingNumbers = 
			new HashSet<LtLeadIdentifyingNumber>(0);
	ltLeadIdentifyingNumbers.add(ltleadidentnum);
	
	return ltLead;

}

public void addIdentifyingNumBerToLead(Long leadid, Long eventTypeCode,
		Long tecsCaseStatusCode, Long tecsSubjectRecStatusCode,
		Long modifiedById, Long appLocationCode, Long visaClassCode,
		Long numberTypeCode, Long stateProvinceCode, Long createdById,
		Long licenseTypeCode, Long countryCode, String number,
		String issueLocation, String comments, String othertype,
		Date tecsSubjectRecCreateDt, Date tecsSubjectRecUpdateDt,
		Date tecsILogIncDt, Date naturalizationDt, Date issueDt,
		Date expirationDt, Date eventDt, Date createDt, Date modifiedDt,
		List<Long> sourceIds) {
	// TODO Auto-generated method stub
	
	// TODO Auto-generated method stub

	//Set the properties in Identification number entity
	
	if (null == number || number.trim().equals("")) return;
	
	LtLead ltLead  = eao.findById(leadid);
	
	LtIdentifyingNumber ltIdentifyingNumber = new LtIdentifyingNumber();
	ltIdentifyingNumber.setIdentifyingNumber(number);
	
	NumberTypeCode number_type_code = null;

	if (null != numberTypeCode) number_type_code = numberTypeCodeEao.findById(numberTypeCode);
	ltIdentifyingNumber.setNumberTypeCode(number_type_code);
	
	LtUser ltUser = null;
	
	if (null != createdById){
		ltUser = ltUserEAO.findById(createdById);
		ltIdentifyingNumber.setLtUserByCreateBy(ltUser);
	}
	
	ltIdentifyingNumber.setCreateDate(new Date(System.currentTimeMillis()));
	
	ltUser = null;
	
	if (null != modifiedById){
		ltUser = ltUserEAO.findById(modifiedById);
		ltIdentifyingNumber.setLtUserByModifiedBy(ltUser);
	}
	
	ltIdentifyingNumber.setModifiedDate(new Date(System.currentTimeMillis()));
	
	//Save Identification number
	
	ltIdentifyingNumber = numberEao.save(ltIdentifyingNumber);		
	
	System.out.println("Saved Identifying number under ID: " + ltIdentifyingNumber.getId());
	
	
	// Save Identifying number sources
	
	Set<LtIdentifyingNumberSource> sourceSet = new HashSet<LtIdentifyingNumberSource>();
	
	for (int i=0; i<sourceIds.size(); i++){
		Long sourceid = sourceIds.get(i);
		if (null != sourceid){
			
			// Set Identifying number Source properties
			LtIdentifyingNumberSource idnumsrc = new LtIdentifyingNumberSource();
			
			// composite ID property relates source to the identifying number
			CompositeId sourcecomp = new CompositeId();
			sourcecomp.setId(sourceid);
			sourcecomp.setCompId(ltIdentifyingNumber.getId());
			idnumsrc.setId(sourcecomp);
			
			
			SourceCode sourcecode = sourceCodeEao.findById(sourceid);
			idnumsrc.setSourceCode(sourcecode);
			
			idnumsrc.setLtIdentifyingNumber(ltIdentifyingNumber);
			
			idnumsrc.setCreateDate(new Date(System.currentTimeMillis()));
			
			idnumsrc.setLtUserByCreateBy(ltUser);
			
			idnumsrc.setModifiedDate(new Date(System.currentTimeMillis()));
			
			idnumsrc.setLtUserByModifiedBy(ltUser);
							
			sourceEao.save(idnumsrc);
			sourceSet.add(idnumsrc);
		}
	}
	
	
	ltIdentifyingNumber.setLtIdentifyingNumberSources(sourceSet);
	
	if (null != comments) ltIdentifyingNumber.setIdentifyingNumberComment(comments);
	
	
	// Associating Lead with the Identification number
	
	// Create and initialize the Composite ID
	
	CompositeId compositeid = new CompositeId();
	
	compositeid.setId(ltLead.getId());
	compositeid.setCompId(ltIdentifyingNumber.getId());
	
	// Set properties of LtLeadIdentifyingNumber
	
	LtLeadIdentifyingNumber ltleadidentnum = new LtLeadIdentifyingNumber();
	ltleadidentnum.setId(compositeid);
	
	ltleadidentnum.setCreateDate(new Date(System.currentTimeMillis()));
	ltleadidentnum.setLtUserByCreateBy(ltUser);
	
	ltleadidentnum.setModifiedDate(new Date(System.currentTimeMillis()));
	ltleadidentnum.setLtUserByModifiedBy(ltUser);
	
	ltleadidentnum.setLtLead(ltLead);
	ltleadidentnum.setLtIdentifyingNumber(ltIdentifyingNumber);
	
	// Save the identification number and the LtLead identification number, the join table

	numberEao.save(ltIdentifyingNumber);
	
	leadIdentEao.save(ltleadidentnum);
	
	
	// Add the LtLeadIdentifyingNumber to the set in LtLead
	
	Set<LtLeadIdentifyingNumber> ltleadinumset = ltLead.getLtLeadIdentifyingNumbers();
	
	if (null != ltleadinumset){
		ltleadinumset = new HashSet<LtLeadIdentifyingNumber>();
	}
	
	ltLead.setLtLeadIdentifyingNumbers(ltleadinumset);
	
	eao.saveOrUpdate(ltLead);
			
}
	
}
