package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberEao;
import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumber;

@Transactional
@Stateless
@Component
public class LtIdentifyingNumberEaoImpl extends BaseEaoImpl<LtIdentifyingNumber> implements LtIdentifyingNumberEao {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LtIdentifyingNumberEaoImpl.class.getName());

    @Override
    public Class<LtIdentifyingNumber> getEntityClass() {
	// TODO Auto-generated method stub
	return LtIdentifyingNumber.class;
    }

}
