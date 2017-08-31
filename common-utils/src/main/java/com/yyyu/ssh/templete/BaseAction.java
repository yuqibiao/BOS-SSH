package com.yyyu.ssh.templete;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yyyu.ssh.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 功能：Action基类
 *
 * @author yu
 * @date 2017/8/30.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

    private  DetachedCriteria detachedCriteria;
    protected PageBean pageBean = new PageBean();

    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        //通过反射创建对象
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定Java对象转为json，并响应到客户端页面
     * @param o
     * @param exclueds
     */
    public void printJson(Object o ,String[] exclueds){
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        if (exclueds!=null){
            jsonConfig.setExcludes(exclueds);
        }
        String json = JSONObject.fromObject(o,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定Java对象转为json，并响应到客户端页面
     * @param o
     * @param exclueds
     */
    public void printJson(List o , String[] exclueds){
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        if (exclueds!=null){
            jsonConfig.setExcludes(exclueds);
        }
        String json = JSONArray.fromObject(o,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //模型对象
    protected T model;
    @Override
    public T getModel() {
        return model;
    }

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

}
