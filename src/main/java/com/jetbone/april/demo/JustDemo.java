package com.jetbone.april.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chirschan on 2019/3/14.
 */

@RestController
public class JustDemo {

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello() {
        return "hello";
    }

}
