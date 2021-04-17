package com.jetbone.app.security.authentication;

import com.jetbone.app.security.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * @author Chris
 * @date 2021-04-11
 */
public class CaptchaAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaAuthenticationProvider.class);

    /**
     * 获取用户
     */
    private final UserDetailsService userDetailsService;
    /**
     * 验证码
     */
    private final CaptchaService captchaService;

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    /**
     * 构造器
     * @param userDetailsService
     * @param captchaService
     */
    public CaptchaAuthenticationProvider(UserDetailsService userDetailsService, CaptchaService captchaService) {
        this.userDetailsService = userDetailsService;
        this.captchaService = captchaService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(CaptchaAuthenticationToken.class, authentication,
                () -> messages.getMessage("CaptchaAuthenticationProvider.onlySupports",
                "Only CaptchaAuthenticationToken is supported"));

        CaptchaAuthenticationToken unAuthenticationToken = (CaptchaAuthenticationToken) authentication;
        String username = (String) unAuthenticationToken.getPrincipal();
        String captcha = (String) unAuthenticationToken.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (captchaService.verifyCaptcha(username, captcha)) {
            logger.info("验证码认证成功");
            captchaService.expireCaptcha(username);
            return createSuccessAuthentication(authentication, userDetails);
        } else {
            captchaService.expireCaptcha(username);
            throw new BadCredentialsException("captcha is not matched");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication createSuccessAuthentication(Authentication authentication, UserDetails userDetails) {
        CaptchaAuthenticationToken captchaAuthenticationToken = new CaptchaAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        captchaAuthenticationToken.setDetails(authentication.getDetails());

        return captchaAuthenticationToken;
    }
}
