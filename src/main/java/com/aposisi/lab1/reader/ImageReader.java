package com.aposisi.lab1.reader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageReader implements Reader {

    @Override
    public byte[] read(InputStream inputStream) throws IOException {
        BufferedImage image;
        try {
            image = ImageIO.read(inputStream);
        } finally {
            inputStream.close();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
       // ImageIO.write(image, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
