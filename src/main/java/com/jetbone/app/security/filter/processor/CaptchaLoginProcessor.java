package com.jetbone.app.security.filter.processor;

import com.jetbone.app.security.authentication.CaptchaAuthenticationToken;
import com.jetbone.app.security.authentication.LoginBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chris
 * @date 2021-04-17
 */
public class CaptchaLoginProcessor extends AbstractJsonLoginProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaLoginProcessor.class);

    private final AuthenticationManager authenticationManager;

    public CaptchaLoginProcessor(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.JSON_CAPTCHA;
    }

    @Override
    public Authentication process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("执行 CaptchaLoginProcessor");
        LoginBody loginBody = getLoginBody(request);
        CaptchaAuthenticationToken captchaAuthenticationToken =
                new CaptchaAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());

        return authenticationManager.authenticate(captchaAuthenticationToken);
    }
}
