/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import java.util.List;

import net.shop.Filter;
import net.shop.Order;
import net.shop.entity.MemberRank;
import net.shop.entity.ProductCategory;
import net.shop.entity.Promotion;

/**
 * Dao - 促销
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface PromotionDao extends BaseDao<Promotion, Long> {

	/**
	 * 查找促销
	 * 
	 * @param memberRank
	 *            会员等级
	 * @param productCategory
	 *            商品分类
	 * @param hasBegun
	 *            是否已开始
	 * @param hasEnded
	 *            是否已结束
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 促销
	 */
	List<Promotion> findList(MemberRank memberRank, ProductCategory productCategory, Boolean hasBegun, Boolean hasEnded, Integer count, List<Filter> filters, List<Order> orders);

}