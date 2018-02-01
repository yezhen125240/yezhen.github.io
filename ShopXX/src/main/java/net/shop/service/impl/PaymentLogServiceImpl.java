/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import net.shop.dao.PaymentLogDao;
import net.shop.dao.SnDao;
import net.shop.entity.DepositLog;
import net.shop.entity.Member;
import net.shop.entity.Order;
import net.shop.entity.Payment;
import net.shop.entity.PaymentLog;
import net.shop.entity.Sn;
import net.shop.service.MemberService;
import net.shop.service.OrderService;
import net.shop.service.PaymentLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service - 支付记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("paymentLogServiceImpl")
public class PaymentLogServiceImpl extends BaseServiceImpl<PaymentLog, Long> implements PaymentLogService {

	@Resource(name = "paymentLogDaoImpl")
	private PaymentLogDao paymentLogDao;
	@Resource(name = "snDaoImpl")
	private SnDao snDao;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;

	@Transactional(readOnly = true)
	public PaymentLog findBySn(String sn) {
		return paymentLogDao.findBySn(sn);
	}

	public void handle(PaymentLog paymentLog) {
		Assert.notNull(paymentLog);

		if (!LockModeType.PESSIMISTIC_WRITE.equals(paymentLogDao.getLockMode(paymentLog))) {
			paymentLogDao.refresh(paymentLog, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(paymentLog.getType());

		if (!PaymentLog.Status.wait.equals(paymentLog.getStatus())) {
			return;
		}

		switch (paymentLog.getType()) {
		case recharge:
			Member member = paymentLog.getMember();
			if (member != null) {
				memberService.addBalance(member, paymentLog.getEffectiveAmount(), DepositLog.Type.recharge, null, null);
			}
			break;
		case payment:
			Order order = paymentLog.getOrder();
			if (order != null) {
				Payment payment = new Payment();
				payment.setMethod(Payment.Method.online);
				payment.setPaymentMethod(paymentLog.getPaymentPluginName());
				payment.setFee(paymentLog.getFee());
				payment.setAmount(paymentLog.getAmount());
				payment.setOrder(order);
				orderService.payment(order, payment, null);
			}
			break;
		}
		paymentLog.setStatus(PaymentLog.Status.success);
	}

	@Override
	@Transactional
	public PaymentLog save(PaymentLog paymentLog) {
		Assert.notNull(paymentLog);

		paymentLog.setSn(snDao.generate(Sn.Type.paymentLog));

		return super.save(paymentLog);
	}

}