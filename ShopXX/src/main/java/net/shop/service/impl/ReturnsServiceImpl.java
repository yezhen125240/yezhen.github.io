/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.SnDao;
import net.shop.entity.Returns;
import net.shop.entity.Sn;
import net.shop.service.ReturnsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 退货单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns, Long> implements ReturnsService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Override
	@Transactional
	public Returns save(Returns returns) {
		Assert.notNull(returns);

		returns.setSn(snDao.generate(Sn.Type.returns));

		return super.save(returns);
	}

}