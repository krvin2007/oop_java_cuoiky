package com.mycompany.quanlygara.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
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
}
