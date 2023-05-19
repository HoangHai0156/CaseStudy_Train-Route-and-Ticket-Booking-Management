package views;

import comparator.RouteComparator;
import model.EStation;
import model.Route;
import service.RouteService;
import service.TrainService;
import utils.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManageRouteView {
    public static Scanner scanner = new Scanner(System.in);
    private static List<Route> routeList;
    private static RouteService routeService;
    private static TrainService trainService;
    private static ManageTrainView manageTrainView;
    private static ManageSeatView manageSeatView;

    private final String routeFilePath = "./Data/route.csv";
    private Date currentDateIncludeHour;
    public ManageRouteView(){
        routeList = FileUtils.readDataFromFile(Route.class,routeFilePath);
        this.currentDateIncludeHour = DateUtils.getCurrentDateIncludeHour();
    }
    public List<Route> getRouteList(){
        routeList = FileUtils.readDataFromFile(Route.class,routeFilePath);
        return routeList;
    }
    public String getRouteFilePath(){
        return routeFilePath;
    }
    public void launcher(){
        boolean continueCheck = true;

        do {
            int manageRouteAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý chuyến đi         ║");
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

            routeList = FileUtils.readDataFromFile(Route.class,routeFilePath);
            manageTrainView = new ManageTrainView();
            manageSeatView = new ManageSeatView();
            routeService = new RouteService(routeList);
            trainService = new TrainService(manageTrainView.getTrainList());

            switch (manageRouteAction) {
                case 1 -> showRouteList(routeList);
                case 2 -> editRouteView();
                case 3 -> removeRouteView();
                case 4 -> addRouteView();
                case 5 -> findRouteView();
                case 6 -> sortRouteView();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void sortRouteView() {
        System.out.println("Sắp xếp chuyến đi...");
        int flowAction;
        boolean isAscending;

        do {
            System.out.println("Sắp xếp theo chiều tăng dần hay giảm dần?");
            System.out.println("▶ 01. Tăng dần");
            System.out.println("▶ 02. Giảm dần");
            flowAction = ActionUtils.intHandleInput("option");
        }while (flowAction < 0 || flowAction > 2);

        isAscending = flowAction == 1;

        boolean continueCheck = true;

        do {
            int sortRouteAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Sắp xếp chuyến đi         ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Sắp xếp chuyến đi theo ID                     ║");
                System.out.println("║ ▶ 02. Sắp xếp chuyến đi theo ngày khởi hành         ║");
                System.out.println("║ ▶ 03. Sắp xếp chuyến đi theo ngày kết thúc          ║");
                System.out.println("║ ▶ 04. Sắp xếp chuyến đi theo điểm xuất phát         ║");
                System.out.println("║ ▶ 05. Sắp xếp chuyến đi theo điểm kết thúc          ║");
                System.out.println("║ ▶ 06. Sắp xếp chuyến đi theo thời gian chạy         ║");
                System.out.println("║ ▶ 07. Sắp xếp chuyến đi theo giá                    ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                sortRouteAction = ActionUtils.intHandleInput("option");
            }while (sortRouteAction < 0 || sortRouteAction > 7);
            switch (sortRouteAction) {
                case 1 -> sortRouteById(isAscending);
                case 2 -> sortRouteByDepartDate(isAscending);
                case 3 -> sortRouteByArriveDate(isAscending);
                case 4 -> sortRouteByFromStation(isAscending);
                case 5 -> sortRouteByDestStation(isAscending);
                case 6 -> sortRouteByRunTime(isAscending);
                case 7 -> sortRouteByPrice(isAscending);
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void sortRouteByPrice(boolean isAscending) {
        RouteComparator priceComparator = new RouteComparator("price",isAscending);
        routeList.sort(priceComparator);
        showRouteList(routeList);
    }

    private void sortRouteByRunTime(boolean isAscending) {
        RouteComparator runTimeComparator = new RouteComparator("runTime",isAscending);
        routeList.sort(runTimeComparator);
        showRouteList(routeList);
    }

    private void sortRouteByDestStation(boolean isAscending) {
        RouteComparator destStationComparator = new RouteComparator("arriveStation",isAscending);
        routeList.sort(destStationComparator);
        showRouteList(routeList);
    }

    private void sortRouteByFromStation(boolean isAscending) {
        RouteComparator fromStationComparator = new RouteComparator("fromStation",isAscending);
        routeList.sort(fromStationComparator);
        showRouteList(routeList);
    }

    private void sortRouteByArriveDate(boolean isAscending) {
        RouteComparator arriveDateComparator = new RouteComparator("arriveDate",isAscending);
        routeList.sort(arriveDateComparator);
        showRouteList(routeList);
    }

    private void sortRouteByDepartDate(boolean isAscending) {
        RouteComparator departDateComparator = new RouteComparator("",isAscending);
        routeList.sort(departDateComparator);
        showRouteList(routeList);
    }

    private void sortRouteById(boolean isAscending) {
        RouteComparator idComparator = new RouteComparator("routeId",isAscending);
        routeList.sort(idComparator);
        showRouteList(routeList);
    }

    private void addRouteView() {
        System.out.println("Thêm chuyến mới...");
        Route newRoute = routeService.createRoute();

        routeList.add(newRoute);
        FileUtils.writeDataToFile(routeList, routeFilePath);
        manageSeatView.updateSeatList();
    }

    private void removeRouteView() {
        Route route = searchRouteById();
        routeService.showSingleRoute(route);
        
        routeService.removeRouteById(route);
        FileUtils.writeDataToFile(routeList, routeFilePath);
        manageSeatView.updateSeatList();
    }

    private void editRouteView() {
        Route route = searchRouteById();
        routeService.showSingleRoute(route);

        System.out.println("Chọn thông tin muốn sửa");

        boolean continueCheck = true;

        do {
            int editRouteAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Sửa chuyến đi             ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Sửa ID tàu                                    ║");
                System.out.println("║ ▶ 02. Sửa thời gian khởi hành                       ║");
                System.out.println("║ ▶ 03. Sửa điểm xuất phát                            ║");
                System.out.println("║ ▶ 04. Sửa điểm kết thúc                             ║");
                System.out.println("║ ▶ 05. Sửa giá                                       ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                editRouteAction = ActionUtils.intHandleInput("option");
            }while (editRouteAction < 0 || editRouteAction > 6);
            switch (editRouteAction) {
                case 1 -> {
                    manageTrainView.showListTrain(manageTrainView.getTrainList());
                    List<Route> updatedRouteList = routeService.editRouteTrainId(route);
                    FileUtils.writeDataToFile(updatedRouteList, routeFilePath);
                }
                case 2 -> {
                    List<Route> updatedRouteList = routeService.editDepartTimeRoute(route);
                    FileUtils.writeDataToFile(updatedRouteList, routeFilePath);
                }
                case 3 -> {
                    List<Route> updatedRouteList = routeService.editFromStation(route);
                    FileUtils.writeDataToFile(updatedRouteList, routeFilePath);
                }
                case 4 -> {
                    List<Route> updatedRouteList = routeService.editDestinationStation(route);
                    FileUtils.writeDataToFile(updatedRouteList, routeFilePath);
                }
                case 5 -> {
                    List<Route> updatedRouteList = routeService.editRoutePrice(route);
                    FileUtils.writeDataToFile(updatedRouteList, routeFilePath);
                }
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
        manageSeatView.updateSeatList();
    }

    protected void findRouteView() {
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

            routeList = FileUtils.readDataFromFile(Route.class,routeFilePath);
            manageTrainView = new ManageTrainView();
            manageSeatView = new ManageSeatView();
            routeService = new RouteService(routeList);
            trainService = new TrainService(manageTrainView.getTrainList());

            switch (findRouteAction) {
                case 1 -> {
                    Route route = searchRouteById();
                    routeService.showSingleRoute(route);
                }
                case 2 -> searchRouteByDepartDate();
                case 3 -> searchRouteByFromStation();
                case 4 -> searchRouteByDesStation();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void searchRouteByDesStation() {
        System.out.println("Tìm kiếm chuyến đi theo điểm kết thúc");
        EStation.showStationList();
        System.out.println("Nhập vào ký hiệu của trạm (HUE-SG-HN) để tìm kiếm: ");
        String keyFrom = scanner.nextLine();

        List<Route> serachedRouteList = routeService.getRoutesByDesStation(keyFrom);
        if (serachedRouteList.isEmpty()){
            System.out.println("Không tìm thấy điểm kết thúc thích hợp");
        }else showRouteList(serachedRouteList);
    }

    private void searchRouteByFromStation() {
        System.out.println("Tìm kiếm chuyến đi theo điểm xuất phát");
        EStation.showStationList();
        System.out.println("Nhập vào ký hiệu của trạm (HUE-SG-HN) để tìm kiếm: ");
        String keyFrom = scanner.nextLine();

        List<Route> serachedRouteList = routeService.getRoutesByFromStation(keyFrom);
        if (serachedRouteList.isEmpty()){
            System.out.println("Không tìm thấy điểm khởi hành thích hợp");
        }else showRouteList(serachedRouteList);
    }

    private void searchRouteByDepartDate() {
        System.out.println("Tìm kiếm chuyến đi theo ngày...");
        String keyDate;
        boolean isInvalidKeyDate;

        do {
            isInvalidKeyDate = false;
            System.out.println("Nhập vào ngày khởi hành theo định dạng dd/MM/yyyy");
            keyDate = scanner.nextLine();
            if (!ValidateUtils.dateValidate(keyDate)){
                System.out.println("Thời gian nhập vào chưa đúng định dạng. Xin nhập lại");
                isInvalidKeyDate = true;
            }
        }while (isInvalidKeyDate);

        List<Route> serachedRouteList = routeService.getRoutesByDepartDate(keyDate);
        if (serachedRouteList.isEmpty()){
            System.out.println("Không tìm thấy chuyến cho ngày "+keyDate);
        }else showRouteList(serachedRouteList);
    }

    private Route searchRouteById() {
        int id;
        boolean check;

        do {
            check = false;
            id = ActionUtils.intHandleInput("id chuyến");
            if (!routeService.isRouteIdExist(id)){
                check = true;
                System.out.println("ID chuyến không tồn tại. Hãy nhập lại");
            }
        }while (check);
        return routeService.getRouteById(id);
    }

    protected void showRouteList(List<Route> routeList) {
        manageTrainView = new ManageTrainView();
        trainService = new TrainService(manageTrainView.getTrainList());

        System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                "ID Chuyến","Tàu hiệu","Thời gian khởi hành","Thời gian chạy (H)","Thời gian đến",
                "Điểm xuất phát","Điểm kết thúc","Giá chuyến");
        for (Route route: routeList){
//            id chuyến đi	id tàu	khởi hành time	thời gian chạy(H)  den noi time	loại chuyến đi	Đ xuất phát	Đ kết thúc	Giá
            Date arriveDate = route.getRouteArriveDate();
            System.out.printf("%-10s %-10s %-20s %-20s %-20s %-20s %-20s %-15s\n",
                    route.getRouteId(),trainService.getTrainByTrainId(route.getTrainId()).getTrainNumber()
                    , DateUtils.format(route.getDepartTime()),
                    route.getRunTime(),DateUtils.format(arriveDate),
                    route.getFrom().getName(),route.getDestination().getName(),
                    CurrencyUtils.getViCurrency(route.getPrice()));
        }
    }
}
