package com.baayso.springcloud.file.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件上传信息。
 *
 * @author ChenFangjie (2020/3/5 15:38)
 * @since 0.1
 */
@Getter
@Setter
@ToString
@Builder
public class FastDFSFile implements Serializable {

    private static final long serialVersionUID = 2634541110894081037L;

    private String name;    // 文件名称
    private String ext;     // 文件扩展名
    private String md5;     // 文件MD5摘要值
    private String author;  // 文件创建作者
    private byte[] content; // 文件内容

    public FastDFSFile(String name, String ext, byte[] content) {
        this.name = name;
        this.ext = ext;
        this.content = content;
    }

    public FastDFSFile(String name, String ext, String md5, String author, byte[] content) {
        this.name = name;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
        this.content = content;
    }

}
