package ar.edu.uade.util;

import java.util.regex.Pattern;

public class FormValidator {

    public static boolean patternMatches(String text, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(text)
                .matches();
    }

    public static boolean validateEmail(String text) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return patternMatches(text, regexPattern);
    }

    public static boolean validateAge(String text) {
        String regexPattern = "\\d+";
        return patternMatches(text, regexPattern)
                && Integer.parseInt(text) < 130;
    }
}
