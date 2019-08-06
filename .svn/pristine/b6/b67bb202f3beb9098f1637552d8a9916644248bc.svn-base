package com.business.msg.util.zip;

import java.io.File;

/**
 * Created by edward on 2017/3/16.
 */
public class DeleteDirectory {
    public static void main(String[] args) {
        String path = "D:\\work";
        String name = "2103012";
        //
        new File(path + "\\" + name + "\\" + "21db7454f0474d158e55fb6596adac79.step").delete();
        new File(path + "\\" + name + "\\" + "b4ddc8476a06472cac0d5df95deaa597.json").delete();

        //销毁压缩包
        new File(path + "\\" + name + ".zip").delete();

        //销毁压缩解压目录
        new File(path + "\\" + name).delete();
    }
}
