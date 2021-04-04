package com.jetbone.app.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jetbone.app.security.LoginBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Chris
 * @date 2021-03-21
 */
public class JsonLoginProcessor extends AbstractLoginProcessor {

    private static final Log logger = LogFactory.getLog(JsonLoginProcessor.class);

    @Override
    public LoginType getLoginType() {
        return LoginType.JSON;
    }

    @Override
    public String getUsername(HttpServletRequest request) {
        LoginBody body = getLoginBody(request);
        return body.getUsername();
    }

    @Override
    public String getPassword(HttpServletRequest request) {
        LoginBody body = getLoginBody(request);
        return body.getPassword();
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        logger.info("执行 JsonLoginProcessor");
    }

    private LoginBody getLoginBody(HttpServletRequest request) {
        LoginBody body = null;
        try (BufferedReader reader = request.getReader()) {
            StringBuilder bodyStr = new StringBuilder();
            String str;
            while((str = reader.readLine()) != null) {
                bodyStr.append(str);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            body = objectMapper.readValue(bodyStr.toString(), LoginBody.class);
            return body;

        } catch (IOException e) {
            // TODO
        }

        return body;
    }
}
