package views;

import model.Seat;
import model.Ticket;
import service.SeatService;
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
    private static ManageSeatView manageSeatView;
    private static SeatService seatService;
    private final String ticketFilePath = "./Data/ticket.csv";
    private final int limitUnpaidTicket = 5;

    public ManageTicketView() {
        ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
    }

    public List<Ticket> getTicketList() {
        ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
        return ticketList;
    }

    public String getTicketFilePath() {
        return ticketFilePath;
    }

    public void launcher() {

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
                System.out.println("║ ▶ 03. Đặt vé theo id ghế                            ║");
                System.out.println("║ ▶ 04. Thanh toán vé đã đặt của KH                   ║");
                System.out.println("║" + PaintUtils.setRed(" ▶ 0. Quit") + "                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageTicketAction = ActionUtils.intHandleInput("option");
            } while (manageTicketAction < 0 || manageTicketAction > 4);

            ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
            ticketService = new TicketService(ticketList);
            manageSeatView = new ManageSeatView();

            switch (manageTicketAction) {
                case 1 -> ticketService.showTicketList(ticketList);
                case 2 -> {
                    cancelTicketView(ticketList);
                    manageSeatView.updateSeatList();
                }
                case 3 -> {
                    bookingTicketView();
                    manageSeatView.updateSeatList();
                }
                case 4 -> {
                    int customerId = ticketService.getInputCustomerId();
                    setPaidForCustomerTickets(customerId);
                }
                case 0 -> continueCheck = false;
            }
        } while (continueCheck);
    }

    protected void setPaidForCustomerTickets(int customerId) {
        ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
        ticketService = new TicketService(ticketList);
        manageSeatView = new ManageSeatView();

        boolean confirm = ActionUtils.getConfirm("thanh toán tất cả những vé đã đặt");

        if (confirm) {
            for (Ticket ticket : ticketList) {
                if (!ticket.isPaid()) {
                    if (ticket.getCustomerId() == customerId) {
                        ticket.setPaid(true);
                    }
                }
            }

            FileUtils.writeDataToFile(ticketList, ticketFilePath);
        }
    }

    private void bookingTicketView() {
        List<Seat> seatList = manageSeatView.getSeatList();
        seatService = new SeatService(seatList);

        seatService.showSeatList(seatList);
        System.out.println(PaintUtils.setBlue("Đặt vé..."));
        int customerId = ticketService.getInputCustomerId();

        List<Ticket> customerTicketList = ticketService.getTicketListByCustomerId(customerId);
        List<Ticket> unpaidCustomerTicketList = ticketService.getTicketListByStatus(false,customerTicketList);

        if (unpaidCustomerTicketList.size() >= limitUnpaidTicket){
            System.out.println(PaintUtils.setRed("Khách hàng có từ hơn "+limitUnpaidTicket+" vé đã đặt chưa thanh toán. Hãy thanh toán trước khi đặt thêm"));
        }else {
            Ticket newTicket = ticketService.createTicket(customerId);

            ticketList.add(newTicket);
            FileUtils.writeDataToFile(ticketList, ticketFilePath);
        }
    }

    protected void bookingTicketView(int customerId) {
        ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
        ticketService = new TicketService(ticketList);
        manageSeatView = new ManageSeatView();

        List<Seat> seatList = manageSeatView.getSeatList();
        seatService = new SeatService(seatList);

        seatService.showSeatList(seatList);
        System.out.println(PaintUtils.setBlue("Đặt vé..."));

//        Ticket newTicket = ticketService.createTicket(customerId);
//
//        ticketList.add(newTicket);
//        FileUtils.writeDataToFile(ticketList, ticketFilePath);
        List<Ticket> customerTicketList = ticketService.getTicketListByCustomerId(customerId);
        List<Ticket> unpaidCustomerTicketList = ticketService.getTicketListByStatus(false,customerTicketList);

        if (unpaidCustomerTicketList.size() >= limitUnpaidTicket){
            System.out.println(PaintUtils.setRed("Khách hàng có từ hơn "+limitUnpaidTicket+" vé đã đặt chưa thanh toán. Hãy thanh toán trước khi đặt thêm"));
        }else {
            Ticket newTicket = ticketService.createTicket(customerId);

            ticketList.add(newTicket);
            FileUtils.writeDataToFile(ticketList, ticketFilePath);
        }
    }

    protected void cancelTicketView(List<Ticket> list) {
        ticketList = FileUtils.readDataFromFile(Ticket.class, ticketFilePath);
        ticketService = new TicketService(ticketList);
        manageSeatView = new ManageSeatView();

        System.out.println("Nhập vào ID vé muốn hủy đặt");
        int cancelTicketID = ticketService.getInputTicketID(list);

        Ticket ticketCancel = ticketService.getTicketByTicketId(cancelTicketID);

        if (ticketCancel.isPaid()) {
            System.out.println(PaintUtils.setRed("Vé đã được thanh toán. Không thể hủy đặt"));
        } else {
            int ticketIndex = ticketService.getTicketIndexByTicketId(cancelTicketID, ticketList);
            ticketList.remove(ticketIndex);
            FileUtils.writeDataToFile(ticketList, ticketFilePath);
        }
    }
}
