/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.admin;

import javax.annotation.Resource;

import net.shop.Message;
import net.shop.Pageable;
import net.shop.service.PaymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 收款单
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminPaymentController")
@RequestMapping("/admin/payment")
public class PaymentController extends BaseController {

	@Resource(name = "paymentServiceImpl")
	private PaymentService paymentService;

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("payment", paymentService.find(id));
		return "/admin/payment/view";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", paymentService.findPage(pageable));
		return "/admin/payment/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		paymentService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}