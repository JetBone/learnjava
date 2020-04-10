package com.jetbone.controller;

import com.jetbone.bean.DefaultResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by Chris on 2020/4/9
 */
@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class Upload {

    @Value("${tmp.dir}")
    private String filePath;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ResponseBody
    public DefaultResult uploadFile(MultipartFile file) {

        System.out.println(filePath);

        if (file != null && file.getSize() != 0) {
            String originalFileName = file.getOriginalFilename();
            String fileName = file.getName();
            System.out.println(originalFileName);
            System.out.println(fileName);

            try {
                File dest = new File(MessageFormat.format("{0}/{1}", filePath, originalFileName));
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return new DefaultResult();
    }
}
