package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LicenseTypeCode;

@Transactional
@Component

public interface LicenseTypeCodeEao extends BaseEao<LicenseTypeCode>

{

}
