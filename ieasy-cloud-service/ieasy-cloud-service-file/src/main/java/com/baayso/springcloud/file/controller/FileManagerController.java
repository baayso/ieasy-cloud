package com.baayso.springcloud.file.controller;

import java.io.IOException;

import org.csource.common.FastdfsException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baayso.springcloud.file.domain.FastDFSFile;
import com.baayso.springcloud.file.utils.FastDFSUtils;

/**
 * 控制器：文件管理。
 *
 * @author ChenFangjie (2020/3/5 16:13)
 * @since 0.1
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileManagerController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, FastdfsException {
        String originalFilename = file.getOriginalFilename();

        FastDFSFile fastDFSFile = FastDFSFile.builder()
                .name(originalFilename) // 文件名称
                .content(file.getBytes()) // 文件字节数组
                .ext(StringUtils.getFilenameExtension(originalFilename)) // 文件扩展名
                .build();

        String[] uploads = FastDFSUtils.upload(fastDFSFile);

        // 拼接文件的访问地址：http://ip:port/group1/M00/02/33/xxxxxx.png
        String url = FastDFSUtils.getTrackerURL() + "/" + uploads[0] + "/" + uploads[1];

        return "上传成功！";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
