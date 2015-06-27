package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtLeadIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.entity.LtLeadIdentifyingNumber;

@Stateless
@Transactional
@Component
public class LtLeadIdentifyingNumberEaoImpl extends BaseEaoImpl<LtLeadIdentifyingNumber> implements LtLeadIdentifyingNumberEao {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LtLeadIdentifyingNumberEaoImpl.class.getName());

    @Override
    public Class<LtLeadIdentifyingNumber> getEntityClass() {
	return LtLeadIdentifyingNumber.class;
    }

}
