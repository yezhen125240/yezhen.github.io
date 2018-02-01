/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shop.BaseAttributeConverter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 角色
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Entity
@Table(name = "xx_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_role")
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -5297794640913081563L;

	/** 名称 */
	private String name;

	/** 是否内置 */
	private Boolean isSystem;

	/** 描述 */
	private String description;

	/** 权限 */
	private List<String> authorities = new ArrayList<String>();

	/** 管理员 */
	private Set<Admin> admins = new HashSet<Admin>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
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
	 * 获取是否内置
	 * 
	 * @return 是否内置
	 */
	@Column(nullable = false, updatable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	/**
	 * 设置是否内置
	 * 
	 * @param isSystem
	 *            是否内置
	 */
	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(max = 200)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取权限
	 * 
	 * @return 权限
	 */
	@NotEmpty
	@Column(nullable = false, length = 4000)
	@Convert(converter = AuthorityConverter.class)
	public List<String> getAuthorities() {
		return authorities;
	}

	/**
	 * 设置权限
	 * 
	 * @param authorities
	 *            权限
	 */
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	/**
	 * 获取管理员
	 * 
	 * @return 管理员
	 */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<Admin> getAdmins() {
		return admins;
	}

	/**
	 * 设置管理员
	 * 
	 * @param admins
	 *            管理员
	 */
	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

	/**
	 * 类型转换 - 权限
	 * 
	 * @author SHOP++ Team
	 * @version 4.0
	 */
	@Converter
	public static class AuthorityConverter extends BaseAttributeConverter<List<String>> implements AttributeConverter<Object, String> {
	}

}
