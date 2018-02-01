/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import net.shop.entity.MessageConfig;

/**
 * Dao - 消息配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface MessageConfigDao extends BaseDao<MessageConfig, Long> {

	/**
	 * 查找消息配置
	 * 
	 * @param type
	 *            类型
	 * @return 消息配置
	 */
	MessageConfig find(MessageConfig.Type type);

}