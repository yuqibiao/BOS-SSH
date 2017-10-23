package com.yyyu.ssh.action;

import com.yyyu.ssh.dao.bean.PageInfo;
import com.yyyu.ssh.templete.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * 功能：页面跳转Action
 *
 * @author yu
 * @date 2017/10/23.
 */
@Controller
@Scope("prototype")
public class PageAction extends BaseAction<PageInfo>{

    /**
     * 重定向
     */
    @Action(value = "redirectTo")
    public void redirectTo(){
        String pageUrl = getModel().getPageUrl();
        try {
            redirectTo(pageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发
     */
    @Action(value = "forwardTo")
    public void forwardTo(){
        String pageUrl = getModel().getPageUrl();
        try {
            forwardTo(pageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
