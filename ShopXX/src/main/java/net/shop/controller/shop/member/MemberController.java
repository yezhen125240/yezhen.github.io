/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.shop.member;

import javax.annotation.Resource;

import net.shop.controller.shop.BaseController;
import net.shop.entity.Member;
import net.shop.entity.Order;
import net.shop.service.ConsultationService;
import net.shop.service.CouponCodeService;
import net.shop.service.GoodsService;
import net.shop.service.MemberService;
import net.shop.service.MessageService;
import net.shop.service.OrderService;
import net.shop.service.ProductNotifyService;
import net.shop.service.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 会员中心
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController {

	/** 最新订单数 */
	private static final int NEW_ORDER_COUNT = 6;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;
	@Resource(name = "couponCodeServiceImpl")
	private CouponCodeService couponCodeService;
	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	@Resource(name = "goodsServiceImpl")
	private GoodsService goodsService;
	@Resource(name = "productNotifyServiceImpl")
	private ProductNotifyService productNotifyService;
	@Resource(name = "reviewServiceImpl")
	private ReviewService reviewService;
	@Resource(name = "consultationServiceImpl")
	private ConsultationService consultationService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		model.addAttribute("pendingPaymentOrderCount", orderService.count(null, Order.Status.pendingPayment, member, null, null, null, null, null, null, false));
		model.addAttribute("pendingShipmentOrderCount", orderService.count(null, Order.Status.pendingShipment, member, null, null, null, null, null, null, null));
		model.addAttribute("messageCount", messageService.count(member, false));
		model.addAttribute("couponCodeCount", couponCodeService.count(null, member, null, false, false));
		model.addAttribute("favoriteCount", goodsService.count(null, member, null, null, null, null, null));
		model.addAttribute("productNotifyCount", productNotifyService.count(member, null, null, null));
		model.addAttribute("reviewCount", reviewService.count(member, null, null, null));
		model.addAttribute("consultationCount", consultationService.count(member, null, null));
		model.addAttribute("newOrders", orderService.findList(null, null, member, null, null, null, null, null, null, null, NEW_ORDER_COUNT, null, null));
		return "/shop/${theme}/member/index";
	}

}