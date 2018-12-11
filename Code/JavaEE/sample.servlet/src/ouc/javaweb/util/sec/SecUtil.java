package ouc.javaweb.util.sec;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecUtil {

	/**
	 * 计算字符串的Md5值
	 * 
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		// 定义字节数组
		byte[] secretBytes = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // 生成MD5加密计算摘要
			md.update(plainText.getBytes()); // 对字符串进行加密
			secretBytes = md.digest(); // 获得加密后的数据
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("No Md5 Algorithm!");
		}

		String md5code = new BigInteger(1, secretBytes).toString(16);// 转换为16进制数字
		for (int i = 0; i < 32 - md5code.length(); i++) { // 如果生成数字未满32位，需要前面补0
			md5code = "0" + md5code;
		}
		
		return md5code;
	}
}
