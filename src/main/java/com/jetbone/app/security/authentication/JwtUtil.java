package com.jetbone.app.security.authentication;

import com.jetbone.app.entity.MyUserDetails;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.mail.AuthenticationFailedException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Chris
 * @date 2021-05-29
 */
@Slf4j
public class JwtUtil {

    private static final String SECRET = UUID.randomUUID().toString();

    static {
        log.info("[SECRET] - " + SECRET);
    }

    /**
     * 生成 jwt
     * @param authentication 认证信息
     * @return jwt
     * @throws JOSEException 异常
     */
    public static String generateToken(Authentication authentication) throws JOSEException {

        // jwt header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        Map<String, Object> payloadMap = new HashMap<>(2);
        payloadMap.put("id", myUserDetails.getId());
        payloadMap.put("username", myUserDetails.getUsername());
        Payload payload = new Payload(payloadMap);

        // jwt payload
        JWSObject jwsObject = new JWSObject(header, payload);

        // jwt signer
        JWSSigner jwsSigner = new MACSigner(SECRET);

        // signer
        jwsObject.sign(jwsSigner);

        return jwsObject.serialize();
    }

    /**
     * 校验 token
     * @param token token
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public boolean verifyToken(String token) throws JOSEException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(SECRET);
        return jwsObject.verify(jwsVerifier);
    }
}
