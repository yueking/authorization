package com.yueking.security.demo.controller;


import com.yueking.security.demo.model.FileInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${upload.path}")
    private String uploadFolder;

    @PostMapping
    @ApiOperation("文件上传")
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(uploadFolder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        System.out.println(localFile.getAbsolutePath());
        System.out.println(file.getResource());
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    @ApiOperation("文件下载")
    public void download(@ApiParam("文件ID") @PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = id + ".txt";
        try (InputStream inputStream = new FileInputStream(new File(uploadFolder, fileName));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
