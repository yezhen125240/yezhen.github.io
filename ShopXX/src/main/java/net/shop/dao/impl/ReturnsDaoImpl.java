/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.ReturnsDao;
import net.shop.entity.Returns;

import org.springframework.stereotype.Repository;

/**
 * Dao - 退货单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("returnsDaoImpl")
public class ReturnsDaoImpl extends BaseDaoImpl<Returns, Long> implements ReturnsDao {

}