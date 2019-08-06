package com.business.core.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Created by sin on 2015/4/28.
 */
public class ImageUtil {

    /**
     * 压缩图片格式
     */
    private static final String IMAGE_FORMAT_SUFFIX = "jpg";
    /**
     * 压缩的码流
     */
    private static final float IMAGE_COMPRESS_QUALITY = 0.8f;


    public static byte[] compress(BufferedImage image, String suffix) {
        Float jpegQuality = IMAGE_COMPRESS_QUALITY;
        byte[] bytes;
        if (StringUtils.isEmpty(suffix)) {
            suffix = IMAGE_FORMAT_SUFFIX;
        }
        Iterator iterator = ImageIO.getImageWritersByFormatName(suffix);
        ImageWriter writer = (ImageWriter) iterator.next();;
        try {
            ByteArrayOutputStream aos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(aos);
            writer.setOutput(ios);
            IIOImage iioImage = new IIOImage(image, null, null);

            ImageWriteParam iwp = writer.getDefaultWriteParam();
            if (iwp.canWriteCompressed()) {//判断是否支持压缩
                //int mode = iwp.getCompressionMode();//支持的压缩模式
                //iwp.setCompressionMode(mode);//设置压缩模式
                iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                bytes = aos.toByteArray();
                if (bytes.length > 1024 * 4096) {
                    jpegQuality = 0.3f;
                }else if (bytes.length > 1024 * 3072) {
                    jpegQuality = 0.4f;
                }else if (bytes.length > 1024 * 2048) {
                    jpegQuality = 0.5f;
                } else if (bytes.length > 1024 * 1024) {
                    jpegQuality = 0.6f;
                }
                iwp.setCompressionQuality(jpegQuality);
            }
            writer.write(null, iioImage, iwp);
            writer.dispose();
            return aos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("compressJPG_toByte_failed", e);
        }
    }

    public static String buildSuffix(String fileName) {
        String suffix = IMAGE_FORMAT_SUFFIX;
        if (fileName.lastIndexOf(".") != -1) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return suffix;
    }

    public static byte[] compress(MultipartFile file) {
        try {
            String suffix = buildSuffix(file.getOriginalFilename());
            return compress(ImageIO.read(file.getInputStream()), suffix);
        } catch (IOException e) {
            try {
                return file.getBytes();
            } catch (IOException e1) {
                // 不作处理
            }
            throw new RuntimeException("image util compress error...", e);
        }
    }

    public static byte[] compress(byte[] bytes, String suffix) {
        try {
            return compress(ImageIO.read(new ByteArrayInputStream(bytes)), suffix);
        } catch (IOException e) {
            throw new RuntimeException("image util compress error...");
        }
    }

    /**
     * 修改图片尺寸
     * @param image image 缓冲区（通过 ImageIo 构建）
     * @param height 高度
     * @param width 宽度
     * @return 修改后的 BufferedImage
     */
    public static BufferedImage resize (BufferedImage image, int height, int width) {
        Image compressionImage = image.getScaledInstance(height, width, Image.SCALE_SMOOTH);
        BufferedImage target = new BufferedImage(height, width, BufferedImage.TYPE_INT_BGR);
        Graphics g = target.getGraphics();
        g.drawImage(compressionImage, 0, 0, null);
        g.dispose();
        return target;
    }

    public static BufferedImage resize (byte[] bytes, int height, int width) throws IOException {
        return resize(ImageIO.read(new ByteArrayInputStream(bytes)), height, width);
    }


//    public static void main(String[] args) {
//        try {
//            InputStream inputStream = new FileInputStream("E:\\testAlbumImage.png");
//            byte[] bytes = compress(ImageIO.read(inputStream));
//            OutputStream outputStream = new FileOutputStream("E:\\Compare.jpg");
//            outputStream.write(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
