package co.adun.mvnejb3jpa.business.service;

import java.util.List;

import co.adun.mvnejb3jpa.persistence.entity.LtSubject;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAlias;

public interface SubjectAliasService extends BusinessService{
	
	public void save(LtSubject ltSubject, LtSubjectAlias ltSubjectAlias);
	public void save(LtSubject ltSubject, List<LtSubjectAlias> ltSubjectAliases);
	public List<LtSubjectAlias> getAliases(LtSubject ltSubject);

}
