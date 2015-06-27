package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtIdentifyingNumberSource;

@Transactional
@Component

public interface LtIdentifyingNumberSourceEao extends BaseEao<LtIdentifyingNumberSource>{
}
