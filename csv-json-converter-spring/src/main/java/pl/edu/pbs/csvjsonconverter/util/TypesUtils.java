package pl.edu.pbs.csvjsonconverter.util;

import java.util.regex.Pattern;

public class TypesUtils {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(strNum).matches();
    }

    public static boolean isBoolean(String strBool) {
        if (strBool == null) {
            return false;
        }
        return BOOLEAN_PATTERN.matcher(strBool).matches();
    }
}
