/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.PointLogDao;
import net.shop.entity.Member;
import net.shop.entity.PointLog;

import org.springframework.stereotype.Repository;

/**
 * Dao - 积分记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("pointLogDaoImpl")
public class PointLogDaoImpl extends BaseDaoImpl<PointLog, Long> implements PointLogDao {

	public Page<PointLog> findPage(Member member, Pageable pageable) {
		if (member == null) {
			return Page.emptyPage(pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PointLog> criteriaQuery = criteriaBuilder.createQuery(PointLog.class);
		Root<PointLog> root = criteriaQuery.from(PointLog.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
		return super.findPage(criteriaQuery, pageable);
	}

}