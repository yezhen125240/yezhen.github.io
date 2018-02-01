/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import net.shop.entity.OrderItem;
import net.shop.service.OrderItemService;

import org.springframework.stereotype.Service;

/**
 * Service - 订单项
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long> implements OrderItemService {

}