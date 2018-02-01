/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.StockLogDao;
import net.shop.entity.StockLog;

import org.springframework.stereotype.Repository;

/**
 * Dao - 库存记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("stockLogDaoImpl")
public class StockLogDaoImpl extends BaseDaoImpl<StockLog, Long> implements StockLogDao {

}