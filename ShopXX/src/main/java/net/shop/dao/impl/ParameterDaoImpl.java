/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.ParameterDao;
import net.shop.entity.Parameter;

import org.springframework.stereotype.Repository;

/**
 * Dao - 参数
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("parameterDaoImpl")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter, Long> implements ParameterDao {

}