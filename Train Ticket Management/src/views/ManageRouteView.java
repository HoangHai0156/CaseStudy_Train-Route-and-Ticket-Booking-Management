package views;

import model.Route;
import service.RouteService;
import utils.ActionUtils;
import utils.DateUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManageRouteView {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Route> routeList = new ArrayList<>();
    private static RouteService routeService;
    private final String routeFilePath = "./Data/route.csv";
    public ManageRouteView(){};
    public void launcher(){
        routeList = FileUtils.readDataFromFile(Route.class,routeFilePath);
        routeService = new RouteService(routeList);

        boolean continueCheck = true;

        do {
            int manageRouteAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý                   ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách chuyến đi                  ║");
                System.out.println("║ ▶ 02. Sửa thông tin chuyến đi theo id               ║");
                System.out.println("║ ▶ 03. Xoá chuyến đi theo id                         ║");
                System.out.println("║ ▶ 04. Thêm chuyến đi                                ║");
                System.out.println("║ ▶ 05. Tìm kiếm chuyến đi                            ║");
                System.out.println("║ ▶ 06. Sắp xếp chuyến đi                             ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageRouteAction = ActionUtils.intHandleInput("option");
            }while (manageRouteAction < 0 || manageRouteAction > 6);
            switch (manageRouteAction){
                case 1:
                    showRouteList(routeList);
                    break;
                case 2:
//                    editRouteView();
                    break;
                case 5:
                    findRouteView();
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    private void findRouteView() {
        boolean continueCheck = true;

        do {
            int findRouteAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Tìm kiếm                  ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Tìm kiếm chuyến đi theo ID                    ║");
                System.out.println("║ ▶ 02. Tìm kiếm chuyến đi theo ngày khởi hành        ║");
                System.out.println("║ ▶ 03. Tìm kiếm chuyến đi theo điểm xuất phát        ║");
                System.out.println("║ ▶ 04. Tìm kiếm chuyến đi theo điểm kết thúc         ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                findRouteAction = ActionUtils.intHandleInput("option");
            }while (findRouteAction < 0 || findRouteAction > 4);
            switch (findRouteAction){
                case 1:
                    searchRouteById();
                    break;
                case 2:
                    searchRouteByDepartDate();
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    private void searchRouteById() {
        int id;
        boolean check;

        do {
            check = false;
            id = ActionUtils.intHandleInput("id");
            if (!routeService.isRouteIdExist(id)){
                check = true;
                System.out.println("ID không tồn tại. Hãy nhập lại");
            }
        }while (check);
        Route idFoundRoute = routeService.findRouteById(id);
        routeService.showSingleRoute(idFoundRoute);
    }

    private void showRouteList(List<Route> routeList) {
        System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                "ID Chuyến","ID tàu","Thời gian khởi hành","Thời gian chạy (H)","Thời gian đến",
                "Điểm xuất phát","Điểm kết thúc","Giá chuyến");
        for (Route route: routeList){
//            id chuyến đi	id tàu	khởi hành time	thời gian chạy(H)  den noi time	loại chuyến đi	Đ xuất phát	Đ kết thúc	Giá
            Date arriveDate = DateUtils.plusHour(route.getDepartTime(),route.getRunTime());
            System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                    route.getRouteId(),route.getTrainId(), DateUtils.format(route.getDepartTime()),
                    route.getRunTime(),DateUtils.format(arriveDate),
                    route.getFrom().getName(),route.getDestination().getName(),route.getPrice());
        }
    }
}
