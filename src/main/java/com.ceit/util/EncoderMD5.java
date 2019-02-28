package com.ceit.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by samsung on 2017/6/13.
 */
public class EncoderMD5 {
    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64Decoder=new BASE64Encoder();
        String newstr=base64Decoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    /**判断用户密码是否正确
     * @param newpassword  用户输入的密码
     * @param oldpassword  数据库中存储的密码－－用户密码的摘要
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public boolean checkpassword(String newpassword, String oldpassword) throws NoSuchAlgorithmException,UnsupportedEncodingException {
        if (EncoderByMd5(newpassword).equals(oldpassword))
            return true;
        else
            return false;
    }
    public static void main(String args[]) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        EncoderMD5 encoderMD5=new EncoderMD5();
         String s=encoderMD5.EncoderByMd5("888888");
        System.out.println(s);
    }
}
