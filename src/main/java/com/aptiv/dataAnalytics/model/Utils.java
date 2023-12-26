package com.aptiv.dataAnalytics.model;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private final Random Random = new SecureRandom();
    private String ALPHABET ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS=10000;
    private int KEY_LENGTH=256;

    public String generateAdminId(int length){
        return generateRandomString(length);
    }

    public String generateProjectId(int length){
        return generateRandomString(length);
    }
    private String generateRandomString(int length){
        StringBuilder returnValue= new StringBuilder(length);

        for (int i =0; i<length; i++){
            returnValue.append(ALPHABET.charAt(Random.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
