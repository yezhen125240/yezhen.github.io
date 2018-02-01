/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.shop;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.shop.Message;
import net.shop.Pageable;
import net.shop.Setting;
import net.shop.entity.Goods;
import net.shop.entity.Member;
import net.shop.entity.Review;
import net.shop.exception.ResourceNotFoundException;
import net.shop.service.CaptchaService;
import net.shop.service.GoodsService;
import net.shop.service.MemberService;
import net.shop.service.ReviewService;
import net.shop.util.SystemUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 评论
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("shopReviewController")
@RequestMapping("/review")
public class ReviewController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;

	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "goodsServiceImpl")
	private GoodsService goodsService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	/**
	 * 发表
	 */
	@RequestMapping(value = "/add/{goodsId}", method = RequestMethod.GET)
	public String add(@PathVariable Long goodsId, ModelMap model) {
		Setting setting = SystemUtils.getSetting();
		if (!setting.getIsReviewEnabled()) {
			throw new ResourceNotFoundException();
		}
		Goods goods = goodsService.find(goodsId);
		if (goods == null) {
			throw new ResourceNotFoundException();
		}

		model.addAttribute("goods", goods);
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/shop/${theme}/review/add";
	}

	/**
	 * 内容
	 */
	@RequestMapping(value = "/content/{goodsId}", method = RequestMethod.GET)
	public String content(@PathVariable Long goodsId, Integer pageNumber, ModelMap model) {
		Setting setting = SystemUtils.getSetting();
		if (!setting.getIsReviewEnabled()) {
			throw new ResourceNotFoundException();
		}
		Goods goods = goodsService.find(goodsId);
		if (goods == null) {
			throw new ResourceNotFoundException();
		}

		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("goods", goods);
		model.addAttribute("page", reviewService.findPage(null, goods, null, true, pageable));
		return "/shop/${theme}/review/content";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	Message save(String captchaId, String captcha, Long goodsId, Integer score, String content, HttpServletRequest request) {
		if (!captchaService.isValid(Setting.CaptchaType.review, captchaId, captcha)) {
			return Message.error("shop.captcha.invalid");
		}
		Setting setting = SystemUtils.getSetting();
		if (!setting.getIsReviewEnabled()) {
			return Message.error("shop.review.disabled");
		}
		Goods goods = goodsService.find(goodsId);
		if (goods == null) {
			return ERROR_MESSAGE;
		}
		if (!isValid(Review.class, "score", score) || !isValid(Review.class, "content", content)) {
			return ERROR_MESSAGE;
		}
		Member member = memberService.getCurrent();
		if (!Setting.ReviewAuthority.anyone.equals(setting.getReviewAuthority()) && member == null) {
			return Message.error("shop.review.accessDenied");
		}
		if (member != null && !reviewService.hasPermission(member, goods)) {
			return Message.error("shop.review.noPermission");
		}

		Review review = new Review();
		review.setScore(score);
		review.setContent(content);
		review.setIp(request.getRemoteAddr());
		review.setMember(member);
		review.setGoods(goods);
		if (setting.getIsReviewCheck()) {
			review.setIsShow(false);
			reviewService.save(review);
			return Message.success("shop.review.check");
		} else {
			review.setIsShow(true);
			reviewService.save(review);
			return Message.success("shop.review.success");
		}
	}

}