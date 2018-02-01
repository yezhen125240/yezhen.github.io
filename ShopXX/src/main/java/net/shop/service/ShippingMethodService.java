/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import java.math.BigDecimal;

import net.shop.entity.Area;
import net.shop.entity.Receiver;
import net.shop.entity.ShippingMethod;

/**
 * Service - 配送方式
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface ShippingMethodService extends BaseService<ShippingMethod, Long> {

	/**
	 * 计算运费
	 * 
	 * @param shippingMethod
	 *            配送方式
	 * @param area
	 *            地区
	 * @param weight
	 *            重量
	 * @return 运费
	 */
	BigDecimal calculateFreight(ShippingMethod shippingMethod, Area area, Integer weight);

	/**
	 * 计算运费
	 * 
	 * @param shippingMethod
	 *            配送方式
	 * @param receiver
	 *            收货地址
	 * @param weight
	 *            重量
	 * @return 运费
	 */
	BigDecimal calculateFreight(ShippingMethod shippingMethod, Receiver receiver, Integer weight);

}