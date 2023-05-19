package views;

import model.Customer;
import service.CustomerService;
import service.RouteService;
import service.SeatService;
import service.TicketService;
import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class CustomerView {
    public Scanner scanner = new Scanner(System.in);
    private Customer customer;
    private ManageRouteView manageRouteView;
    private RouteService routeService;
    private ManageSeatView manageSeatView;
    private SeatService seatService;
    private ManageTicketView manageTicketView;
    private TicketService ticketService;
    private ManageCustomerView manageCustomerView;
    private CustomerService customerService;
    public CustomerView(Customer customer){
        this.customer = customer;
    }
    public void launcher(){

        boolean continueCheck = true;

        do {
            int userAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║               THICK MENU - Khách hàng               ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách chuyến đi                  ║");
                System.out.println("║ ▶ 02. Tìm kiếm chuyến đi                            ║");
                System.out.println("║ ▶ 03. Hiển thị danh sách ghế ngồi theo id chuyến đi ║");
                System.out.println("║ ▶ 04. Đặt vé theo id ghế ngồi                       ║");
                System.out.println("║ ▶ 05. Hiển thị danh sách vé đã đặt                  ║");
                System.out.println("║ ▶ 06. Hủy đặt vé theo id vé                         ║");
                System.out.println("║ ▶ 07. Thanh toán những vé đã đặt                    ║");
                System.out.println("║ ▶ 08. Sửa thông tin cá nhân                         ║");
                System.out.println("║"+PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                userAction = ActionUtils.intHandleInput("option");
            }while (userAction < 0 || userAction > 8);

            manageRouteView = new ManageRouteView();
            manageSeatView = new ManageSeatView();
            manageTicketView = new ManageTicketView();
            manageCustomerView = new ManageCustomerView();

            routeService = new RouteService(manageRouteView.getRouteList());
            seatService = new SeatService(manageSeatView.getSeatList());
            ticketService = new TicketService(manageTicketView.getTicketList());
            customerService = new CustomerService(manageCustomerView.getCustomerList());

            switch (userAction){
                case 1:
                    manageRouteView.showRouteList(manageRouteView.getRouteList());
                    break;
                case 2:
                    manageRouteView.findRouteView();
                    break;
                case 3:
                    manageSeatView.showSeatListByRoute();
                    break;
                case 4:
                    manageTicketView.bookingTicketView(customer.getCustomerId());
                    manageSeatView.updateSeatList();
                    break;
                case 5:
                    ticketService.showTicketList(ticketService.getTicketListByCustomerId(customer.getCustomerId()));
                    break;
                case 6:
                    manageTicketView.cancelTicketView(ticketService.getTicketListByCustomerId(customer.getCustomerId()));
                    manageSeatView.updateSeatList();
                    break;
                case 7:
                    manageTicketView.setPaidForCustomerTickets(customer.getCustomerId());
                    break;
                case 8:
                    manageCustomerView.editCustomerById(customer.getCustomerId());
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
        System.out.println(PaintUtils.setRed("x͙i͙n͙ c͙h͙à͙o͙ v͙à͙ h͙ẹ͙n͙ g͙ặ͙p͙ l͙ạ͙i͙"));
    }
}
