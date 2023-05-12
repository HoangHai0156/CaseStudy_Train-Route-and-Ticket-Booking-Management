package service;

import model.Route;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Route> routeList;

    public RouteService(List<Route> routeList) {
        this.routeList = routeList;
    }

    public Route findRouteById(int id) {
        for (Route route : routeList) {
            if (route.getRouteId() == id) {
                return route;
            }
        }
        return null;
    }

    public boolean isRouteIdExist(int id) {
        for (Route route : routeList) {
            if (route.getRouteId() == id)
                return true;
        }
        return false;
    }

    public void showSingleRoute(Route route) {
        System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                "ID Chuyến", "ID tàu", "Thời gian khởi hành", "Thời gian chạy (H)", "Thời gian đến",
                "Điểm xuất phát", "Điểm kết thúc", "Giá chuyến");
        Date arriveDate = DateUtils.plusHour(route.getDepartTime(), route.getRunTime());
        System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                route.getRouteId(), route.getTrainId(), DateUtils.format(route.getDepartTime()),
                route.getRunTime(), DateUtils.format(arriveDate),
                route.getFrom().getName(), route.getDestination().getName(), route.getPrice());
    }

}
