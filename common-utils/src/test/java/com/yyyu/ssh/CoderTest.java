package com.yyyu.ssh;

import com.yyyu.ssh.codec.Coder;
import com.yyyu.ssh.codec.DESCoder;
import com.yyyu.ssh.codec.PBECoder;
import com.yyyu.ssh.codec.RSACoder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

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
            String pwd = "123PW";
            String encrypt = PBECoder.encrypt(str, pwd ,salt);
            log("加密后："+encrypt);
            String decrypt = PBECoder.decrypt(encrypt, pwd, salt);
            log("解密后："+decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testDES(){
        String str = "123yuqi标";
        String key = DESCoder.initKey();
        log("key==="+key);
        String encrypt = DESCoder.encrypt(str, key);
        log("==加密后："+encrypt);
        String decrpt = DESCoder.decrpt(encrypt, key);
        log("==解密后："+decrpt);
    }

    @Test
    public void testRSA(){

        String str = "123yuqi标";
        //生成密钥对（公钥、私钥）
        String publicKey = null;
        String privateKey = null;
        try {
            Map<String, Object> stringObjectMap = RSACoder.initKey();
            publicKey  = RSACoder.getPublicKey(stringObjectMap);
            privateKey = RSACoder.getPrivateKey(stringObjectMap);
            log("==公钥："+publicKey+"               长度："+publicKey.length());
            log("==私钥："+privateKey+"                  长度："+privateKey.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //公钥加密-私钥解密
        String encryptByPublicKey = RSACoder.encryptByPublicKey(str, publicKey);
        log("==加密后："+encryptByPublicKey);
        String decryptByPrivateKey = RSACoder.decryptByPrivateKey(encryptByPublicKey, privateKey);
        log("==解密后："+decryptByPrivateKey);

        log("==============华丽的分割线===========");

        //私钥加密-公钥解密
        String encryptByPrivateKey = RSACoder.encryptByPrivateKey(str, privateKey);
        log("==加密后："+encryptByPrivateKey);
        String decryptByPublicKey = RSACoder.decryptByPublicKey(encryptByPrivateKey, publicKey);
        log("==解密后："+decryptByPublicKey);

        log("==============华丽的分割线===========");

        //私钥签名-公钥验证签名（判断消息的准确性）
        String sign = RSACoder.sign(encryptByPublicKey, privateKey);
        boolean verify = RSACoder.verify(encryptByPublicKey, publicKey, sign);
        log("==合法性："+verify);

    }




}
