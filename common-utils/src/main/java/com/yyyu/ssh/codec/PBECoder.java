package com.yyyu.ssh.codec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * 功能：PBE加密（基于密码的加密）
 * 其特点在于口令由用户自己掌管，不借助任何物理媒体；采用随机
 * 数（这里我们叫做盐）杂凑多重加密等方法保证数据的安全性。是
 * 一种简便的加密方式。
 *
 * @author yu
 * @date 2017/11/17.
 */
public abstract class PBECoder extends Coder{

    /**
     * 支持以下任意一种算法
     *
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";


    /**
     * 转换密钥
     *
     * @param password
     * @return
     */
    public static Key toKey(String password){
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKey secretKey = null;
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 加密
     *
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt)
            throws Exception {
        Key key = toKey(password);

        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

        return cipher.doFinal(data);
    }


    public static String encrypt(String data, String password){
        byte[] bytes = null;
        try {
            bytes = encrypt(data.getBytes(), password , initSalt());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(byte2Hex16(bytes));
    }

    /**
     * 解密
     *
     * @param data
     * @param password
     * @param salt
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt)
            throws Exception {

        Key key = toKey(password);
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        return cipher.doFinal(data);

    }

    public static String decrypt(String data ,String password ){

        byte[] decrypt = null;
        try {
            decrypt = decrypt(data.getBytes(), password, initSalt());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(decrypt);
    }

    /**
     * 盐初始化
     *
     * @return
     * @throws Exception
     */
    public static byte[] initSalt() throws Exception {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }



}
