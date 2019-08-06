package com.business.file.upload;

import com.business.file.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2015/4/21.
 */
@Controller
public class BaseUploadController {

    @RequestMapping("upload")
    public void upload (MultipartFile file, HttpServletRequest request) throws IOException {
        String categoryId = request.getParameter("categoryId");
        BufferedOutputStream bufferedOutputStream = null;
        {
            File mkFile = new File(Constants.FILE_PATH + "/" + categoryId);
            if (!mkFile.isDirectory()) {
                mkFile.mkdirs();
            }
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Constants.FILE_PATH + "/" + categoryId + "/" +file.getOriginalFilename()));
            bufferedOutputStream.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
    }

    @RequestMapping("remove")
    public void remove (@RequestParam("url") String url) {
        File file = new File(Constants.FILE_PATH + url);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除文件" + url);
        }
    }

    @RequestMapping("download-resume")
    public void uploadResume (Model model, @RequestParam(value = "url", required = false) String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] urlArray = url.split("/");
        String fileName = urlArray[urlArray.length - 1];
        String fileMkdir = urlArray[urlArray.length - 2];
        File file = new File(String.format("%s/%s/%s", Constants.FILE_PATH, fileMkdir, fileName));
        if (!file.exists()) {
            model.addAttribute("core", 10);
            model.addAttribute("msg", "文件不存在");
            return;
        }

        RandomAccessFile raFile = null;
        ServletOutputStream servletOutputStream = null;
        try {
            raFile = new RandomAccessFile(file, "r");
            String range = request.getHeader("RANGE");
            int start=0, end=0;
            if(null != range && range.startsWith("bytes=")){
                String[] values =range.split("=")[1].split("-");
                start = Integer.parseInt(values[0]);
                end = Integer.parseInt(values[1]);
            }
            int requestSize = 0;
            if (end!=0 && end > start) {
                requestSize = end - start + 1;
                response.addHeader("content-length", "" + (requestSize));
            } else {
                requestSize = Integer.MAX_VALUE;
            }

            byte[] buffer = new byte[1024 * 2];

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            servletOutputStream = response.getOutputStream();

            int needSize = requestSize;

            raFile.seek(start);
            while(needSize > 0){
                int len = raFile.read(buffer);
                if(needSize < buffer.length){
                    servletOutputStream.write(buffer, 0, needSize);
                } else {
                    servletOutputStream.write(buffer, 0, len);
                    if(len < buffer.length){
                        break;
                    }
                }
                needSize -= buffer.length;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            raFile.close();
            servletOutputStream.close();
        }
    }
}
