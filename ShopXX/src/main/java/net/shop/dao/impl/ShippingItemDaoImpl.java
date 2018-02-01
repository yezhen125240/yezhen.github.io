/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.ShippingItemDao;
import net.shop.entity.ShippingItem;

import org.springframework.stereotype.Repository;

/**
 * Dao - 发货项
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("shippingItemDaoImpl")
public class ShippingItemDaoImpl extends BaseDaoImpl<ShippingItem, Long> implements ShippingItemDao {

}