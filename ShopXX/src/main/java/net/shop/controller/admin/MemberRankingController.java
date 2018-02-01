/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.admin;

import javax.annotation.Resource;

import net.shop.Pageable;
import net.shop.entity.Member;
import net.shop.service.MemberService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 会员排名
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminMemberRankingController")
@RequestMapping("/admin/member_ranking")
public class MemberRankingController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Member.RankingType rankingType, Pageable pageable, Model model) {
		if (rankingType == null) {
			rankingType = Member.RankingType.amount;
		}
		model.addAttribute("rankingTypes", Member.RankingType.values());
		model.addAttribute("rankingType", rankingType);
		model.addAttribute("page", memberService.findPage(rankingType, pageable));
		return "/admin/member_ranking/list";
	}

}