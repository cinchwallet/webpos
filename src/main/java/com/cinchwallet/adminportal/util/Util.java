package com.cinchwallet.adminportal.util;

import java.security.MessageDigest;

public class Util {

    public static String getMD5(String text){
    	String hashedString = null;
	    try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[40];
			md.update(text.getBytes("UTF-8"), 0, text.length());
			md5hash = md.digest();
			hashedString = bytesToHexString(md5hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return hashedString;
    }

    
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
