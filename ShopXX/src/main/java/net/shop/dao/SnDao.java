/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import net.shop.entity.Sn;

/**
 * Dao - 序列号
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface SnDao {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Sn.Type type);

}