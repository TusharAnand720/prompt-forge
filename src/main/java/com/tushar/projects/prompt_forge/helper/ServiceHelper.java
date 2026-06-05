package com.tushar.projects.prompt_forge.helper;

import java.time.Instant;

public class ServiceHelper {

    public static boolean isAvailable(String str) {
        return str != null && !str.isBlank();
    }

    public static String getRandomAlphaNumeric(int length) {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        if (length > 30) {
            length = 30;
        }
        StringBuilder randomAlphaNumeric = new StringBuilder();
        while (length-- > 0) {
            int randomCharPosition = (int) (Math.random() * alphaNumeric.length());
            char randomCharacter = alphaNumeric.charAt(randomCharPosition);
            randomAlphaNumeric.append(randomCharacter);
        }
        return randomAlphaNumeric.toString();
    }

    public static String createId(String prefix) {
        try {
            return prefix.toUpperCase() + Instant.now().toEpochMilli() + getRandomAlphaNumeric(10);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
