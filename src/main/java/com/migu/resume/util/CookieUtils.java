package com.migu.resume.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 方便从request中提醒cookie字段
 * @author zhangpanfu
 */
public class CookieUtils {

    private static String getCookie(HttpServletRequest req,String key)
    {
        if(req.getCookies()==null)
        {
            return null;
        }
        for(Cookie cookie : req.getCookies()){
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;

    }
    public static String getSessionId(HttpServletRequest req)
    {
      return getCookie(req,"JESSION_ID");

    }
}
