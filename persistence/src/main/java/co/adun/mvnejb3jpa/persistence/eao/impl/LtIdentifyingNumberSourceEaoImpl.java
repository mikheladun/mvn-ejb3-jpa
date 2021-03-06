package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LtIdentifyingNumberSourceEao;
import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumberSource;

@Stateless
@Transactional
@Component
public class LtIdentifyingNumberSourceEaoImpl extends BaseEaoImpl<LtIdentifyingNumberSource> implements LtIdentifyingNumberSourceEao {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LtIdentifyingNumberSourceEaoImpl.class.getName());

    @Override
    public Class<LtIdentifyingNumberSource> getEntityClass() {
	// TODO Auto-generated method stub
	return LtIdentifyingNumberSource.class;
    }

}
