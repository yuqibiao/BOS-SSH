package com.yyyu.ssh.upload;

import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.TextUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.yyyu.ssh.upload.progress.CustomMultiPartRequest.SIZE_LIMIT_EXCEEDED_EXCEPTION;

/**
 * 功能：文件上传Action
 *
 * @author yu
 * @date 2017/12/4.
 */
public abstract class BaseUploadAction<T extends BaseUploadBean> extends BaseAction<T> {

    public static final String FILEPATH_IMG = "/uploadFiles/upload_Img/";


    /**
     * 文件上传
     *
     */
    protected void fileUpload() {
        BaseJsonResult result ;
        File tempFile = getModel().getFile();
        String fileFileName = getModel().getFileFileName();
        String  sizeLimitExceededException= (String) getAttributeValue(SIZE_LIMIT_EXCEEDED_EXCEPTION);
        if (!TextUtils.isEmpty(sizeLimitExceededException)){//全局大小
            result = ResultUtils.error(504, "文件太大");
            result.setData(sizeLimitExceededException);
            printJson(result, null);
            return;
        }
        if (TextUtils.isEmpty(fileFileName)) {//没选择文件
            result = ResultUtils.error(501, "没选择文件");
            printJson(result, null);
            return;
        }
        String rootPath = ServletActionContext.getServletContext().getRealPath("/");//项目的绝对路径
        System.out.println("rootPath=11===" + rootPath);
        String picName = UUID.randomUUID().toString();
        //获取文件后缀
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        if (!checkSuffix(extName)) {//文件格式不正确
            result = ResultUtils.error(502, "文件格式不正确");
            printJson(result, null);
            return;
        }
        if (tempFile.length()> getFileMaxLength()){//文件太大
            result = ResultUtils.error(503, "文件太大");
            printJson(result, null);
            return;
        }
        String uploadRelativePath = FILEPATH_IMG + picName + extName;
        String uploadPath = rootPath + uploadRelativePath;
        System.out.println("uploadPath====" + uploadPath);
        try {
            result = ResultUtils.success("成功" );
            handleFile(tempFile ,  uploadPath ,uploadRelativePath, result);
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

    /**
     * 具体处理文件操作
     * @param tempFile
     * @param uploadPath 上传文件保存路径（绝对）
     * @param uploadRelativePath 访问路径（相对）
     * @throws IOException
     */
    protected abstract void handleFile(File tempFile, String uploadPath ,String uploadRelativePath ,BaseJsonResult result) throws IOException;

    /**
     * 文件最大长度
     *
     * @return
     */
    protected abstract long getFileMaxLength();

    /**
     * 验证文件格式
     * @return
     */
    protected abstract boolean checkSuffix(String suf);


    public boolean isPic(String suf) {
        List<String> sufList = new ArrayList<>();
        sufList.add(".jpg");
        sufList.add(".gif");
        sufList.add(".png");
        return isContainSuf(sufList, suf);
    }

    public boolean isContainSuf(List<String> sufList, String targetSuf) {
        for (String suf : sufList) {
            if (suf.equalsIgnoreCase(targetSuf)) {
                return true;
            }
        }
        return false;
    }

}
