package com.world.producer.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public class JavaWebTokenUtil {
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("AiDou");
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public static String createJavaWebToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static Map<String, Object> verifyJavaWebToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
