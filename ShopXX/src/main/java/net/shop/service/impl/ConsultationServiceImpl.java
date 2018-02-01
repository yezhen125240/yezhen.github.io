/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.shop.Filter;
import net.shop.Order;
import net.shop.Page;
import net.shop.Pageable;
import net.shop.dao.ConsultationDao;
import net.shop.dao.GoodsDao;
import net.shop.dao.MemberDao;
import net.shop.entity.Consultation;
import net.shop.entity.Goods;
import net.shop.entity.Member;
import net.shop.service.ConsultationService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 咨询
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("consultationServiceImpl")
public class ConsultationServiceImpl extends BaseServiceImpl<Consultation, Long> implements ConsultationService {

	@Resource(name = "consultationDaoImpl")
	private ConsultationDao consultationDao;
	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;
	@Resource(name = "goodsDaoImpl")
	private GoodsDao goodsDao;

	@Transactional(readOnly = true)
	public List<Consultation> findList(Member member, Goods goods, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders) {
		return consultationDao.findList(member, goods, isShow, count, filters, orders);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "consultation", condition = "#useCache")
	public List<Consultation> findList(Long memberId, Long goodsId, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		Member member = memberDao.find(memberId);
		if (memberId != null && member == null) {
			return Collections.emptyList();
		}
		Goods goods = goodsDao.find(goodsId);
		if (goodsId != null && goods == null) {
			return Collections.emptyList();
		}
		return consultationDao.findList(member, goods, isShow, count, filters, orders);
	}

	@Transactional(readOnly = true)
	public Page<Consultation> findPage(Member member, Goods goods, Boolean isShow, Pageable pageable) {
		return consultationDao.findPage(member, goods, isShow, pageable);
	}

	@Transactional(readOnly = true)
	public Long count(Member member, Goods goods, Boolean isShow) {
		return consultationDao.count(member, goods, isShow);
	}

	@CacheEvict(value = "consultation", allEntries = true)
	public void reply(Consultation consultation, Consultation replyConsultation) {
		if (consultation == null || replyConsultation == null) {
			return;
		}
		consultation.setIsShow(true);

		replyConsultation.setIsShow(true);
		replyConsultation.setGoods(consultation.getGoods());
		replyConsultation.setForConsultation(consultation);
		consultationDao.persist(replyConsultation);

		Goods goods = consultation.getGoods();
		if (goods != null) {
			goods.setGenerateMethod(Goods.GenerateMethod.lazy);
		}
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public Consultation save(Consultation consultation) {
		Goods goods = consultation.getGoods();
		if (goods != null) {
			goods.setGenerateMethod(Goods.GenerateMethod.lazy);
		}
		return super.save(consultation);
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public Consultation update(Consultation consultation) {
		Goods goods = consultation.getGoods();
		if (goods != null) {
			goods.setGenerateMethod(Goods.GenerateMethod.lazy);
		}
		return super.update(consultation);
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public Consultation update(Consultation consultation, String... ignoreProperties) {
		return super.update(consultation, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "consultation", allEntries = true)
	public void delete(Consultation consultation) {
		Goods goods = consultation.getGoods();
		if (goods != null) {
			goods.setGenerateMethod(Goods.GenerateMethod.lazy);
		}
		super.delete(consultation);
	}

}