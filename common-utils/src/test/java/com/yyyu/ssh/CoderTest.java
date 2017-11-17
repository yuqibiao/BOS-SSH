package com.yyyu.ssh;

import com.yyyu.ssh.codec.Coder;
import com.yyyu.ssh.codec.PBECoder;
import org.junit.Test;

import java.io.IOException;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/17.
 */
public class CoderTest extends BaseTest{

    @Test
    public void testBASE64() throws IOException {
        String key = "123yuqi标";
        String enKey = Coder.encryptBASE64(key.getBytes());
        log("==BASE64加密后=="+enKey);
        byte[] deBytes = Coder.decryptBASE64(enKey);
        String deKey =new String(deBytes);
        log("==BASE64解密后=="+deKey);
    }

    @Test
    public void testMD5(){
        String str = "123";
        String s1 = Coder.encryptMD5(str);
        log("====MD5加密一次："+s1);
        String s2 = Coder.encryptMD5(10, "yu", "123");
        log("===="+s2);
    }

    @Test
    public void testSHA(){
        String str = "123";
        String s1 = Coder.encryptSHA(str);
        log("==SHA 加密一次=="+s1);
        String s2 = Coder.encryptSHA(10 , "yu" , "123");
        log("==多次加salt=="+s2);

    }

    @Test
    public void testHMAC(){
        String str="123";
        String key= Coder.generateHMACKey();
        log("key========"+key);
        String s1 = Coder.encryptHMAC(str, key);
        String s2= Coder.encryptHMAC(str, key);

        log("s1=="+s1);
        log("s2=="+s2);

    }

    @Test
    public void testPBE(){
        String str = "123yuqi标";
        try {
            byte[] salt = PBECoder.initSalt();
            String encrypt = PBECoder.encrypt(str, "12345");
            log("加密后："+encrypt);
            String decrypt = PBECoder.decrypt(encrypt, "12345");
            log("解密后："+decrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
