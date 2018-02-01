/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Coupon;

/**
 * Service - 优惠券
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface CouponService extends BaseService<Coupon, Long> {

	/**
	 * 验证价格运算表达式
	 * 
	 * @param priceExpression
	 *            价格运算表达式
	 * @return 验证结果
	 */
	boolean isValidPriceExpression(String priceExpression);

	/**
	 * 查找优惠券分页
	 * 
	 * @param isEnabled
	 *            是否启用
	 * @param isExchange
	 *            是否允许积分兑换
	 * @param hasExpired
	 *            是否已过期
	 * @param pageable
	 *            分页信息
	 * @return 优惠券分页
	 */
	Page<Coupon> findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable);

}