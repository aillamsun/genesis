package com.flame.provider.good.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * http://localhost:8081/index.html
 * Created by sungang on 2017/9/19.
 */

@RestController
@RequestMapping("/goods/upload")
@Slf4j
public class FileUploadController {


    @PostMapping
    public String file(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("file:{}", file.getOriginalFilename());//at this, is print ????.txt, not print 中文.txt.  Chinese garbled
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
