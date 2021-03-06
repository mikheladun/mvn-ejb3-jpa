package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.MissionCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.MissionCode;

/**
 * A entity access object class to provide persistent storage functions and CRUD
 * operations for Member entity. Strongly-typed interface created since it could
 * be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@Transactional
public class MissionCodeEaoImpl extends BaseEaoImpl<MissionCode> implements MissionCodeEao {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(MissionCodeEaoImpl.class.getName());

    @Override
    public Class<MissionCode> getEntityClass() {
	return MissionCode.class;
    }
}