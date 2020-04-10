package com.jetbone.controller;

import com.jetbone.bean.DefaultQuery;
import com.jetbone.bean.DefaultResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chris on 2019-06-20.
 */
@Controller
@RequestMapping(value = "/hello")
public class Chapter1 {

    @RequestMapping(value = "/world", method = RequestMethod.GET)
    @ResponseBody
    public DefaultResult hello(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request);
        System.out.println(response);

        return new DefaultResult();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public DefaultResult test(DefaultQuery query) {

        System.out.println(query.getDate());

        return new DefaultResult();
    }


}
