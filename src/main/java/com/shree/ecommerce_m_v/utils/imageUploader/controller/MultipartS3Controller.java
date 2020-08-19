package com.shree.ecommerce_m_v.utils.imageUploader.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shree.ecommerce_m_v.utils.imageUploader.domain.ImageUrlResponse;
import com.shree.ecommerce_m_v.utils.imageUploader.service.Thumbnail;
import com.shree.ecommerce_m_v.utils.values.CONST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestPart;

import java.io.File;
import java.io.IOException;


/**
 * This controller is used for uploading files to s3 bucket
 * Every rest controller which needs to upload image to s3 should extends this controller class
 */
public abstract class MultipartS3Controller extends MultipartController {

    @Autowired
    private AmazonS3 s3client;

    @Value("${endpointUrl}")
    private String endpointUrl;

    @Value("${bucketName}")
    private String bucketName;

    private String originalUrl;
    private String thumbnailUrl;


    @Autowired
    Thumbnail thumbnail;

    private String uploadFileTos3bucket(String fileName, File file) {
        try {
            s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
        } catch (AmazonServiceException e) {
            return "uploadFileTos3bucket().Uploading failed :" + e.getMessage();
        }
        return s3client.getUrl(bucketName, fileName).toString();
    }


    protected ImageUrlResponse uploadImage(@RequestPart(value = "file") final File file, final String imagePath) throws Exception {
        Thread thread = new uploadOriginal(imagePath, file);
        thread.start();
        Thread thread1 = new uploadThumbnail(thumbnail, imagePath, file);
        thread1.start();
        thread.join();
        thread1.join();
        return new ImageUrlResponse(originalUrl, thumbnailUrl);
    }

    protected ImageUrlResponse uploadImageSlider(@RequestPart(value = "file") File file) throws InterruptedException {
        Thread thread = new uploadOriginal(CONST.IMAGE_SLIDER_AWS_URL, file);
        thread.start();
        thread.join();
        return new ImageUrlResponse(originalUrl);
    }

    class uploadThumbnail extends Thread {
        private final File file;
        private final String imagePath;
        private Thumbnail thumbnailsIMPL;

        uploadThumbnail(Thumbnail thumbnailsIMPL, final String imagePath, final File file) {
            this.thumbnailsIMPL = thumbnailsIMPL;
            this.file = file;
            this.imagePath = imagePath;
        }

        @Override
        public void run() {
            try {
                final File tempFile = thumbnailsIMPL.scaleImage(file);
                thumbnailUrl = uploadFileTos3bucket(imagePath + tempFile.getName(), tempFile);
                tempFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class uploadOriginal extends Thread {
        private final String imagePath;
        private final File file;


        uploadOriginal(final String imagePath, final File file) {
            this.imagePath = imagePath;
            this.file = file;
        }


        @Override
        public void run() {
            originalUrl = uploadFileTos3bucket(imagePath + file.getName(),
                  file);
            file.delete();

        }

    }
}
