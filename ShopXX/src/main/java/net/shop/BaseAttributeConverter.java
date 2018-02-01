/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeConverter;

import net.shop.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.ResolvableType;

import com.fasterxml.jackson.databind.JavaType;

/**
 * 类型转换 - 基类
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public abstract class BaseAttributeConverter<T> implements AttributeConverter<Object, String> {

	/** 类型 */
	private JavaType javaType;

	/**
	 * 构造方法
	 */
	public BaseAttributeConverter() {
		ResolvableType resolvableType = ResolvableType.forClass(getClass());
		Type type = resolvableType.getSuperType().getGeneric().getType();
		javaType = JsonUtils.constructType(type);
	}

	/**
	 * 转换属性为数据库值
	 * 
	 * @param attribute
	 *            属性
	 * @return 数据库值
	 */
	public String convertToDatabaseColumn(Object attribute) {
		if (attribute == null) {
			return null;
		}

		return JsonUtils.toJson(attribute);
	}

	/**
	 * 转换数据库值为属性
	 * 
	 * @param dbData
	 *            数据库值
	 * @return 属性
	 */
	public Object convertToEntityAttribute(String dbData) {
		if (StringUtils.isEmpty(dbData)) {
			if (List.class.isAssignableFrom(javaType.getRawClass())) {
				return Collections.EMPTY_LIST;
			} else if (Set.class.isAssignableFrom(javaType.getRawClass())) {
				return Collections.EMPTY_SET;
			} else if (Map.class.isAssignableFrom(javaType.getRawClass())) {
				return Collections.EMPTY_MAP;
			} else {
				return null;
			}
		}

		return JsonUtils.toObject(dbData, javaType);
	}

}