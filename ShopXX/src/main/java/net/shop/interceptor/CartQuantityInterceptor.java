/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shop.entity.Cart;
import net.shop.util.WebUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor - 购物车数量
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class CartQuantityInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 请求前处理
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param handler
	 *            处理器
	 * @return 是否继续执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		WebUtils.removeCookie(request, response, Cart.QUANTITY_COOKIE_NAME);
		return super.preHandle(request, response, handler);
	}

}