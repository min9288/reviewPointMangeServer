package com.tripleCMS.tripleCMS.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.tripleCMS.tripleCMS.config.S3Uploader;
import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.repository.PhotoRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class PhotoService extends S3Uploader {

    private final PhotoRepository photoRepository;

    public PhotoService(AmazonS3Client amazonS3Client, PhotoRepository photoRepository) {
        super(amazonS3Client);
        this.photoRepository = photoRepository;
    }

    @Transactional
    public String savePhoto(MultipartFile multipartFile, String dirName, Review review) throws IOException {
        String uri = super.upload(multipartFile, dirName);
        Attphoto attphoto = new Attphoto(uri, review);
        photoRepository.save(attphoto);
        return uri;

    }

}
