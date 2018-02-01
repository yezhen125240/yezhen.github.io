/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.admin;

import javax.annotation.Resource;

import net.shop.TemplateConfig;
import net.shop.service.TemplateService;
import net.shop.util.SystemUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Controller - 模板
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminTemplateController")
@RequestMapping("/admin/template")
public class TemplateController extends BaseController {

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, ModelMap model) {
		if (StringUtils.isEmpty(id)) {
			return ERROR_VIEW;
		}
		model.addAttribute("templateConfig", SystemUtils.getTemplateConfig(id));
		model.addAttribute("content", templateService.read(id));
		return "/admin/template/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String id, String content, RedirectAttributes redirectAttributes) {
		if (StringUtils.isEmpty(id) || content == null) {
			return ERROR_VIEW;
		}
		templateService.write(id, content);
		freeMarkerConfigurer.getConfiguration().clearTemplateCache();
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(TemplateConfig.Type type, ModelMap model) {
		model.addAttribute("type", type);
		model.addAttribute("types", TemplateConfig.Type.values());
		model.addAttribute("templateConfigs", SystemUtils.getTemplateConfigs(type));
		return "/admin/template/list";
	}

}