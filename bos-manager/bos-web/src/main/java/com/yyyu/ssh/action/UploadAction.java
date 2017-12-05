package com.yyyu.ssh.action;

import com.yyyu.ssh.bean.UploadBean;
import com.yyyu.ssh.upload.BaseUploadAction;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/12/5.
 */
@Controller
@Scope("prototype")
@Namespace("/upload")
public class UploadAction extends BaseUploadAction<UploadBean>{

    @Action(value = "modifyUserIcon")
    public void modifyUserIcon(){

        fileUpload("修改成功");
    }

    @Override
    protected void handleFile(File tempFile, String uploadPath) throws IOException {
        float x = getModel().getX();
        float y = getModel().getY();
        float x2 = getModel().getX2();
        float y2 = getModel().getY2();
        float boundx = getModel().getBoundx();
        float boundy = getModel().getBoundy();
        BufferedImage bufferedImage = ImageIO.read(tempFile);
        float srcImageHeight = bufferedImage.getHeight();
        float srcImageWidth = bufferedImage.getWidth();
        float scaleX = srcImageWidth / boundx;
        float scaleY = srcImageHeight / boundy;
        int rx = (int) (x * scaleX);
        int ry = (int) (y * scaleY);
        int rx2 = (int) (x2 * scaleX);
        int ry2 = (int) (y2 * scaleY);
        File saveFile = new File(uploadPath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        Thumbnails.of(tempFile)
                .sourceRegion(rx, ry, rx2, ry2)
                .size(rx2 - rx, ry2 - ry)
                .keepAspectRatio(false)
                .toFile(saveFile);
        //FileUtils.copyFile(tempFile, new File(uploadPath));
        //删除临时文件
        tempFile.delete();
    }

    @Override
    protected long getFileMaxLength() {
        return 1024*1024*5;
    }

    @Override
    protected boolean checkSuffix(String suf) {
        return isPic(suf);
    }

}
