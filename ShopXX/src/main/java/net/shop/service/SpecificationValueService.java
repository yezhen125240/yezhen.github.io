/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service;

import java.util.List;

import net.shop.entity.SpecificationItem;
import net.shop.entity.SpecificationValue;

/**
 * Service - 规格值
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface SpecificationValueService {

	/**
	 * 规格值验证
	 * 
	 * @param specificationItems
	 *            规格项
	 * @param specificationValues
	 *            规格值
	 * @return 验证结果
	 */
	boolean isValid(List<SpecificationItem> specificationItems, List<SpecificationValue> specificationValues);

}