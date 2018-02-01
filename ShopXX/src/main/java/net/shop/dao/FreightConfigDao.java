/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Area;
import net.shop.entity.FreightConfig;
import net.shop.entity.ShippingMethod;

/**
 * Dao - 运费配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface FreightConfigDao extends BaseDao<FreightConfig, Long> {

	/**
	 * 判断运费配置是否存在
	 * 
	 * @param shippingMethod
	 *            配送方式
	 * @param area
	 *            地区
	 * @return 运费配置是否存在
	 */
	boolean exists(ShippingMethod shippingMethod, Area area);

	/**
	 * 查找运费配置分页
	 * 
	 * @param shippingMethod
	 *            配送方式
	 * @param pageable
	 *            分页信息
	 * @return 运费配置分页
	 */
	Page<FreightConfig> findPage(ShippingMethod shippingMethod, Pageable pageable);

}