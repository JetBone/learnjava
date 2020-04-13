package com.jetbone.controller;

import com.jetbone.bean.DefaultResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;

/**
 * Created by Chris on 2020/4/9
 */
@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileApi {

    @Value("${tmp.dir}")
    private String filePath;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
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

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity downloadFile() {

        String filename = "000";

        FileSystemResource resource = new FileSystemResource(MessageFormat.format("{0}/{1}", filePath, filename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @RequestMapping(value = "/download2", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile2(HttpServletResponse response) {

        String filename = "000";

        File file = new File(MessageFormat.format("{0}/{1}", filePath, filename));

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        try (OutputStream outputStream = response.getOutputStream();
             InputStream inputStream = new FileInputStream(file)) {
//            OutputStream outputStream = response.getOutputStream();
//            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[2048];
            int data = inputStream.read(bytes);
            if (data != -1) {
                outputStream.write(bytes);
                data = inputStream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
