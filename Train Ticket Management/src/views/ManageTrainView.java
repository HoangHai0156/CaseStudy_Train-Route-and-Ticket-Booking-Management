package views;

import model.ETrainProvider;
import model.Route;
import model.Train;
import service.RouteService;
import service.TrainService;
import utils.ActionUtils;
import utils.DateUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageTrainView {
    public static Scanner scanner = new Scanner(System.in);
    private static String trainFilePath = "./Data/train.csv";
    private static TrainService trainService;
    private static RouteService routeService;
    private static ManageRouteView manageRouteView;
    private static List<Train> trainList;

    public List<Train> getTrainList() {
        trainList = FileUtils.readDataFromFile(Train.class, trainFilePath);
        return trainList;
    }

    public ManageTrainView() {
        trainList = FileUtils.readDataFromFile(Train.class, trainFilePath);
    }

    public void launcher() {
        trainList = FileUtils.readDataFromFile(Train.class, trainFilePath);
        manageRouteView = new ManageRouteView();
        routeService = new RouteService(manageRouteView.getRouteList());
        trainService = new TrainService(trainList);

        boolean continueCheck = true;

        do {
            int manageTrainAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý tàu               ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách tàu                        ║");
                System.out.println("║ ▶ 02. Thêm tàu                                      ║");
                System.out.println("║ ▶ 03. Sửa thông tin tàu                             ║");
                System.out.println("║ ▶ 04. Xóa tàu                                       ║");
                System.out.println("║ ▶ 05. Tìm kiếm tàu                                  ║");
                System.out.println("║ ▶ 06. Sắp xếp tàu                                   ║");
                System.out.println("║" + PaintUtils.setRed(" ▶ 0. Quit") + "                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageTrainAction = ActionUtils.intHandleInput("option");
            } while (manageTrainAction < 0 || manageTrainAction > 6);
            switch (manageTrainAction) {
                case 1 -> showListTrain(trainList);
                case 2 -> addTrainView();
                case 3 -> editTrainView();
                case 4 -> removeTrainView();
                case 5 -> findTrainView();
                case 6 -> sortTrainView();
                case 0 -> continueCheck = false;
            }
        } while (continueCheck);
    }

    private void sortTrainView() {
        System.out.println("Sắp xếp tàu...");
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
                System.out.println("║              THICK MENU - Sắp xếp tàu               ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Sắp xếp tàu theo ID                           ║");
                System.out.println("║ ▶ 02. Sắp xếp tàu theo số hiệu                      ║");
                System.out.println("║ ▶ 03. Sắp xếp tàu theo nhà cung cấp                 ║");
                System.out.println("║ ▶ 04. Sắp xếp tàu theo số toa                       ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                sortRouteAction = ActionUtils.intHandleInput("option");
            }while (sortRouteAction < 0 || sortRouteAction > 4);
            switch (sortRouteAction) {
                case 1 -> showListTrain(trainService.sortTrainById(isAscending));
                case 2 -> showListTrain(trainService.sortTrainByTrainNumber(isAscending));
                case 3 -> showListTrain(trainService.sortTrainByProvider(isAscending));
                case 4 -> showListTrain(trainService.sortTrainByCarQuanity(isAscending));
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void findTrainView() {
        boolean continueCheck = true;

        do {
            int findTrainAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Tìm kiếm                  ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Tìm kiếm tàu theo ID                          ║");
                System.out.println("║ ▶ 02. Tìm kiếm tàu theo số hiệu                     ║");
                System.out.println("║ ▶ 03. Tìm kiếm tàu theo nhà cung cấp                ║");
                System.out.println("║ ▶ 04. Tìm kiếm tàu theo số toa                      ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                findTrainAction = ActionUtils.intHandleInput("option");
            }while (findTrainAction < 0 || findTrainAction > 4);
            switch (findTrainAction) {
                case 1 -> {
                    Train train = trainService.getTrainByTrainId();
                    showSingleTrain(train);
                }
                case 2 -> searchTrainByTrainNumber();
                case 3 -> searchTrainByTrainProvider();
                case 4 -> searchTrainByCarQuanity();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void searchTrainByCarQuanity() {
        int searchCarQuanity = trainService.getInputCarQuanity();
        List<Train> searchedTrains = trainService.getTrainsByCarQuanity(searchCarQuanity);
        if (searchedTrains.isEmpty()){
            System.out.println("Không tìm thấy tàu nào có "+searchCarQuanity+" toa");
        }else showListTrain(searchedTrains);
    }

    private void searchTrainByTrainProvider() {
        ETrainProvider searchTrainProvider = trainService.getInputTrainProvider();
        List<Train> searchedTrains = trainService.getTrainsByTrainProvier(searchTrainProvider.name());
        if (searchedTrains.isEmpty()){
            System.out.println("Không tìm thấy tàu nào từ nhà cung cấp "+searchTrainProvider.getFullName());
        }else showListTrain(searchedTrains);
    }

    private void searchTrainByTrainNumber() {
        String searchTrainNumber = trainService.getInputTrainNumber();
        List<Train> searchedTrains = trainService.getTrainsByTrainNumber(searchTrainNumber);
        if (searchedTrains.isEmpty()){
            System.out.println("Không tìm thấy tàu nào với số hiệu "+searchTrainNumber);
        }else showListTrain(searchedTrains);
    }

    private void showSingleTrain(Train train) {
        System.out.printf("%-15s %-15s %-35s %-10s\n", "ID Tàu", "Số hiệu", "Nhà cung cấp", "Số toa");
            System.out.printf("%-15s %-15s %-35s %-10s\n", train.getTrainId(),
                    train.getTrainNumber(), train.getProvider().getFullName(), train.getCarQuanity());
    }

    private void removeTrainView() {
        System.out.println(PaintUtils.setBlue("Xóa tàu khỏi danh sách tàu..."));
        Train trainRemove = trainService.getTrainByTrainId();
        List<Route> routeList = manageRouteView.getRouteList();

        // xóa những chuyến tàu có sử dụng con tàu đang bị xóa
        List<Route> routeListByTrainId = routeService.getRoutesByTrainId(trainRemove.getTrainId(), routeList);
        if (!routeListByTrainId.isEmpty()) {
            for (Route route: routeListByTrainId){
                routeList.removeIf(route1 -> route1.equals(route));
            }
            FileUtils.writeDataToFile(routeList, manageRouteView.getRouteFilePath());
        }
        // TODO: 15/5/2023 Cập nhật lại danh sách ghế ngồi

        int trainRemoveIndex = trainService.getTrainIndexByID(trainRemove.getTrainId());
        trainList.remove(trainRemoveIndex);
        FileUtils.writeDataToFile(trainList, trainFilePath);
    }

    private void addTrainView() {
        System.out.println(PaintUtils.setBlue("Thêm tàu mới..."));
        Train newTrain = trainService.createTrain();
        trainList.add(newTrain);
        FileUtils.writeDataToFile(trainList, trainFilePath);
    }

    private void editTrainView() {
        System.out.println(PaintUtils.setBlue("Chỉnh sửa thông tin tàu"));
        Train trainEdit = trainService.getTrainByTrainId();
        boolean continueCheck = true;

        do {
            int editTrainAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý tàu               ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Sửa số hiệu của tàu                           ║");
                System.out.println("║ ▶ 02. Sửa hãng tàu                                  ║");
                System.out.println("║ ▶ 03. Sửa số toa của tàu                            ║");
                System.out.println("║" + PaintUtils.setRed(" ▶ 0. Quit") + "                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                editTrainAction = ActionUtils.intHandleInput("option");
            } while (editTrainAction < 0 || editTrainAction > 4);
            switch (editTrainAction) {
                case 1 -> {
                    System.out.println(PaintUtils.setBlue("Chỉnh sửa số hiệu của tàu..."));
                    trainService.editTrainNumber(trainEdit);
                    FileUtils.writeDataToFile(trainList, trainFilePath);
                }
                case 2 -> {
                    System.out.println(PaintUtils.setBlue("Chỉnh sửa hãng tàu..."));
                    trainService.editTrainProvider(trainEdit);
                    FileUtils.writeDataToFile(trainList, trainFilePath);
                }
                case 3 -> {
                    System.out.println(PaintUtils.setBlue("Chỉnh sửa số toa của tàu..."));
                    trainService.editCarQuanity(trainEdit);
                    FileUtils.writeDataToFile(trainList, trainFilePath);
                    // TODO: 15/5/2023 Cập nhật lại danh sách ghế ngồi
                }
                case 0 -> continueCheck = false;
            }
        } while (continueCheck);
    }

    public void showListTrain(List<Train> trainList) {
//        ID,	Tên tàu,	Hãng tàu,	số toa
        System.out.printf("%-15s %-15s %-35s %-10s\n", "ID Tàu", "Số hiệu", "Nhà cung cấp", "Số toa");
        for (Train train : trainList) {
            System.out.printf("%-15s %-15s %-35s %-10s\n", train.getTrainId(),
                    train.getTrainNumber(), train.getProvider().getFullName(), train.getCarQuanity());
        }
    }
}
