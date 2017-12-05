package com.yyyu.ssh.upload.progress;

import org.apache.commons.fileupload.ProgressListener;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 功能：上传进度监听
 *
 * @author yu
 * @date 2017/11/24.
 */
@Resource
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;
    public void setSession(HttpSession session){
        this.session=session;
        Progress status = new Progress();//保存上传状态
        session.setAttribute("status", status);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);
        System.out.println("status："+status);
    }
}
