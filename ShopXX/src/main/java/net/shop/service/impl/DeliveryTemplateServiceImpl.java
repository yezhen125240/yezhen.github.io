/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.DeliveryTemplateDao;
import net.shop.entity.DeliveryTemplate;
import net.shop.service.DeliveryTemplateService;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 快递单模板
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("deliveryTemplateServiceImpl")
public class DeliveryTemplateServiceImpl extends BaseServiceImpl<DeliveryTemplate, Long> implements DeliveryTemplateService {

	@Resource(name = "deliveryTemplateDaoImpl")
	private DeliveryTemplateDao deliveryTemplateDao;

	@Transactional(readOnly = true)
	public DeliveryTemplate findDefault() {
		return deliveryTemplateDao.findDefault();
	}

	@Override
	@Transactional
	public DeliveryTemplate save(DeliveryTemplate deliveryTemplate) {
		Assert.notNull(deliveryTemplate);

		if (BooleanUtils.isTrue(deliveryTemplate.getIsDefault())) {
			deliveryTemplateDao.setDefault(deliveryTemplate);
		}
		return super.save(deliveryTemplate);
	}

	@Override
	@Transactional
	public DeliveryTemplate update(DeliveryTemplate deliveryTemplate) {
		Assert.notNull(deliveryTemplate);

		if (BooleanUtils.isTrue(deliveryTemplate.getIsDefault())) {
			deliveryTemplateDao.setDefault(deliveryTemplate);
		}
		return super.update(deliveryTemplate);
	}

}