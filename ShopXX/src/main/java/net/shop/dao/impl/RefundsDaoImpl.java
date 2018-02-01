/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.RefundsDao;
import net.shop.entity.Refunds;

import org.springframework.stereotype.Repository;

/**
 * Dao - 退款单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("refundsDaoImpl")
public class RefundsDaoImpl extends BaseDaoImpl<Refunds, Long> implements RefundsDao {

}