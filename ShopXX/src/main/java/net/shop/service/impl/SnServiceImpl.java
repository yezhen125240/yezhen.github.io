/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.SnDao;
import net.shop.entity.Sn;
import net.shop.service.SnService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 序列号
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("snServiceImpl")
public class SnServiceImpl implements SnService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Transactional
	public String generate(Sn.Type type) {
		return snDao.generate(type);
	}

}