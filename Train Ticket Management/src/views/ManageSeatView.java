package views;

import model.Route;
import model.Seat;
import model.Train;
import service.SeatService;
import service.TrainService;
import utils.ActionUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageSeatView {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Seat> seatList;
    private static SeatService seatService;
    private static TrainService trainService;
    private static ManageTrainView manageTrainView;
    private final String seatFilePath = "./Data/seat.csv";
    private static ManageRouteView manageRouteView;
    public ManageSeatView(){
        seatList = FileUtils.readDataFromFile(Seat.class,seatFilePath);
    }
    public List<Seat> getSeatList(){
        seatList = FileUtils.readDataFromFile(Seat.class,seatFilePath);
        return seatList;
    }
    public String getSeatFilePath(){
        return seatFilePath;
    }
    public void launcher(){
        seatList = FileUtils.readDataFromFile(Seat.class,seatFilePath);
        seatService = new SeatService(seatList);
        manageRouteView = new ManageRouteView();

        boolean continueCheck = true;

        do {
            int manageSeatAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║       THICK MENU - Quản lý danh sách ghế ngồi       ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách ghế ngồi theo chuyến đi    ║");
                System.out.println("║ ▶ 02. Cập nhật trạng thái ghế ngồi                  ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageSeatAction = ActionUtils.intHandleInput("option");
            }while (manageSeatAction < 0 || manageSeatAction > 2);
            switch (manageSeatAction){
                case 1:
                    manageRouteView.showRouteList(manageRouteView.getRouteList());
                    break;
                case 2:
//                    manageTrainView.launcher();
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }
    protected void updateSeatList(){
        manageTrainView = new ManageTrainView();
        manageRouteView = new ManageRouteView();
        List<Train> trainList = manageTrainView.getTrainList();
        trainService = new TrainService(trainList);

        List<Seat> seatListUpdate = new ArrayList<>();
        List<Route> routeList = manageRouteView.getRouteList();
        for (Route route: routeList){
            int trainIndex = trainService.getTrainIndexByID(route.getTrainId());
            Train train = trainList.get(trainIndex);
//            for (int i = 0; i<train.getCarQuanity();i++){
//
//            }
        }
    }
}
