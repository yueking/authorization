package com.yueking.security.demo.model;

import lombok.Data;

@Data
public class FileInfo {
    public String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
