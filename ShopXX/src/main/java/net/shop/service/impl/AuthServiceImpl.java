///*
// * Copyright 2005-2015 shop.net. All rights reserved.
// * Support: http://www.shop.net
// * License: http://www.shop.net/license
// */
//package net.shop.service.impl;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import javassist.ClassClassPath;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtConstructor;
//import javassist.CtField;
//import javassist.CtNewMethod;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.mgt.RealmSecurityManager;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.realm.Realm;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
///**
// * Service - Auth
// * 
// * @author SHOP++ Team
// * @version 4.0
// */
//@Lazy(false)
//@Service("authServiceImpl")
//public class AuthServiceImpl implements InitializingBean {
//
//	@Resource(type = RealmSecurityManager.class)
//	private RealmSecurityManager securityManager;
//
//	/**
//	 * 初始化
//	 */
//	@SuppressWarnings("unchecked")
//	public void afterPropertiesSet() throws Exception {
//		try {
//			if (securityManager == null) {
//				return;
//			}
//			Collection<Realm> realms = securityManager.getRealms();
//			if (CollectionUtils.isEmpty(realms)) {
//				return;
//			}
//
//			String a ="org.apache.shiro.realm.AuthorizingRealm";
//			String b ="org.apache.shiro.realm.AuthRealm";
//			String c = "private long count = 0L;";
//			String d = "private org.apache.shiro.realm.AuthorizingRealm realm;";
//			String e = "{$0.realm = $1; setAuthorizationCacheName($1.getAuthorizationCacheName());}";
//			String f ="protected org.apache.shiro.authc.AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) { Object result = realm.doGetAuthenticationInfo(token); try { net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create(); net.sf.ehcache.Ehcache cache = cacheManager.getEhcache(net.shop.Setting.CACHE_NAME); net.sf.ehcache.Element element = cache != null ? cache.get(\"\") : null; String value = element != null ? (String) element.getObjectValue() : null; long timestamp = element != null ? element.getCreationTime() : 0L; boolean isValid = false; if (value != null && timestamp > 0) { for (int i = -10; i <= 10; i++) { String str = org.apache.commons.codec.digest.DigestUtils.sha512Hex(\"normal\" + (timestamp / 60000 + i)); if (org.apache.commons.lang.StringUtils.equals(value, str)) { isValid = true; break; } } } if (isValid || count++ < 100) { return result; } } catch (Exception e) { throw new net.shop.exception.IncorrectLicenseException(); } throw new net.shop.exception.IncorrectLicenseException(); }";
//			String g = "protected org.apache.shiro.authz.AuthorizationInfo doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection principalCollection) { return realm.doGetAuthorizationInfo(principalCollection); }";
//			ClassPool classPool = ClassPool.getDefault();
//			classPool.insertClassPath(new ClassClassPath(getClass()));
//			CtClass superClass = classPool.get(a);
//			CtClass realmClass = classPool.makeClass(b, superClass);
//			realmClass.addField(CtField.make(c, realmClass));
//			realmClass.addField(CtField.make(d, realmClass));
//			CtConstructor constructor = new CtConstructor(new CtClass[] { superClass }, realmClass);
//			constructor.setBody(e);
//			realmClass.addConstructor(constructor);
//			realmClass.addMethod(CtNewMethod.make(f, realmClass));
//			realmClass.addMethod(CtNewMethod.make(g, realmClass));
//			Collection<Realm> newRealms = new ArrayList<Realm>();
//			for (Realm realm : realms) {
//				if (realm instanceof AuthorizingRealm) {
//					AuthorizingRealm authorizingRealm = (AuthorizingRealm) realmClass.toClass().getConstructor(AuthorizingRealm.class).newInstance(realm);
//					newRealms.add(authorizingRealm);
//				} else {
//					newRealms.add(realm);
//				}
//			}
//			securityManager.setRealms(newRealms);
//		} catch (Exception e) {
//		}
//	}
//
//}