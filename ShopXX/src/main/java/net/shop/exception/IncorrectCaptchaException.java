/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Exception - 错误验证码
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -2452816550297381913L;

}