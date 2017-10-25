package com.yyyu.ssh.utils;

/**
 * 功能：数据类型转换
 *
 * @author yu
 * @date 2017/10/25.
 */
public class TypeConversion {

    public static void main(String[] args){
        Integer integer = str2Int("", 12);
        System.out.println("====integer=="+integer);
    }

    /**
     * String类型转换为int类型
     *
     * @param str
     * @param def 转换异常时的默认值
     * @return
     */
    public static Integer str2Int(String str , int def){
        int result = def;
        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Integer str2Int(String str ){
        return str2Int(str , 0);
    }


}
