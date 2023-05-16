package views;

import model.Route;
import model.Seat;
import model.Ticket;
import model.Train;
import service.RouteService;
import service.SeatService;
import service.TicketService;
import service.TrainService;
import utils.ActionUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class ManageSeatView {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Seat> seatList;
    private static SeatService seatService;
    private static TicketService ticketService;
    private static ManageTicketView manageTicketView;
    private static ManageSeatView manageSeatView;
    private static TrainService trainService;
    private static ManageTrainView manageTrainView;
    private final String seatFilePath = "./Data/seat.csv";
    private static ManageRouteView manageRouteView;
    private static RouteService routeService;
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

        boolean continueCheck = true;

        do {
            int manageSeatAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║       THICK MENU - Quản lý danh sách ghế ngồi       ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách ghế ngồi theo chuyến đi    ║");
                System.out.println("║ ▶ 02. Cập nhật ghế ngồi                             ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageSeatAction = ActionUtils.intHandleInput("option");
            }while (manageSeatAction < 0 || manageSeatAction > 2);

            manageRouteView = new ManageRouteView();
            seatList = FileUtils.readDataFromFile(Seat.class,seatFilePath);
            seatService = new SeatService(seatList);
            routeService = new RouteService(manageRouteView.getRouteList());

            switch (manageSeatAction) {
                case 1 -> showSeatListByRoute();
                case 2 -> updateSeatList();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
    }

    private void showSeatListByRoute() {
        routeService = new RouteService(manageRouteView.getRouteList());
        seatService = new SeatService(getSeatList());
        manageRouteView.showRouteList(manageRouteView.getRouteList());

        int routeId;
        boolean check;

        do {
            check = false;
            routeId = ActionUtils.intHandleInput("id chuyến");
            if (!routeService.isRouteIdExist(routeId)){
                check = true;
                System.out.println("ID chuyến không tồn tại. Hãy nhập lại");
            }
        }while (check);

        List<Seat> list = seatService.getSeatListByRouteId(routeId);
        seatService.showSeatList(list);
    }

    protected void updateSeatList(){
        manageTrainView = new ManageTrainView();
        manageRouteView = new ManageRouteView();
        manageSeatView = new ManageSeatView();
        manageTicketView = new ManageTicketView();
        seatService = new SeatService(manageSeatView.getSeatList());
        List<Ticket> ticketList = manageTicketView.getTicketList();
        List<Ticket> updateTicketList = new ArrayList<>();
        List<Train> trainList = manageTrainView.getTrainList();
        trainService = new TrainService(trainList);
        ticketService = new TicketService(ticketList);
        int carSeat = 4;

        List<Seat> seatListUpdate = new ArrayList<>();
        List<Route> routeList = manageRouteView.getRouteList();
        for (Route route: routeList){
            int trainIndex = trainService.getTrainIndexByID(route.getTrainId());
            Train train = trainList.get(trainIndex);
            for (int i = 1; i<=train.getCarQuanity();i++){
                for (int j = 1; j <= carSeat; j++){
                    Seat seat = new Seat(String.format("R%sT%sC%sS%s",route.getRouteId(),train.getTrainId(),i,j)
                            , route.getRouteId(), train.getTrainId(),i,true);
                    seatListUpdate.add(seat);
                }
            }
        }

        for (Ticket ticket: ticketList){
            for (Seat seat: seatListUpdate){
                if (seat.getSeatId().equals(ticket.getSeatId())){
                    seat.setEmpty(false);
                    updateTicketList.add(ticket);
                    break;
                }
            }
        }

        ticketService.showTicketList(updateTicketList);
        seatService.showSeatList(seatListUpdate);
        FileUtils.writeDataToFile(updateTicketList,manageTicketView.getTicketFilePath());
        FileUtils.writeDataToFile(seatListUpdate,seatFilePath);
    }
}
