/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 消息配置
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Entity
@Table(name = "xx_message_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_message_config")
public class MessageConfig extends BaseEntity<Long> {

	private static final long serialVersionUID = -3088643986864643815L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 会员注册 */
		registerMember,

		/** 订单创建 */
		createOrder,

		/** 订单更新 */
		updateOrder,

		/** 订单取消 */
		cancelOrder,

		/** 订单审核 */
		reviewOrder,

		/** 订单收款 */
		paymentOrder,

		/** 订单退款 */
		refundsOrder,

		/** 订单发货 */
		shippingOrder,

		/** 订单退货 */
		returnsOrder,

		/** 订单收货 */
		receiveOrder,

		/** 订单完成 */
		completeOrder,

		/** 订单失败 */
		failOrder
	}

	/** 类型 */
	private MessageConfig.Type type;

	/** 是否启用邮件 */
	private Boolean isMailEnabled;

	/** 是否启用短信 */
	private Boolean isSmsEnabled;

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false, unique = true)
	public MessageConfig.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(MessageConfig.Type type) {
		this.type = type;
	}

	/**
	 * 获取是否启用邮件
	 * 
	 * @return 是否启用邮件
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsMailEnabled() {
		return isMailEnabled;
	}

	/**
	 * 设置是否启用邮件
	 * 
	 * @param isMailEnabled
	 *            是否启用邮件
	 */
	public void setIsMailEnabled(Boolean isMailEnabled) {
		this.isMailEnabled = isMailEnabled;
	}

	/**
	 * 获取是否启用短信
	 * 
	 * @return 是否启用短信
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsSmsEnabled() {
		return isSmsEnabled;
	}

	/**
	 * 设置是否启用短信
	 * 
	 * @param isSmsEnabled
	 *            是否启用短信
	 */
	public void setIsSmsEnabled(Boolean isSmsEnabled) {
		this.isSmsEnabled = isSmsEnabled;
	}

}
