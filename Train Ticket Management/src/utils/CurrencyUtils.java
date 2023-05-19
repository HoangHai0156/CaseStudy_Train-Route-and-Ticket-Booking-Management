package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyUtils {
    public static String getViCurrency(double money){
        Locale vietnamLocale = new Locale("vi","VN");

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(vietnamLocale);
        Currency vndCurrency = Currency.getInstance("VND");
        decimalFormat.setCurrency(vndCurrency);

        String formattedMoney = decimalFormat.format(money);

        return formattedMoney;
    }
}
