package com.mechanicaleng.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info(request.getRequestURI().toString());
		// 特定的token

		// 从客户端的cookie中获取登录token
		Cookie cookieByName = CookiesUtil.getCookieByName(request, ServerConstants.USER_COOKIE_KEY);
		if (cookieByName != null) {
			UserInfoUtil.CURRENT_USER.set(cookieByName.getValue());
			CookiesUtil.extendCookie(request, response, ServerConstants.USER_COOKIE_KEY, true);
			CookiesUtil.extendCookie(request, response, ServerConstants.LOGIN_FLAG_KEY, false);
			return true;
		}
		log.info("token 校验失败.");
		//TODO, need to redirect to login page, due to tech issue, give up first
//		response.setStatus(302);
//		String loginPath = "http://" + request.getHeaders("HOST").nextElement() + ServerConstants.LOGIN_PAGE_PATH;
//		response.setHeader("location", "/");
//		response.sendRedirect("/");
		return false;
	}
}
