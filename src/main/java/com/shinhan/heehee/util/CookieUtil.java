package com.shinhan.heehee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static void addCookie(String cookieName, String value, HttpServletResponse resp) {
		try {
			value = URLEncoder.encode(value, "utf-8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
		}
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		resp.addCookie(cookie);
	}
}
