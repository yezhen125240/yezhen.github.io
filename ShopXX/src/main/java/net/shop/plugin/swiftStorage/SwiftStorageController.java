/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.plugin.swiftStorage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.shop.Message;
import net.shop.controller.admin.BaseController;
import net.shop.entity.PluginConfig;
import net.shop.service.PluginConfigService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 阿里云存储
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminSwiftStorageController")
@RequestMapping("/admin/storage_plugin/swift_storage")
public class SwiftStorageController extends BaseController {

	@Resource(name = "swiftStoragePlugin")
	private SwiftStoragePlugin swiftStoragePlugin;
	@Resource(name = "pluginConfigServiceImpl")
	private PluginConfigService pluginConfigService;

	/**
	 * 安装
	 */
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public @ResponseBody
	Message install() {
		if (!swiftStoragePlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(swiftStoragePlugin.getId());
			pluginConfig.setIsEnabled(false);
			pluginConfig.setAttributes(null);
			pluginConfigService.save(pluginConfig);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 卸载
	 */
	@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
	public @ResponseBody
	Message uninstall() {
		if (swiftStoragePlugin.getIsInstalled()) {
			pluginConfigService.deleteByPluginId(swiftStoragePlugin.getId());
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 设置
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = swiftStoragePlugin.getPluginConfig();
		model.addAttribute("pluginConfig", pluginConfig);
		//return "/net/shopxx/plugin/swiftStorage/setting";
		return "/admin/storage_plugin/setting/swiftstorage_setting";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String userId, String secretKey, String authUrl, String containerName, String urlPrefix, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order, RedirectAttributes redirectAttributes) {
		PluginConfig pluginConfig = swiftStoragePlugin.getPluginConfig();
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("userId", userId);
		attributes.put("secretKey", secretKey);
		attributes.put("authUrl", authUrl);
		attributes.put("containerName", containerName);
		attributes.put("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
		pluginConfig.setAttributes(attributes);
		pluginConfig.setIsEnabled(isEnabled);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/storage_plugin/list.jhtml";
		
	}

}