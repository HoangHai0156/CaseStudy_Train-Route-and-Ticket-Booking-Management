package utils;

public class PaintUtils {
    public static String maudo = "\033[31m";
    public static String mauden = "\033[30m";
    public static String setRed(String str){
        return maudo+str+mauden;
    }
}
