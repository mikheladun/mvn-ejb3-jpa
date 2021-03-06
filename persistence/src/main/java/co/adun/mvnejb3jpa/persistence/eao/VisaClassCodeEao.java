/**
 * @Author - Kalyan
 * Local EJB persistence query interface to Visa Classification entity
 */

package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.VisaClassCode;

@Transactional
@Component

public interface VisaClassCodeEao extends BaseEao<VisaClassCode>{}

