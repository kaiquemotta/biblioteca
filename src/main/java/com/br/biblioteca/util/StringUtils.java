package com.br.biblioteca.util;

public class StringUtils {

    public static String capitalizeString(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
