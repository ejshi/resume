package com.migu.resume.util;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 加密算法的封装
 * @author zhangpanfu
 */
public class EncryptUtils {

    private  final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * 将字节数组转换为16进制的字符串
     * @param byteArray 字节数组
     * @return 16进制的字符串
     */
    private static String byteArrayToHexString(byte[] byteArray){
        StringBuffer sb = new StringBuffer();
        for(byte byt:byteArray){
            sb.append(byteToHexString(byt));
        }
        return sb.toString();
    }
    /**
     * 将字节转换为16进制字符串
     * @param byt 字节
     * @return 16进制字符串
     */
    private static String byteToHexString(byte byt) {
        int n = byt;
        if (n < 0)
            n = 256 + n;
        return hexDigits[n/16] + hexDigits[n%16];
    }
    /**
     * 将摘要信息转换为相应的编码
     * @param code 编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    private static  String Encode(String code,String message){
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message
                    .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }
    /**
     * 将摘要信息转换成MD5编码
     * @param message 摘要信息
     * @return MD5编码之后的字符串
     */
    public static  String md5Encode(String message){
        return Encode("MD5",message);
    }

    /**
     * 将摘要信息转换成SHA-256编码
     * @param message 摘要信息
     * @return SHA-256编码之后的字符串
     */
    public static  String sha256Encode(String message){
        return Encode("SHA-256",message);
    }

    public static String sha256Encode(File file){
        byte[] b = new byte[1024 * 4];
        int len = 0;
        InputStream fis = null;
        String encode = null;
        try {
            fis = FileUtils.openInputStream(file);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            while ((len = fis.read(b)) != -1) {
                md.update(b, 0, len);
            }
            byte[] digest = md.digest();

            encode = byteArrayToHexString(digest);
        } catch (Exception e) {
            System.out.println("Error computing Digest: " + e);
        } finally {
            IOUtils.closeQuietly(fis);
        }
        return encode;
    }

    public static void main(String[] args) {

        System.out.println("--MD5--:"+EncryptUtils.md5Encode("Huawei123"));
        System.out.println("SHA-256:"+EncryptUtils.sha256Encode("Migu@2016"));

    }

}
