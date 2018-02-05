package com.wdb007.baseservice.utility;

import java.security.Key;

import javax.crypto.Cipher; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESedeKeySpec; 
import javax.crypto.spec.IvParameterSpec;

import org.apache.xerces.impl.dv.util.Base64;

public class Encrypt {
		// 密钥
		private final static String secretKey = "zhengqiao@wdb007.com!@#$";
		// 向量
		private final static String iv = "wdb007.c";
		// 加解密统一使用的编码方式
		private final static String encoding = "utf-8";

		/**
		 * 3DES加密
		 * 
		 * @param plainText 普通文本
		 * @return
		 * @throws Exception
		 */
		public static String encode(String plainText) {
			Key deskey = null;
			try {
				DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
				SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
				deskey = keyfactory.generateSecret(spec);

				Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
				IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
				cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
				byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
				return Base64.encode(encryptData);
			} catch (Exception e) {
				e.printStackTrace();
				return plainText;
			}
		}

		/**
		 * 3DES解密
		 * 
		 * @param encryptText 加密文本
		 * @return
		 * @throws Exception
		 */
		public static String decode(String encryptText) {
			Key deskey = null;
			try {
				DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
				SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
				deskey = keyfactory.generateSecret(spec);
				Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
				IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

				byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
				return new String(decryptData, encoding);
			} catch (Exception e) {
				e.printStackTrace();
				return encryptText;
			}
		}
		/*
		public static void main(String[] args) {
			try {
				System.out.println(encode("{'publish_ip':'101.132.76.165','publish_port':'22','publish_usr':'root','publish_pws':'ZhgZt20170904$$','local_ip':'127.0.0.1','local_port':'3306','local_usr':'root','local_pws':'Zt@165_20170906##'}"));
				System.out.println(decode("ulu0C1SpKmjF1v4ZVOBP2oj3LvWbO89EXu7JSuZc4bXcpwtZbfSeZdIibV/qka4E3E3PSibiEr4XsleYhP9C7JYXDf2A/FcrodCzmoq6a3NeXjaghmFuLBhtZxPQrqQ+G64ANdv5ztefbXpMNi/G5hDETkBtZPFdiz3XKBlvQk43b/hlZZzIFwKAt+fqwTeZ3mprQ9un6Dk0EZ5Rdwq8NRRh3F1KlmXSTIM+0MIDkENDqLJY8MbbW+6BjlQY+iZoLFLqzld2Aio="));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}

