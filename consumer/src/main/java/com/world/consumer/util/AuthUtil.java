package com.world.consumer.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/21.
 */
public class AuthUtil {
    private static Map<String, Object> getClientLoginInfo(HttpServletRequest request) throws Exception {
        Map<String, Object> map;
        String token = request.getHeader("token");
        if (token != null && !token.equals("")) {
            map = decodeSession(token);
            return map;
        }
        throw new Exception("token解析错误");
    }
    public static long getUserId(HttpServletRequest request) throws Exception {
        return Long.valueOf((Integer)getClientLoginInfo(request).get("id"));
    }

    /*** token解密 **/
    public static Map<String, Object> decodeSession(String sessionId) {
        try {
            return JavaWebTokenUtil.verifyJavaWebToken(sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
