/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.admin;

import javax.annotation.Resource;

import net.shop.Message;
import net.shop.Pageable;
import net.shop.entity.BaseEntity;
import net.shop.entity.Specification;
import net.shop.service.ProductCategoryService;
import net.shop.service.SpecificationService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AndPredicate;
import org.apache.commons.collections.functors.UniquePredicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 规格
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminSpecificationController")
@RequestMapping("/admin/specification")
public class SpecificationController extends BaseController {

	@Resource(name = "specificationServiceImpl")
	private SpecificationService specificationService;
	@Resource(name = "productCategoryServiceImpl")
	private ProductCategoryService productCategoryService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long sampleId, ModelMap model) {
		model.addAttribute("sample", specificationService.find(sampleId));
		model.addAttribute("productCategoryTree", productCategoryService.findTree());
		return "/admin/specification/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Specification specification, Long productCategoryId, RedirectAttributes redirectAttributes) {
		CollectionUtils.filter(specification.getOptions(), new AndPredicate(new UniquePredicate(), new Predicate() {
			public boolean evaluate(Object object) {
				String option = (String) object;
				return StringUtils.isNotEmpty(option);
			}
		}));
		specification.setProductCategory(productCategoryService.find(productCategoryId));
		if (!isValid(specification, BaseEntity.Save.class)) {
			return ERROR_VIEW;
		}
		specificationService.save(specification);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("productCategoryTree", productCategoryService.findTree());
		model.addAttribute("specification", specificationService.find(id));
		return "/admin/specification/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Specification specification, RedirectAttributes redirectAttributes) {
		CollectionUtils.filter(specification.getOptions(), new AndPredicate(new UniquePredicate(), new Predicate() {
			public boolean evaluate(Object object) {
				String option = (String) object;
				return StringUtils.isNotEmpty(option);
			}
		}));
		if (!isValid(specification)) {
			return ERROR_VIEW;
		}
		specificationService.update(specification, "productCategory");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", specificationService.findPage(pageable));
		return "/admin/specification/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		specificationService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}