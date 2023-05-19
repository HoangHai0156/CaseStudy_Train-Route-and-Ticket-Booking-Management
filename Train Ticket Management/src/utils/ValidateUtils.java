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
    public static boolean seatIdValidate(String seatId){
        REGEX = "^R[1-9]\\d*T[1-9]\\d*C[1-9]\\d*S[1-9]\\d*$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(seatId);
        return matcher.matches();
    }
    public static boolean customerNameValidate(String customerName){
        REGEX = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(customerName);
        return matcher.matches();
    }
    public static boolean userameValidate(String userame){
        REGEX = "^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(userame);
        return matcher.matches();
    }
    public static boolean passwordValidate(String password){
        REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
