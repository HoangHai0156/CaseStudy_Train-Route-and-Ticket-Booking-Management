package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public static String format(Date date) {
        return formatter.format(date);
    }

    public static Date parse(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date plusHour(Date date,double hour){
        return new Date(date.getTime() + (long) hour * 60 * 60 * 1000);
    }
}
