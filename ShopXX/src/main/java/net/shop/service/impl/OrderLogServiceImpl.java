/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import net.shop.entity.OrderLog;
import net.shop.service.OrderLogService;

import org.springframework.stereotype.Service;

/**
 * Service - 订单记录
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long> implements OrderLogService {

}