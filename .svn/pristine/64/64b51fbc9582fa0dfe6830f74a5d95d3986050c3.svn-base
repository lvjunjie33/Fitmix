package com.business.core.utils;

import com.business.core.client.FileCenterClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ZxingUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private ZxingUtil() {}

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static String writeToFile(BitMatrix matrix, String format, int fileType) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(image, format,bao);
        byte[] bytes= bao.toByteArray();
        String fileName = "." + format;
        String path = FileCenterClient.upload(bytes, fileType, fileName);
        return path;
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static String createEWCode(String content, int fileType) {
        String result = "";
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
            result = ZxingUtil.writeToFile(bitMatrix, "png", fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        String[] versionArray = new String[]{"fitmix.1.1.15.1", "fitmix.1.1.15.2", "fitmix.1.1.15.3", "fitmix.1.1.15.4"};
//        try {
//            for (String str:  versionArray) {
//                String content = "http://fs.igeekery.com/" + str + ".apk";
//                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//                Map hints = new HashMap();
//                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//                BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500,hints);
//                BufferedImage image = toBufferedImage(bitMatrix);
//                ByteArrayOutputStream bao = new ByteArrayOutputStream();
//                ImageIO.write(image, "png",bao);
//                byte[] bytes= bao.toByteArray();
//                FileUtils.writeByteArrayToFile(new File("E:\\" +  str + ".png"), bytes);
//                System.out.println(content);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//
//        String[] versionArray = new String[]{"1.1.15.1", "1.1.15.2", "1.1.15.3", "1.1.15.4"};
//        try {
//            for (String str:  versionArray) {
//                String content = "https://igeekery.com/app/" + str + "/ios_download.html";
//                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//                Map hints = new HashMap();
//                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//                BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500,hints);
//                BufferedImage image = toBufferedImage(bitMatrix);
//                ByteArrayOutputStream bao = new ByteArrayOutputStream();
//                ImageIO.write(image, "png",bao);
//                byte[] bytes= bao.toByteArray();
//                FileUtils.writeByteArrayToFile(new File("E:\\ios_" +  str + ".png"), bytes);
//                System.out.println(content);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}