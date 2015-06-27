package co.adun.mvnejb3jpa.persistence.eao.impl;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtAssociatedSubjectEao;
import co.adun.mvnejb3jpa.persistence.entity.LtAssociatedSubject;

@Stateless
@Transactional
@Component

public class LtAssociatedSubjectEaoImpl extends BaseEaoImpl<LtAssociatedSubject>
implements LtAssociatedSubjectEao{

	@Override
	public Class<LtAssociatedSubject> getEntityClass() {
		// TODO Auto-generated method stub
		return LtAssociatedSubject.class;
	}

}
