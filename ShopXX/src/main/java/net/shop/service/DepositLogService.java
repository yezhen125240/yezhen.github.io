/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.DepositLog;
import net.shop.entity.Member;

/**
 * Service - 预存款记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface DepositLogService extends BaseService<DepositLog, Long> {

	/**
	 * 查找预存款记录分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 预存款记录分页
	 */
	Page<DepositLog> findPage(Member member, Pageable pageable);

}