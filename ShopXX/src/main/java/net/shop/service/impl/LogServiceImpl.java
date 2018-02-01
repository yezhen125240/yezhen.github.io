/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.LogDao;
import net.shop.entity.Log;
import net.shop.service.LogService;

import org.springframework.stereotype.Service;

/**
 * Service - 日志
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	public void clear() {
		logDao.removeAll();
	}

}