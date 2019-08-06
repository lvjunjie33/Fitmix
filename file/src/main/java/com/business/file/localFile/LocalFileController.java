package com.business.file.localFile;

import com.business.file.Constants;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by sin on 2015/10/14.
 */
@Controller
@RequestMapping("local")
public class LocalFileController {

    @RequestMapping("path")
    public void path(Model model,
                     @RequestParam(value = "path", required = false) String path) {
        File srcFile;
        if (StringUtils.isEmpty(path)) {
            srcFile = new File(Constants.LOCAL_ROOT_PATH);
        }
        else {
            srcFile = new File(path);
        }

        if (!srcFile.isDirectory() && !srcFile.isFile()) {
            model.addAttribute("code", "89000");
            model.addAttribute("_msg", "错误目录");
        }

        if (srcFile.isDirectory()) {
            model.addAttribute("files", getFileNames(srcFile));
        } else if (srcFile.isFile()) {
            String name = getNewName(path);
            File destFile = new File(Constants.FILE_MIX_PATH + "/" + name);
            try {
                FileUtils.copyFile(srcFile, destFile);
                FileUtils.deleteQuietly(srcFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            model.addAttribute("newPath", destFile.getPath());
            model.addAttribute("newName", name);
        }
        model.addAttribute("path", srcFile.getPath());
    }

    public List<String> getFileNames(File srcFile) {
        Collection<File> files = FileUtils.listFiles(srcFile, null, false);
        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public String getNewName(String srcPath) {
        return  UUID.randomUUID().toString().replace("-", "") + srcPath.substring(srcPath.lastIndexOf("."));
    }
}
