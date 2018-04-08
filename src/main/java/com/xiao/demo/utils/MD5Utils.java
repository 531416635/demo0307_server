package com.xiao.demo.utils;

import java.security.MessageDigest;

public class MD5Utils {

    /**
     * 32位MD加密
     * @param key
     * @return
     */
    public static String toMD5_32(String key){
            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 16位MD加密
     * @param key
     * @return
     */
    public static String toMD5_16(String key){
        return toMD5_32(key).substring(8, 24);
    }

    public static void main(String args[]){
        String a = "NACEebh7BwXeHzk";
        System.out.println(MD5Utils.toMD5_16(a));
        System.out.println(MD5Utils.toMD5_32(a));
    }

}
