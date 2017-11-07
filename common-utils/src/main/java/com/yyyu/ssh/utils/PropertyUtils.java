package com.yyyu.ssh.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 功能：property文件读取的工具类
 *
 * @author yu
 * @date 2017/11/7.
 */
public class PropertyUtils {

    /**
     * 获取值
     * @param key
     * @param filePath
     * @return
     */
    public static String  getValue(String key,String filePath , Class actionClazz) throws IOException {
        Properties properties = new Properties();
        InputStream in = actionClazz.getClassLoader().getResourceAsStream(filePath);
        properties.load(in);
       return properties.getProperty("hashIterations");
    }

    /**
     * 读取property文件存在map集合中
     *
     * @param filePath
     * @return
     */
    private  static Map<String, String> parseProperty2Map(String filePath) {

        Map<String, String> result = new HashMap();

        Properties prop = new Properties();
        try {
            //读取属性文件
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = prop.getProperty(key);
                result.put(key, value);
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

}
