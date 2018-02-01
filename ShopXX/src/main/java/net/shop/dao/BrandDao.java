/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import java.util.List;

import net.shop.Filter;
import net.shop.Order;
import net.shop.entity.Brand;
import net.shop.entity.ProductCategory;

/**
 * Dao - 品牌
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface BrandDao extends BaseDao<Brand, Long> {

	/**
	 * 查找品牌
	 * 
	 * @param productCategory
	 *            商品分类
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 品牌
	 */
	List<Brand> findList(ProductCategory productCategory, Integer count, List<Filter> filters, List<Order> orders);

}