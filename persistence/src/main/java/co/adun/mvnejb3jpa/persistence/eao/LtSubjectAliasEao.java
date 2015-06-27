package co.adun.mvnejb3jpa.persistence.eao;

/**
 * @Author - Kalyan
 */

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAlias;

@Transactional
@Component

public interface LtSubjectAliasEao extends BaseEao<LtSubjectAlias>{}
