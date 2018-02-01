/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.PointLogDao;
import net.shop.entity.Member;
import net.shop.entity.PointLog;
import net.shop.service.PointLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 积分记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("pointLogServiceImpl")
public class PointLogServiceImpl extends BaseServiceImpl<PointLog, Long> implements PointLogService {

	@Resource(name = "pointLogDaoImpl")
	private PointLogDao pointLogDao;

	@Transactional(readOnly = true)
	public Page<PointLog> findPage(Member member, Pageable pageable) {
		return pointLogDao.findPage(member, pageable);
	}

}