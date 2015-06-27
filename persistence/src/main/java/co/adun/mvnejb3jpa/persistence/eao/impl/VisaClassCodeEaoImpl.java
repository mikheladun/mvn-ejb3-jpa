package co.adun.mvnejb3jpa.persistence.eao.impl;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.VisaClassCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.VisaClassCode;

@Stateless
@Component
@Transactional

public class VisaClassCodeEaoImpl extends BaseEaoImpl<VisaClassCode>
implements VisaClassCodeEao{

        @Override
        public Class<VisaClassCode> getEntityClass() {
                // TODO Auto-generated method stub
                return VisaClassCode.class;
        }
}


