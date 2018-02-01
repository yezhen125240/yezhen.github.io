/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import net.shop.entity.Log;

/**
 * Service - 日志
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface LogService extends BaseService<Log, Long> {

	/**
	 * 清空日志
	 */
	void clear();

}