/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.FreightConfigDao;
import net.shop.entity.Area;
import net.shop.entity.FreightConfig;
import net.shop.entity.ShippingMethod;

import org.springframework.stereotype.Repository;

/**
 * Dao - 运费配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("freightConfigDaoImpl")
public class FreightConfigDaoImpl extends BaseDaoImpl<FreightConfig, Long> implements FreightConfigDao {

	public boolean exists(ShippingMethod shippingMethod, Area area) {
		if (shippingMethod == null || area == null) {
			return false;
		}
		String jpql = "select count(*) from FreightConfig freightConfig where freightConfig.shippingMethod = :shippingMethod and freightConfig.area = :area";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("shippingMethod", shippingMethod).setParameter("area", area).getSingleResult();
		return count > 0;
	}

	public Page<FreightConfig> findPage(ShippingMethod shippingMethod, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FreightConfig> criteriaQuery = criteriaBuilder.createQuery(FreightConfig.class);
		Root<FreightConfig> root = criteriaQuery.from(FreightConfig.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (shippingMethod != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("shippingMethod"), shippingMethod));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}