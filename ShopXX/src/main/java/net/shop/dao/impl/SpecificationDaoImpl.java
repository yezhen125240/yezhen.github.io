/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.SpecificationDao;
import net.shop.entity.Specification;

import org.springframework.stereotype.Repository;

/**
 * Dao - 规格
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("specificationDaoImpl")
public class SpecificationDaoImpl extends BaseDaoImpl<Specification, Long> implements SpecificationDao {

}