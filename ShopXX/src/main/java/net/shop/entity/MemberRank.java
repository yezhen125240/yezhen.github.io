/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 会员等级
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Entity
@Table(name = "xx_member_rank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_member_rank")
public class MemberRank extends BaseEntity<Long> {

	private static final long serialVersionUID = 7058744879564482705L;

	/** 名称 */
	private String name;

	/** 优惠比例 */
	private Double scale;

	/** 消费金额 */
	private BigDecimal amount;

	/** 是否默认 */
	private Boolean isDefault;

	/** 是否特殊 */
	private Boolean isSpecial;

	/** 会员 */
	private Set<Member> members = new HashSet<Member>();

	/** 促销 */
	private Set<Promotion> promotions = new HashSet<Promotion>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取优惠比例
	 * 
	 * @return 优惠比例
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	@Column(nullable = false, precision = 12, scale = 6)
	public Double getScale() {
		return scale;
	}

	/**
	 * 设置优惠比例
	 * 
	 * @param scale
	 *            优惠比例
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}

	/**
	 * 获取消费金额
	 * 
	 * @return 消费金额
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置消费金额
	 * 
	 * @param amount
	 *            消费金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取是否默认
	 * 
	 * @return 是否默认
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsDefault() {
		return isDefault;
	}

	/**
	 * 设置是否默认
	 * 
	 * @param isDefault
	 *            是否默认
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 获取是否特殊
	 * 
	 * @return 是否特殊
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIsSpecial() {
		return isSpecial;
	}

	/**
	 * 设置是否特殊
	 * 
	 * @param isSpecial
	 *            是否特殊
	 */
	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */
	@OneToMany(mappedBy = "memberRank", fetch = FetchType.LAZY)
	public Set<Member> getMembers() {
		return members;
	}

	/**
	 * 设置会员
	 * 
	 * @param members
	 *            会员
	 */
	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	/**
	 * 获取促销
	 * 
	 * @return 促销
	 */
	@ManyToMany(mappedBy = "memberRanks", fetch = FetchType.LAZY)
	public Set<Promotion> getPromotions() {
		return promotions;
	}

	/**
	 * 设置促销
	 * 
	 * @param promotions
	 *            促销
	 */
	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		Set<Promotion> promotions = getPromotions();
		if (promotions != null) {
			for (Promotion promotion : promotions) {
				promotion.getMemberRanks().remove(this);
			}
		}
	}

}
