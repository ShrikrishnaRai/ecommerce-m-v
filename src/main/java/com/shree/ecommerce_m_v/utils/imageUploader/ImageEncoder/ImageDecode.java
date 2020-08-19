package com.shree.ecommerce_m_v.utils.imageUploader.ImageEncoder;

import java.io.File;

public interface ImageDecode {

    File decodeImage(String base64Image, String pathFile);
}
