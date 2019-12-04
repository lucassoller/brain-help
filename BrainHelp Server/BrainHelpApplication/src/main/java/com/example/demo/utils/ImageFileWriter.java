package com.example.demo.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ImageFileWriter {
	//private final static String BASE_PATH = "C:\\imageDir\\";
	private final static String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/imagens/";


    public static String saveImageToFolder(String imageName, String base64) throws Exception {
        if (imageName == null) {
            imageName = java.util.UUID.randomUUID().toString();
        }
        byte[] array = Base64.getMimeDecoder().decode(base64);
        BufferedImage image = null;

        try (ByteArrayInputStream bis = new ByteArrayInputStream(array)) {
            image = ImageIO.read(bis);
        }
        String filePath = BASE_PATH + imageName + ".png";
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);

        return imageName;
    }

    public static String returnBase64FromFile(String imageName) throws Exception {
        String imagePath = BASE_PATH + imageName + ".png";
        File f = new File(imagePath);
        Path path = f.toPath();
        
        return Base64.getMimeEncoder().encodeToString(Files.readAllBytes(path));
    }
    
    public static void deleteFile(String imageName) {
    	String imagePath = BASE_PATH + imageName + ".png";
        File f = new File(imagePath);
        f.delete();
    }
}
