/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.FreightConfigDao;
import net.shop.entity.Area;
import net.shop.entity.FreightConfig;
import net.shop.entity.ShippingMethod;
import net.shop.service.FreightConfigService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 运费配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("freightConfigServiceImpl")
public class FreightConfigServiceImpl extends BaseServiceImpl<FreightConfig, Long> implements FreightConfigService {

	@Resource(name = "freightConfigDaoImpl")
	private FreightConfigDao freightConfigDao;

	@Transactional(readOnly = true)
	public boolean exists(ShippingMethod shippingMethod, Area area) {
		return freightConfigDao.exists(shippingMethod, area);
	}

	@Transactional(readOnly = true)
	public boolean unique(ShippingMethod shippingMethod, Area previousArea, Area currentArea) {
		if (previousArea != null && previousArea.equals(currentArea)) {
			return true;
		}
		return !freightConfigDao.exists(shippingMethod, currentArea);
	}

	@Transactional(readOnly = true)
	public Page<FreightConfig> findPage(ShippingMethod shippingMethod, Pageable pageable) {
		return freightConfigDao.findPage(shippingMethod, pageable);
	}

}