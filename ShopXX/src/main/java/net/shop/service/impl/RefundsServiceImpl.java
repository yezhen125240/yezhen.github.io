/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.SnDao;
import net.shop.entity.Refunds;
import net.shop.entity.Sn;
import net.shop.service.RefundsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 退款单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds, Long> implements RefundsService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Override
	@Transactional
	public Refunds save(Refunds refunds) {
		Assert.notNull(refunds);

		refunds.setSn(snDao.generate(Sn.Type.refunds));

		return super.save(refunds);
	}

}