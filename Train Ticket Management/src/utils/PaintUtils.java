package utils;

public class PaintUtils {
    private final static String maudo = "\033[31m";
    private final static String mauxanh = "\u001B[32m";
    private final static String maucam = "\u001B[33m";
    private final static String mauxanhdatroi = "\u001B[36m";

    private final static String mauden = "\033[30m";
    public static String setRed(String str){
        return maudo+str+mauden;
    }
    public static String setGreen(String str){
        return mauxanh+str+mauden;
    }
    public static String setOrange(String str){
        return maucam+str+mauden;
    }
    public static String setBlue(String str){
        return mauxanhdatroi+str+mauden;
    }
}
