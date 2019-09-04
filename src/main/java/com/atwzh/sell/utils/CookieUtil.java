package com.atwzh.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 */
public class CookieUtil {

    public static void set(HttpServletResponse response, String name, String value, int expire) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name) {

        Map<String, Cookie> map = readCookie(request);
        if(map.containsKey(name)) {
            return map.get(name);
        } else {
            return null;
        }
    }

    private static Map<String, Cookie> readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> map = new HashMap<>();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie);
            }
        }
        return map;
    }

}
