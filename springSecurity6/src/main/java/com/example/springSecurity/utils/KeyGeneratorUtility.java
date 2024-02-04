package com.example.springSecurity.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {

    public static KeyPair generateRsaKey(){
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPairGenerator.generateKeyPair();

        }
        catch(Exception e){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
