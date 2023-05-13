package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat formatter;

    public static String format(Date date) {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(date);
    }

    public static Date parse(String strDate) {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date plusHour(Date date,double hour){
//        return new Date(date.getTime() + (long) hour * 60 * 60 * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, (int) hour);
        return calendar.getTime();
    }
    public static String formatDateWithoutHour(Date date){
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
