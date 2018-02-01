/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.shop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.shop.entity.Member;
import net.shop.service.MemberService;
import net.shop.util.WebUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 会员注销
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("shopLogoutController")
public class LogoutController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		WebUtils.removeCookie(request, response, Member.USERNAME_COOKIE_NAME);
		WebUtils.removeCookie(request, response, Member.NICKNAME_COOKIE_NAME);
		if (memberService.isAuthenticated()) {
			session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		}
		return "redirect:/";
	}

}