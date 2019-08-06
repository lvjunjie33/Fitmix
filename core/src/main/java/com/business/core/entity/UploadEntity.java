package com.business.core.entity;

import com.business.core.utils.MathUtil;

/**
 * Created by sin on 2015/11/10.
 * 文件上传信息
 */
public class UploadEntity {

    /**
     * 文件名
     */
    private String name;
    /**
     * 已上传
     */
    private long read;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 上传 百分百
     */
    private Double rate;
    /**
     * 每块大小
     */
    private int partSize;
    /**
     * 一共多少块
     */
    private long partCount;
    /**
     * 上传的部分
     */
    private long uploadPartCount;

    public UploadEntity(String name, long size, long partSize) {
        // 计算分块数目
        int partCount = (int) (size / partSize);
        if (size % partSize != 0){
            partCount++;
        }
        this.name = name;
        this.partCount = partCount;
        this.size = size;
        this.partSize = (int) partSize;
    }

    public String getName() {
        return name;
    }

    public long getRead() {
        return read;
    }

    public void setRead(long read) {
        this.read = read;
        // 计算 率
        this.rate = read > this.size ? 100.00 : MathUtil.setDoubleScale(((double) read) / this.size, 2) * 100;
    }

    public long getSize() {
        return size;
    }

    public Double getRate() {
        return rate;
    }

    public int getPartSize() {
        return partSize;
    }

    public long getPartCount() {
        return partCount;
    }

    public long getUploadPartCount() {
        return uploadPartCount;
    }

    public void setUploadPartCount(long uploadPartCount) {
        this.uploadPartCount = uploadPartCount;
    }
}
