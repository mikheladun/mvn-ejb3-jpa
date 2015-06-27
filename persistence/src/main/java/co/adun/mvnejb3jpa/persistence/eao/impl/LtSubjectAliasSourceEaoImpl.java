/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.persistence.eao.impl;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtSubjectAliasSourceEao;
import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAliasSource;

@Stateless
@Transactional
@Component
public class LtSubjectAliasSourceEaoImpl extends BaseEaoImpl<LtSubjectAliasSource> 
implements LtSubjectAliasSourceEao {

	@Override
	public Class<LtSubjectAliasSource> getEntityClass() {
		// TODO Auto-generated method stub
		return LtSubjectAliasSource.class;
	}

}
