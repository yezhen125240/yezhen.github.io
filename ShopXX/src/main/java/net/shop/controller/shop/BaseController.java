/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.controller.shop;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import net.shop.DateEditor;
import net.shop.Message;
import net.shop.Setting;
import net.shop.StringEditor;
import net.shop.exception.IllegalLicenseException;
import net.shop.exception.ResourceNotFoundException;
import net.shop.template.directive.FlashMessageDirective;
import net.shop.util.SpringUtils;
import net.shop.util.SystemUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 基类
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class BaseController {

	/** Logger */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** 错误视图 */
	protected static final String ERROR_VIEW = "/shop/${theme}/common/error";

	/** 错误消息 */
	protected static final Message ERROR_MESSAGE = Message.error("shop.message.error");

	/** 成功消息 */
	protected static final Message SUCCESS_MESSAGE = Message.success("shop.message.success");

	/** "验证结果"属性名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	@Resource(name = "validator")
	private Validator validator;

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
		binder.registerCustomEditor(String.class, "password", new StringEditor(true));
	}

	/**
	 * 异常处理
	 * 
	 * @param exception
	 *            异常
	 * @param response
	 *            HttpServletResponse
	 * @return 数据视图
	 */
	@ExceptionHandler
	public ModelAndView exceptionHandler(Exception exception, HttpServletResponse response) {
		if (exception instanceof ResourceNotFoundException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ModelAndView("/shop/${theme}/common/resource_not_found");
		} else if (exception instanceof IllegalLicenseException) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return new ModelAndView("/shop/${theme}/common/illegal_license");
		} else {
			logger.warn("Handler execution resulted in exception", exception);
			Setting setting = SystemUtils.getSetting();
			ModelMap model = new ModelMap();
			if (setting.getIsDevelopmentEnabled()) {
				model.addAttribute("exception", exception);
			}
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ModelAndView(ERROR_VIEW, model);
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Assert.notNull(target);

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		}
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
		return false;
	}

	/**
	 * 数据验证
	 * 
	 * @param targets
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Collection<Object> targets, Class<?>... groups) {
		Assert.notEmpty(targets);

		for (Object target : targets) {
			if (!isValid(target, groups)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Assert.notNull(type);
		Assert.hasText(property);

		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		}
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
		return false;
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param properties
	 *            属性
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, Map<String, Object> properties, Class<?>... groups) {
		Assert.notNull(type);
		Assert.notEmpty(properties);

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			if (!isValid(type, entry.getKey(), entry.getValue(), groups)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 货币格式化
	 * 
	 * @param amount
	 *            金额
	 * @param showSign
	 *            显示标志
	 * @param showUnit
	 *            显示单位
	 * @return 货币格式化
	 */
	protected String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
		Setting setting = SystemUtils.getSetting();
		String price = setting.setScale(amount).toString();
		if (showSign) {
			price = setting.getCurrencySign() + price;
		}
		if (showUnit) {
			price += setting.getCurrencyUnit();
		}
		return price;
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	/**
	 * 添加瞬时消息
	 * 
	 * @param redirectAttributes
	 *            RedirectAttributes
	 * @param message
	 *            消息
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}

}