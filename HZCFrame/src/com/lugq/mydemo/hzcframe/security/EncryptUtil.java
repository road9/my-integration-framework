package com.lugq.mydemo.hzcframe.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @ClassName: EncryptUtil
 * @author lugq
 * @date 2014年9月10日 下午6:26:16
 *
 */
public class EncryptUtil {

    // encrypt the string data with MD5
    public final static String MD5Encrypt(String s) {
        String result = "";
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        byte[] strTemp = s.getBytes();
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            result = new String(str);
        }
        catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
