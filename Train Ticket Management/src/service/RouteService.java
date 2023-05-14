package service;

import Comparator.RouteComparator;
import model.ERouteType;
import model.EStation;
import model.Route;
import utils.*;
import views.ManageTrainView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    public static Scanner scanner = new Scanner(System.in);
    private List<Route> routeList;
    public static ManageTrainView manageTrainView = new ManageTrainView();
    public static TrainService trainService = new TrainService(manageTrainView.getTrainList());

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

    public int getRouteIndexByID(int id) {
        for (int i = 0; i < routeList.size(); i++) {
            if (routeList.get(i).getRouteId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void editRoutePrice(Route route) {
        double price = getInputPrice();

        int index = getRouteIndexByID(route.getRouteId());
        route.setPrice(price);
        routeList.set(index, route);
    }

    public double getInputPrice() {
        double price;
        boolean check;

        do {
            check = false;
            System.out.println("Nhập giá cho chuyến đi");
            price = ActionUtils.doubleHandleInput("giá");
            if (price < 0) {
                System.out.println("Giá không thể để giá trị âm. Vui lòng nhập lại");
                check = true;
            }
        } while (check);
        return price;
    }

    public void editRouteTrainId(Route route) {
        int id;
        boolean check;

        do {
            check = false;
            System.out.println("Nhập id tàu mới");
            id = getInputTrainId();
            check = checkConflictDepartDate(route.getDepartTime(), id,route.getRouteId(),route.getFrom(),route.getDestination());
        } while (check);

        int index = getRouteIndexByID(route.getRouteId());
        route.setTrainId(id);
        routeList.set(index, route);
    }
    public int getInputTrainId(){
        int trainId;
        boolean check;

        do {
            check = false;
            System.out.println("Nhập id tàu cho chuyến đi");
            trainId = ActionUtils.intHandleInput("id");
            if (!trainService.isTrainIdExist(trainId)) {
                System.out.println("Không tìm thấy ID tàu. Vui lòng nhập lại");
                check = true;
            }
        } while (check);
        return trainId;
    }
    public void editFromStation(Route route){
        int stationId;
        EStation eStation;
        boolean isInvalidStation;

        System.out.println("Sửa điểm khởi hành");
        EStation.showStationList();
        do {
            isInvalidStation = false;
            System.out.println("Chọn điểm khởi hành thích hợp theo ID");
            stationId = Integer.parseInt(scanner.nextLine());
            eStation = EStation.getEStationById(stationId);
            if (eStation == null || eStation.name().equals(route.getDestination().name())){
                System.out.println("Điểm khởi hành không tồn tại hoặc trùng với địa điểm kết thúc. Xin nhập lại");
                isInvalidStation = true;
                continue;
            }
            isInvalidStation = checkConflictDepartDate(route.getDepartTime(),
                    route.getTrainId(), route.getRouteId(),eStation,route.getDestination());
        }while (isInvalidStation);

        int index = getRouteIndexByID(route.getRouteId());
        route.setFrom(eStation);
        routeList.set(index, route);
    }
    public EStation getInputStation(){
        int stationId;
        EStation fromStation;
        boolean isInvalidStation;

        do {
            isInvalidStation = false;
            System.out.println("Chọn trạm thích hợp theo ID");
            stationId = Integer.parseInt(scanner.nextLine());
            fromStation = EStation.getEStationById(stationId);

            if (fromStation == null){
                System.out.println("Trạm không tồn tại. Xin nhập lại");
                isInvalidStation = true;
            }

        }while (isInvalidStation);
        return fromStation;
    }

    private static boolean isStationsConflict(EStation from, EStation destination) {
        if (from.name().equals(destination.name())){
            System.out.println("Điểm khởi hành trùng với địa điểm kết thúc. Xin nhập lại");
            return true;
        }
        return false;
    }

    public void editDestinationStation(Route route){
        int stationId;
        EStation eStation;
        boolean isInvalidStation;

        System.out.println("Sửa điểm kết thúc");
        EStation.showStationList();
        do {
            isInvalidStation = false;
            System.out.println("Chọn điểm kết thúc thích hợp theo ID");
            stationId = Integer.parseInt(scanner.nextLine());
            eStation = EStation.getEStationById(stationId);
            if (eStation == null || eStation.name().equals(route.getFrom().name())){
                System.out.println("Điểm khởi hành không tồn tại hoặc trùng với địa điểm xuất phát. Xin nhập lại");
                isInvalidStation = true;
                continue;
            }
            isInvalidStation = checkConflictDepartDate(route.getDepartTime(),
                    route.getTrainId(), route.getRouteId(),route.getFrom(),eStation);
        }while (isInvalidStation);

        int index = getRouteIndexByID(route.getRouteId());
        route.setDestination(eStation);
        routeList.set(index, route);
    }

    public List<Route> getRoutesByDepartDate(String dateString) {
        List<Route> routeListByDepartDate = new ArrayList<>();
        for (Route route : routeList) {
            if (DateUtils.format(route.getDepartTime()).contains(dateString)) {
                routeListByDepartDate.add(route);
            }
        }
        return routeListByDepartDate;
    }
    public List<Route> getRoutesByFromStation(String station){
        List<Route> routeListByFromStation = new ArrayList<>();
        for (Route route: routeList){
            if (route.getFrom().name().equalsIgnoreCase(station)){
                routeListByFromStation.add(route);
            }
        }
        return routeListByFromStation;
    }
    public List<Route> getRoutesByDesStation(String station){
        List<Route> routeListByDesStation = new ArrayList<>();
        for (Route route: routeList){
            if (route.getDestination().name().equalsIgnoreCase(station)){
                routeListByDesStation.add(route);
            }
        }
        return routeListByDesStation;
    }

    public List<Route> getRoutesByTrainId(int trainId, List<Route> routeList) {
        List<Route> routeListById = new ArrayList<>();
        for (Route route : routeList) {
            if (route.getTrainId() == trainId) {
                routeListById.add(route);
            }
        }
        return routeListById;
    }

    public Date getRouteArriveTime(Route route) {
        double runTime = route.getRunTime();
        Date departDateTime = route.getDepartTime();

        return DateUtils.plusHour(departDateTime, runTime);
    }

    public void editDepartTimeRoute(Route route) {
        Date newDepartDate;
        boolean isConflictDate;

        do {
            isConflictDate = false;
            System.out.println("Sửa thời gian khởi hành");
            newDepartDate = getInputDate();

            isConflictDate = checkConflictDepartDate(newDepartDate, route.getTrainId(), route.getRouteId(),
                    route.getFrom(),route.getDestination());

        } while (isConflictDate);

        int index = getRouteIndexByID(route.getRouteId());
        route.setDepartTime(newDepartDate);
        routeList.set(index, route);
    }

    public Date getInputDate() {
        String newDateStr;
        boolean checkInvalidate = false;

        do {
            checkInvalidate = false;
            System.out.println("Nhập vào thời gian khởi hành (dd/MM/yyyy HH:mm):");
            newDateStr = scanner.nextLine();
            if (!ValidateUtils.dateWithHourValidate(newDateStr)) {
                System.out.println("Thời gian nhập vào không đúng định dạng. Hãy nhập lại");
                checkInvalidate = true;
            }
        } while (checkInvalidate);
        return DateUtils.parse(newDateStr);
    }

    public boolean checkConflictDepartDate(Date departDate, int trainID, int routeId,EStation from, EStation destination) {
        double runTime = getRuntime(from,destination);
        Date newArriveDate = DateUtils.plusHour(departDate, runTime);
        List<Route> routeListByTrainId = getRoutesByTrainId(trainID, routeList);
        List<Route> routeListByTrainIdExceptThis = getRouteListExceptThis(routeId, routeListByTrainId);

        Route previousRoute = getPreviousRoute(departDate, routeListByTrainIdExceptThis);
        Route nextRoute = getNextRoute(departDate, routeListByTrainIdExceptThis);

        if (previousRoute == null) {
            if (nextRoute == null) {
                return false;
            } else {
                Date nextRouteDepartDate = nextRoute.getDepartTime();
                double runTimeDestToNextRoute = getRuntime(destination, nextRoute.getFrom());
                if (DateUtils.plusHour(newArriveDate, runTimeDestToNextRoute).getTime() > nextRouteDepartDate.getTime()) {
                    System.out.println("Thời gian khởi hành bị mâu thuẫn với thời gian chạy các chuyến khác của tàu. Xin hãy nhập lại");
                    return true;
                }
            }
        } else {
            if (nextRoute == null) {
                Date previousRouteAriveDate = getRouteArriveTime(previousRoute);
                double runTimePreviousToThis = getRuntime(previousRoute.getDestination(), from);
                if (DateUtils.plusHour(previousRouteAriveDate, runTimePreviousToThis).getTime() > departDate.getTime()) {
                    System.out.println("Thời gian khởi hành bị mâu thuẫn với thời gian chạy các chuyến khác của tàu. Xin hãy nhập lại");
                    return true;
                }
            } else {
                Date previousRouteAriveDate = getRouteArriveTime(previousRoute);
                double runTimePreviousToThis = getRuntime(previousRoute.getDestination(), from);
                Date nextRouteDepartDate = nextRoute.getDepartTime();
                double runTimeDestToNextRoute = getRuntime(destination, nextRoute.getFrom());
                if (DateUtils.plusHour(previousRouteAriveDate, runTimePreviousToThis).getTime() > departDate.getTime() ||
                        DateUtils.plusHour(newArriveDate, runTimeDestToNextRoute).getTime() > nextRouteDepartDate.getTime()) {
                    System.out.println("Thời gian khởi hành bị mâu thuẫn với thời gian chạy các chuyến khác của tàu. Xin hãy nhập lại");
                    return true;
                }
            }
        }
        return false;
    }

    public Route getPreviousRoute(Date departDate, List<Route> routeListByTrainId) {
        routeListByTrainId = sortRoutesByDepartDate(routeListByTrainId);
        Route previousRoute = null;
        for (Route route: routeListByTrainId){
            if (route.getDepartTime().getTime() < departDate.getTime()){
                previousRoute = route;
                break;
            }
        }
        // có thể trả về Null
        for (int i = 1; i < routeListByTrainId.size(); i++) {
            if (routeListByTrainId.get(i-1).getDepartTime().getTime() < departDate.getTime() &&
                    routeListByTrainId.get(i).getDepartTime().getTime() > departDate.getTime()) {
                previousRoute = routeListByTrainId.get(i-1);
            }
        }
        return previousRoute;
    }

    public Route getNextRoute(Date departDate, List<Route> routeListByTrainId) {
        routeListByTrainId = sortRoutesByDepartDate(routeListByTrainId);
        //có thể trả về Null
        for (Route route : routeListByTrainId) {
            if (route.getDepartTime().getTime() > departDate.getTime()) {
                return route;
            }
        }
        return null;
    }

    public List<Route> sortRoutesByDepartDate(List<Route> routeList) {
        RouteComparator routeComparatorByDepartDate = new RouteComparator("");
        routeList.sort(routeComparatorByDepartDate);
        return routeList;
    }

    public double getRuntime(EStation depart, EStation arrive) {
        if ((depart.name().equals("HN") && arrive.name().equals("SG")) ||
                (arrive.name().equals("HN") && depart.name().equals("SG"))) {
            return 32;
        } else if ((depart.name().equals("HUE") && arrive.name().equals("HN")) ||
                depart.name().equals("HN") && arrive.name().equals("HUE")) {
            return 13;
        } else if ((depart.name().equals("HUE") && arrive.name().equals("SG")) ||
                depart.name().equals("SG") && arrive.name().equals("HUE")) {
            return 20;
        } else return 0;
    }
    public List<Route> getRouteListExceptThis(int routeId, List<Route> routeList){
        List<Route> list = new ArrayList<>();
        for (Route route: routeList){
            if (route.getRouteId() == routeId){
                continue;
            }
            list.add(route);
        }
        return list;
    }
    public void removeRouteById(Route route){
        int index = getRouteIndexByID(route.getRouteId());
        routeList.remove(index);
    }
    public int getNewId(){
        int maxId = 0;
        for (Route route : routeList){
            if (route.getRouteId() > maxId){
                maxId = route.getRouteId();
            }
        }
        return maxId+1;
    }
    public Route createRoute(){
        int routeId = getNewId();
        EStation fromStation;
        EStation destStation;
        Date departDate;

        System.out.println("Chọn ID tàu");
        manageTrainView.showListTrain(manageTrainView.getTrainList());
        int trainId = getInputTrainId();

        do {
            System.out.println("Chọn điểm xuất phát và kết thúc");
            EStation.showStationList();
            System.out.println("Chọn điểm xuất phát bằng id trạm");
            fromStation = getInputStation();
            System.out.println("Chọn điểm kết thúc bằng id trạm");
            destStation = getInputStation();
        }while (isStationsConflict(fromStation,destStation));

        ERouteType eRouteType = ERouteType.NORMAL;

        do {
            departDate = getInputDate();
        }while (checkConflictDepartDate(departDate,trainId,routeId,fromStation,destStation));

        double price = getInputPrice();

        return new Route(routeId,trainId,departDate,getRuntime(fromStation,destStation),eRouteType,fromStation,destStation,price);
    }

}
