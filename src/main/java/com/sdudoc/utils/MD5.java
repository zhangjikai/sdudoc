package com.sdudoc.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MD5 {

	/**
	 * 对信息进行加密，先MD5，再base64
	 * 
	 * @param message
	 * @return
	 */
	public static String md5_base64(String message) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("md5");
			byte[] md5 = mDigest.digest(message.getBytes("UTF-8"));
			String newstr = Base64.encodeBase64String(md5);
			return newstr;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String md5(String message) {
		MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("md5");  
            md.update(message.getBytes());  
            byte[] md5Bytes = md.digest();  
            return bytes2Hex(md5Bytes);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
		return "";
	}

	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

}
