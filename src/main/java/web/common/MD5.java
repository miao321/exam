package web.common;

import java.security.MessageDigest;

import Decoder.BASE64Encoder;
//import sun.misc.BASE64Encoder;
public class MD5 {
	public String EncoderByMd5(String str){
		String newstr = "";
		try{
			//确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			//加密后的 字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		}catch(Exception e){
			System.out.println(e+"  加密错误");
		}
		return newstr;
	}

}
