package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.TravelDirectionCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.TravelDirectionCode;

/**
 * A entity access object class to provide persistent storage functions and CRUD operations for Member entity. Strongly-typed interface created since
 * it could be used by @Autowired
 * 
 * @author Mikhel Adun
 * 
 */
@Component
@Stateless
@Transactional
public class TravelDirectionCodeEaoImpl extends BaseEaoImpl<TravelDirectionCode> implements TravelDirectionCodeEao
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TravelDirectionCodeEaoImpl.class.getName());

	@Override
	public Class<TravelDirectionCode> getEntityClass()
	{
		return TravelDirectionCode.class;
	}
}
