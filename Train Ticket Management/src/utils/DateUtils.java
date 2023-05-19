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
    public static Date getCurrentDate(){
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String strCurrentDate = formatter.format(currentDate);
        return parseDate(strCurrentDate);
    }
    public static Date getCurrentDateIncludeHour(){
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date currentDate = new Date();
        String strCurrentDate = formatter.format(currentDate);
        return parseDateWithHour(strCurrentDate);
    }

    public static Date parseDateWithHour(String strDate) {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date parseDate(String strDate){
        formatter = new SimpleDateFormat("dd/MM/yyyy");
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
    public static int getAgeByDOB(Date DOB){
        Date currentDate = new Date();
        Calendar clDOB = Calendar.getInstance();
        Calendar clDate = Calendar.getInstance();

        clDOB.setTime(DOB);
        clDate.setTime(currentDate);

        int age = clDate.get(Calendar.YEAR) - clDOB.get(Calendar.YEAR);
        return age;
    }
}
