package com.yyyu.ssh.action;

import com.yyyu.ssh.bean.UploadBean;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 功能：文件上传Action
 *
 * @author yu
 * @date 2017/12/4.
 */
@Controller
@Scope("prototype")
@Namespace("/upload")
public class UploadAction extends BaseAction<UploadBean>{

    public static final String FILEPATH_IMG = "/uploadFiles/upload_Img/";

    @Action(value = "test")
    public void test(){
        BaseJsonResult<String> result = new BaseJsonResult<>();
        result.setMsg("123");
        printJson(result , null);

    }


    @Action(value = "fileUpload")
    public void fileUpload(){

        BaseJsonResult<String > result ;

        File tempFile = getModel().getFile();
        String fileFileName = getModel().getFileFileName();

        String rootPath = ServletActionContext.getServletContext().getRealPath("/");

        System.out.println("rootPath====" + rootPath);
        String picName = UUID.randomUUID().toString();
        //获取文件后缀
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        if (!isPic(extName)){
            result = ResultUtils.error(501 ,"文件格式不正确");
        }else{
            String uploadPath = rootPath + FILEPATH_IMG + picName + extName;
            System.out.println("uploadPath====" + uploadPath);
            try {
                FileUtils.copyFile(tempFile, new File(uploadPath));
                //删除临时文件
                tempFile.delete();
                result = ResultUtils.success("成功");
            } catch (IOException e) {
                result = ResultUtils.error(500 , e.getMessage());
                e.printStackTrace();
                System.out.println("异常===" + e.getMessage());
            }
        }
        printJson(result, null);
    }


    public boolean isPic(String suf){
        List<String> sufList = new ArrayList<>();
        sufList.add(".jpg");
        sufList.add(".gif");
        sufList.add(".png");
        return isContainSuf(sufList , suf);
    }

    public boolean isContainSuf(List<String> sufList ,String targetSuf){
        for (String suf :sufList) {
            if (suf.equalsIgnoreCase(targetSuf)){
                return true;
            }
        }
        return false;
    }

}
