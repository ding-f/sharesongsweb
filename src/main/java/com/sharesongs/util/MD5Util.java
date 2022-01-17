package com.sharesongs.util;

import org.junit.Test;

import java.security.MessageDigest;
import java.util.UUID;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];		//转换成双位十六进制
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");		//设置转码函数为MD5
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));		//没有设置字符集就默认平台默认字符集
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	/**
	 * 获得16个长度的十六进制的UUID
	 * public static String get16UUID() {
	 * 		UUID id = UUID.randomUUID();
	 * 		String[] idd = id.toString().split("-");
	 * 		return idd[0] + idd[1] + idd[2];
	 *        }
	 * @return UUID
	 */


	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

}
