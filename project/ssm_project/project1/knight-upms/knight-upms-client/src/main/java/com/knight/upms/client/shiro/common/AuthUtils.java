package com.knight.upms.client.shiro.common;

import org.apache.shiro.crypto.hash.SimpleHash;

public class AuthUtils {

    private AuthUtils(){};
    private static final String ALGORITHM_NAME = "md5";
    private static final int HASH_ITERATIONS =2;

    public static String encryptPassword(String password,String salt){
        return new SimpleHash(ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toHex();
    }

}
