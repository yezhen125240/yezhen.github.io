/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao.impl;

import net.shop.dao.RoleDao;
import net.shop.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * Dao - 角色
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}