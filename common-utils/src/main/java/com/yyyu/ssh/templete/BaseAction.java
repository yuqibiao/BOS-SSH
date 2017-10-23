package com.yyyu.ssh.templete;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yyyu.ssh.utils.page.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 功能：Action基类
 *
 * @author yu
 * @date 2017/8/30.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{


    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        //通过反射创建对象
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // setSessionValue
    protected void setSessionValue(String name, Object value) {
        this.getHttpSession().setAttribute(name, value);
    }

    // getSessionValue
    protected Object getSessionValue(String name) {
        Object object = this.getHttpSession().getAttribute(name);

        return object;
    }

    // Session
    protected HttpSession getHttpSession() {
        HttpSession object = this.getRequestParm().getSession();
        return object;
    }

    // 获取request对象
    public HttpServletRequest getRequestParm() {
        // 通过ActionContext得到request对象
        return ServletActionContext.getRequest();
    }

    // 获取session对象
    public Map<String, Object> getSession() {
        return ActionContext.getContext().getSession();
    }

    // 获取application对象
    public Map<String, Object> getApplication() {
        return (Map) ActionContext.getContext().getApplication();
    }

    // 获取response对象
    public HttpServletResponse getResponse() {
        // 通过ServletActionContext类获取HttpServletResponse对象。
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置响应头与字符编码
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        return response;
    }

    //获得Request对象
    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }

    /**
     * 重定向
     * @param path
     */
    public void redirectTo(String path) throws IOException {
        getResponse().sendRedirect(path);
    }

    /**
     * 转发资源
     * @param path
     */
    public void  forwardTo(String path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getRequest().getRequestDispatcher(path);
        requestDispatcher.forward(getRequest() , getResponse());
    }

    public PrintWriter getWriter(){
        PrintWriter writer=null;
        try {
            writer = getResponse().getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    /**
     * 设置页面不缓存
     *
     * @param response
     */
    protected void setNoCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
    }

    /**
     * string to html.
     *
     * @param text String
     * @throws Exception ex
     */
    protected void toHtml(String text) throws Exception {
        HttpServletResponse response = this.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(text);
        out.flush();
        out.close();
    }

    protected void toXml(String text) throws Exception {
        HttpServletResponse response = this.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(text);
        out.flush();
        out.close();
    }

    public void setParameters(Map<String, String[]> arg0) {
        // TODO Auto-generated method stub
        arg0.remove("pager.offset");
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
}
