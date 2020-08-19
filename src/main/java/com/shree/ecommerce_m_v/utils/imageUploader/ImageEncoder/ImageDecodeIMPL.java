package com.shree.ecommerce_m_v.utils.imageUploader.ImageEncoder;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class ImageDecodeIMPL implements ImageDecode {

    @Override
    public File decodeImage(String base64Image, String pathFile) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathFile)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(imageByteArray);
            return new File(pathFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
