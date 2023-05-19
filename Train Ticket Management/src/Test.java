import model.EStation;
import model.Route;
import service.RouteService;
import utils.CurrencyUtils;
import utils.DateUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        Date date = DateUtils.parse("16/05/2023 08:20");
//        date = DateUtils.plusHour(date,33);
//        System.out.println(DateUtils.format(date));
//        EStation.showStationList();

//        List<Route> routeList = new ArrayList<>();
//        RouteService routeService = new RouteService(routeList);
//        Route route = routeService.createRoute();
//        System.out.println(route);
        System.out.println(CurrencyUtils.getViCurrency(200000));
    }
}
