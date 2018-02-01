/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.shop.Filter;
import net.shop.Order;
import net.shop.dao.AttributeDao;
import net.shop.entity.Attribute;
import net.shop.entity.Goods;
import net.shop.entity.ProductCategory;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao - 属性
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("attributeDaoImpl")
public class AttributeDaoImpl extends BaseDaoImpl<Attribute, Long> implements AttributeDao {

	public Integer findUnusedPropertyIndex(ProductCategory productCategory) {
		Assert.notNull(productCategory);

		for (int i = 0; i < Goods.ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
			String jpql = "select count(*) from Attribute attribute where attribute.productCategory = :productCategory and attribute.propertyIndex = :propertyIndex";
			Long count = entityManager.createQuery(jpql, Long.class).setParameter("productCategory", productCategory).setParameter("propertyIndex", i).getSingleResult();
			if (count == 0) {
				return i;
			}
		}
		return null;
	}

	public List<Attribute> findList(ProductCategory productCategory, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Attribute> criteriaQuery = criteriaBuilder.createQuery(Attribute.class);
		Root<Attribute> root = criteriaQuery.from(Attribute.class);
		criteriaQuery.select(root);
		if (productCategory != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("productCategory"), productCategory));
		}
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

}