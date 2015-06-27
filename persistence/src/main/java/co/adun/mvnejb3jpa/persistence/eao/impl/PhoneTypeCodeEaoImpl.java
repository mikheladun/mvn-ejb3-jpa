package co.adun.mvnejb3jpa.persistence.eao.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.adun.mvnejb3jpa.persistence.eao.PhoneTypeCodeEao;
import co.adun.mvnejb3jpa.persistence.entity.PhoneTypeCode;

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
public class PhoneTypeCodeEaoImpl extends BaseEaoImpl<PhoneTypeCode> implements PhoneTypeCodeEao
{
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PhoneTypeCodeEaoImpl.class.getName());

	@Override
	public Class<PhoneTypeCode> getEntityClass()
	{
		return PhoneTypeCode.class;
	}
}