/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.shop.member;

import javax.annotation.Resource;

import net.shop.Pageable;
import net.shop.controller.shop.BaseController;
import net.shop.entity.Member;
import net.shop.service.MemberService;
import net.shop.service.PointLogService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 会员中心 - 我的积分
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("shopMemberPointLogController")
@RequestMapping("/member/point_log")
public class PointLogController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "pointLogServiceImpl")
	private PointLogService pointLogService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("page", pointLogService.findPage(member, pageable));
		return "/shop/${theme}/member/point_log/list";
	}

}