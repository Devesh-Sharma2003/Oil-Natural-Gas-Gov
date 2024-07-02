package com.ongc.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.management.RuntimeErrorException;

public class PasswordEncryption {
	
	public static String getMd5(String input) {
		try {
			System.out.println("---------hit envcryption");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			
			BigInteger no = new BigInteger(1,messageDigest);
			
			String hashText = no.toString(16);
			while(hashText.length()<32) {
				hashText = "0"+hashText;
			}
			return hashText;
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
