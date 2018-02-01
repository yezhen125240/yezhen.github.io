/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.shop.dao.NavigationDao;
import net.shop.entity.Navigation;

import org.springframework.stereotype.Repository;

/**
 * Dao - 导航
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("navigationDaoImpl")
public class NavigationDaoImpl extends BaseDaoImpl<Navigation, Long> implements NavigationDao {

	public List<Navigation> findList(Navigation.Position position) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Navigation> criteriaQuery = criteriaBuilder.createQuery(Navigation.class);
		Root<Navigation> root = criteriaQuery.from(Navigation.class);
		criteriaQuery.select(root);
		if (position != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("position"), position));
		}
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}