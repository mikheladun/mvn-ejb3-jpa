package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.CustomFilterField;
import co.adun.mvnejb3jpa.persistence.CustomSortField;
import co.adun.mvnejb3jpa.persistence.eao.LtLeadHistoryEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadHistory;

@Stateless
@Component
@Transactional

public class LtLeadHistoryEaoImpl extends BaseEaoImpl<LtLeadHistory> implements LtLeadHistoryEao {

	@Override
	public Class<LtLeadHistory> getEntityClass() {
		// TODO Auto-generated method stub
		return LtLeadHistory.class;
	}

	
}
