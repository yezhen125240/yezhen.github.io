/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.AdDao;
import net.shop.entity.Ad;

import org.springframework.stereotype.Repository;

/**
 * Dao - 广告
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

}