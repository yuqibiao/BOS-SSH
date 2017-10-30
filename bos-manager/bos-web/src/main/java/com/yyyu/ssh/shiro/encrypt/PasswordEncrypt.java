package com.yyyu.ssh.shiro.encrypt;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 功能：密码加密
 *
 * @author yu
 * @date 2017/10/26.
 */
public class PasswordEncrypt {

    public static void main(String[] args){
        String admin = new PasswordEncrypt().Md5(1, "admin", "123");
        System.out.println("===="+admin);
    }

    /**
     * MD5加密
     *
     * @param hashIterations 加密的次数
     * @param salt 盐
     * @param credentials 密码明文
     * @return
     */
    public String  Md5(int hashIterations , String salt , String credentials){
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                salt, hashIterations);
       return simpleHash.toString();
    }

}
