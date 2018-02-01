/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import net.shop.entity.AdPosition;

/**
 * Service - 广告位
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface AdPositionService extends BaseService<AdPosition, Long> {

	/**
	 * 查找广告位
	 * 
	 * @param id
	 *            ID
	 * @param useCache
	 *            是否使用缓存
	 * @return 广告位
	 */
	AdPosition find(Long id, boolean useCache);

}