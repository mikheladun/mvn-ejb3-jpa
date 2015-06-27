package co.adun.mvnejb3jpa.business.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.business.exception.BusinessException;
import co.adun.mvnejb3jpa.business.service.EntryService;
import co.adun.mvnejb3jpa.business.service.SubjectAliasService;
import co.adun.mvnejb3jpa.business.service.UserService;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectAliasEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectAliasSourceEao;
import co.adun.mvnejb3jpa.persistence.eao.LtSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAlias;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAliasSource;
import co.adun.mvnejb3jpa.persistence.entity.LtUser;
@Component
@Stateless
@EJB(name = "java:global/SubjectAliasService", beanInterface = SubjectAliasService.class)
public class SubjectAliasServiceImpl implements SubjectAliasService{

	private static final Logger logger = Logger.getLogger(SubjectAliasServiceImpl.class.getName());

	@Inject
	private LtSubjectAliasEao aliasEao;
	
	@Inject
	private LtSubjectAliasSourceEao aliasSourceEao;
	
	@Inject
	private LtSubjectEao subjectEao;
	
	@Inject
	UserService userService;

	@Override
	public void save(LtSubject ltSubject, LtSubjectAlias ltSubjectAlias) {
		// TODO Auto-generated method stub
		
		LtUser createBy = ltSubjectAlias.getCreateBy();
		Date createDate = ltSubjectAlias.getCreateDate();
		
		try{
			ltSubjectAlias.setCreateBy(userService.getSystemUser());
		
		
		if (null == createBy)
			
				ltSubjectAlias.setCreateBy(userService.getSystemUser());
		
		if (null == createDate) 
			
			ltSubjectAlias.setCreateDate(new Date(System.currentTimeMillis()));
		
		
			ltSubjectAlias.setModifiedBy(userService.getSystemUser());
			
			ltSubjectAlias.setModifiedDate(new Date(System.currentTimeMillis()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		ltSubjectAlias.setLtSubject(ltSubject);
		
/**		
		Set<LtSubjectAliasSource> aliasSources = ltSubjectAlias.getLtSubjectAliasSources();
		
		if (null != aliasSources){
			Iterator<LtSubjectAliasSource> iterator = aliasSources.iterator();
			
			while (iterator.hasNext()){
				LtSubjectAliasSource aliasSource = iterator.next();
				aliasSourceEao.saveOrUpdate(aliasSource);
			}
		}
**/	
		
		aliasEao.saveOrUpdate(ltSubjectAlias);
		
	}
	
	@Override
	public void save(LtSubject ltSubject, List<LtSubjectAlias> ltSubjectAliases){
		for( LtSubjectAlias ltSubjectAlias: ltSubjectAliases ){
			save(ltSubject, ltSubjectAlias);
		}
	}

	@Override
	public List<LtSubjectAlias> getAliases(LtSubject ltSubject) {
		// TODO Auto-generated method stub
		Long subjectid = ltSubject.getId();
		LtSubject subject = subjectEao.findById(subjectid);
		List<LtSubjectAlias> aliases = aliasEao.findByProperty("ltSubject", subject);
		return aliases;
	}
	
}
