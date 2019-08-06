package com.business.msg.util.zip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by edward on 2017/3/16.
 *
 * 程序实现了ZIP压缩。共分为2部分 ：
 * 压缩（compression）与解压（decompression）
 * <p>
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。
 * 需在代码中自定义源输入路径和目标输出路径。
 * <p>
 * 在本段代码中，实现的是解压部分；压缩部分见本包中compression部分。
 * @author HAN
 *
 */
public class ZipDecompressing {

    public static List<String> handle(String filePath, String local) {
        List<String> files = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ZipInputStream zin =new ZipInputStream(fileInputStream);//输入源zip路径
            BufferedInputStream bin = new BufferedInputStream(zin);
            String Parent = local; //输出路径（文件夹目录）
            File fout = null;
            ZipEntry entry;
            try {
                while((entry = zin.getNextEntry()) != null && !entry.isDirectory()){
                    fout = new File(Parent,entry.getName());
                    if(!fout.exists()) {
                        (new File(fout.getParent())).mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(fout);
                    BufferedOutputStream Bout = new BufferedOutputStream(out);
                    int b;
                    while((b = bin.read()) != -1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    files.add(fout.getPath());
                }
                bin.close();
                zin.close();
            } catch (IOException e) {
                try {
                    bin.close();
                } catch (IOException e1) {
                }
                try {
                    zin.close();
                } catch (IOException e1) {
                }
            }
        } catch (FileNotFoundException e) {
        }
        long endTime = System.currentTimeMillis();
        return files;
    }

}