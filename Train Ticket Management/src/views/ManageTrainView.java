package views;

import model.Train;
import service.RouteService;
import service.TrainService;
import utils.ActionUtils;
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
    public List<Train> getTrainList(){
        return FileUtils.readDataFromFile(Train.class,trainFilePath);
    }
    public ManageTrainView(){
        trainList = FileUtils.readDataFromFile(Train.class,trainFilePath);
    }
    public void launcher(){
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
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageTrainAction = ActionUtils.intHandleInput("option");
            }while (manageTrainAction < 0 || manageTrainAction > 4);
            switch (manageTrainAction){
                case 1:
                    showListTrain(trainList);
                    break;
                case 2:
                    addTrainView();
                    break;
                case 3:
                    editTrainView();
                    break;
                case 4:
                    removeTrainView();
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    private void removeTrainView() {
        System.out.println(PaintUtils.setBlue("Xóa tàu khỏi danh sách tàu..."));
        Train trainRemove = trainService.getTrainByTrainId();

        routeService.getRoutesByTrainId(trainRemove.getTrainId(), manageRouteView.getRouteList());

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
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                editTrainAction = ActionUtils.intHandleInput("option");
            }while (editTrainAction < 0 || editTrainAction > 4);
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
        }while (continueCheck);
    }

    public void showListTrain(List<Train> trainList) {
//        ID,	Tên tàu,	Hãng tàu,	số toa
        System.out.printf("%-15s %-15s %-20s %-10s\n","ID Tàu","Số hiệu","Hãng tàu","Số toa");
        for (Train train: trainList){
            System.out.printf("%-15s %-15s %-20s %-10s\n",train.getTrainId(),
                    train.getTrainNumber(),train.getProvider(),train.getCarQuanity());
        }
    }
}
