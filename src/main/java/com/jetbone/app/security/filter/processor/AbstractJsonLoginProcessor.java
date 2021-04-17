package com.jetbone.app.security.filter.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetbone.app.security.authentication.LoginBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Chris
 * @date 2021-04-17
 */
public abstract class AbstractJsonLoginProcessor extends AbstractLoginProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJsonLoginProcessor.class);

    @Override
    public LoginBody getLoginBody(HttpServletRequest request) {
        LoginBody body = null;
        try (BufferedReader reader = request.getReader()) {
            StringBuilder bodyStr = new StringBuilder();
            String str;
            while((str = reader.readLine()) != null) {
                bodyStr.append(str);
            }

            if (bodyStr.length() == 0) {
                logger.error("未获取到Body信息");
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                body = objectMapper.readValue(bodyStr.toString(), LoginBody.class);
                return body;
            }

        } catch (IOException e) {
            logger.error(e.toString());
        }

        return body;
    }
}
