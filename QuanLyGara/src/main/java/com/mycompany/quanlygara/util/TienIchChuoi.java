package com.mycompany.quanlygara.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class TienIchChuoi {
    public static String removeAccents(String str) {
        if (str == null) return null;
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(nfdNormalizedString).replaceAll("");
        result = result.replaceAll("Đ", "D");
        result = result.replaceAll("đ", "d");
        return result;
    }
    
    public static boolean hasAccents(String str) {
        if (str == null) return false;
        String unaccented = removeAccents(str);
        return !str.equals(unaccented);
    }

    public static String hashPassword(String password) {
        if (password == null) return null;
        if (password.length() == 64 && password.matches("^[a-fA-F0-9]{64}$")) {
            return password; 
        }
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
