/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.job;

import javax.annotation.Resource;

import net.shop.service.CartService;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job - 购物车
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Lazy(false)
@Component("cartJob")
public class CartJob {

	@Resource(name = "cartServiceImpl")
	private CartService cartService;

	/**
	 * 清除过期购物车
	 */
	@Scheduled(cron = "${job.cart_evict_expired.cron}")
	public void evictExpired() {
		cartService.evictExpired();
	}

}