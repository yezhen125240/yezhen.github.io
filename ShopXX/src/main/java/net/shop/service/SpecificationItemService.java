/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import java.util.List;

import net.shop.entity.SpecificationItem;

/**
 * Service - 规格项
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface SpecificationItemService {

	/**
	 * 规格项过滤
	 * 
	 * @param specificationItems
	 *            规格项
	 */
	void filter(List<SpecificationItem> specificationItems);

}