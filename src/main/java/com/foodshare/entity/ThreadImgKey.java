package com.foodshare.entity;

public class ThreadImgKey {
    private Long threadId;

    private String imgMd5;

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getImgMd5() {
        return imgMd5;
    }

    public void setImgMd5(String imgMd5) {
        this.imgMd5 = imgMd5 == null ? null : imgMd5.trim();
    }
}