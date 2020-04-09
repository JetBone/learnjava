package com.jetbone.controller;

import com.jetbone.bean.DefaultResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Chris on 2020/4/9
 */
@Controller
@RequestMapping("/upload")
public class Upload {

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ResponseBody
    public DefaultResult uploadFile(MultipartFile file) {
        if (file != null && file.getSize() != 0) {
            String originalFileName = file.getOriginalFilename();
            String fileName = file.getName();
            System.out.print(originalFileName);
            System.out.print(fileName);
        }

        return new DefaultResult();
    }
}
