package views;

import model.Ticket;
import service.TicketService;
import utils.ActionUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.List;
import java.util.Scanner;

public class ManageTicketView {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Ticket> ticketList;
    private static TicketService ticketService;
    private final String ticketFilePath = "./Data/ticket.csv";
    public ManageTicketView(){
        ticketList = FileUtils.readDataFromFile(Ticket.class,ticketFilePath);
    }
    public List<Ticket> getTicketList(){
        ticketList = FileUtils.readDataFromFile(Ticket.class,ticketFilePath);
        return ticketList;
    }
    public String getTicketFilePath(){
        return ticketFilePath;
    }
    public void launcher(){
        ticketList = FileUtils.readDataFromFile(Ticket.class,ticketFilePath);
        ticketService = new TicketService(ticketList);

        boolean continueCheck = true;

        do {
            int manageTicketAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║       THICK MENU - Quản lý danh sách vé             ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách vé đã đặt của tất cả KH    ║");
                System.out.println("║ ▶ 02. Hủy đặt vé theo id vé                         ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageTicketAction = ActionUtils.intHandleInput("option");
            }while (manageTicketAction < 0 || manageTicketAction > 2);
            switch (manageTicketAction){
                case 1:
//                    manageRouteView.showRouteList(manageRouteView.getRouteList());
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
}
