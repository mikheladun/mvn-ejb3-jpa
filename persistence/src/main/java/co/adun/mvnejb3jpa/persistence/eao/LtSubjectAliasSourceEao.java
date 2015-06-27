/**
 * @Author - Kalyan
 */

package co.adun.mvnejb3jpa.persistence.eao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.entity.LtSubjectAliasSource;

@Transactional
@Component
public interface LtSubjectAliasSourceEao extends BaseEao<LtSubjectAliasSource>{

}
