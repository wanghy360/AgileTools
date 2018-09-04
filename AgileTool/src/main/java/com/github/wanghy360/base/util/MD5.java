package com.github.wanghy360.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encrypt(String paramString) {
        try {
            MessageDigest localMessageDigest;
            (localMessageDigest = MessageDigest.getInstance("MD5"))
                    .update(paramString.getBytes());
            return toHexString(localMessageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    public static String encrypt(byte[] paramArrayOfByte) {
        try {
            MessageDigest localMessageDigest;
            (localMessageDigest = MessageDigest.getInstance("MD5"))
                    .update(paramArrayOfByte);
            return toHexString(localMessageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String toHexString(byte[] paramArrayOfByte) {
        StringBuffer localStringBuffer = new StringBuffer(
                paramArrayOfByte.length * 2);
        for (int i = 0; i < paramArrayOfByte.length; i++) {
            localStringBuffer.append(Character.forDigit(
                    (paramArrayOfByte[i] & 0xF0) >> 4, 16));
            localStringBuffer.append(Character.forDigit(
                    paramArrayOfByte[i] & 0xF, 16));
        }
        return localStringBuffer.toString();
    }
}
