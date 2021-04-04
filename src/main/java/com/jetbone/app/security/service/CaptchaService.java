package com.jetbone.app.security.service;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.StringUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Chris
 * @date 2021-03-21
 */
@Service
public class CaptchaService {

    private static final Log logger = LogFactory.getLog(CaptchaService.class);

    private static final String CAPTCHA = "captcha";

    public boolean verifyCaptcha(String phone, String code) {
        String captcha = getCaptcha(phone);

        if (StringUtils.isBlank(captcha)) {
            logger.info("验证码已失效");
            return false;
        }

        if (captcha.equals(code)) {
            logger.info("验证码错误");
            return false;
        }

        return true;
    }

    /**
     * 发送手机验证码
     * @param phone 手机号
     * @return 验证码
     */
    public String sendCaptcha(String phone) {
        // 获取手机验证码
        String captcha = putCaptcha(phone);
        // TODO 发送

        return captcha;
    }

    /**
     * 设置随机验证码并返回
     * @param phone 手机号
     * @return 验证码
     */
    @CachePut(cacheNames = CAPTCHA, key = "#phone")
    public String putCaptcha(String phone) {
        int randInt = (int) ((Math.random()*9+1)*100000);
        return String.valueOf(randInt);
    }

    /**
     * 获取验证码
     * @param phone 手机号
     * @return 验证码
     */
    @Cacheable(cacheNames = CAPTCHA, key = "#phone")
    public String getCaptcha(String phone) {
        return null;
    }

    /**
     * 让验证码失效
     * @param phone 手机号
     */
    @CacheEvict(cacheNames = CAPTCHA, key = "#phone")
    public void expireCaptcha(String phone) {

    }
}
