/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import javax.persistence.NoResultException;

import net.shop.dao.MessageConfigDao;
import net.shop.entity.MessageConfig;

import org.springframework.stereotype.Repository;

/**
 * Dao - 消息配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("messageConfigDaoImpl")
public class MessageConfigDaoImpl extends BaseDaoImpl<MessageConfig, Long> implements MessageConfigDao {

	public MessageConfig find(MessageConfig.Type type) {
		if (type == null) {
			return null;
		}
		try {
			String jpql = "select messageConfig from MessageConfig messageConfig where messageConfig.type = :type";
			return entityManager.createQuery(jpql, MessageConfig.class).setParameter("type", type).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}