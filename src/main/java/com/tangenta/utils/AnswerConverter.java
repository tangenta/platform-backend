package com.tangenta.utils;

public class AnswerConverter {
    public static String convertTrueOrFalse(String dbVersion) {
        switch (dbVersion) {
            case "T": return "true";
            case "F": return "false";
            default: return dbVersion;
        }
    }

}
