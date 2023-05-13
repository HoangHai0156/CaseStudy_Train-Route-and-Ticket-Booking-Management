import utils.DateUtils;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = DateUtils.parse("16/05/2023 08:20");
        date = DateUtils.plusHour(date,33);
        System.out.println(DateUtils.format(date));
    }
}
