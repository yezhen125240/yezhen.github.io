/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.shop.entity.Admin;
import net.shop.exception.IncorrectCaptchaException;
import net.shop.service.AdminService;
import net.shop.service.CaptchaService;
import net.shop.util.SystemUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 权限认证
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();
		if (!captchaService.isValid(Setting.CaptchaType.adminLogin, captchaId, captcha)) {
			throw new IncorrectCaptchaException();
		}
		if (username != null && password != null) {
			Admin admin = adminService.findByUsername(username);
			if (admin == null) {
				throw new UnknownAccountException();
			}
			if (!admin.getIsEnabled()) {
				throw new DisabledAccountException();
			}
			Setting setting = SystemUtils.getSetting();
			if (admin.getIsLocked()) {
				if (ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin)) {
					int loginFailureLockTime = setting.getAccountLockTime();
					if (loginFailureLockTime == 0) {
						throw new LockedAccountException();
					}
					Date lockedDate = admin.getLockedDate();
					Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					if (new Date().after(unlockDate)) {
						admin.setLoginFailureCount(0);
						admin.setIsLocked(false);
						admin.setLockedDate(null);
						adminService.update(admin);
					} else {
						throw new LockedAccountException();
					}
				} else {
					admin.setLoginFailureCount(0);
					admin.setIsLocked(false);
					admin.setLockedDate(null);
					adminService.update(admin);
				}
			}
			if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
				int loginFailureCount = admin.getLoginFailureCount() + 1;
				if (loginFailureCount >= setting.getAccountLockCount()) {
					admin.setIsLocked(true);
					admin.setLockedDate(new Date());
				}
				admin.setLoginFailureCount(loginFailureCount);
				adminService.update(admin);
				throw new IncorrectCredentialsException();
			}
			admin.setLoginIp(ip);
			admin.setLoginDate(new Date());
			admin.setLoginFailureCount(0);
			adminService.update(admin);
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principalCollection
	 *            PrincipalCollection
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Principal principal = (Principal) principalCollection.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.findAuthorities(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}