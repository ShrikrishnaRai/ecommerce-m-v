package com.shree.ecommerce_m_v.utils.imageUploader.service;

import java.io.File;
import java.io.IOException;

public interface Thumbnail {
     File scaleImage(File image) throws IOException;
}
