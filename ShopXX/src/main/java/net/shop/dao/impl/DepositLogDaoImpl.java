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
import net.shop.dao.DepositLogDao;
import net.shop.entity.DepositLog;
import net.shop.entity.Member;

import org.springframework.stereotype.Repository;

/**
 * Dao - 预存款记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("depositLogDaoImpl")
public class DepositLogDaoImpl extends BaseDaoImpl<DepositLog, Long> implements DepositLogDao {

	public Page<DepositLog> findPage(Member member, Pageable pageable) {
		if (member == null) {
			return Page.emptyPage(pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DepositLog> criteriaQuery = criteriaBuilder.createQuery(DepositLog.class);
		Root<DepositLog> root = criteriaQuery.from(DepositLog.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
		return super.findPage(criteriaQuery, pageable);
	}

}