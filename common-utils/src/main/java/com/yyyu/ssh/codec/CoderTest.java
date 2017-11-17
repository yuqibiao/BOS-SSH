package com.yyyu.ssh.codec;

import com.yyyu.ssh.codec.cpy.Coder;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class CoderTest {


	public static void main(String[] args) throws Exception {
		String inputStr = "简单加密123123";
		System.err.println("原文:\n" + inputStr);

		byte[] inputData = inputStr.getBytes();
		String code = Coder.encryptBASE64(inputData);

		System.err.println("BASE64加密后:\n" + code);

		byte[] output = Coder.decryptBASE64(code);

		String outputStr = new String(output);

		System.err.println("BASE64解密后:\n" + outputStr);

		byte[] bytes = Coder.encryptMD5("123".getBytes());
		System.err.println("MD5解密后:\n" + new String(bytes));


	}

}
