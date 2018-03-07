package com.xiao.demo.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

    private static int length = 128;

    public static String key_value = "MIGfMUFDUIDSFHNIUDFNIONAAB";
    /**
     * 加密
     *
     * @param content 需要加密的内容
     *
     * @return
     *
     * @throws Exception 异常
     */
    private static byte[] encrypt1( String content )
            throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
        SecureRandom secureRandom = SecureRandom.getInstance( "SHA1PRNG" );
        secureRandom.setSeed( key_value.getBytes( ) );
        kgen.init( length, secureRandom );
        SecretKey secretKey = kgen.generateKey( );
        byte[] enCodeFormat = secretKey.getEncoded( );
        SecretKeySpec key = new SecretKeySpec( enCodeFormat, "AES" );
        Cipher cipher = Cipher.getInstance( "AES" );// 创建密码器
        byte[] byteContent = content.getBytes( "utf-8" );
        cipher.init( Cipher.ENCRYPT_MODE, key );// 初始化
        return cipher.doFinal( byteContent ); // 加密

    }

    /**
     * 解密
     *
     * @param content 待解密内容
     *
     * @return
     */
    private static byte[] decrypt1( byte[] content )
            throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
        SecureRandom secureRandom = SecureRandom.getInstance( "SHA1PRNG" );
        secureRandom.setSeed(key_value.getBytes( ) );
        kgen.init( length, secureRandom );
        SecretKey secretKey = kgen.generateKey( );
        byte[] enCodeFormat = secretKey.getEncoded( );
        SecretKeySpec key = new SecretKeySpec( enCodeFormat, "AES" );
        Cipher cipher = Cipher.getInstance( "AES" );// 创建密码器
        cipher.init( Cipher.DECRYPT_MODE, key );// 初始化
        byte[] result = cipher.doFinal( content );
        return result; // 加密  


    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     *
     * @return
     */
    public static byte[] encrypt2( String content ) {
        try {
            SecretKeySpec key = new SecretKeySpec( key_value.getBytes( ), "AES" );
            Cipher cipher = Cipher.getInstance( "AES/ECB/NoPadding" );
            byte[] byteContent = content.getBytes( "utf-8" );
            cipher.init( Cipher.ENCRYPT_MODE, key );// 初始化
            byte[] result = cipher.doFinal( byteContent );
            return result; // 加密  
        } catch ( NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException e ) {
            e.printStackTrace( );
        }
        return null;
    }

    public static String encrypt( String content ) {
        byte[] encryptResult;
        String stri = null;
        try {
        	if(StringUtils.isEmpty(content)){
        		return content;
        	}
            encryptResult = encrypt1( content );
            stri = Base64.encode( encryptResult );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
        if(stri==null){
        	stri=content;
        }
        return stri;
    }

    public static String decrypt( String content ) {

        byte[] decryptResult;
        String destr = null;
        try {
        	if(StringUtils.isEmpty(content)){
        		return content;
        	}
            decryptResult = decrypt1( Base64.decode( content ) );
            destr = new String( decryptResult, "UTF-8" );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
        if(destr==null){
        	destr=content;
        }
        return destr;
    }

    public static void main( String[] args ) throws Exception {
        String content = "t太阳est地";
//        // 加密
//        System.out.println( "加密前：" + content );
//
        String tt4 = encrypt( content );
        System.out.println("===="+encrypt(tt4));
        System.out.println( tt4 );
//
        System.out.println("asdf:"+decrypt(content));
//        // 解密
        String d = decrypt( tt4 );
        System.out.println( "解密后：" + d );
    }
}
