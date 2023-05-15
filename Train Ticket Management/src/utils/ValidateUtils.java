package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private static String REGEX;
    public static boolean dateValidate(String date){
        REGEX = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[012])/\\d{4}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    public static boolean dateWithHourValidate(String date){
        REGEX = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[012])/\\d{4} ([01]\\d|2[0-3]):([0-5]\\d)$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    public static boolean trainNumberValidate(String trainNumber){
        REGEX = "^SE\\d{2}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(trainNumber);
        return matcher.matches();
    }
}
