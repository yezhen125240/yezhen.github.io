/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.DepositLogDao;
import net.shop.entity.DepositLog;
import net.shop.entity.Member;
import net.shop.service.DepositLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 预存款记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("depositLogServiceImpl")
public class DepositLogServiceImpl extends BaseServiceImpl<DepositLog, Long> implements DepositLogService {

	@Resource(name = "depositLogDaoImpl")
	private DepositLogDao depositLogDao;

	@Transactional(readOnly = true)
	public Page<DepositLog> findPage(Member member, Pageable pageable) {
		return depositLogDao.findPage(member, pageable);
	}

}