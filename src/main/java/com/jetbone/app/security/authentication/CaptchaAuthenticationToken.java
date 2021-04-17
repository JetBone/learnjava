package com.jetbone.app.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义验证码 token
 * @author Chris
 * @date 2021-04-05
 */
public class CaptchaAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private String captcha;


    public CaptchaAuthenticationToken(Object principal, String credentials) {
        super(null);
        this.principal = principal;
        this.captcha = credentials;
        setAuthenticated(false);
    }

    public CaptchaAuthenticationToken(Object principal, String captcha,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.captcha = captcha;

        // must use super, as we override
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.captcha;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        captcha = null;
    }
}
