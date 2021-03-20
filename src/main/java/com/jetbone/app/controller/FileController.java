package com.jetbone.app.controller;

import com.jetbone.app.bean.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;

/**
 * Created by Chris on 2020/4/9
 * @author Chris
 */
@Api(value = "文件", tags = {"文件API"})
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @Value("${tmp.dir}")
    private String filePath;

    @ApiOperation("上传单个文件")
    @GetMapping(value = "/upload")
    public ApiResult uploadFile(MultipartFile file) {

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

        return ApiResult.ok();
    }

    @ApiOperation("下载文件")
    @GetMapping(value = "/download")
    public ResponseEntity downloadFile() {

        String filename = "000";

        FileSystemResource resource = new FileSystemResource(MessageFormat.format("{0}/{1}", filePath, filename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @ApiOperation("下载文件2")
    @GetMapping(value = "/download2")
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
