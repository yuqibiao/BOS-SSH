package com.yyyu.ssh.codec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能：基础加密组件(BASE64 、MD5、SHA、HMAC）
 *              消息摘要加密、验证重复性
 *
 * @author yu
 * @date 2017/11/17.
 */
public abstract  class Coder {


    /**
     * 生成HMAC密钥
     *
     * @return
     */
    public static String generateHMACKey(){

        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("HmacMD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey secretKey = keyGenerator.generateKey();

        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) {

        SecretKey secretKey = null;
        Mac mac =null;
        try {
            secretKey = new SecretKeySpec(decryptBASE64(key), "HmacMD5");
            mac= Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac.doFinal(data);

    }

    public static String encryptHMAC(String data , String key){

        byte[] bytes = encryptHMAC(data.getBytes(), key);

        return byte2Hex16(bytes);
    }


    /**
     * SHA加密
     *
     * @param data
     * @return
     */
    public static byte[] encryptSHA(byte[] data){

        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sha.update(data);

        return sha.digest();
    }

    public static String encryptSHA(String data){

        byte[] bytes = encryptSHA(data.getBytes());

        return byte2Hex16(bytes);
    }

    public static String encryptSHA(Integer hashIterations , String salt  , String data){

        String result = salt+data;
        for (int i = 0; i < hashIterations; i++) {
            result= encryptSHA(result);
        }

        return result;
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static byte[] encryptMD5(byte[] data){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(data);
        return md5.digest();
    }

    public static String encryptMD5(String data){

        byte[] md = encryptMD5(data.getBytes());

        return byte2Hex16(md);
    }

    public static String encryptMD5(Integer hashIterations , String salt  , String data){

        String result = salt+data;
        for (int i = 0; i < hashIterations; i++) {
            result= encryptMD5(result);
        }

        return result;
    }


    /**
     * BASE64 解密
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] decryptBASE64(String data) throws IOException {

        return new BASE64Decoder().decodeBuffer(data);
    }


    /**
     * BASE64 加密
     *
     * @param data
     * @return
     */
    public static String encryptBASE64( byte[] data){

        return new BASE64Encoder().encodeBuffer(data);
    }

    protected static String byte2Hex16(byte[] bytes){
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        // 把密文转换成十六进制的字符串形式
        int j = bytes.length;
        char[]  str= new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {   //  i = 0
            byte byte0 = bytes[i];  //95
            str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
            str[k++] = md5String[byte0 & 0xf];   //   F
        }
        return  new String(str);
    }

}
