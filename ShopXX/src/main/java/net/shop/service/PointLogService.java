/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Member;
import net.shop.entity.PointLog;

/**
 * Service - 积分记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface PointLogService extends BaseService<PointLog, Long> {

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