package co.adun.mvnejb3jpa.persistence.eao.impl;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.LicenseTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.LicenseTypeCode;

@Component
@Stateless
@Transactional
public class LicenseTypeCodeEaoImpl extends BaseEaoImpl<LicenseTypeCode> implements LicenseTypeCodeEao {

    @Override
    public Class<LicenseTypeCode> getEntityClass() {
	// TODO Auto-generated method stub
	return LicenseTypeCode.class;
    }

}
