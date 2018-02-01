/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.OrderLogDao;
import net.shop.entity.OrderLog;

import org.springframework.stereotype.Repository;

/**
 * Dao - 订单记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("orderLogDaoImpl")
public class OrderLogDaoImpl extends BaseDaoImpl<OrderLog, Long> implements OrderLogDao {

}