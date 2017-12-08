package com.yyyu.ssh.upload.progress;

import java.io.Serializable;

/**
 * 功能：文件上传进度信息封装bean
 *
 * @author yu
 * @date 2017/11/24.
 */
public class Progress implements Serializable{

    private long bytesRead;//读取的字节数
    private long contentLength;//文件的字节总长度
    private long items;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "bytesRead=" + bytesRead +
                ", contentLength=" + contentLength +
                ", items=" + items +
                '}';
    }

}
