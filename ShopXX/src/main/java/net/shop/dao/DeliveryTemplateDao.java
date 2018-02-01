/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import net.shop.entity.DeliveryTemplate;

/**
 * Dao - 快递单模板
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface DeliveryTemplateDao extends BaseDao<DeliveryTemplate, Long> {

	/**
	 * 查找默认快递单模板
	 * 
	 * @return 默认快递单模板，若不存在则返回null
	 */
	DeliveryTemplate findDefault();

	/**
	 * 设置默认快递单模板
	 * 
	 * @param deliveryTemplate
	 *            快递单模板
	 */
	void setDefault(DeliveryTemplate deliveryTemplate);

}