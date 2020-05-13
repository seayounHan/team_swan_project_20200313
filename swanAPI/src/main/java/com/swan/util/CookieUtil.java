package com.swan.util;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

@Service
public class CookieUtil {

	public Cookie makeCookie(String cookieName, String value) {
		Cookie newCookie = new Cookie(cookieName, value);
		newCookie.setPath("/");

		return newCookie;
	}

	public Cookie setExpiredCookie(String cookieName) {
		Cookie expiredCookie = new Cookie(cookieName, null);
		expiredCookie.setPath("/");
		expiredCookie.setMaxAge(0);

		return expiredCookie;
	}
}
