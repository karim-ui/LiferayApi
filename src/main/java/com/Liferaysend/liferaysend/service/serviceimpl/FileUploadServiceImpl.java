package com.Liferaysend.liferaysend.service.serviceimpl;

import com.Liferaysend.liferaysend.model.fichier;
import com.Liferaysend.liferaysend.repository.FileUploadRepository;
import com.Liferaysend.liferaysend.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    @Override
    public void uploadToLocal(MultipartFile file,String target) {
        try {
            byte[] data=file.getBytes();
            Path path= Paths.get(target);
            Files.write(path,data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void uploadToDb(MultipartFile file)
    {
     fichier fich=new fichier();
        try {
            fich.setFileType(file.getContentType());
            fich.setFileData(file.getBytes());
            fich.setFileName(file.getOriginalFilename());
            fileUploadRepository.save(fich);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
