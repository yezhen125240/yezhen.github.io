/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shop.Filter;
import net.shop.Order;
import net.shop.entity.Promotion;
import net.shop.service.PromotionService;
import net.shop.util.FreeMarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 促销列表
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Component("promotionListDirective")
public class PromotionListDirective extends BaseDirective {

	/** "会员等级ID"参数名称 */
	private static final String MEMBER_RANK_ID_PARAMETER_NAME = "memberRankId";

	/** "商品分类ID"参数名称 */
	private static final String PRODUCT_CATEGORY_ID_PARAMETER_NAME = "productCategoryId";

	/** "是否已开始"参数名称 */
	private static final String HAS_BEGUN_PARAMETER_NAME = "hasBegun";

	/** "是否已结束"参数名称 */
	private static final String HAS_ENDED_PARAMETER_NAME = "hasEnded";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "promotions";

	@Resource(name = "promotionServiceImpl")
	private PromotionService promotionService;

	/**
	 * 执行
	 * 
	 * @param env
	 *            环境变量
	 * @param params
	 *            参数
	 * @param loopVars
	 *            循环变量
	 * @param body
	 *            模板内容
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long memberRankId = FreeMarkerUtils.getParameter(MEMBER_RANK_ID_PARAMETER_NAME, Long.class, params);
		Long productCategoryId = FreeMarkerUtils.getParameter(PRODUCT_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
		Boolean hasBegun = FreeMarkerUtils.getParameter(HAS_BEGUN_PARAMETER_NAME, Boolean.class, params);
		Boolean hasEnded = FreeMarkerUtils.getParameter(HAS_ENDED_PARAMETER_NAME, Boolean.class, params);
		Integer count = getCount(params);
		List<Filter> filters = getFilters(params, Promotion.class);
		List<Order> orders = getOrders(params);
		boolean useCache = useCache(env, params);
		List<Promotion> promotions = promotionService.findList(memberRankId, productCategoryId, hasBegun, hasEnded, count, filters, orders, useCache);
		setLocalVariable(VARIABLE_NAME, promotions, env, body);
	}

}