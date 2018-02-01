/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;

import net.shop.dao.PaymentDao;
import net.shop.dao.SnDao;
import net.shop.entity.Payment;
import net.shop.entity.Sn;
import net.shop.service.PaymentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("paymentServiceImpl")
public class PaymentServiceImpl extends BaseServiceImpl<Payment, Long> implements PaymentService {

	@Resource(name = "paymentDaoImpl")
	private PaymentDao paymentDao;
	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Transactional(readOnly = true)
	public Payment findBySn(String sn) {
		return paymentDao.findBySn(sn);
	}

	@Override
	@Transactional
	public Payment save(Payment payment) {
		Assert.notNull(payment);

		payment.setSn(snDao.generate(Sn.Type.payment));

		return super.save(payment);
	}

}