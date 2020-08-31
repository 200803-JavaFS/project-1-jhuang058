package com.revature.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

//	public static void main(String[] args) throws Exception {
//
//		String data = "";
//		String algorithm = "MD5";
//		System.out.println(generateHash(data, algorithm));
//
//	}
	
	public static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
		//Message digests are secure one-way hash functions that take arbitrary-sized data and output a 
		//fixed-length hash value.
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		byte[] hash = digest.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private static String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	
}
