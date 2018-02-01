/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Member;
import net.shop.entity.PointLog;

/**
 * Dao - 积分记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface PointLogDao extends BaseDao<PointLog, Long> {

	/**
	 * 查找积分记录分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 积分记录分页
	 */
	Page<PointLog> findPage(Member member, Pageable pageable);

}