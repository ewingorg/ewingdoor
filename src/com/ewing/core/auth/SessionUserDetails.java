package com.ewing.core.auth;


/**
 * Session级别的回话，记录用户登录状态，如果从ServletRequestAttribute中获取不到PreSessionUserDetails，标识着用户没有登录状态，需要进行第三方平台（
 * 微信、QQ）的登陆验证；
 * 如果获取到PreSessionUserDetails，用PreSessionUserDetails.userSessionId则可以到缓存中获取客户的SessionUserDetails
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月7日
 */
public interface SessionUserDetails {

}
