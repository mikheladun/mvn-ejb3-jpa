package co.adun.mvnejb3jpa.persistence.eao.impl;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;

import co.adun.mvnejb3jpa.persistence.eao.LtSubjectAliasEao;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAlias;

@Stateless
@Component

public class LtSubjectAliasEaoImpl extends BaseEaoImpl<LtSubjectAlias> implements LtSubjectAliasEao{

	@Override
	public Class<LtSubjectAlias> getEntityClass() {
		// TODO Auto-generated method stub
		return LtSubjectAlias.class;
	}

}
