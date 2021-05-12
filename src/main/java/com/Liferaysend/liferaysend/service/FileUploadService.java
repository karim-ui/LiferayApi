package com.Liferaysend.liferaysend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public void uploadToLocal(MultipartFile multipartFile,String path);
    public void uploadToDb(MultipartFile file);

}
