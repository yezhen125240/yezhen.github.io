/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import java.util.List;

import net.shop.Filter;
import net.shop.Order;
import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Consultation;
import net.shop.entity.Goods;
import net.shop.entity.Member;

/**
 * Dao - 咨询
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface ConsultationDao extends BaseDao<Consultation, Long> {

	/**
	 * 查找咨询
	 * 
	 * @param member
	 *            会员
	 * @param goods
	 *            货品
	 * @param isShow
	 *            是否显示
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 咨询，不包含咨询回复
	 */
	List<Consultation> findList(Member member, Goods goods, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找咨询分页
	 * 
	 * @param member
	 *            会员
	 * @param goods
	 *            货品
	 * @param isShow
	 *            是否显示
	 * @param pageable
	 *            分页信息
	 * @return 咨询分页，不包含咨询回复
	 */
	Page<Consultation> findPage(Member member, Goods goods, Boolean isShow, Pageable pageable);

	/**
	 * 查找咨询数量
	 * 
	 * @param member
	 *            会员
	 * @param goods
	 *            货品
	 * @param isShow
	 *            是否显示
	 * @return 咨询数量，不包含咨询回复
	 */
	Long count(Member member, Goods goods, Boolean isShow);

}