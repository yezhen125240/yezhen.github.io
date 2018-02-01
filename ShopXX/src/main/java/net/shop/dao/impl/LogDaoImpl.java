/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.LogDao;
import net.shop.entity.Log;

import org.springframework.stereotype.Repository;

/**
 * Dao - 日志
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).executeUpdate();
	}

}