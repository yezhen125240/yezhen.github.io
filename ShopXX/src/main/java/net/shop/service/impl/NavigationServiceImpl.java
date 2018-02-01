/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shop.Filter;
import net.shop.Order;
import net.shop.dao.NavigationDao;
import net.shop.entity.Navigation;
import net.shop.service.NavigationService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 导航
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, Long> implements NavigationService {

	@Resource(name = "navigationDaoImpl")
	private NavigationDao navigationDao;

	@Transactional(readOnly = true)
	public List<Navigation> findList(Navigation.Position position) {
		return navigationDao.findList(position);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "navigation", condition = "#useCache")
	public List<Navigation> findList(Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		return navigationDao.findList(null, count, filters, orders);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public Navigation save(Navigation navigation) {
		return super.save(navigation);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public Navigation update(Navigation navigation) {
		return super.update(navigation);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public Navigation update(Navigation navigation, String... ignoreProperties) {
		return super.update(navigation, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "navigation", allEntries = true)
	public void delete(Navigation navigation) {
		super.delete(navigation);
	}

}