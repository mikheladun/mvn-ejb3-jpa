package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtLeadIdentifyingNumber;

@Transactional
@Component
public interface LtLeadIdentifyingNumberEao extends BaseEao<LtLeadIdentifyingNumber>{

}
